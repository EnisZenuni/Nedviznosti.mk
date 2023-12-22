package ez.ndvz.realestateservice.domain.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Apartment")
public class Apartment extends Property {

    @NotBlank
    @NonNull
    @PositiveOrZero
    private Integer floor;

    @Positive
    private double price;

    @Positive
    private double rentalPrice;
}
