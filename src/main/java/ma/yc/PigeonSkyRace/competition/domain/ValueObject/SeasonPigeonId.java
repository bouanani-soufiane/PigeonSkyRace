package ma.yc.PigeonSkyRace.competition.domain.ValueObject;

import com.fasterxml.jackson.annotation.JsonValue;
import org.bson.types.ObjectId;

public record SeasonPigeonId(ObjectId value) {
    public SeasonPigeonId () {
        this(new ObjectId());
    }

    @JsonValue
    public String toHexString () {
        return value.toHexString();
    }

    public static SeasonPigeonId fromString ( String id ) {
        return new SeasonPigeonId(new ObjectId(id));
    }
}
