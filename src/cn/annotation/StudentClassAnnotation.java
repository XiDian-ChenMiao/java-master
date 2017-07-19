package cn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 学生类的注解练习
 * @author Administrator
 *
 */
@Target({ElementType.TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface StudentClassAnnotation {
	public String stu_name() default "陈苗";
	public int stu_number() default 13111270;
	public float sum_score() default 80;
}
