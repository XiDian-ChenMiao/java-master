package cn.xidian.mybatis;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * MyBatis数据库访问的工具类
 * @ClassName: MyBatisUtil 
 * @author 陈苗 
 * @date 2016年4月12日 下午4:39:42
 */
public class MyBatisUtil {
	/**
	 * 指定MyBatis的配置文件路径
	 */
    private static final String CONFIG_PATH = "MyBatisTest/MyBatis-Config.xml";
    /**
     * 获取数据库的连接
     * @return
     */
    public static SqlSession getSqlSession() {
        SqlSession session = null;
        try {
            InputStream stream = Resources.getResourceAsStream(CONFIG_PATH);
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
            session = factory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }
    /**
     * 关闭数据库的连接
     * @param session
     */
    public static void closeSession(SqlSession session) {
        session.close();
    }
}