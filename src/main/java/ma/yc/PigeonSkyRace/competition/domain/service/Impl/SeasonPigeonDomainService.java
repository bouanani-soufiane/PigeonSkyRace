package ma.yc.PigeonSkyRace.competition.domain.service.Impl;

import lombok.RequiredArgsConstructor;
import ma.yc.PigeonSkyRace.common.domain.exception.NotFoundException;
import ma.yc.PigeonSkyRace.competition.application.dto.request.SeasonPigeonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.SeasonPigeonResponseDto;
import ma.yc.PigeonSkyRace.competition.application.mapping.SeasonPigeonMapper;
import ma.yc.PigeonSkyRace.competition.domain.Exception.FailedToRegister;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.SeasonPigeonId;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;
import ma.yc.PigeonSkyRace.competition.infrastructure.repository.SeasonPigeonRepository;
import ma.yc.PigeonSkyRace.competition.domain.service.SeasonPigeonService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeasonPigeonDomainService implements SeasonPigeonService {
    private final SeasonPigeonRepository repository;
    private final SeasonPigeonMapper mapper;


    @Override
    public SeasonPigeonResponseDto RegisterToSeason(SeasonPigeonRequestDto seasonPigeonRequestDto) {
        repository.findByPigeonAndSeason(
                seasonPigeonRequestDto.pigeon(), seasonPigeonRequestDto.season()
        ).ifPresent(existing -> {
            throw new FailedToRegister("This pigeon is already registered in the season.");
        });

        SeasonPigeon savedSeasonPigeon = repository.save(mapper.toEntity(seasonPigeonRequestDto));

        return mapper.toDto(savedSeasonPigeon);
    }

    @Override
    public SeasonPigeonResponseDto getSeasonById(SeasonPigeonId seasonPigeonId){
        SeasonPigeon seasonPigeon = repository.findById(seasonPigeonId).orElseThrow(() ->  new NotFoundException("seasonPigeon", seasonPigeonId));
        return mapper.toDto(seasonPigeon);
    }



}
