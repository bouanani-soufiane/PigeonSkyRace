package ma.yc.PigeonSkyRace.piegon.infrastructure.repository;

import ma.yc.PigeonSkyRace.piegon.domain.model.entity.Loft;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.LoftId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoftRepository extends MongoRepository<Loft, LoftId> {
    boolean existsByName (String name);
}
