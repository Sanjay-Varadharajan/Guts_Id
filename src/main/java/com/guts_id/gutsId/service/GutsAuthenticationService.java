package com.guts_id.gutsId.service;

import com.guts_id.gutsId.client.GutsIamClient;
import com.guts_id.gutsId.dto.IamLoginRequest;
import com.guts_id.gutsId.dto.IamTokenResponse;
import com.guts_id.gutsId.model.GutsSession;
import com.guts_id.gutsId.security.JwtIdentityExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GutsAuthenticationService {


    private final GutsIamClient gutsIamClient;
    private final JwtIdentityExtractor jwtIdentityExtractor;
    private final GutsSessionService gutsSessionService;



    public GutsSession authenticate(IamLoginRequest request) {

        IamTokenResponse response = gutsIamClient.authenticate(request);

        // Verify IAM JWT and extract user email
        String iamUserMail = jwtIdentityExtractor.extractUserMail(
                response.getAccessToken()
        );

        // Create GutsID SSO session
        return gutsSessionService.createSession(iamUserMail);
    }


}
