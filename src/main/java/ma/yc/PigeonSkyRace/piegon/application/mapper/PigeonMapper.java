package ma.yc.PigeonSkyRace.piegon.application.mapper;

import ma.yc.PigeonSkyRace.piegon.application.dto.request.PigeonRequestDTO;
import ma.yc.PigeonSkyRace.piegon.application.dto.response.PigeonResponseDTO;
import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.LoftId;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.PigeonId;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PigeonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "loft", source = "loftId", qualifiedByName = "stringToLoftId")
    Pigeon toEntity ( PigeonRequestDTO dto );


    @Mapping(source = "id", target = "id", qualifiedByName = "pigeonIdToString")
    @Mapping(source = "loft", target = "loftId", qualifiedByName = "loftIdToString")
    @Mapping(source = "createdDate", target = "createdDate")
    PigeonResponseDTO toDto( Pigeon pigeon);

    @Named("pigeonIdToString")
    default String pigeonIdToString ( PigeonId id) {
        return id != null ? id.toHexString() : null;
    }

    @Named("loftIdToString")
    default String loftIdToString ( LoftId id) {
        return id != null ? id.toHexString() : null;
    }


    @Named("stringToLoftId")
    default LoftId stringToLoftId ( String loftId ) {
        if (loftId == null) {
            return null;
        }
        return new LoftId(new ObjectId(loftId));
    }

}
