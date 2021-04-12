package com.yl.admin.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/10
 */
public class DateUtil {

    public static Date str2Day(String time) {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf2.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
