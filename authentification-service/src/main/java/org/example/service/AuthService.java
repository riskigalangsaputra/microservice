package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.AuthRequest;
import org.example.dto.AuthResponse;
import org.example.dto.UserSchool;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;
    private final JwtUtils jwtUtils;

    public AuthResponse register(AuthRequest request) {
        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        UserSchool registerUser = restTemplate.postForObject("http://user-service/users", request, UserSchool.class);
        assert registerUser != null;
        String accessToken = jwtUtils.generateToken(registerUser.getId(), registerUser.getRole(), "ACCESS");
        String refreshToken = jwtUtils.generateToken(registerUser.getId(), registerUser.getRole(), "REFRESH");
        return new AuthResponse(accessToken, refreshToken);
    }

}
