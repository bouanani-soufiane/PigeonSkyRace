package ma.yc.PigeonSkyRace.competition.application.mapping;

import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonPigeonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonPigeonResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeasonPigeonMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    SeasonPigeon toEntity(SeasonPigeonRequestDto seasonPigeonRequestDto);
    SeasonPigeonResponseDto toDto(SeasonPigeon seasonPigeon);
    SeasonPigeon toEntity(SeasonPigeonResponseDto seasonPigeonResponseDto);
}