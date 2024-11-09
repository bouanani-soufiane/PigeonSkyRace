package ma.yc.PigeonSkyRace.competition.domain.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "seasons")
public class Season {

    @Id
    private SeasonId id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;


    @Field(name = "is_active")
    private Boolean isActive;

    @DBRef
    private List<SeasonPigeon> seasonPigeons = new ArrayList<>();

    @DBRef
    private List<Competition> competitions = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdDate;

    public Season () {
        this.id = new SeasonId();
    }
}
