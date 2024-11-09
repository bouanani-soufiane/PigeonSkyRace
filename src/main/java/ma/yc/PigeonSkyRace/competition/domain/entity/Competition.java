package ma.yc.PigeonSkyRace.competition.domain.entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.AdmissionPercentage;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionId;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Document(collection = "competitions")
public class Competition {

    @Id
    private CompetitionId id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Coordinate coordinate;

    @Positive
    private Double distance;

    @Field("max_pigeons")
    @Positive
    private Integer maxPigeons;

    @Field("admission_percentage")
    @Positive
    private AdmissionPercentage admissionPercentage;

    @Field("date_start")
    private LocalDateTime dateStart;

    @Field("date_end")
    private LocalDateTime dateEnd;

    @NotNull
    private SeasonId seasonId;

    @CreatedDate
    private LocalDateTime createdDate;

    public Competition(){
        this.id = new CompetitionId();
    }

}

