package ez.ndvz.userservice.api.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Builder
@Getter
public class JwtValidationResponse {
    private String status;
    private boolean isAuthenticated;
    private String methodType;
    private String email;
    private String token;
    private Set<GrantedAuthority> authorities;
}
