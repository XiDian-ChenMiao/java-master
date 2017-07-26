package cn.xidian.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Employee
 * 类描述：抽象雇员类
 * 创建时间：2016年1月28日 下午6:43:25
 * 创建人： 陈苗
 */
abstract class Employee {
	public final static int MALE = 0;//代表男性
	public final static int FEMALE = 1;//代表女性
	private String name;//姓名
	private int sex;//性别
	private int salary;//薪水
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	/**
	 * 允许一个访问者访问
	 * @param visitor 访问者对象
	 */
	public abstract void accept(IVisitor visitor);
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：CommonEmployee
 * 类描述：普通员工类
 * 创建时间：2016年1月28日 下午6:44:50
 * 创建人： 陈苗
 */
class CommonEmployee extends Employee {
	private String job;
	
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：ManagerEmployee
 * 类描述：部门经理类
 * 创建时间：2016年1月28日 下午7:18:14
 * 创建人： 陈苗
 */
class ManagerEmployee extends Employee {

	private String performance;
	
	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
	
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：IVisitor
 * 类描述：抽象访问者
 * 创建时间：2016年1月28日 下午6:39:32
 * 创建人： 陈苗
 */
interface IVisitor {
	public void visit(CommonEmployee employee);
	public void visit(ManagerEmployee manager);
	public int getTotalSalary();//获取所有员工工资总和
}
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Visitor
 * 类描述：具体访问者
 * 创建时间：2016年1月28日 下午7:19:50
 * 创建人： 陈苗
 */
class Visitor implements IVisitor {
	private final static int MANAGER_COEFFICIENT = 5;//部门经理的工资系数是5
	private final static int COMMONEMPLOYEE_COEFFICIENT = 2;//员工的工资系数是2
	private int commonTotalSalary = 0;//普通员工的工资总和
	private int managerTotalSalary = 0;//部门经理的工资总和
	/**
	 * 计算部门经理的工资
	 * @param salary
	 */
	private void calManagerSalary(int salary){
		this.managerTotalSalary += salary * MANAGER_COEFFICIENT;
	}
	/**
	 * 计算普通员工的工资
	 * @param salary
	 */
	private void calCommonSalary(int salary){
		this.commonTotalSalary += salary * COMMONEMPLOYEE_COEFFICIENT;
	}
	@Override
	public void visit(CommonEmployee employee) {
		this.calCommonSalary(employee.getSalary());
		System.out.println(this.getCommonEmplyee(employee));
	}

	@Override
	public void visit(ManagerEmployee manager) {
		this.calManagerSalary(manager.getSalary());
		System.out.println(this.getManagerInfo(manager));
	}

	@Override
	public int getTotalSalary() {
		return this.commonTotalSalary + this.managerTotalSalary;
	}
	/**
	 * 组装个人基本信息
	 * @param employee
	 * @return
	 */
	private String getBasicInfo(Employee employee){
		StringBuilder info = new StringBuilder();
		info.append("姓名：" + employee.getName() + "\t");
		info.append("性别：" + (employee.getSex() == Employee.FEMALE ? "女" : "男") + "\t");
		info.append("薪水：" + employee.getSalary() + "\t");
		return info.toString();
	}
	/**
	 * 获取到部门经理的信息
	 * @param manager
	 * @return
	 */
	private String getManagerInfo(ManagerEmployee manager){
		StringBuilder info = new StringBuilder();
		info.append(this.getBasicInfo(manager));
		info.append("业绩：" + manager.getPerformance() + "\t");
		return info.toString();
	}
	/**
	 * 获取普通员工的信息
	 * @param employee
	 * @return
	 */
	private String getCommonEmplyee(CommonEmployee employee){
		StringBuilder info = new StringBuilder();
		info.append(this.getBasicInfo(employee));
		info.append("工作：" + employee.getJob() + "\t");
		return info.toString();
	}
}
/*
 * 访问者模式的使用场景：
 * 	a.一个对象结构包含很多类对象，它们有不同的接口，而你相对这些对象实施一些依赖于其具体类的操作，也就是用迭代器模式已经不能胜任的情景；
 * 	b.需要对一个对象结构中的对象进行不同而且不相关的操作，而你想避免让这些操作污染这些对象的类。
 */
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：VisitorPatternTest
 * 类描述：访问者模式客户端测试类
 * 创建时间：2016年1月28日 下午6:16:52
 * 创建人： 陈苗
 */
public class VisitorPatternTest {
	/**
	 * 模拟公司的人员情况
	 * @return
	 */
	public static List<Employee> mockEmployee(){
		List<Employee> employees = new ArrayList<Employee>();
		CommonEmployee zhangsan = new CommonEmployee();
		zhangsan.setJob("程序员");
		zhangsan.setName("张三");
		zhangsan.setSalary(8000);
		zhangsan.setSex(Employee.MALE);
		employees.add(zhangsan);
		
		ManagerEmployee manager = new ManagerEmployee();
		manager.setName("王五");
		manager.setPerformance("业绩行业顶尖");
		manager.setSalary(18500);
		manager.setSex(Employee.MALE);
		employees.add(manager);
		return employees;
	}
	/**
	 * 主函数
	 * @param args
	 */
	public static void main(String[] args) {
		IVisitor visitor = new Visitor();
		List<Employee> employees = mockEmployee();
		for (Employee employee : employees) {
			employee.accept(visitor);
		}
		System.out.println("本公司的月工资总额为：" + visitor.getTotalSalary());
	}
}
