package ma.yc.PigeonSkyRace.piegon.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.yc.PigeonSkyRace.common.infrastructure.web.ApiResponse;
import ma.yc.PigeonSkyRace.common.infrastructure.web.ResponseApi;
import ma.yc.PigeonSkyRace.piegon.application.dto.request.PigeonRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.PigeonResponseDTO;
import ma.yc.PigeonSkyRace.piegon.domain.service.PigeonDomainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pigeons")
@RequiredArgsConstructor
public class PigeonController {

    private final PigeonDomainService service;

    @PostMapping
    public ResponseEntity<ResponseApi<PigeonResponseDTO>> create( @Valid @RequestBody PigeonRequestDTO dto) {
        PigeonResponseDTO createdPigeon = service.create(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseApi.created(createdPigeon, "Pigeon created successfully"));
    }
}