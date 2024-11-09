package ma.yc.PigeonSkyRace.competition.application.dto.request;

import jakarta.validation.constraints.NotNull;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.PigeonId;

public record SeasonPigeonRequestDTO(@NotNull SeasonId seasonId, @NotNull PigeonId pigeonId) {
}
