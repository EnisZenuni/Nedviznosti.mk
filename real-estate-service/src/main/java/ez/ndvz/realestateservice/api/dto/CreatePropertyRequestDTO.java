package ez.ndvz.realestateservice.api.dto;

import ez.ndvz.realestateservice.domain.enumerations.Details;
import ez.ndvz.realestateservice.domain.enumerations.EnergySource;
import ez.ndvz.realestateservice.domain.enumerations.FlooringType;
import ez.ndvz.realestateservice.domain.enumerations.Heating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@Value
@AllArgsConstructor
public class CreatePropertyRequestDTO {

    @NonNull
    @NotBlank
    @Pattern(regexp = "Apartment|House", message = "Property type must be either 'Apartment' or 'House'")
    String propertyType;

    @NotNull
    @NotBlank
    String address;

    @NotNull
    @NotBlank(message = "Please provide a detailed description")
    String description;

    @NotNull
    @NotBlank(message = "Please Insert size in km")
    Float squareFootage;

    @NotNull
    @NotBlank(message = "Please insert number of bedrooms")
    @Size(min = 1)
    Integer bedrooms;

    @NotNull
    @NotBlank(message = "Please insert number of bedrooms")
    @Size(min = 1)
    Integer bathrooms;

    @NotNull
    @NotBlank(message = "Please select heating type from the provided options")
    Heating heatingType;

    @NotNull
    @NotBlank
    EnergySource energySource;

    @NotNull
    @NotBlank
    FlooringType flooringType;

    @NonNull
    @NotBlank
    List<Details> detailsList;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 6)
    List<MultipartFile> multipartFiles;
}
