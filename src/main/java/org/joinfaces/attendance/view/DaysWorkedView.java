package org.joinfaces.attendance.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.joinfaces.attendance.dto.Day;
import org.joinfaces.attendance.xlsx.DaysWorkedExcel;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Component
@SessionScoped
public class DaysWorkedView implements Serializable{

    @Inject
    private RestTemplate restTemplate;
    private List<LocalDate> daysToWork;
    private Map<LocalDate, Day> daysWorked;
    private TextStyle textStyle;
    private Locale locale;

    @PostConstruct
    private void init(){
        textStyle = TextStyle.FULL;
        locale = Locale.FRENCH;
        daysWorked = new HashMap<>(25);
        daysToWork= new ArrayList<>(25);
        LocalDate currentDate = LocalDate.now().minusDays(5L);
        String apiHolidays = "https://calendrier.api.gouv.fr/jours-feries/metropole/";
        ResponseEntity<Map> response
                = restTemplate.getForEntity(apiHolidays + +currentDate.getYear()+ ".json", Map.class);
        Set<LocalDate> holidays = (Set<LocalDate>) response.getBody().keySet()
                .stream()
                .map(key ->
                        LocalDate.parse(key.toString(), DateTimeFormatter.ISO_LOCAL_DATE)
                ).collect(Collectors.toUnmodifiableSet());
        for (int i = 1; i <= currentDate.getMonth().maxLength() ; i++) {
            LocalDate dayOfMonth = currentDate.withDayOfMonth(i);
            if(dayOfMonth.getDayOfWeek() != DayOfWeek.SATURDAY
                    && dayOfMonth.getDayOfWeek() != DayOfWeek.SUNDAY
                    && !holidays.contains(holidays)){
                daysToWork.add(dayOfMonth);
                daysWorked.put(dayOfMonth,new Day());
            }
        }
    }

    public StreamedContent download() throws IOException {
        File file = DaysWorkedExcel.mapDaysWorkedToExcelOutputStream(daysWorked);

        StreamedContent streamedContent =
                DefaultStreamedContent.builder()
                        .name(daysToWork.get(0) +"_" + daysToWork.get(daysToWork.size()-1)+".xls")
                        .contentType("application/vnd.ms-excel")
                        .stream(() -> {
                            try {
                                return new ByteArrayInputStream(new FileInputStream(file).readAllBytes());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .build();
        return streamedContent;
    }
}
