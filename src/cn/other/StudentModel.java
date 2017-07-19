package cn.other;

public class StudentModel{
	private int id;
	private String name;
	private String engineering;
	private String grade;
	private String classname;
	private int password;
	public StudentModel(){}
	
	public StudentModel(int id, String name, String engineering, String grade, String classname, int password) {
		super();
		this.id = id;
		this.name = name;
		this.engineering = engineering;
		this.grade = grade;
		this.classname = classname;
		this.password = password;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEngineering() {
		return engineering;
	}
	public void setEngineering(String engineering) {
		this.engineering = engineering;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
}
