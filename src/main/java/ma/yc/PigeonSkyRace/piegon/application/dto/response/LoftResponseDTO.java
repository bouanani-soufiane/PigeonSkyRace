package ma.yc.PigeonSkyRace.piegon.application.dto.response;

import ma.yc.PigeonSkyRace.piegon.application.dto.request.CoordinateRequestDTO;

public record LoftResponseDTO(
        String id,
        String name,
        CoordinateRequestDTO coordinate,
        String userId,
        String createdDate
) {
}