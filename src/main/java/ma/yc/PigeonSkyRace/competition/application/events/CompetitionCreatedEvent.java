package ma.yc.PigeonSkyRace.competition.application.events;

import lombok.Getter;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;

@Getter
public class CompetitionCreatedEvent {
    private final Competition competition;
    private final SeasonId seasonId;

    public CompetitionCreatedEvent(Competition competition, SeasonId seasonId) {
        this.competition = competition;
        this.seasonId = seasonId;
    }

}
