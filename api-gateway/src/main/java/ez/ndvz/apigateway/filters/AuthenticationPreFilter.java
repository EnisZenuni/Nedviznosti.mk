package ez.ndvz.apigateway.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ez.ndvz.apigateway.dto.Authorities;
import ez.ndvz.apigateway.dto.ErrorResponseModel;
import ez.ndvz.apigateway.dto.JwtValidationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class AuthenticationPreFilter extends AbstractGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig> {
    private final WebClient.Builder webClientBuilder;


    // public Predicate<ServerHttpRequest> isSecured =
    // request -> excludedUrls.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
    @Override
    public GatewayFilter apply(NameConfig config) {
        return (exchange, chain) -> {

            if (!exchange.getRequest().getHeaders().containsKey("Authorization"))
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

            //ServerHttpRequest request = exchange.getRequest();

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String[] parts = authHeader.split(" ");

            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                throw new RuntimeException("Incorrect authorization structure");
            }//user-service
            ///api/v1/validateToken

            return webClientBuilder.build().post()
                    .uri("lb://user-service/api/v1/validateToken")
                    .header("token", parts[1])
                    .retrieve()
                    .bodyToMono(JwtValidationResponse.class)
                    .map(response -> {
                        exchange.getRequest().mutate().header("username", response.getEmail());
                        exchange.getRequest().mutate().header("authorities", response.getAuthorities().stream().map(Authorities::getAuthority).reduce("", (a, b) -> a + "," + b));
                        exchange.getRequest().mutate().header("token", response.getToken());

                        return exchange;
                    }).flatMap(chain::filter).onErrorResume(error -> {

                        HttpStatus errorCode;
                        String errorMsg;

                        if (error instanceof WebClientResponseException) {
                            WebClientResponseException webClientException = (WebClientResponseException) error;
                            errorCode = (HttpStatus) webClientException.getStatusCode();
                            errorMsg = webClientException.getStatusText();
                        } else {
                            errorCode = HttpStatus.INTERNAL_SERVER_ERROR;
                            errorMsg = "Internal Server Error";
                        }

                        // You can customize this method based on your needs
                        return handleErrorResponse(exchange, errorCode, errorMsg);
                    });


            //TODO change this to using HTTPHeaders.Authorization
            //String bearerToken = request.getHeaders().getFirst("Authorization");
        };
    }

    private Mono<? extends Void> handleErrorResponse(ServerWebExchange exchange, HttpStatus errorCode, String errorMsg) {
        // Create a custom error response
        ErrorResponseModel errorResponse = new ErrorResponseModel(
                errorCode.value(),
                errorCode.getReasonPhrase(),
                errorMsg,
                LocalDateTime.now()
        );

        // Convert the error response to a JSON string
        String jsonResponse = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonResponse = objectMapper.writeValueAsString(errorResponse);
        } catch (JsonProcessingException e) {
            jsonResponse = "{\"error\":\"Internal Server Error\"}"; // Fallback response
        }

        // Set the appropriate headers and write the JSON response to the client
        DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(errorCode);
        response.getHeaders().add("Content-Type", "application/json");

        return response.writeWith(Mono.just(jsonResponse)
                .map(s -> dataBufferFactory.wrap(s.getBytes())));
    }
}
