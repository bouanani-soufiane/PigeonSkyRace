package ma.yc.PigeonSkyRace.competition.application.dto.request;

import jakarta.validation.constraints.NotNull;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;

public record CompetitionPigeonRequestDto(
        @NotNull SeasonPigeon seasonPigeon,
        @NotNull Competition competition
) {
}
