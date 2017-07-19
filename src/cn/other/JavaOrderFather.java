package cn.other;

public class JavaOrderFather{
	static{
		System.out.println("JavaOrderFather的静态代码段执行且只执行一次.");
	}
	{
		System.out.println("在JavaOrderFather类的构造方法之前.");
	}
	public JavaOrderFather(){
		System.out.println("JavaOrderFather的构造方法.");
	}
	public void doSomething() {
		System.out.println("JavaOrderFather的内部函数方法执行.");
	}
	
}
