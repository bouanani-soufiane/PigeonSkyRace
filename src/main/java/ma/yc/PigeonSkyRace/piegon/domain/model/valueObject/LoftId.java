package ma.yc.PigeonSkyRace.piegon.domain.model.valueObject;

import com.fasterxml.jackson.annotation.JsonValue;
import org.bson.types.ObjectId;

public record LoftId(ObjectId value) {

    public LoftId () {
        this(new ObjectId());
    }

    @JsonValue
    public String toHexString () {
        return value.toHexString();
    }

    public static LoftId fromString ( String id ) {
        return new LoftId(new ObjectId(id));
    }
}
