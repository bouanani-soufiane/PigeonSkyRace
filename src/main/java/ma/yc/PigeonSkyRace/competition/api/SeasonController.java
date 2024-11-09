package ma.yc.PigeonSkyRace.competition.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.common.infrastructure.web.ApiResponse;
import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonResponseDto;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import ma.yc.PigeonSkyRace.competition.domain.service.SeasonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/season")
@Slf4j
@Validated
@RequiredArgsConstructor
class SeasonController {

    private final SeasonService seasonService;
    @PostMapping
    public ResponseEntity<ApiResponse<SeasonResponseDto>> createSeason ( @Valid @RequestBody SeasonRequestDto season ) {
        SeasonResponseDto createdSeason = seasonService.createSeason(season);
        ApiResponse<SeasonResponseDto> response = new ApiResponse<>(createdSeason, "Season created successfully", HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<SeasonResponseDto>> getAllSeasons () {
        List<SeasonResponseDto> seasons = seasonService.getAllSeasons();
        return new ResponseEntity<>(seasons, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SeasonResponseDto>> updateSeason ( @Valid @PathVariable String id, @Valid @RequestBody SeasonRequestDto seasonRequestDto ) {
        SeasonResponseDto updatedSeason = seasonService.updateSeason(SeasonId.fromString(id), seasonRequestDto);
        ApiResponse<SeasonResponseDto> response = new ApiResponse<>(updatedSeason, "Season updated successfully", HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeason ( @Valid @PathVariable String id ) {
        seasonService.deleteSeason(SeasonId.fromString(id));
        return new ResponseEntity<>("Season Deleted Successfully", HttpStatus.OK);
    }


}