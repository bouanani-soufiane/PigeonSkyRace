package ma.yc.PigeonSkyRace.piegon.domain.service;

import ma.yc.PigeonSkyRace.piegon.application.dto.request.PigeonRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.PigeonResponseDTO;

public interface PigeonDomainService {
    PigeonResponseDTO create ( PigeonRequestDTO dto );

}
