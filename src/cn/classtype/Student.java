package cn.classtype;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Student implements Serializable{
	private int identity;
	private String name;
	private char gender;
	private int grade;
	private float scores;
	public String[] favoriteCourse;
	public String[] getFavoriteCourse() {
		return favoriteCourse;
	}
	public void setFavoriteCourse(String[] favoriteCourse) {
		this.favoriteCourse = favoriteCourse;
	}
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public float getScores() {
		return scores;
	}
	public void setScores(float scores) {
		this.scores = scores;
	}
	public Student(){}
	public Student(int identity, String name, char gender) {
		super();
		this.identity = identity;
		this.name = name;
		this.gender = gender;
	}
	public Student(int identity, String name, char gender, int grade, float scores) {
		super();
		this.identity = identity;
		this.name = name;
		this.gender = gender;
		this.grade = grade;
		this.scores = scores;
	}
	public Student(int identity, String name, char gender, int grade, float scores, String[] favoriteCourse) {
		super();
		this.identity = identity;
		this.name = name;
		this.gender = gender;
		this.grade = grade;
		this.scores = scores;
		this.favoriteCourse = favoriteCourse;
	}
	public void showStudentInfo(){
		System.out.println("ID:" + this.identity + "\nName:" + this.name + "\nGender:" + this.gender
				+ "\nGrade:" + this.grade + "\nScores:" + this.scores);
	}
	public void compareStudentScores(Student anothor){
		if(this.scores > anothor.scores)
			System.out.println("The front student has higher scores.");
		else if(this.scores == anothor.scores)
			System.out.println("This two students have same scores.");
		else
			System.out.println("The rear student has higher scores.");
	}
	public String toString() {
		return "ID:" + this.identity + "\nName:" + this.name + "\nGender:" + this.gender + "\nGrade:" + this.grade + "\nScores:" + this.scores;
	}
}
