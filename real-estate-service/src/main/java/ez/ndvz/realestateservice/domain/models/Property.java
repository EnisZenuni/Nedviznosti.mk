package ez.ndvz.realestateservice.domain.models;

import ez.ndvz.realestateservice.domain.enumerations.Details;
import ez.ndvz.realestateservice.domain.enumerations.EnergySource;
import ez.ndvz.realestateservice.domain.enumerations.FlooringType;
import ez.ndvz.realestateservice.domain.enumerations.Heating;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
//TODO change to MappedSuperClass if we dont to use inheritnce type single table but then we wont have a discriminator to differentiate what kind of property we want to persist
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "property_type", discriminatorType = DiscriminatorType.STRING)
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public abstract class Property extends BaseEntity {

    @NonNull
    @NotBlank
    private String address; //TODO -> Create Value Object

    @NotNull
    @NotBlank(message = "Please provide a detailed description")
    private String description;

    @NotNull
    @NotBlank(message = "Please Insert size in km")
    private Float squareFootage;

    @NotNull
    @NotBlank(message = "Please insert number of bedrooms")
    @Size(min = 1)
    private Integer bedrooms;

    @NotNull
    @NotBlank(message = "Please insert number of bedrooms")
    @Size(min = 1)
    private Integer bathrooms;

    @NotNull
    @NotBlank(message = "Please select heating type from the provided options")
    @Enumerated(EnumType.STRING)
    private Heating heatingType;

    @Enumerated(EnumType.STRING)
    private EnergySource energySource;

    @Enumerated(EnumType.STRING)
    FlooringType flooringType;

    @NonNull
    @NotBlank
    @ElementCollection(targetClass = Details.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "property_details", joinColumns = @JoinColumn(name = "property_id"))
    @Column(name = "details")
    List<Details> detailsList;

}
