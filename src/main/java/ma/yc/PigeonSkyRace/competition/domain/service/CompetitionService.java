package ma.yc.PigeonSkyRace.competition.domain.service;

import ma.yc.PigeonSkyRace.competition.application.dto.request.CompetitionRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionId;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;

import java.util.List;
import java.util.UUID;

public interface CompetitionService {

    CompetitionResponseDto createCompetition(CompetitionRequestDto seasonRequestDto);

    Boolean updateCompetition(CompetitionId id, Coordinate coordinate);

    CompetitionResponseDto getCompetition(CompetitionId id);

    List<CompetitionResponseDto> getAllCompetitions();
}
