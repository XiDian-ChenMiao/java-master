package cn.mybatis;

import java.util.List;
import java.util.Map;

/**
 * 用户类的Mapper接口
 * @ClassName: UserModelMapper 
 * @author 陈苗 
 * @date 2016年4月12日 下午9:42:52
 */
public interface UserModelMapper {
	public List<UserModel> getAllUser() throws Exception;
	List<Account> getAllAccounts() throws Exception;
	public void selectUserByGender(Map<String, Integer> params) throws Exception;
}
