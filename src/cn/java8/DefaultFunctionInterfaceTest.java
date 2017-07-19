package cn.java8;

import com.google.common.base.Supplier;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface DefaultFunction {
    default String defaultFunction() {
        return "default function";
    }

    static String staticFunction() {
        return "static function";
    }
}
class DefaultFunctionImpl implements DefaultFunction {

}

class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行了动态代理");
        Object result = method.invoke(target, args);
        return result;
    }
}

/**
 * 文件描述：Java新特性之接口的默认方法和静态方法
 * 创建作者：陈苗
 * 创建时间：2017/3/7 17:18
 */
public class DefaultFunctionInterfaceTest {
    public static void main(String[] args) {

        DefaultFunction df = new DefaultFunctionImpl();
        DefaultFunction function = new DynamicProxy(df).getProxy();
        System.out.println(function.defaultFunction());
        System.out.println(DefaultFunction.staticFunction());
    }
}
