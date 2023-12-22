package ez.ndvz.realestateservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document(collation = "PropertyMetaData")
@Data
@AllArgsConstructor
@Getter
public class PropertyMetaData extends BaseEntity {

    @Id
    private Long id;

    private Long apartmentIdReference;
    @GeoSpatialIndexed
    private GeoJsonPoint location;

    private String filename;

    private List<Image> files;

    //getter
    public Long getApartmentIdReference() {
        return super.getId();
    }

    //setter
    public void setApartmentIdReference() {
        this.apartmentIdReference = super.getId();
    }
}
