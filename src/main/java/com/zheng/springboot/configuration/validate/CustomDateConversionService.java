package com.zheng.springboot.configuration.validate;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author zhenglian
 * @Date 2018/5/15 18:13
 */
public class CustomDateConversionService implements ConversionService {
    private String pattern = "yyyy-MM-dd HH:mm:ss";
    
    @Override
    public boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType) {
        if (sourceType == String.class && targetType == Date.class) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canConvert(@Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
        return canConvert(sourceType.getType(), targetType.getType());
    }

    @Nullable
    @Override
    public <T> T convert(@Nullable Object source, Class<T> targetType) {
        String time = (String) source;
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        T date = null;
        try {
            date = (T) sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Nullable
    @Override
    public Object convert(@Nullable Object source, @Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
        return convert(source, targetType.getType());
    }
}
