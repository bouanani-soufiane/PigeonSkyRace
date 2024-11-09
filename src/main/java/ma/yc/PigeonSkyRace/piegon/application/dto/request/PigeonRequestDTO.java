package ma.yc.PigeonSkyRace.piegon.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ma.yc.PigeonSkyRace.piegon.domain.model.enums.Gender;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.BandNumber;

public record PigeonRequestDTO(@NotNull BandNumber bandNumber,

                               @NotNull Gender gender,

                               @Positive double age,

                               @NotBlank String color,

                               @NotBlank String loftId

) {
}
