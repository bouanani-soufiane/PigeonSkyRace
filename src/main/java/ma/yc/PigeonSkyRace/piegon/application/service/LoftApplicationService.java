package ma.yc.PigeonSkyRace.piegon.application.service;

import ma.yc.PigeonSkyRace.competition.domain.ValueObject.Coordinate;
import ma.yc.PigeonSkyRace.piegon.domain.model.valueObject.LoftId;

public interface LoftApplicationService {
    Coordinate geLoftCoordinate(LoftId loftId);
}
