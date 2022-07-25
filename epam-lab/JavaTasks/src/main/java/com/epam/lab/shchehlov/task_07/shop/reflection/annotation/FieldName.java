package com.epam.lab.shchehlov.task_07.shop.reflection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldName {

    /**
     * Returns user-friendly field name from string resources
     *
     * @return user-friendly field name from string resources
     */
    String value();
}
