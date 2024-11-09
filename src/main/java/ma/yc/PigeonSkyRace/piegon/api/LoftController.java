package ma.yc.PigeonSkyRace.piegon.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.yc.PigeonSkyRace.common.infrastructure.web.ResponseApi;
import ma.yc.PigeonSkyRace.piegon.application.dto.request.LoftRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.LoftResponseDTO;
import ma.yc.PigeonSkyRace.piegon.domain.service.impl.DefaultLoftDomainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lofts")
@RequiredArgsConstructor
@Validated
class LoftController {

    private final DefaultLoftDomainService service;

    @PostMapping()
    public ResponseEntity<ResponseApi<LoftResponseDTO>> createLoft ( @Valid @RequestBody LoftRequestDTO loftRequest ) {
        LoftResponseDTO response = service.create(loftRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseApi.created(response, "Loft created successfully"));
    }
}
