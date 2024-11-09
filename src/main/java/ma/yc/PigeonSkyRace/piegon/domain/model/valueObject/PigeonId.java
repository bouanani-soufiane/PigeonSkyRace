package ma.yc.PigeonSkyRace.piegon.domain.model.valueObject;

import com.fasterxml.jackson.annotation.JsonValue;
import org.bson.types.ObjectId;

public record PigeonId(ObjectId value) {
    public PigeonId () {
        this(new ObjectId());
    }

    @JsonValue
    public String toHexString () {
        return value.toHexString();
    }

    public static PigeonId fromString ( String id ) {
        return new PigeonId(new ObjectId(id));
    }
}
