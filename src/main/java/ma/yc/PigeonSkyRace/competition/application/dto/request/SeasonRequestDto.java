package ma.yc.PigeonSkyRace.competition.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SeasonRequestDto(
        @NotBlank String name,
        @NotBlank String description,
        @NotNull Boolean isActive
) {
}
