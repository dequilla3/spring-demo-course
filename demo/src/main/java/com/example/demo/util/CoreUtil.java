package com.example.demo.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CoreUtil {
  public static Date convertLocalDateToDate(LocalDate localDate) {
    ZoneId defaultZoneId = ZoneId.systemDefault();
    return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
  }
}
