package com.qiyue.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UniqueId {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JANUARY, 1, 0, 0, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String day = sdf.format(calendar.getTime());
        System.out.println(day);
        System.out.println(calendar.getTime().getTime());
    }
}
