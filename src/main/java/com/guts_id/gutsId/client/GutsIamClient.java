package com.guts_id.gutsId.client;


import com.guts_id.gutsId.dto.IamLoginRequest;
import com.guts_id.gutsId.dto.IamTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GutsIamClient {

    private final WebClient webClient;


    public GutsIamClient(
            WebClient.Builder builder,
            @Value("${guts.iam.base-url}") String iamBaseUrl) {

        this.webClient = builder
                .baseUrl(iamBaseUrl)
                .build();
    }

    public IamTokenResponse authenticate(IamLoginRequest request) {

        return webClient.post()
                .uri("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(IamTokenResponse.class)
                .block();
    }
}
