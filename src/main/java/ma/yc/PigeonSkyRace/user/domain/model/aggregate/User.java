package ma.yc.PigeonSkyRace.user.domain.model.aggregate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import ma.yc.PigeonSkyRace.piegon.domain.entity.Loft;
import ma.yc.PigeonSkyRace.user.domain.enums.Role;
import ma.yc.PigeonSkyRace.user.domain.model.valueObject.UserId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Document(collection = "users")
public class User {

    @Id
    private UserId id;

    @NotBlank
    private String name;

    @NotBlank
    @Indexed(unique = true)
    private String username;

    @Email
    @Indexed(unique = true)
    private String email;

    @NotBlank
    private String password;

    private Role role;

    @DBRef
    private List<Loft> lofts  = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdDate;

    public User() {
        this.id = new UserId();
    }
}
