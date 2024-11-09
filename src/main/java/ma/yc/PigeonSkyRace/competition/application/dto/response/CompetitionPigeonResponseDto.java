package ma.yc.PigeonSkyRace.competition.application.dto.response;

import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionPigeonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;

import java.time.LocalDateTime;

public record CompetitionPigeonResponseDto(
        CompetitionPigeonId id,
        SeasonPigeon seasonPigeon,
        Competition competition,
        LocalDateTime createdDate
) {
}
