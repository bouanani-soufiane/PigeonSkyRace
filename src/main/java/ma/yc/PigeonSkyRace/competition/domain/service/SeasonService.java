package ma.yc.PigeonSkyRace.competition.domain.service;

import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;

import java.util.List;

public interface SeasonService {

    List<SeasonResponseDto> getAllSeasons();

    SeasonResponseDto getSeasonById(SeasonId seasonId);

    SeasonResponseDto createSeason(SeasonRequestDto seasonRequestDto);

    SeasonResponseDto updateSeason(SeasonId id,SeasonRequestDto seasonRequestDto);

    Boolean deleteSeason(SeasonId seasonId);
}