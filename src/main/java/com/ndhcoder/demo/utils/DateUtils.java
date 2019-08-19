package com.ndhcoder.demo.utils;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtils {
    public static Date getCurrentTimestamp() {
        return new Timestamp(new Date().getTime());
    }
}
