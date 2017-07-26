package cn.xidian.designpattern;

/**
 * 抽象状态类
 */
abstract class AbstractState {
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    protected abstract void operation1();
    protected abstract void operation2();
}

/**
 * 具体状态一的类
 */
class ConcreteState1 extends AbstractState {
    @Override
    protected void operation1() {
        System.out.println("这是状态一执行的方法");
    }

    @Override
    protected void operation2() {
        super.getContext().setAbstractState(Context.STATE_2);
        super.getContext().doOperation2();
    }
}
/**
 * 具体状态二的类
 */
class ConcreteState2 extends AbstractState {
    @Override
    protected void operation1() {
        super.getContext().setAbstractState(Context.STATE_1);
        super.getContext().doOperation1();
    }

    @Override
    protected void operation2() {
        System.out.println("这是状态二执行的方法");
    }
}

/**
 * 管理状态的容器类
 */
class Context {
    public final static AbstractState STATE_1 = new ConcreteState1();
    public final static AbstractState STATE_2 = new ConcreteState2();

    private AbstractState currentState;

    public void setAbstractState(AbstractState currentState) {
        this.currentState = currentState;
        this.currentState.setContext(this);
    }

    public void doOperation1() {
        this.currentState.operation1();
    }

    public void doOperation2() {
        this.currentState.operation2();
    }
}
/**
 * 文件描述：状态模式测试类
 * Note：状态模式的使用场景：行为随状态的改变而改变的场景；条件、分支判断语句的替代者
 * 创建作者：陈苗
 * 创建时间：2016年5月18日 23:08
 */
public class StatePatternTest {
    public static void main(String[] args) {
        Context context = new Context();
        context.setAbstractState(new ConcreteState1());
        context.doOperation1();
        context.doOperation2();
    }
}
