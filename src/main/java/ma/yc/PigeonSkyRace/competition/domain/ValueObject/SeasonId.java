package ma.yc.PigeonSkyRace.competition.domain.ValueObject;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonValue;

public record SeasonId(ObjectId value) {
    public SeasonId () {
        this(new ObjectId());
    }

    @JsonValue
    public String toHexString() {
        return value.toHexString();
    }
    
    public static SeasonId fromString(String id) {
        return new SeasonId(new ObjectId(id));
    }
}
