package ma.yc.PigeonSkyRace.competition.domain.ValueObject;


import com.fasterxml.jackson.annotation.JsonValue;
import org.bson.types.ObjectId;


public record CompetitionId(ObjectId value) {
    public CompetitionId() {
        this(new ObjectId());
    }

    @JsonValue
    public String toHexString() {
        return value.toHexString();
    }

    public static CompetitionId fromString(String id) {
        return new CompetitionId(new ObjectId(id));
    }
}
