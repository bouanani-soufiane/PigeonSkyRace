package ma.yc.PigeonSkyRace.piegon.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.common.domain.exception.NotFoundException;
import ma.yc.PigeonSkyRace.piegon.application.dto.request.PigeonRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.PigeonResponseDTO;
import ma.yc.PigeonSkyRace.piegon.application.mapper.PigeonMapper;
import ma.yc.PigeonSkyRace.piegon.application.service.PigeonApplicationService;
import ma.yc.PigeonSkyRace.piegon.domain.exception.InvalidLoftException;
import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.LoftId;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.PigeonId;
import ma.yc.PigeonSkyRace.piegon.domain.service.LoftDomainService;
import ma.yc.PigeonSkyRace.piegon.domain.service.PigeonDomainService;
import ma.yc.PigeonSkyRace.piegon.infrastructure.repository.PigeonRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultPigeonDomainService implements PigeonDomainService, PigeonApplicationService {
    private final PigeonRepository repository;
    private final PigeonMapper mapper;
    private final LoftDomainService loftDomainService;

    @Override
    public PigeonResponseDTO create ( PigeonRequestDTO dto ) {
        LoftId loftId = LoftId.fromString(dto.loftId());
        if (!loftDomainService.existsById(loftId)) {
            throw new InvalidLoftException("Loft with ID " + dto.loftId() + " does not exist");
        }
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Pigeon findPigeonById ( PigeonId value ) {
        return repository.findById(value).orElseThrow(() -> new NotFoundException("pigeon", value));
    }
}
