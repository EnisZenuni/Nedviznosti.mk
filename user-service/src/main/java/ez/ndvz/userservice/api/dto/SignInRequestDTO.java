package ez.ndvz.userservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class SignInRequestDTO {
    String email;
    String password;
}
