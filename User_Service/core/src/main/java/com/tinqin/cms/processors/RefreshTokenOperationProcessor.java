package com.tinqin.cms.processors;

import com.tinqin.cms.auth.JwtService;
import com.tinqin.cms.entities.User;
import com.tinqin.cms.exceptions.UserNotFoundException;
import com.tinqin.cms.operations.RefreshTokenOperation;
import com.tinqin.cms.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenOperationProcessor implements RefreshTokenOperation {  // Functionality is still under question
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public RefreshTokenResponse process(final RefreshTokenRequest request) {
        String username = jwtService.extractUsername(request.getToken());
        log.info("Extracted username from token: {}", username);

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> {
                    log.warn("User with username {} not found", username);
                    return new UserNotFoundException();
                });

        RefreshTokenResponse response = new RefreshTokenResponse();

        if (jwtService.isValid(request.getToken(), user)) {
            log.info("Token is valid for user {}", username);
            String newToken = jwtService.generateToken(user);
            response.setNewToken(newToken);
            response.setRefreshedToken(request.getToken());
            log.info("Generated new token for user {}", username);
        } else {
            log.warn("Token is invalid for user {}", username);
        }

        return response;
    }
}
