package ma.yc.PigeonSkyRace.user.application.dto.response;

import ma.yc.PigeonSkyRace.user.domain.enums.Role;


public record AuthResponseDTO(String id, String name, String email, String username, Role role) {
}