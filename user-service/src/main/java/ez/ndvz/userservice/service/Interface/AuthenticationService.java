package ez.ndvz.userservice.service.Interface;

import ez.ndvz.userservice.api.dto.SignInRequestDTO;
import ez.ndvz.userservice.api.dto.SignInResponseDTO;
import ez.ndvz.userservice.api.dto.SignUpRequestDTO;
import ez.ndvz.userservice.api.dto.SignUpResponseDTO;

public interface AuthenticationService {
    SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO);

    SignInResponseDTO signIn(SignInRequestDTO signInRequestDTO);
}
