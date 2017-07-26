package cn.xidian.mybatis;

/**
 * 文件描述：
 * 创建作者：陈苗
 * 创建时间：2016年月日 17:02
 */
public class Account {
    private int aid;
    private String accname;
    private float accmoney;

    public Account() {}
    public Account(int aid, String accname, float accmoney) {
        this.aid = aid;
        this.accname = accname;
        this.accmoney = accmoney;
    }

    @Override
    public String toString() {
        return "Account{" +
                "aid=" + aid +
                ", accname='" + accname + '\'' +
                ", accmoney=" + accmoney +
                '}';
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public float getAccmoney() {
        return accmoney;
    }

    public void setAccmoney(float accmoney) {
        this.accmoney = accmoney;
    }
}
