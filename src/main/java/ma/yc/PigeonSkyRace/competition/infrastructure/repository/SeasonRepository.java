package ma.yc.PigeonSkyRace.competition.infrastructure.repository;

import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Season;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SeasonRepository extends MongoRepository<Season, SeasonId> {
}
