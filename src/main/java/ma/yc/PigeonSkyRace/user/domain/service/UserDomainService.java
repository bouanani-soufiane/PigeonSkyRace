package ma.yc.PigeonSkyRace.user.domain.service;

import ma.yc.PigeonSkyRace.user.application.dto.request.LoginRequestDTO;
import ma.yc.PigeonSkyRace.user.application.dto.request.RegisterRequestDTO;
import ma.yc.PigeonSkyRace.user.application.dto.response.AuthResponseDTO;
import ma.yc.PigeonSkyRace.user.domain.model.valueObject.UserId;

import java.util.List;

public interface UserDomainService {
    AuthResponseDTO registerUser ( RegisterRequestDTO registerRequest );

    AuthResponseDTO login ( LoginRequestDTO loginRequest );

    AuthResponseDTO findById ( UserId id );

    List<AuthResponseDTO> findAll ();
}
