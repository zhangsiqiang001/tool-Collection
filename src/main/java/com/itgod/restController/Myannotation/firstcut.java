package com.itgod.restController.Myannotation;

import java.lang.annotation.*;

/**
 * 第一个注解
 * @author 张思强
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface firstcut {
    String Value() default "first one";
}
