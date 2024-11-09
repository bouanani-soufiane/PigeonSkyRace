package ma.yc.PigeonSkyRace.piegon.application.dto.request;

import jakarta.validation.constraints.NotNull;

public record CoordinateRequestDTO(@NotNull Double latitude, @NotNull Double longitude) {
}