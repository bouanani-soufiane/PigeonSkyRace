package ma.yc.PigeonSkyRace.serviceTest;

import ma.yc.PigeonSkyRace.common.domain.exception.NotFoundException;
import ma.yc.PigeonSkyRace.competition.application.dto.request.CompetitionRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionResponseDto;
import ma.yc.PigeonSkyRace.competition.application.events.CompetitionCreatedEvent;
import ma.yc.PigeonSkyRace.competition.application.mapping.CompetitionMapper;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.AdmissionPercentage;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionId;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.competition.infrastructure.repository.CompetitionRepository;
import ma.yc.PigeonSkyRace.competition.domain.service.Impl.CompetitionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompetitionServiceImplTest {

    @InjectMocks
    private CompetitionServiceImpl competitionService;

    @Mock
    private CompetitionRepository repository;

    @Mock
    private CompetitionMapper mapper;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    private Competition competition;
    private CompetitionId competitionId;
    private CompetitionRequestDto competitionRequestDto;
    private CompetitionResponseDto competitionResponseDto;
    private Coordinate coordinate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        competitionId = new CompetitionId();
        competition = new Competition();
        competition.setId(competitionId);
        competition.setCoordinate(new Coordinate(34.05, -118.25));
        SeasonId seasonId = new SeasonId();
        AdmissionPercentage num = new AdmissionPercentage(12.3);
        competitionRequestDto = new CompetitionRequestDto("Spring Competition", "A fun competition", 100,100.0, num, LocalDateTime.now(), LocalDateTime.now(), seasonId);
        coordinate = new Coordinate(35.68, 139.69);
        competitionResponseDto = new CompetitionResponseDto(competitionId, "Spring Competition", "A fun competition", coordinate, 100,100.0, num,seasonId, LocalDateTime.now(), LocalDateTime.now(), null);
    }

    @Test
    void testCreateCompetition() {
        when(mapper.toEntity(competitionRequestDto)).thenReturn(competition);
        when(repository.save(competition)).thenReturn(competition);
        when(mapper.toDto(competition)).thenReturn(competitionResponseDto);

        CompetitionResponseDto result = competitionService.createCompetition(competitionRequestDto);

        assertEquals(competitionResponseDto.name(), result.name());
        verify(mapper, times(1)).toEntity(competitionRequestDto);
        verify(repository, times(1)).save(competition);
        verify(eventPublisher, times(1)).publishEvent(any(CompetitionCreatedEvent.class));
    }

    @Test
    void testGetCompetition_Success() {
        when(repository.findById(competitionId)).thenReturn(Optional.of(competition));
        when(mapper.toDto(competition)).thenReturn(competitionResponseDto);

        CompetitionResponseDto result = competitionService.getCompetition(competitionId);

        assertEquals(competitionResponseDto.name(), result.name());
        verify(repository, times(1)).findById(competitionId);
    }

    @Test
    void testGetCompetition_NotFound() {
        when(repository.findById(competitionId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> competitionService.getCompetition(competitionId));
        verify(repository, times(1)).findById(competitionId);
    }

    @Test
    void testGetAllCompetitions() {
        List<Competition> competitions = List.of(competition);
        when(repository.findAll()).thenReturn(competitions);
        when(mapper.toDto(competition)).thenReturn(competitionResponseDto);

        List<CompetitionResponseDto> result = competitionService.getAllCompetitions();

        assertEquals(1, result.size());
        assertEquals(competitionResponseDto.name(), result.get(0).name());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testUpdateCompetition_Success() {
        when(repository.existsById(competitionId)).thenReturn(true);
        Boolean result = competitionService.updateCompetition(competitionId, coordinate);
        assertTrue(result);
        verify(repository, times(1)).updateCoordinateById(competitionId, coordinate);
    }

    @Test
    void testUpdateCompetition_NotFound() {
        when(repository.existsById(competitionId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> competitionService.updateCompetition(competitionId, coordinate));
        verify(repository, never()).updateCoordinateById(competitionId, coordinate);
    }
}
