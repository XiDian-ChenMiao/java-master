package cn.designpattern;

import org.junit.Test;

import java.io.*;
/**
 * 教授类
 */
class Professor implements Serializable,Cloneable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    /**
     * 教授类的构造函数
     * @param name
     * @param age
     */
    public Professor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
/**
 * 原型类
 * Note:
 *  深复制：将一个对象复制后，不论是基本类型还是引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底；
 *  浅复制：将一个对象复制后，基本类型数据的变量都会被创建，而引用类型指向的还是原对象所指向的。
 */
class StudentPrototype implements Cloneable,Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private Professor professor;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public StudentPrototype(String name, int age, Professor professor) {
        this.name = name;
        this.age = age;
        this.professor = professor;
    }

    /**
     * 浅复制的例子
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        StudentPrototype obj = null;
        try {
            obj = (StudentPrototype) super.clone();//克隆对象时并没有调用构造函数
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 深复制的例子
     * @return
     */
    public Object deepCopy() throws IOException, ClassNotFoundException {
        //写入当前对象的二进制流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        //读出二进制流产生的对象
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }
}
/**
 * 原型模式测试类
 * 创建作者：陈苗
 * 创建时间：2016年5月18日 22:57
 */
public class PrototypePatternTest {
    @Test
    public void testShallowCopy() {
        Professor professor = new Professor("大秦之帝",90);
        StudentPrototype studentPrototype1 = new StudentPrototype("陈苗",23,professor);
        StudentPrototype studentPrototype2 = null;
        try {
            studentPrototype2 = (StudentPrototype) studentPrototype1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        studentPrototype2.getProfessor().setName("LLH");
        studentPrototype2.getProfessor().setAge(45);
        studentPrototype2.setAge(24);
        studentPrototype2.setName("大秦之帝");
        //由打印可知，学生1的值类型并未被改变，但其引用类型已经被改变
        System.out.println("学生1的姓名：" + studentPrototype1.getName() + "，年龄：" + studentPrototype1.getAge()
            + "，其教授的名称：" + studentPrototype1.getProfessor().getName() + "，年龄：" + studentPrototype1.getProfessor().getAge()
        );
    }

    @Test
    public void testDeepCopy() {
        Professor professor = new Professor("大秦之帝",90);
        StudentPrototype studentPrototype1 = new StudentPrototype("陈苗",23,professor);
        StudentPrototype studentPrototype2 = null;
        try {
            studentPrototype2 = (StudentPrototype) studentPrototype1.deepCopy();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        studentPrototype2.getProfessor().setName("LLH");
        studentPrototype2.getProfessor().setAge(45);
        //由打印可知，学生1的引用类型并未改变
        System.out.println("学生1教授的名称：" + studentPrototype1.getProfessor().getName() + "，年龄：" + studentPrototype1.getProfessor().getAge());
    }
}
