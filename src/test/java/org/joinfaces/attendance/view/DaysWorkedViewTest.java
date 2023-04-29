package org.joinfaces.attendance.view;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DaysWorkedViewTest {

    private RestTemplate restTemplate;

    @Test
    public void testRestTemplate(){
        restTemplate = new RestTemplate();
        LocalDate currentDate = LocalDate.now().minusDays(5L);
        String apiHolidays = "https://calendrier.api.gouv.fr/jours-feries/metropole/";
        ResponseEntity<Map> response
                = restTemplate.getForEntity(apiHolidays + +currentDate.getYear()+ ".json", Map.class);
        Set<LocalDate> holidays = (Set<LocalDate>) response.getBody().keySet()
                .stream()
                .map(key ->
                        LocalDate.parse(key.toString(), DateTimeFormatter.ISO_LOCAL_DATE)
                ).collect(Collectors.toUnmodifiableSet());
        for (LocalDate holiday : holidays) {
            System.out.println(holiday);
        }

    }

}