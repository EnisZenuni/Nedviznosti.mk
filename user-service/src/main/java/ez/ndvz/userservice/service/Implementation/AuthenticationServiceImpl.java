package ez.ndvz.userservice.service.Implementation;

import ez.ndvz.userservice.api.dto.SignInRequestDTO;
import ez.ndvz.userservice.api.dto.SignInResponseDTO;
import ez.ndvz.userservice.api.dto.SignUpRequestDTO;
import ez.ndvz.userservice.api.dto.SignUpResponseDTO;
import ez.ndvz.userservice.domain.enumerations.Roles;
import ez.ndvz.userservice.repository.UserRepository;
import ez.ndvz.userservice.service.Interface.AuthenticationService;
import ez.ndvz.userservice.service.userDetailsServiceImpl.JwtService;
import ez.ndvz.userservice.service.userDetailsServiceImpl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO) {
        var User = ez.ndvz.userservice.domain.models.User.builder()
                .name(signUpRequestDTO.getName())
                .lastName(signUpRequestDTO.getLastName())
                .email(signUpRequestDTO.getEmail())
                .phoneNumber(signUpRequestDTO.getPhoneNumber())
                .password(passwordEncoder.encode(signUpRequestDTO.getPassword()))
                .roles(Collections.singleton(Roles.USER))
                .build();

        userRepository.save(User);

        var jwtToken = jwtService.generateToken(userDetailsService.loadUserByUsername(User.getEmail()));

        return SignUpResponseDTO.builder()
                .jwt(jwtToken)
                .message(String.format("User %s registered", User.getName()))
                .success(true)
                .build();
    }

    @Override
    public SignInResponseDTO signIn(SignInRequestDTO signInRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequestDTO.getEmail(), signInRequestDTO.getPassword()));

        var User = userRepository.findApplicationUserByEmail(signInRequestDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        var jwtToken = jwtService.generateToken(userDetailsService.loadUserByUsername(User.getEmail()));

        return SignInResponseDTO.builder()
                .name(User.getName())
                .lastName(User.getLastName())
                .email(User.getEmail())
                .token(jwtToken)
                .build();
    }
}
