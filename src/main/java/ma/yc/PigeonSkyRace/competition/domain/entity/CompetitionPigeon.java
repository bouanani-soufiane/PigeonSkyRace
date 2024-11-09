package ma.yc.PigeonSkyRace.competition.domain.entity;


import lombok.Getter;
import lombok.Setter;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionPigeonId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Getter
@Setter
@Document(collection = "competition_pigeons")
public class CompetitionPigeon {
    @Id
    private CompetitionPigeonId id;

    @DBRef
    private SeasonPigeon seasonPigeon;

    @DBRef
    private Competition competition;

    @CreatedDate
    private LocalDateTime createdDate;

    public CompetitionPigeon() {
        this.id = new CompetitionPigeonId();
    }
}

