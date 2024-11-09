package ma.yc.PigeonSkyRace.competition.application.dto.response;

import ma.yc.PigeonSkyRace.competition.domain.ValueObject.AdmissionPercentage;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionId;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;

import java.time.LocalDateTime;
import java.util.UUID;

public record CompetitionResponseDto(
        CompetitionId id,
        String name,
        String description,
        Coordinate coordinate,
        Integer maxPigeons,
        Double distance,
        AdmissionPercentage admissionPercentage,
        SeasonId seasonId,
        LocalDateTime dateStart,
        LocalDateTime dateEnd,
        LocalDateTime createdAt
){
}
