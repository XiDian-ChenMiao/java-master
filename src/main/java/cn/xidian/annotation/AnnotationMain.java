package cn.xidian.annotation;

/**
 * 注解测试类
 * NOTE:
 * 	四个重要的元注解：
 * 	（1）@Target:目标注解。该注解表明该定义注解的使用范围
 * 		ElementType.TYPE：接口、类、，枚举和注解
 * 		ElementType.FIELD：字段、Enum枚举常量
 * 		ElementType.METHOD：方法
 * 		ElementType.PARAMETER：方法参数
 * 		ElementType.CONSTRUCTOR：构造方法
 * 		ElementType.LOCAL_VARIABLE：局部变量
 * 		ElementType.ANNOTATION_TYPE：注解
 * 		ElementType.PACKAGE：包
 * 	（2）@Retention:保留政策。该注解定义JAVA编译时的保留方式
 * 		RetentionPolicy.SOURCE :仅存于源码中，编译[.class]时舍弃
 * 		RetentionPolicy.CLASS :编译于.class中，但是无法运行时使用
 * 		RetentionPolicy.RUNTIME : 编译于[.class]文件中，并且可以通过反射获取
 * 	（3）@Document:该注解将用于JAVADOC
 * 	（4）@Inherited:子类可以继承父类的该注解
 * @ClassName: AnnotationMain 
 * @author 陈苗 
 * @date 2016年2月18日 下午11:44:13
 */
public class AnnotationMain {
	public static void main(String[] args) throws Exception{
		StudentClassAnnotation sca = Student.class.getAnnotation(StudentClassAnnotation.class);
		System.out.println("stu_name:" + sca.stu_name() + ",stu_number:" + sca.stu_number());
		MethodAnntation ma = Student.class.getDeclaredMethod("getStu_name").getAnnotation(MethodAnntation.class); 
		System.out.println("methodName:" + ma.methodName() + ",methodMeanning:" + ma.methodMeanning());
		FieldAnnotation fa = Student.class.getDeclaredField("stu_name").getAnnotation(FieldAnnotation.class);
		System.out.println("Field Value:" + fa.value());
	}
}
