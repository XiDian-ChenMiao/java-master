package cn.xidian.designpattern;

import java.util.HashMap;
import java.util.Map;

interface IUserInfo {
    String getUserName();
    String getHomeAddress();
    String getMobileNumber();
    String getOfficeTelNumber();
    String getJobPosition();
    String getHomeTelNumber();
}

class UserInfo implements IUserInfo {
    @Override
    public String getUserName() {
        System.out.println("姓名叫做张三");
        return null;
    }

    @Override
    public String getHomeAddress() {
        System.out.println("陕西西安");
        return null;
    }

    @Override
    public String getMobileNumber() {
        System.out.println("18702853033");
        return null;
    }

    @Override
    public String getOfficeTelNumber() {
        System.out.println("029-88203644");
        return null;
    }

    @Override
    public String getJobPosition() {
        System.out.println("经理");
        return null;
    }

    @Override
    public String getHomeTelNumber() {
        System.out.println("029-88203344");
        return null;
    }
}

interface IOuterUser {
    Map getUserBaseInfo();
    Map getUserOfficeInfo();
    Map getUserHomeInfo();
}

class OuterUser implements IOuterUser {
    @Override
    public Map getUserBaseInfo() {
        Map<String, String> info = new HashMap<String, String>();
        info.put("userName", "张三");
        info.put("mobileNumber", "18200348745");
        return info;
    }

    @Override
    public Map getUserOfficeInfo() {
        Map<String, String> info = new HashMap<String, String>();
        info.put("jobPosition", "经理");
        info.put("officeTelNumber", "029-88207788");
        return info;
    }

    @Override
    public Map getUserHomeInfo() {
        Map<String, String> info = new HashMap<String, String>();
        info.put("homeTelNumber", "029-88203355");
        info.put("homeAddress", "陕西西安");
        return info;
    }
}

class OuterUserInfo extends OuterUser implements IUserInfo {
    private Map baseInfo = super.getUserBaseInfo();
    private Map homeInfo = super.getUserHomeInfo();
    private Map officeInfo = super.getUserOfficeInfo();
    @Override
    public String getUserName() {
        return (String) this.baseInfo.get("userName");
    }

    @Override
    public String getHomeAddress() {
        return (String) this.homeInfo.get("homeAddress");
    }

    @Override
    public String getMobileNumber() {
        return (String) this.baseInfo.get("mobileNumber");
    }

    @Override
    public String getOfficeTelNumber() {
        return (String) this.officeInfo.get("officeTelNumber");
    }

    @Override
    public String getJobPosition() {
        return (String) this.officeInfo.get("jobPosition");
    }

    @Override
    public String getHomeTelNumber() {
        return (String) this.homeInfo.get("homeTelNumber");
    }
}

/**
 * 文件描述：适配器模式测试类
 * NOTE:
 *  适配器模式可以让两个没有任何关系的类在一起运行，只要适配器这个角色能够处理；
 *  增加了类的透明性；
 *  提高了类的复用度；
 * 创建作者：陈苗
 * 创建时间：2016/11/23 10:10
 */
public class AdapterPatternTest {
    public static void main(String[] args) {
        IUserInfo userInfo = new UserInfo();
        IUserInfo outerUserInfo = new OuterUserInfo();
        userInfo.getHomeAddress();
        userInfo.getHomeTelNumber();
        userInfo.getJobPosition();
        userInfo.getOfficeTelNumber();
        userInfo.getUserName();
        userInfo.getMobileNumber();
        System.out.println(outerUserInfo.getHomeAddress());
        System.out.println(outerUserInfo.getHomeTelNumber());
        System.out.println(outerUserInfo.getJobPosition());
        System.out.println(outerUserInfo.getOfficeTelNumber());
        System.out.println(outerUserInfo.getUserName());
        System.out.println(outerUserInfo.getMobileNumber());
    }
}
