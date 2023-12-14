package ez.ndvz.userservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class SignUpResponseDTO {
    String jwt;
    boolean success;
    String message;
}
