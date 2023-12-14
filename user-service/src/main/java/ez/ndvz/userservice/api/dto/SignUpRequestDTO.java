package ez.ndvz.userservice.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class SignUpRequestDTO {

    @NonNull
    @NotBlank
    String name;

    @NotBlank
    @NonNull
    String lastName;

    @NonNull
    @NotBlank
    @Pattern(regexp = "^\\+(389)\\s?([2-7]0|78|77|75|76|71|72|73|74)\\s?([0-9]{6})$", message = "Invalid phone number format for North Macedonia")
    String phoneNumber;

    @NotBlank
    @NonNull
    @Email
    String email;

    @NonNull
    @NotBlank
    @Size(min = 6)
    String password;
}
