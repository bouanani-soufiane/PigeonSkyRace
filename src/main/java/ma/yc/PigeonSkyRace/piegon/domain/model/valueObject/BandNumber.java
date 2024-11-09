package ma.yc.PigeonSkyRace.piegon.domain.model.valueObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.regex.Pattern;


public record BandNumber(@NotBlank String value) {
    private static final Pattern BAND_NUMBER_PATTERN =
            Pattern.compile("^[A-Z]{2} \\d{4} [A-Z]{3} \\d{7}$");

    public BandNumber {
        validate(value);
    }

    private static void validate ( String value ) {
        if (!BAND_NUMBER_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException(
                    "Invalid band number format. Expected format: AU 2003 XYZ 3234534"
            );
        }

        String[] parts = value.split(" ");
        int year = Integer.parseInt(parts[1]);
        LocalDateTime now = LocalDateTime.now();
        if (year > now.getYear() || year < now.minusYears(10).getYear()) {
            throw new IllegalArgumentException(
                    "Year must be between " + now.minusYears(10).getYear() +
                            " and " + now.getYear() + "."
            );
        }
    }

    @JsonCreator
    public static BandNumber fromString ( String value ) {
        return new BandNumber(value);
    }

    @JsonValue
    public String getValue () {
        return value;
    }
}


