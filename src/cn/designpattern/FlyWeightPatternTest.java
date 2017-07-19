package cn.designpattern;

import java.util.Hashtable;
import java.util.Map;

/**
 * 抽象享元角色接口
 */
interface FlyWeight {
    void operation(String state);
}

/**
 * 具体享元类
 */
class ConcreteFlyWeight implements FlyWeight {
    private Character internalState = null;//内蕴状态

    /**
     * 内蕴状态不能由外部改变，只能通过由构造器直接传入并且不能修改
     * @param internalState
     */
    public ConcreteFlyWeight(Character internalState) {
        this.internalState = internalState;
    }

    @Override
    public void operation(String state) {
        System.out.println("内部状态为：" + this.internalState);
        System.out.println("外部状态为：" + state);
    }
}

/**
 * 享元工厂类：客户端不能将具体享元类实例化，必须通过工厂对象，利用一个工厂方法得到享元对象。一般而言，享元工厂在系统中只有一个，因此可以设置为单例。
 */
class FlyWeightFactory {
    private static final FlyWeightFactory factory = new FlyWeightFactory();
    private Map<Character,FlyWeight> flyWeights = new Hashtable<Character, FlyWeight>();
    private FlyWeightFactory() {}

    /**
     * 外部获取工厂对象的方法
     * @return
     */
    public static FlyWeightFactory getFactory() {
        return factory;
    }

    /**
     * 获取享元实例的工厂方法
     * @param state
     * @return
     */
    public FlyWeight factory(Character state) {
        FlyWeight flyWeight = flyWeights.get(state);
        if(flyWeight == null) {
            flyWeight = new ConcreteFlyWeight(state);
            flyWeights.put(state,flyWeight);
        }
        return flyWeight;
    }
}
/**
 * 文件描述：享元模式测试类
 * Note：享元模式的结构
 *  享元模式采用一个共享来避免大量拥有相同内容对象的开销。这种开销最常见、最直观的就是内存的损耗。享元对象能做到共享的关键是区分内蕴状态和外蕴状态；
 *  （1）一个内蕴状态是存储在享元对象内部的，并且是不会随环境的改变而有所不同。因此，一个享元可以具有内蕴状态并可以共享。
 *  （2）一个外蕴状态是随环境的改变而改变的、不可以共享的。享元对象的外蕴状态必须由客户端保存，并在享元对象被创建以后，在需要使用的时候再传入到享元对象内部，外蕴状态不可以影响享元对象的内蕴状态，它们是相互独立的。
 * 创建作者：陈苗
 * 创建时间：2016年5月19日 9:41
 */
public class FlyWeightPatternTest {
    public static void main(String[] args) {
        FlyWeightFactory factory = FlyWeightFactory.getFactory();
        FlyWeight flyWeightOne = factory.factory(new Character('A'));
        flyWeightOne.operation("这是flyWeightOne的operation调用");
        FlyWeight flyWeightTwo = factory.factory(new Character('B'));
        flyWeightTwo.operation("这是flyWeightTwo的operation调用");
        FlyWeight flyWeightThree = factory.factory(new Character('A'));
        flyWeightThree.operation("这是flyWeightThree的operation调用，并且flyWeightThree与flyWeightOne是否相同的结果为：" + (flyWeightThree == flyWeightOne));
    }
}
