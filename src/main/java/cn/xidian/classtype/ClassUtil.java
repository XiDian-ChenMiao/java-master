package cn.xidian.classtype;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：ClassUtil
 * 类描述：类类型的工具类
 * 创建时间：2015年8月31日 上午11:05:01
 * 创建人： 陈苗
 */
public class ClassUtil {
	/**
	 * 得到所有类中方法的信息
	 * @param obj
	 */
	public static void getMethodsMessage(Object obj){
		//得到参数的类类型
		Class<?> objClassType = obj.getClass();
		System.out.println("参数类型为:" + objClassType.getName());
		//得到所有类类型的方法
		Method[] methods = objClassType.getMethods();
		int methodsLength = methods.length;
		System.out.println("方法个数为:" + methodsLength);
		
		for(int i = 0;i < methodsLength;i++){
			//得到所有方法返回值的类型
			Class<?> returnValue = methods[i].getReturnType();
			System.out.print((i+1) + " " + returnValue.getSimpleName() + " ");
			//得到所有方法的名称
			System.out.print(methods[i].getName() + "(");
			//得到所有方法参数的类类型
			Class<?>[] paraTypes = methods[i].getParameterTypes();
			for(int j = 0;j < paraTypes.length;j++){
				System.out.print(paraTypes[j].getSimpleName() + ",");
			}
			System.out.println(")");
		}
	}
	/**
	 * 得到对象所对应的属性信息
	 * @param obj
	 */
	public static void getFieldsMessage(Object obj){
		Class<?> objClassType = obj.getClass();
		Field[] fields = objClassType.getDeclaredFields();
		int fieldLength = fields.length;
		for(int i = 0;i < fieldLength;i++){
			System.out.println((i+1) + " " + fields[i].getType().getSimpleName() + " " + fields[i].getName());
		}
	}
	/**
	 * 得到所有的构造器信息
	 * @param obj
	 */
	public static void getConstructor(Object obj) {
		Class<?> objConstructer = obj.getClass();
		Constructor<?>[] constructer = objConstructer.getConstructors();
		int conLength = constructer.length;
		for(int i = 0;i < conLength;i++){
			System.out.print((i+1) + " " + constructer[i].getName() + "(");
			Class<?>[] conParameters = constructer[i].getParameterTypes();
			for(int j = 0;j < conParameters.length;j++){
				System.out.print(conParameters[j].getSimpleName() + ",");
			} 
			System.out.println(")");
		}
	}
	public static void methodsReflect(Object obj,String meth,Class<?>[] classes,Object[] param){
		Class<?> objClass = obj.getClass();
		try {
			Method method = objClass.getMethod(meth, classes);
			method.invoke(obj,param);
		} catch(NoSuchMethodException e){
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} 
	}
}
