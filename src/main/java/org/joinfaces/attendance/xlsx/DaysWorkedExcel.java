package org.joinfaces.attendance.xlsx;

import com.sargeraswang.util.ExcelUtil.ExcelUtil;
import org.joinfaces.attendance.dto.Day;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.time.LocalDate;
import java.util.*;

public class DaysWorkedExcel {

    public static File mapDaysWorkedToExcelOutputStream(Map<LocalDate, Day> daysWorked) throws IOException {
        List<Map<String,Object>> list = new ArrayList<>();
        daysWorked.forEach( (localDate, day) -> {
            Map<String,Object> mapLine =new LinkedHashMap<>();
            mapLine.put("date", localDate);
            mapLine.put("matin", day.isMorning()? "OUI": "NON");
            mapLine.put("après-midi",day.isAfternoon()? "OUI": "NON");
            list.add(mapLine);
        });
        Map<String,String> map0 = new LinkedHashMap<>();
        map0.put("date", "jour");
        map0.put("matin", "matin");
        map0.put("après-midi","après-midi");
        File f= Files.createTempFile(UUID.randomUUID().toString(),".xls").toFile();
        OutputStream out = new FileOutputStream(f);
        ExcelUtil.exportExcel(map0,list, out );
        out.close();
        return f;
    }
}
