package cn.other;

public class Resume {
	private String resumeid;
	private String name;
	private String school;
	private String phonenumber;
	private String department;
	private String position;
	private String description;
	private String mail;
	public String getResumeid() {
		return resumeid;
	}
	public void setResumeid(String resumeid) {
		this.resumeid = resumeid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	@Override
	public String toString() {
		return "Resume [resumeid=" + resumeid + ", name=" + name + ", school="
				+ school + ", phonenumber=" + phonenumber + ", department="
				+ department + ", position=" + position + ", description="
				+ description + ", mail=" + mail + "]";
	}

}
