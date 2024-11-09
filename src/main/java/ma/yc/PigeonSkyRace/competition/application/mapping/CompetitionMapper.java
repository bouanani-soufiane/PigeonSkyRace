package ma.yc.PigeonSkyRace.competition.application.mapping;

import ma.yc.PigeonSkyRace.competition.application.dto.request.CompetitionRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    Competition toEntity (CompetitionRequestDto dto );

    CompetitionResponseDto toDto (Competition entity );
    Competition toEntity (CompetitionResponseDto competitionResponseDto);
}
