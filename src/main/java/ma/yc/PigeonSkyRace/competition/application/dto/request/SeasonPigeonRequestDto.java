package ma.yc.PigeonSkyRace.competition.application.dto.request;

import jakarta.validation.constraints.NotNull;
import ma.yc.PigeonSkyRace.competition.domain.entity.Season;
import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;

public record SeasonPigeonRequestDto(
        @NotNull Pigeon pigeon,
        @NotNull Season season
) {
}
