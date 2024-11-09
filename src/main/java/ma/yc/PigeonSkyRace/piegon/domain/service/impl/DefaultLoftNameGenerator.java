package ma.yc.PigeonSkyRace.piegon.domain.service.impl;

import ma.yc.PigeonSkyRace.piegon.domain.service.LoftNameGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class DefaultLoftNameGenerator implements LoftNameGenerator {

    @Override
    public String generateUniqueLoftName () {
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
        String seconds = LocalTime.now().format(DateTimeFormatter.ofPattern("ss"));
        String uniqueSuffix = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
        return String.format("LOFT-%s-%s-%s",  seconds, year, uniqueSuffix);
    }
}
