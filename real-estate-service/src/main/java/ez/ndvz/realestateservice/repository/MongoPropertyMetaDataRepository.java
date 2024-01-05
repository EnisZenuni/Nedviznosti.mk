package ez.ndvz.realestateservice.repository;

import ez.ndvz.realestateservice.domain.models.PropertyMetaData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoPropertyMetaDataRepository extends MongoRepository<PropertyMetaData, Long> {
}
