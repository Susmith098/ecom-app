package com.susmith.ecomapp.Auth;

import com.susmith.ecomapp.Auth.dto.UserDto;
import com.susmith.ecomapp.Auth.request.AuthenticationRequest;
import com.susmith.ecomapp.Auth.request.RegisterRequest;
import com.susmith.ecomapp.Auth.response.AuthenticationResponse;
import com.susmith.ecomapp.Auth.response.NewAuthResponse;
import com.susmith.ecomapp.Repository.UserRepository;
import com.susmith.ecomapp.Entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    private final UserRepository repository;

    public static final String TOKEN_PREFIX = "Bearer";

    public static final String HEADER_STRING = "Authorization";


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<NewAuthResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response
    ) throws IOException {

        AuthenticationResponse authResponse = service.authenticate(request);

        Optional<User> user = repository.findByEmail(request.getEmail());

        NewAuthResponse newAuth = null;
        if (user.isPresent()) {

            new UserDto();
            UserDto userDto = UserDto.builder()
                    .userId(user.get().getId())
                    .name(user.get().getUsername())
                    .role(String.valueOf(user.get().getRole()))
                    .build();


            new NewAuthResponse();
            newAuth = NewAuthResponse.builder()
                    .userDto(userDto)
                    .accessToken(authResponse.getAccessToken())
                    .refreshToken(authResponse.getRefreshToken())
                    .build();

        }

        return ResponseEntity.ok(newAuth);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


}
