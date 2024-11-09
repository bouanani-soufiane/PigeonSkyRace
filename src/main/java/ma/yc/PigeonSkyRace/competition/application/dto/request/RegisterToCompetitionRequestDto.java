package ma.yc.PigeonSkyRace.competition.application.dto.request;

import jakarta.validation.constraints.NotNull;

public record RegisterToCompetitionRequestDto(
        @NotNull String seasonPigeonId
) {
}
