package com.zheng.springboot.configuration.validate;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;

/**
 * @Author zhenglian
 * @Date 2018/5/15 18:13
 */
public class CustomConversionService implements ConversionService {
    @Override
    public boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType) {
        return false;
    }

    @Override
    public boolean canConvert(@Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
        return false;
    }

    @Nullable
    @Override
    public <T> T convert(@Nullable Object source, Class<T> targetType) {
        return null;
    }

    @Nullable
    @Override
    public Object convert(@Nullable Object source, @Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
        return null;
    }
}
