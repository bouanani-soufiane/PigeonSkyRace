package ma.yc.PigeonSkyRace.competition.domain.entity;

import lombok.Getter;
import lombok.Setter;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonPigeonId;
import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "season_pigeon")
public class SeasonPigeon {
    @Id
    private SeasonPigeonId id;

    @DBRef
    private Pigeon pigeon;

    @DBRef
    private Season season;

    @CreatedDate
    private LocalDateTime createdDate;

    public SeasonPigeon () {
        this.id = new SeasonPigeonId();
    }
}
