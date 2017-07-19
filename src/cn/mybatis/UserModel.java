package cn.mybatis;
/**
 * MyBatis测试用用户类
 * @ClassName: UserModel 
 * @author 陈苗 
 * @date 2016年4月12日 下午4:41:39
 */
public class UserModel {
	private int id;
	private String username;
	private char gender;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "用户ID：" + id + "，用户性别：" + (gender == 'M' ? "男" : "女") + ",用户姓名：" + username;
	}
}
