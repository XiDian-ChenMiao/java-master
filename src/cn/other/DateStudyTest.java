package cn.other;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStudyTest {

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("模式为：" + format.toPattern());
        String result = format.format(new Date());
        System.out.println("转化后：" + result);
    }
}
