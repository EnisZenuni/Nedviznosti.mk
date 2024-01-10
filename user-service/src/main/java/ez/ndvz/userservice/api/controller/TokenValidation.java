package ez.ndvz.userservice.api.controller;

import ez.ndvz.userservice.api.dto.JwtValidationResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/v1/validateToken")
public class TokenValidation {

    @GetMapping(value = " ", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<JwtValidationResponse> validateGet() {
        return ResponseEntity.ok(JwtValidationResponse.builder().status("OK").methodType(HttpMethod.POST.name())
                .isAuthenticated(true).build());
    }


    @PostMapping(value = " ", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<JwtValidationResponse> validatePost(HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        String token = (String) request.getAttribute("token");
        Set<GrantedAuthority> grantedAuthorities = (Set<GrantedAuthority>) request.getAttribute("authorities");

        return ResponseEntity.ok(JwtValidationResponse.builder().status("OK").methodType(HttpMethod.GET.name())
                .email(email).token(token).authorities(grantedAuthorities)
                .isAuthenticated(true).build());
    }
}
