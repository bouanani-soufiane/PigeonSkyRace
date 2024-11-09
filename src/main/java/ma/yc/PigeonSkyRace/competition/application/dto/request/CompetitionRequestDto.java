package ma.yc.PigeonSkyRace.competition.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.AdmissionPercentage;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;

import java.time.LocalDateTime;

public record CompetitionRequestDto(
        @NotBlank String name,
        @NotBlank String description,
        @Positive Integer maxPigeons,
        @Positive Double distance,
        @NotNull AdmissionPercentage admissionPercentage,
        @NotNull LocalDateTime dateStart,
        @NotNull LocalDateTime dateEnd,
        @NotNull SeasonId seasonId
        ) {

}
