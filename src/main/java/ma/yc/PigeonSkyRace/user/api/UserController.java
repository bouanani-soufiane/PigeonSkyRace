package ma.yc.PigeonSkyRace.user.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.common.infrastructure.web.ResponseApi;
import ma.yc.PigeonSkyRace.user.application.dto.request.LoginRequestDTO;
import ma.yc.PigeonSkyRace.user.application.dto.request.RegisterRequestDTO;
import ma.yc.PigeonSkyRace.user.application.dto.response.AuthResponseDTO;
import ma.yc.PigeonSkyRace.user.domain.model.valueObject.UserId;
import ma.yc.PigeonSkyRace.user.domain.service.UserDomainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {

    private final UserDomainService service;

    @PostMapping("/register")
    public ResponseEntity<ResponseApi<AuthResponseDTO>> registerUser ( @Valid @RequestBody RegisterRequestDTO registerRequest ) {
        AuthResponseDTO authResponse = service.registerUser(registerRequest);
        log.info("User successfully registered: {}", registerRequest.email());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseApi.created(authResponse, "user registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseApi<AuthResponseDTO>> login ( @Valid @RequestBody LoginRequestDTO loginRequest ) {
        AuthResponseDTO authResponse = service.login(loginRequest);
        log.info("User successfully logged in: {}", loginRequest.email());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseApi.created(authResponse, "user logged in successfully"));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<AuthResponseDTO>> findById ( @Valid @PathVariable String id ) {
        AuthResponseDTO authResponse = service.findById(UserId.fromString(id));
        log.info("User found with ID: {}", id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseApi.success(authResponse, String.format("User with ID %s found", id)));
    }

    @GetMapping
    public ResponseEntity <ResponseApi<List<AuthResponseDTO>>> findAll () {
        List<AuthResponseDTO> authResponse = service.findAll();
        log.info("Retrieved list of all users");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseApi.success(authResponse, "list of users "));
    }

}
