package ma.yc.PigeonSkyRace.piegon.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import ma.yc.PigeonSkyRace.piegon.application.dto.request.LoftRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.LoftResponseDTO;
import ma.yc.PigeonSkyRace.piegon.application.mapper.LoftMapper;
import ma.yc.PigeonSkyRace.piegon.application.service.LoftApplicationService;
import ma.yc.PigeonSkyRace.piegon.domain.model.entity.Loft;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.LoftId;
import ma.yc.PigeonSkyRace.piegon.domain.service.LoftDomainService;
import ma.yc.PigeonSkyRace.piegon.domain.service.LoftNameGenerator;
import ma.yc.PigeonSkyRace.piegon.infrastructure.repository.LoftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultLoftDomainService implements LoftDomainService, LoftApplicationService {

    private final LoftRepository repository;
    private final LoftMapper mapper;
    private final LoftNameGenerator loftNameGenerator;

    @Override
    public boolean existsById(LoftId id) {
        return repository.existsById(id);
    }

    @Override
    public LoftResponseDTO create(LoftRequestDTO dto) {
        Loft loft = mapper.toEntity(dto);
        String uniqueName = generateUniqueLoftName();

        loft.setName(uniqueName);
        return mapper.toDto(repository.save(loft));
    }

    @Override
    public Coordinate geLoftCoordinate(LoftId loftId){
        return repository.getCoordinateById(loftId);
    }


    private String generateUniqueLoftName() {
        String uniqueName;
        do {
            uniqueName = loftNameGenerator.generateUniqueLoftName();
        } while (repository.existsByName(uniqueName));
        return uniqueName;
    }
}
