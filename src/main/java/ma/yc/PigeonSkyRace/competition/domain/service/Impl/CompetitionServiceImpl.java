package ma.yc.PigeonSkyRace.competition.domain.service.Impl;

import lombok.RequiredArgsConstructor;
import ma.yc.PigeonSkyRace.common.domain.exception.NotFoundException;
import ma.yc.PigeonSkyRace.competition.application.dto.request.CompetitionRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;
import ma.yc.PigeonSkyRace.competition.application.events.CompetitionCreatedEvent;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionId;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.competition.domain.service.CompetitionService;
import ma.yc.PigeonSkyRace.competition.infrastructure.repository.CompetitionRepository;
import ma.yc.PigeonSkyRace.competition.application.mapping.CompetitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository repository;
    private final CompetitionMapper mapper;
    private final ApplicationEventPublisher eventPublisher;


    @Override
    public CompetitionResponseDto createCompetition(CompetitionRequestDto competitionRequestDto) {
        Competition competition = mapper.toEntity(competitionRequestDto);
        Competition savedCompetition = repository.save(competition);
        eventPublisher.publishEvent(new CompetitionCreatedEvent(savedCompetition, competitionRequestDto.seasonId()));
        return mapper.toDto(savedCompetition);
    }

    @Override
    public CompetitionResponseDto getCompetition(CompetitionId id){
        Competition competition = repository.findById(id).orElseThrow(() -> new NotFoundException("Competition", id));
        return mapper.toDto(competition);
    }

    @Override
    public List<CompetitionResponseDto> getAllCompetitions() {
        List<Competition> competitions = repository.findAll();
        return   competitions.stream().map(mapper::toDto).collect(Collectors.toList());

    }


    @Override
    public Boolean updateCompetition(CompetitionId id, Coordinate coordinate) {
        if(repository.existsById(id)){
            repository.updateCoordinateById(id, coordinate);
            return true;
        }
       throw new NotFoundException("Competition", id);
    }

}
