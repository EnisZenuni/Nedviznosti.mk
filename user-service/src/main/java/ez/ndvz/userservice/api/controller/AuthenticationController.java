package ez.ndvz.userservice.api.controller;

import ez.ndvz.userservice.api.dto.SignInRequestDTO;
import ez.ndvz.userservice.api.dto.SignInResponseDTO;
import ez.ndvz.userservice.api.dto.SignUpRequestDTO;
import ez.ndvz.userservice.api.dto.SignUpResponseDTO;
import ez.ndvz.userservice.service.Interface.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signUp")
    ResponseEntity<SignUpResponseDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequestDTO));
    }

    @PostMapping("/signIn")
    ResponseEntity<SignInResponseDTO> signIn(@RequestBody SignInRequestDTO signInRequestDTO) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequestDTO));
    }
}
