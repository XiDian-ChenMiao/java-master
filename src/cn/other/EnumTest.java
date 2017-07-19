package cn.other;

import org.junit.Test;

import java.util.EnumMap;

/**
 * 项目名称：DailyJavaTest
 * 类名称：WeekEnumTest
 * 类描述：枚举使用
 * 创建时间：2015年11月23日 下午9:15:29
 * 创建人： 陈苗
 */
enum Weekend {
	MONDAY(1),
	TUESDAY(2),
	WEDNESDAY(3),
	THURSDAY(4),
	FRIDAY(5),
	SATURDAY(6),
	SUNDAY(7);
	private int weekNum;//定义星期所对应的数值属性
	//枚举类型的构造器只能为private
	private Weekend(int _weekNum){
		this.weekNum = _weekNum;
	}
	@Override
	public String toString() {
		return String.valueOf(this.weekNum);
	}
	/**
	 * 枚举类型的遍历
	 */
    public static void traverseEnum(){
        Weekend[] week = Weekend.values();
		for(Weekend _week : week){
			System.out.println("名称为：" + _week.name());
			System.out.println("当前值为：" + _week);
			System.out.println("前面值为：" + _week.ordinal());
		}
	}
	/**
	 * 枚举Map的遍历
	 */
	public static void testEnumMap(){
		EnumMap<Weekend, String> enumMap = new EnumMap<Weekend, String>(Weekend.class);
		enumMap.put(MONDAY, "星期一");
		enumMap.put(TUESDAY, "星期二");
		enumMap.put(WEDNESDAY, "星期三");
		enumMap.put(THURSDAY, "星期四");
		enumMap.put(FRIDAY, "星期五");
		enumMap.put(SATURDAY, "星期六");
		enumMap.put(SUNDAY, "星期天");
		for(Weekend _week : Weekend.values()){
			System.out.println(_week.name() + "对应的值为" + enumMap.get(_week));
		}
	}
}

/**
 * 算术操作接口
 */
interface Operation {
    double apply(double x,double y);
}

/**
 * 算术的加减乘除枚举
 */
enum BasicOperation implements Operation {
    PLUS("+") {@Override public double apply(double x, double y) { return x + y;}},
    MINUS("-") {@Override public double apply(double x, double y) { return x - y;}},
    TIMES("*") {@Override public double apply(double x, double y) { return x * y;}},
    DIVIDE("/") {@Override public double apply(double x, double y) { return x / y;}};
    private final String symbol;
    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}

/**
 * 颜色枚举
 */
enum ColorEnum {
    RED(255,0,0),
    BLUE(0,0,255),
    BLACK(0,0,0),
    YELLOW(255,255,0),
    GREEN(0,255,0);
    private ColorEnum(int redValue,int greenValue,int blueValue)
    {
        System.out.println("颜色：R-" + redValue + "，G-" + greenValue + "，B-" + blueValue);
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }

    @Override
    public String toString() {
        return "颜色值分别为，红：" + this.redValue + ",绿：" + this.greenValue + ",蓝：" + this.blueValue;
    }
    private int redValue;
    private int greenValue;
    private int blueValue;
}

/**
 * 主测试类
 */
public class EnumTest {
    private static <T extends Enum<T> & Operation> void getResult(Class<T> opSet,double x,double y) {
        for (Operation op : opSet.getEnumConstants())
            System.out.printf("%f %s %f = %f%n",x,op,y,op.apply(x, y));
    }

    @Test
    public void testEnumMethod() {
        Weekend.traverseEnum();
    }
    @Test
    public void testEnumMapMethod() {
        Weekend.testEnumMap();
    }
    @Test
    public void testBasicOperation() {
        getResult(BasicOperation.class,8.0,4.0);
    }
    @Test
    public void testColorEnum() {
        ColorEnum[] colors = ColorEnum.values();
        for(ColorEnum c : colors)
        {
            System.out.println(c.name() + c);
        }
    }
}
