package ez.ndvz.realestateservice.repository;

import ez.ndvz.realestateservice.domain.models.PropertyMetaData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyMetaDataRepository extends MongoRepository<PropertyMetaData, Long> {
}
