package ma.yc.PigeonSkyRace.competition.domain.service;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionPigeonResponseDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonPigeonResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;

import java.util.List;

public interface CompetitionPigeonService {

    CompetitionPigeonResponseDto registerToCompetition(SeasonPigeon seasonPigeon, Competition competition) ;
    List<CompetitionPigeonResponseDto> getAllCompetitionsPigeons();
}
