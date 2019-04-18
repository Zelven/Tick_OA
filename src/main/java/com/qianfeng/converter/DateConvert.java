package com.qianfeng.converter;


import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert implements Converter<String, Date> {
    private SimpleDateFormat[] sdfs = new SimpleDateFormat[]{
            new SimpleDateFormat("yyyy-MM-dd"),
            new SimpleDateFormat("yyyyMMdd"),
            new SimpleDateFormat("yyyy年MM月dd日")
    };

    @Override
    public Date convert(String info) {
        if(info == null || info.isEmpty()){
            return null;
        }
        // 遍历格式，如果可以转换，直接返回，不过格式不匹配，进行下次转换
        for (SimpleDateFormat sdf : sdfs) {
            try {
                return sdf.parse(info);
            } catch (ParseException e) {
                //e.printStackTrace();
                continue;
            }
        }

        // 如果所有格式都不满足，返回null
        return null;
    }
}
