package ma.yc.PigeonSkyRace.piegon.domain.service;

import ma.yc.PigeonSkyRace.piegon.application.dto.request.LoftRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.LoftResponseDTO;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.LoftId;

public interface LoftDomainService {
    LoftResponseDTO create ( LoftRequestDTO dto );
    boolean existsById( LoftId id);
}
