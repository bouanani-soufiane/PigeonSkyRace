package ma.yc.PigeonSkyRace.competition.application.dto.response;


import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;

import java.time.LocalDateTime;
import java.util.List;

public record SeasonResponseDto(
        SeasonId id,
        String name,
        String description,
        Boolean isActive,
        LocalDateTime createdAt,
        List<Competition>competitions
) {
}
