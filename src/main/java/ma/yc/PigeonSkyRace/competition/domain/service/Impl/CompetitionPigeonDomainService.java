package ma.yc.PigeonSkyRace.competition.domain.service.Impl;

import lombok.RequiredArgsConstructor;
import ma.yc.PigeonSkyRace.competition.application.dto.request.CompetitionPigeonRequestDto;
import ma.yc.PigeonSkyRace.competition.application.dto.response.CompetitionPigeonResponseDto;
import ma.yc.PigeonSkyRace.competition.application.mapping.CompetitionPigeonMapper;
import ma.yc.PigeonSkyRace.competition.domain.Exception.FailedToRegister;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import ma.yc.PigeonSkyRace.competition.domain.entity.Competition;
import ma.yc.PigeonSkyRace.competition.domain.entity.CompetitionPigeon;
import ma.yc.PigeonSkyRace.competition.domain.entity.SeasonPigeon;
import ma.yc.PigeonSkyRace.competition.infrastructure.repository.CompetitionPigeonRepository;
import ma.yc.PigeonSkyRace.competition.domain.service.CompetitionPigeonService;
import ma.yc.PigeonSkyRace.piegon.application.service.LoftApplicationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetitionPigeonDomainService implements CompetitionPigeonService {

    private final CompetitionPigeonRepository repository;
    private final CompetitionPigeonMapper mapper;
    private final LoftApplicationService applicationService;

    @Override
    public CompetitionPigeonResponseDto registerToCompetition(SeasonPigeon seasonPigeon, Competition competition) {

        if (!competition.getSeasonId().equals(seasonPigeon.getSeason().getId())) {
            throw new FailedToRegister("This pigeon is not registered in the same season as the competition.");
        }

        CompetitionPigeonRequestDto requestDto = new CompetitionPigeonRequestDto(seasonPigeon, competition);
        Integer countRegistration = repository.countByCompetition(competition);

        repository.findBySeasonPigeonAndCompetition(
                requestDto.seasonPigeon(), requestDto.competition()
        ).ifPresent(p -> {
            throw new FailedToRegister("This pigeon is already registered in the competition.");
        });

        if(countRegistration.equals(competition.getMaxPigeons()) || competition.getDateStart().equals(LocalDateTime.now().minusHours(4))) {

            throw new FailedToRegister("The competition has reached its maximum number of registered pigeons.");
        }


        CompetitionPigeon savedCompetitionPigeon = repository.save(mapper.toEntity(requestDto));
        return mapper.toDto(savedCompetitionPigeon);
    }

    @Override
    public List<CompetitionPigeonResponseDto> getAllCompetitionsPigeons(){
        List<CompetitionPigeon> competitionPigeons = repository.findAll();
       return competitionPigeons.stream().map(mapper::toDto).collect(Collectors.toList());
    }


    private Coordinate calculateAverageCoordinate(List<CompetitionPigeon> competitionPigeons) {
        double sumLatitude = 0;
        double sumLongitude = 0;
        int count = competitionPigeons.size();

        for (CompetitionPigeon pigeon : competitionPigeons) {
            Coordinate pigeonCoordinate = applicationService.geLoftCoordinate(pigeon.getSeasonPigeon().getPigeon().getLoft());
            sumLatitude += pigeonCoordinate.latitude();
            sumLongitude += pigeonCoordinate.longitude();
        }

        return new Coordinate(sumLatitude / count, sumLongitude / count);
    }
    

}
