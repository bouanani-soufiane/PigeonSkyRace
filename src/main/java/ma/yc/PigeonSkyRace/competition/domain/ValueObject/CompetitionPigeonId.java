package ma.yc.PigeonSkyRace.competition.domain.ValueObject;

import com.fasterxml.jackson.annotation.JsonValue;
import org.bson.types.ObjectId;

public record CompetitionPigeonId(ObjectId value) {
    public CompetitionPigeonId() {
        this(new ObjectId());
    }

    @JsonValue
    public String toHexString() {
        return value.toHexString();
    }

    public static CompetitionPigeonId fromString(String id) {
        return new CompetitionPigeonId(new ObjectId(id));
    }
}
