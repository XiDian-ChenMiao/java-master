package cn.annotation;
@StudentClassAnnotation(stu_name = "欧阳阳",stu_number = 13111273,sum_score = 78)
public class Student {
	@FieldAnnotation("陈苗")
	private String stu_name;
	private int stu_number;
	private float stu_score;
	public Student(){}
	@MethodAnntation(methodName = "getStu_name",methodMeanning = "得到学生姓名")
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public int getStu_number() {
		return stu_number;
	}
	public void setStu_number(int stu_number) {
		this.stu_number = stu_number;
	}
	public float getStu_score() {
		return stu_score;
	}
	public void setStu_score(float stu_score) {
		this.stu_score = stu_score;
	}
	@Override
	public String toString() {
		return "Student [stu_name=" + stu_name + ", stu_number=" + stu_number
				+ ", stu_score=" + stu_score + "]";
	}
	
	
}
