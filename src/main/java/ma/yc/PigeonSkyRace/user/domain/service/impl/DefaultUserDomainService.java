package ma.yc.PigeonSkyRace.user.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.PigeonSkyRace.common.application.service.PasswordEncoder;
import ma.yc.PigeonSkyRace.common.domain.exception.NotFoundException;
import ma.yc.PigeonSkyRace.user.application.dto.request.LoginRequestDTO;
import ma.yc.PigeonSkyRace.user.application.dto.request.RegisterRequestDTO;
import ma.yc.PigeonSkyRace.user.application.dto.response.AuthResponseDTO;
import ma.yc.PigeonSkyRace.user.application.mapper.UserMapper;
import ma.yc.PigeonSkyRace.user.domain.exception.InvalidCredentialsException;
import ma.yc.PigeonSkyRace.user.domain.exception.UserAlreadyExistsException;
import ma.yc.PigeonSkyRace.user.domain.exception.UserRegistrationException;
import ma.yc.PigeonSkyRace.user.domain.model.aggregate.User;
import ma.yc.PigeonSkyRace.user.domain.model.valueObject.UserId;
import ma.yc.PigeonSkyRace.user.domain.service.UserDomainService;
import ma.yc.PigeonSkyRace.user.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultUserDomainService implements UserDomainService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public AuthResponseDTO registerUser ( RegisterRequestDTO registerRequest ) {
        validateNewUser(registerRequest);

        User user = mapper.toEntity(registerRequest);
        if (user == null) {
            throw new UserRegistrationException("Error mapping user data");
        }

        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        User savedUser = repository.save(user);

        log.info("User successfully registered: {}", savedUser.getEmail());
        return mapper.toDto(savedUser);
    }

    public AuthResponseDTO login ( LoginRequestDTO loginRequest ) {
        User user = repository.findUserByEmail(loginRequest.email()).orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return mapper.toDto(user);
    }

    @Override
    public AuthResponseDTO findById ( UserId id ) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException("User", id));
        return mapper.toDto(user);
    }

    @Override
    public List<AuthResponseDTO> findAll () {
        List<User> users = repository.findAll();
        return users
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    private void validateNewUser ( RegisterRequestDTO registerRequest ) {
        if (registerRequest == null) {
            throw new UserRegistrationException("User data cannot be null");
        }

        if (repository.existsByEmail(registerRequest.email())) {
            throw new UserAlreadyExistsException("Email already exists: " + registerRequest.email());
        }
        if (repository.existsByUsername(registerRequest.username())) {
            throw new UserAlreadyExistsException("Username already exists: " + registerRequest.username());
        }
    }
}
