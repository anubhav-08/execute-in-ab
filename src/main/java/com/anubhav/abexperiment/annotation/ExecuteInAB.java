package com.anubhav.abexperiment.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExecuteInAB {
  String value();
  boolean generateIdentifierFromArgs() default false;
  String identifier() default "";
  boolean stickiness() default false;
}
