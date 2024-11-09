package ma.yc.PigeonSkyRace.piegon.application.service;

import ma.yc.PigeonSkyRace.piegon.domain.model.aggregate.Pigeon;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.PigeonId;

public interface PigeonApplicationService {
    Pigeon findPigeonById ( PigeonId value );
}
