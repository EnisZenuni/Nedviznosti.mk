package ez.ndvz.userservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class SignInResponseDTO {
    String name;
    String lastName;
    String email;
    String token;
    String tokenType = "Bearer"; //TODO maybe remove
}
