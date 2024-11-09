package ma.yc.PigeonSkyRace.competition.domain.service;

import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonPigeonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonPigeonResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonPigeonId;

public interface SeasonPigeonService {
    SeasonPigeonResponseDto RegisterToSeason(SeasonPigeonRequestDto seasonPigeonRequestDto);
    SeasonPigeonResponseDto getSeasonById(SeasonPigeonId seasonPigeonId);
}
