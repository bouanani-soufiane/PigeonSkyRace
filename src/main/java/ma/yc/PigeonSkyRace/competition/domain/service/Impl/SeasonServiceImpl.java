package ma.yc.PigeonSkyRace.competition.domain.service.Impl;

import lombok.RequiredArgsConstructor;
import ma.yc.PigeonSkyRace.common.domain.exception.NotFoundException;
import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonResponseDto;
import ma.yc.PigeonSkyRace.competition.application.events.CompetitionCreatedEvent;
import ma.yc.PigeonSkyRace.competition.application.mapping.SeasonMapper;
import ma.yc.PigeonSkyRace.competition.application.service.SeasonApplicationService;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Season;
import ma.yc.PigeonSkyRace.competition.domain.service.SeasonService;
import ma.yc.PigeonSkyRace.competition.infrastructure.repository.SeasonRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService, SeasonApplicationService {

    private final SeasonRepository repository;
    private final SeasonMapper mapper;

    @EventListener
    public void handleCompetitionCreatedEvent ( CompetitionCreatedEvent event ) {
        Season season = repository.findById(event.getSeasonId()).orElseThrow(() -> new NotFoundException("Season", event.getSeasonId()));

        season.getCompetitions().add(event.getCompetition());
        repository.save(season);
    }

    @Override
    public List<SeasonResponseDto> getAllSeasons () {
        List<Season> seasons = repository.findAll();
        return seasons.stream().map(mapper::toDto).collect(Collectors.toList());

    }

    @Override
    public SeasonResponseDto getSeasonById ( SeasonId seasonId ) {
        Season season = repository.findById(seasonId).orElseThrow(() -> new NotFoundException("Season", seasonId));
        return mapper.toDto(season);
    }

    @Override
    public SeasonResponseDto createSeason ( SeasonRequestDto seasonRequestDto ) {
        Season season = mapper.toEntity(seasonRequestDto);
        return mapper.toDto(repository.save(season));
    }

    @Override
    public SeasonResponseDto updateSeason ( SeasonId seasonId, SeasonRequestDto seasonRequestDto ) {
        Season season = repository.findById(seasonId).orElseThrow(() -> new NotFoundException("Season", seasonId));
        season.setDescription(seasonRequestDto.description());
        season.setIsActive(seasonRequestDto.isActive());
        season.setName(seasonRequestDto.name());
        return mapper.toDto(repository.save(season));

    }

    @Override
    public Boolean deleteSeason ( SeasonId id ) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        throw new NotFoundException("Season", id);
    }


    @Override
    public Season findById ( SeasonId value ) {
        return repository.findById(value).orElseThrow(() -> new NotFoundException("Season", value));
    }
}