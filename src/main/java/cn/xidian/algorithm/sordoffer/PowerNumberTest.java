package cn.xidian.algorithm.sordoffer;

/**
 * 文件描述：数的幂函数实现
 * 创建作者：陈苗
 * 创建时间：2016年6月1日 20:22
 */
public class PowerNumberTest {
    /**
     * 幂次方的实现
     * @param base 底数
     * @param exponent 次方
     * @return 结果值
     * @throws Exception
     */
    public double power(double base,int exponent) throws Exception {
        if (equal(base,0.0) && exponent < 0)
            throw new Exception("算数异常");
        int absExponent = exponent & 0xFFFFFFFF;
        if (exponent < 0)
            absExponent = -absExponent;

        double result = powerWithResurive(base,absExponent);

        if (exponent < 0)
            result = 1.0 / result;
        return result;
    }

    /**
     * 递归实现
     * @param base
     * @param exponent 无符号指数
     * @return
     */
    private double powerWithResurive(double base,int exponent) {
        if(exponent == 0)
            return 1;
        if (exponent == 1)
            return base;
        double result = powerWithResurive(base,exponent >> 1);
        result *= result;
        if((exponent & 0x1) == 1)//如果指数为奇数，则需要在乘以基数
            result *= base;
        return result;
    }
    /**
     * 判断相等函数
     * @param a
     * @param b
     * @return
     */
    private boolean equal(double a,double b) {
        return (a - b > -0.00000001 && a - b < 0.00000001) ? true : false;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        PowerNumberTest test = new PowerNumberTest();
        try {
            System.out.println("结果为：" + test.power(2,0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
