package ma.yc.PigeonSkyRace.user.infrastructure.repository;

import ma.yc.PigeonSkyRace.user.domain.model.aggregate.User;
import ma.yc.PigeonSkyRace.user.domain.model.valueObject.UserId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, UserId> {
    Optional<User> findUserByEmail ( String email );

    boolean existsByEmail ( String email );

    boolean existsByUsername ( String username );
}
