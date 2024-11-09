package ma.yc.PigeonSkyRace.piegon.domain.model.valueObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.NotBlank;

public record Gender(@NotBlank String value) {
    public Gender {
        validate(value);
    }

    private static void validate ( String value ) {
        if (!value.equalsIgnoreCase("male") && !value.equalsIgnoreCase("female")) {
            throw new IllegalArgumentException("The gender must be either 'male' or 'female'.");
        }
    }

    @JsonCreator
    public static Gender fromString ( String value ) {
        return new Gender(value);
    }

    @JsonValue
    public String getValue () {
        return value;
    }
}