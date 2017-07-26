package cn.xidian.designpattern;

/**
 * 抽象同事类
 */
abstract class AbstractColleague {
    protected int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * 改变数字的抽象方法
     * @param number 需要设置的数字
     * @param abstractMediator 中介者对象
     */
    public abstract void changeNumber(int number,AbstractMediator abstractMediator);
}
class ColleagueA extends AbstractColleague {
    @Override
    public void changeNumber(int number, AbstractMediator abstractMediator) {
        this.number = number;
        abstractMediator.affectB();
    }
}
class ColleagueB extends AbstractColleague {
    @Override
    public void changeNumber(int number, AbstractMediator abstractMediator) {
        this.number = number;
        abstractMediator.affectA();
    }
}
/**
 * 抽象中介者
 */
abstract class AbstractMediator {
    protected AbstractColleague colleagueA;
    protected AbstractColleague colleagueB;

    public AbstractMediator(AbstractColleague colleagueA, AbstractColleague colleagueB) {
        this.colleagueA = colleagueA;
        this.colleagueB = colleagueB;
    }

    public abstract void affectB();
    public abstract void affectA();
}

/**
 * 抽象中介者的实现类
 */
class Mediator extends AbstractMediator {
    public Mediator(AbstractColleague colleagueA, AbstractColleague colleagueB) {
        super(colleagueA, colleagueB);
    }

    @Override
    public void affectB() {
        colleagueB.setNumber(colleagueA.getNumber() * 100);
    }

    @Override
    public void affectA() {
        colleagueA.setNumber(colleagueB.getNumber() / 100);
    }
}
/**
 * 文件描述：中介者模式测试类
 * Note:中介者模式的优点：
 *  （1）适当的使用中介者模式可以避免同事类之间的过渡耦合，使得同事类之间可以相对独立的使用；
 *  （2）使用中介者模式可以将对象之间一对多的关联转变为一对一的关联，使对象间的关系易于理解和维护；
 *  （3）使用中介者模式可以将对象的行为和协作进行抽象，能够比较灵活的处理对象间的相互作用；
 *  一般来说，对于那种同事类之间是网状结构的关系，才会考虑使用中介者模式。
 * 创建作者：陈苗
 * 创建时间：2016年月日 11:59
 */
public class MediatorPatternTest {
    public static void main(String[] args) {
        AbstractColleague colleagueA = new ColleagueA();
        AbstractColleague colleagueB = new ColleagueB();

        AbstractMediator mediator = new Mediator(colleagueA,colleagueB);
        System.out.println("通过设置A来影响B：");
        colleagueA.changeNumber(100,mediator);
        System.out.println("colleagueA的number值为：" + colleagueA.getNumber());
        System.out.println("colleagueB的number值为：" + colleagueB.getNumber());

        System.out.println("通过设置B来影响A：");
        colleagueB.changeNumber(100,mediator);
        System.out.println("colleagueA的number值为：" + colleagueA.getNumber());
        System.out.println("colleagueB的number值为：" + colleagueB.getNumber());
    }
}
