package ma.yc.PigeonSkyRace.serviceTest;

import ma.yc.PigeonSkyRace.common.domain.exception.NotFoundException;
import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonResponseDto;
import ma.yc.PigeonSkyRace.competition.application.mapping.SeasonMapper;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Season;
import ma.yc.PigeonSkyRace.competition.infrastructure.repository.SeasonRepository;
import ma.yc.PigeonSkyRace.competition.domain.service.Impl.SeasonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SeasonServiceImplTest {

    @InjectMocks
    private SeasonServiceImpl seasonService;

    @Mock
    private SeasonRepository seasonRepository;

    @Mock
    private SeasonMapper seasonMapper;

    private SeasonId seasonId;
    private Season season;
    private SeasonRequestDto seasonRequestDto;
    private SeasonResponseDto seasonResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        seasonId = new SeasonId();
        season = new Season();
        season.setId(seasonId);
        season.setName("Summer Season");
        season.setDescription("Description of summer season");
        season.setIsActive(true);

        seasonRequestDto = new SeasonRequestDto("Summer Season", "Description of summer season", true);
        seasonResponseDto = new SeasonResponseDto(seasonId, "Summer Season", "Description of summer season", true, null, null);
    }

    @Test
    void testGetAllSeasons() {
        List<Season> seasons = List.of(season);

        when(seasonRepository.findAll()).thenReturn(seasons);
        when(seasonMapper.toDto(any(Season.class))).thenReturn(seasonResponseDto);

        List<SeasonResponseDto> result = seasonService.getAllSeasons();

        assertEquals(1, result.size());
        assertEquals(seasonResponseDto, result.get(0));
        verify(seasonRepository, times(1)).findAll();
    }

    @Test
    void testGetSeasonById_Success() {
        when(seasonRepository.findById(seasonId)).thenReturn(Optional.of(season));
        when(seasonMapper.toDto(season)).thenReturn(seasonResponseDto);

        SeasonResponseDto result = seasonService.getSeasonById(seasonId);

        assertEquals(seasonResponseDto, result);
        verify(seasonRepository, times(1)).findById(seasonId);
    }

    @Test
    void testGetSeasonById_NotFound() {
        when(seasonRepository.findById(seasonId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> seasonService.getSeasonById(seasonId));
        verify(seasonRepository, times(1)).findById(seasonId);
    }

    @Test
    void testCreateSeason() {
        when(seasonMapper.toEntity(seasonRequestDto)).thenReturn(season);
        when(seasonRepository.save(season)).thenReturn(season);
        when(seasonMapper.toDto(season)).thenReturn(seasonResponseDto);

        SeasonResponseDto result = seasonService.createSeason(seasonRequestDto);

        assertEquals(seasonResponseDto, result);
        verify(seasonMapper, times(1)).toEntity(seasonRequestDto);
        verify(seasonRepository, times(1)).save(season);
    }

    @Test
    void testUpdateSeason_Success() {
        when(seasonRepository.findById(seasonId)).thenReturn(Optional.of(season));
        when(seasonRepository.save(season)).thenReturn(season);
        when(seasonMapper.toDto(season)).thenReturn(seasonResponseDto);

        SeasonResponseDto result = seasonService.updateSeason(seasonId, seasonRequestDto);

        assertEquals(seasonResponseDto, result);
        verify(seasonRepository, times(1)).findById(seasonId);
        verify(seasonRepository, times(1)).save(season);
    }

    @Test
    void testUpdateSeason_NotFound() {
        when(seasonRepository.findById(seasonId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> seasonService.updateSeason(seasonId, seasonRequestDto));
        verify(seasonRepository, times(1)).findById(seasonId);
    }

    @Test
    void testDeleteSeason_Success() {
        when(seasonRepository.existsById(seasonId)).thenReturn(true);

        assertTrue(seasonService.deleteSeason(seasonId));
        verify(seasonRepository, times(1)).deleteById(seasonId);
    }

    @Test
    void testDeleteSeason_NotFound() {
        when(seasonRepository.existsById(seasonId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> seasonService.deleteSeason(seasonId));
        verify(seasonRepository, never()).deleteById(seasonId);
    }
}
