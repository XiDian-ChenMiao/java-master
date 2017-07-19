package cn.designpattern;

/**
 * 解释器上下文类
 */
class InterpreterContext {
    private int preNumber;
    private int nextNumber;

    /**
     * 构造函数
     * @param preNumber
     * @param nextNumber
     */
    public InterpreterContext(int preNumber, int nextNumber) {
        this.preNumber = preNumber;
        this.nextNumber = nextNumber;
    }

    public int getPreNumber() {
        return preNumber;
    }

    public int getNextNumber() {
        return nextNumber;
    }

}
/**
 * 抽象表达式类
 */
abstract class Expression {
    protected abstract int interpreter(InterpreterContext context);
}

/**
 * 对表达式执行相加操作
 */
class PlusOperation extends Expression {

    @Override
    protected int interpreter(InterpreterContext context) {
        return context.getPreNumber() + context.getNextNumber();
    }
}
/**
 * 对表达式执行相减操作
 */
class MinusOperation extends Expression {

    @Override
    protected int interpreter(InterpreterContext context) {
        return context.getPreNumber() - context.getNextNumber();
    }
}
/**
 * 文件描述：解释器模式测试类
 * 创建作者：陈苗
 * 创建时间：2016年5月19日 15:21
 */
public class InterpreterPatternTest {
    public static void main(String[] args) {
        System.out.println(new MinusOperation().interpreter(new InterpreterContext(new PlusOperation().interpreter(new InterpreterContext(9,2)),8)));
    }
}
