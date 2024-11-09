package ma.yc.PigeonSkyRace.piegon.application.dto.response;

import ma.yc.PigeonSkyRace.piegon.domain.model.enums.Gender;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.BandNumber;

import java.time.LocalDateTime;

public record PigeonResponseDTO(String id, BandNumber bandNumber, Gender gender, double age, String color,
                                String loftId, LocalDateTime createdDate) {

}
