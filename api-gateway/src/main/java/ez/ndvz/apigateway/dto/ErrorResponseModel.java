package ez.ndvz.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Builder
@Getter
@AllArgsConstructor
public class ErrorResponseModel {
    private int errorCode;
    private String errorReason;
    private String errorMessage;
    private LocalDateTime timestamp;
}
