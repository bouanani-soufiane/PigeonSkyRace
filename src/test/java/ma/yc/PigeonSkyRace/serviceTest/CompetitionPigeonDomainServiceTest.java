package ma.yc.PigeonSkyRace.serviceTest;
import ma.yc.PigeonSkyRace.competition.application.dto.request.CompetitionPigeonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionPigeonResponseDto;
import ma.yc.PigeonSkyRace.competition.application.mapping.CompetitionPigeonMapper;
import ma.yc.PigeonSkyRace.competition.domain.Exception.FailedToRegister;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionId;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.CompetitionPigeonId;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonPigeonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.competition.domain.entity.CompetitionPigeon;
import ma.yc.PigeonSkyRace.competition.domain.entity.Season;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;
import ma.yc.PigeonSkyRace.competition.infrastructure.repository.CompetitionPigeonRepository;
import ma.yc.PigeonSkyRace.competition.domain.service.Impl.CompetitionPigeonDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompetitionPigeonDomainServiceTest {

    @Mock
    private CompetitionPigeonRepository repository;

    @Mock
    private CompetitionPigeonMapper mapper;

    @InjectMocks
    private CompetitionPigeonDomainService service;

    private SeasonPigeon seasonPigeon;
    private Competition competition;
    private CompetitionPigeon competitionPigeon;
    private CompetitionPigeonRequestDto requestDto;
    private CompetitionPigeonResponseDto responseDto;

    @BeforeEach
    void setUp() {
        seasonPigeon = new SeasonPigeon();
        seasonPigeon.setSeason(new Season());
        SeasonId seasonId = new SeasonId();
        seasonPigeon.getSeason().setId(seasonId);
        seasonPigeon.setId(new SeasonPigeonId());

        competition = new Competition();
        competition.setId(new CompetitionId());
        competition.setSeasonId(seasonId);

        competitionPigeon = new CompetitionPigeon();
        requestDto = new CompetitionPigeonRequestDto(seasonPigeon, competition);
        responseDto = new CompetitionPigeonResponseDto(new CompetitionPigeonId(), seasonPigeon, competition, LocalDateTime.now());
    }


    @Test
    void registerToCompetition_success() {
        when(repository.findBySeasonPigeonAndCompetition(seasonPigeon, competition)).thenReturn(Optional.empty());
        when(mapper.toEntity(requestDto)).thenReturn(competitionPigeon);
        when(repository.save(competitionPigeon)).thenReturn(competitionPigeon);
        when(mapper.toDto(competitionPigeon)).thenReturn(responseDto);

        CompetitionPigeonResponseDto result = service.registerToCompetition(seasonPigeon, competition);

        assertNotNull(result);
        assertEquals(responseDto, result);

        verify(repository).findBySeasonPigeonAndCompetition(seasonPigeon, competition);
        verify(repository).save(competitionPigeon);
    }

    @Test
    void registerToCompetition_differentSeasonIds_throwsException() {
        competition.setSeasonId(new SeasonId());

        FailedToRegister exception = assertThrows(FailedToRegister.class,
                () -> service.registerToCompetition(seasonPigeon, competition));

        assertEquals("This pigeon is not registered in the same season as the competition.", exception.getMessage());
        verify(repository, never()).save(any(CompetitionPigeon.class));
    }

    @Test
    void registerToCompetition_alreadyRegistered_throwsException() {
        when(repository.findBySeasonPigeonAndCompetition(seasonPigeon, competition))
                .thenReturn(Optional.of(competitionPigeon));

        FailedToRegister exception = assertThrows(FailedToRegister.class,
                () -> service.registerToCompetition(seasonPigeon, competition));

        assertEquals("This pigeon is already registered in the competition.", exception.getMessage());
        verify(repository, never()).save(any(CompetitionPigeon.class));
    }
}
