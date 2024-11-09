package ma.yc.PigeonSkyRace.competition.infrastructure.repository;

import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonPigeonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Season;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;
import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SeasonPigeonRepository extends MongoRepository<SeasonPigeon, SeasonPigeonId> {
    Optional<SeasonPigeon> findByPigeonAndSeason(Pigeon pigeon, Season season);
}
