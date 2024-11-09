package ma.yc.PigeonSkyRace.user.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import ma.yc.PigeonSkyRace.user.domain.enums.Role;

@Builder
public record RegisterRequestDTO(@NotBlank(message = "Name is required") String name,

                                 @NotBlank(message = "Username is required") String username,

                                 @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,

                                 @NotBlank(message = "Password is required") String password,

                                 Role role) {
}