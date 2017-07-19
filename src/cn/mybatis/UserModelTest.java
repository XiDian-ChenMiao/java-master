package cn.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 用户类测试类
 * @ClassName: UserModelTest 
 * @author 陈苗 
 * @date 2016年4月12日 下午5:13:14
 */
public class UserModelTest {
	private SqlSession sqlSession;
	private UserModelMapper userModelMapper;
	@Before
	public void initial(){
		sqlSession = MyBatisUtil.getSqlSession();
		userModelMapper = sqlSession.getMapper(UserModelMapper.class);
	}
	@Test
	public void getUserByGender() throws Exception {
		Map<String, Integer> parameterMap = new HashMap<String, Integer>();
		parameterMap.put("genderNumber", 1);
		parameterMap.put("userCount", -1);
		userModelMapper.selectUserByGender(parameterMap);
		Integer result = parameterMap.get("userCount");
		System.out.println("结果：" + ((result == -1) ? "未找到" : "找到" + result + "条记录"));
	}
	@Test
	public void getAllUsers() throws Exception {
		List<UserModel> userList = userModelMapper.getAllUser();
		System.out.println("共有用户：" + userList.size());
	}
	@Test
	public void getAllUsersPage() throws Exception {
		PageHelperPlugin.startPage(2,5);
		userModelMapper.getAllUser();
		Pager<UserModel> userPage = PageHelperPlugin.endPage();
		System.out.println(userPage);
		printList(userPage.getResult());
	}

	@Test
	public void getAllAccountsPage() throws Exception {
		PageHelperPlugin.startPage(2,1);
		userModelMapper.getAllAccounts();
		Pager<Account> accountPage = PageHelperPlugin.endPage();
		System.out.println(accountPage);
		printList(accountPage.getResult());
	}

	private <T> void printList(List<T> userModels) {
		for(T model : userModels)
			System.out.println(model);
	}
	@After
	public void destory(){
		MyBatisUtil.closeSession(sqlSession);
	}
}
