package ma.yc.PigeonSkyRace.competition.domain.Exception;

public class FailedToRegister extends RuntimeException {
    public FailedToRegister(String message) {
        super(message);
    }
}
