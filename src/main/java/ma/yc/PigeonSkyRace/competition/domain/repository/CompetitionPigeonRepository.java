package ma.yc.PigeonSkyRace.competition.domain.repository;

import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionPigeonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.competition.domain.entity.CompetitionPigeon;

import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;
import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompetitionPigeonRepository extends MongoRepository<CompetitionPigeon, CompetitionPigeonId> {
    Optional<CompetitionPigeon> findBySeasonPigeonAndCompetition(SeasonPigeon seasonPigeon, Competition competition);
}
