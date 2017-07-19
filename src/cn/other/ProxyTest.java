package cn.other;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface IHelloWorld {
    void sayHelloWorld();
}

class HelloWorldImpl implements IHelloWorld {
    @Override
    public void sayHelloWorld() {
        System.out.println("Hello World");
    }
}

/**
 * JDK动态代理实现类
 */
class DynamicProxy implements InvocationHandler {
    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * Processes a method invocation on a proxy instance and returns
     * the result.  This method will be invoked on an invocation handler
     * when a method is invoked on a proxy instance that it is
     * associated with.
     *
     * @param proxy  the proxy instance that the method was invoked on
     * @param method the {@code Method} instance corresponding to
     *               the interface method invoked on the proxy instance.  The declaring
     *               class of the {@code Method} object will be the interface that
     *               the method was declared in, which may be a superinterface of the
     *               proxy interface that the proxy class inherits the method through.
     * @param args   an array of objects containing the values of the
     *               arguments passed in the method invocation on the proxy instance,
     *               or {@code null} if interface method takes no arguments.
     *               Arguments of primitive types are wrapped in instances of the
     *               appropriate primitive wrapper class, such as
     *               {@code java.lang.Integer} or {@code java.lang.Boolean}.
     * @return the value to return from the method invocation on the
     * proxy instance.  If the declared return type of the interface
     * method is a primitive type, then the value returned by
     * this method must be an instance of the corresponding primitive
     * wrapper class; otherwise, it must be a type assignable to the
     * declared return type.  If the value returned by this method is
     * {@code null} and the interface method's return type is
     * primitive, then a {@code NullPointerException} will be
     * thrown by the method invocation on the proxy instance.  If the
     * value returned by this method is otherwise not compatible with
     * the interface method's declared return type as described above,
     * a {@code ClassCastException} will be thrown by the method
     * invocation on the proxy instance.
     * @throws Throwable the exception to throw from the method
     *                   invocation on the proxy instance.  The exception's type must be
     *                   assignable either to any of the exception types declared in the
     *                   {@code throws} clause of the interface method or to the
     *                   unchecked exception types {@code java.lang.RuntimeException}
     *                   or {@code java.lang.Error}.  If a checked exception is
     *                   thrown by this method that is not assignable to any of the
     *                   exception types declared in the {@code throws} clause of
     *                   the interface method, then an
     *                   {@link UndeclaredThrowableException} containing the
     *                   exception that was thrown by this method will be thrown by the
     *                   method invocation on the proxy instance.
     * @see UndeclaredThrowableException
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("called before proxy method");
    }

    private void after() {
        System.out.println("called after proxy method");
    }
}

/**
 * CGLIB动态代理实现类
 */
class CglibDynamicProxy implements MethodInterceptor {

    private static CglibDynamicProxy proxy = new CglibDynamicProxy();

    private CglibDynamicProxy() {}

    public static CglibDynamicProxy getInstance() {
        return proxy;
    }

    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }

    private void before() {
        System.out.println("called before proxy method");
    }

    private void after() {
        System.out.println("called after proxy method");
    }
}

/**
 * 文件描述：测试代理的实现
 * 创建作者：陈苗
 * 创建时间：2016/11/30 20:28
 */
public class ProxyTest {
    /**
     * 采用JDK动态代理的方式
     */
    @Test
    public void jdkDynamicProxy() {
        IHelloWorld helloWorld = new DynamicProxy(new HelloWorldImpl()).getProxy();
        helloWorld.sayHelloWorld();
    }

    /**
     * 采用CgLib的动态代理方式
     */
    @Test
    public void cglibDynamicProxy() {
        HelloWorldImpl helloWorld = CglibDynamicProxy.getInstance().getProxy(HelloWorldImpl.class);
        helloWorld.sayHelloWorld();
    }
}
