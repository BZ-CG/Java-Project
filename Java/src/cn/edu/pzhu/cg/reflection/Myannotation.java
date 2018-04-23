package cn.edu.pzhu.cg.reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Myannotation {
	String value() default "我是测试的注解";
}
