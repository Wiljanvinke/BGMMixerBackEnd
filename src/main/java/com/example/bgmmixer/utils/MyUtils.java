package com.example.bgmmixer.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

public abstract class MyUtils {
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null || propertyName.equals("id"))
                .toArray(String[]::new);
    }

    public static void copyProperties(Object src, Object target) {
        String[] nullPropertyNames = getNullPropertyNames(src);
        BeanUtils.copyProperties(src, target, nullPropertyNames);
        for (String name: nullPropertyNames){
            System.out.println("Ignore copying: " + name);
        }
    }
}
