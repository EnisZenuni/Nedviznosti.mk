package ez.ndvz.realestateservice.domain.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("House")
public class House extends Property {

    @NonNull
    @NotBlank
    private Integer numberOfFloors;

    @Positive
    private double price;

    @Positive
    private double rentalPrice;
}
