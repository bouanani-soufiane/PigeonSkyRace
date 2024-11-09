package ma.yc.PigeonSkyRace.competition.domain.ValueObject;

public record AdmissionPercentage(Double percentage) {

    public AdmissionPercentage {
        if (percentage == null || percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Admission percentage must be between 0 and 100.");
        }
    }
    public AdmissionPercentage(double percentage) {
        this(Double.valueOf(percentage));
    }
}
