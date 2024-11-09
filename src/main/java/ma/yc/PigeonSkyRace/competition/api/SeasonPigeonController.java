package ma.yc.PigeonSkyRace.competition.api;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.common.infrastructure.web.ApiResponse;
import ma.yc.PigeonSkyRace.competition.application.dto.request.RegisterToSeasonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonPigeonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonPigeonResponseDto;
import ma.yc.PigeonSkyRace.competition.application.mapping.SeasonMapper;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.Season;
import ma.yc.PigeonSkyRace.competition.domain.service.SeasonPigeonService;
import ma.yc.PigeonSkyRace.competition.domain.service.SeasonService;
import ma.yc.PigeonSkyRace.piegon.application.service.PigeonApplicationService;
import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.PigeonId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/season/register")
@Slf4j
@RequiredArgsConstructor
@Validated
public class SeasonPigeonController {
    private final SeasonPigeonService service;
    private final SeasonService seasonService;
    private final SeasonMapper seasonMapper;
    private final PigeonApplicationService pigeonService;
    private static final Logger logger = LoggerFactory.getLogger(SeasonPigeonController.class);

    @PostMapping("/{seasonId}")
    public ResponseEntity<ApiResponse<SeasonPigeonResponseDto>> registerToSeason(
            @Valid @PathVariable String seasonId,
            @Valid @RequestBody RegisterToSeasonRequestDto dto) {

        Season season = seasonMapper.toEntity(seasonService.getSeasonById(SeasonId.fromString(seasonId)));
        Pigeon pigeon = pigeonService.findPigeonById(PigeonId.fromString(dto.pigeonId()));

        SeasonPigeonResponseDto responseDto = service.RegisterToSeason(new SeasonPigeonRequestDto(pigeon, season));
        ApiResponse<SeasonPigeonResponseDto> response = new ApiResponse<>(
                responseDto,
                "Pigeon Registered to season: " + responseDto.season().getName() + " successfully",
                HttpStatus.CREATED
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



}