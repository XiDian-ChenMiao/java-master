package cn.xidian.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段的注解
 * @ClassName: FieldAnnotation 
 * @author 陈苗 
 * @date 2016年4月16日 下午4:34:16
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldAnnotation {
	public String value() default "Field";
}
