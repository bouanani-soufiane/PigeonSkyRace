package ma.yc.PigeonSkyRace.piegon.application.dto.request;

import jakarta.validation.constraints.NotNull;

public record LoftRequestDTO(@NotNull CoordinateRequestDTO coordinate, @NotNull String userId) {
}