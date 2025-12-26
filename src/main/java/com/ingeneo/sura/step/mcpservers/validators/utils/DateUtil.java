package com.ingeneo.sura.step.mcpservers.validators.utils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static String converDate(String fecha){


        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime localDateTime = LocalDateTime.parse(fecha, inputFormatter);

        ZoneId zoneId = ZoneId.of("America/Bogota");

        OffsetDateTime offsetDateTime = localDateTime.atZone(zoneId).toOffsetDateTime();

        String input = offsetDateTime.toString();
        if (input.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}[-+]\\d{2}:\\d{2}")) {
            return input.replaceFirst(
                    "T(\\d{2}:\\d{2})([-+]\\d{2}:\\d{2})",
                    "T$1:00.000$2"
            );
        }

        return input;

    }

    public static String converDate(Date fecha){


        ZoneId zoneId = ZoneId.of("America/Bogota");


        OffsetDateTime offsetDateTime = fecha.toInstant().atZone(zoneId).toOffsetDateTime();

        String input = offsetDateTime.toString();
        if (input.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}[-+]\\d{2}:\\d{2}")) {
            return input.replaceFirst(
                    "T(\\d{2}:\\d{2})([-+]\\d{2}:\\d{2})",
                    "T$1:00.000$2"
            );
        }

        return input;
    }
}
