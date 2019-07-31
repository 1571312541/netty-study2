package cn.zcclj.netty;

import java.math.BigDecimal;

/**
 * 〈〉
 *
 * @author 22902
 * @create 2019/1/23
 */
public class TestMain {

    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal(Double.toString(0.8));
        BigDecimal b2 = new BigDecimal(Double.toString(0.3));
        System.out.println(b1.subtract(b2));
        BigDecimal b3 = b1.subtract(b2);

        BigDecimal b4 = new BigDecimal(Double.toString(11.955234));
        //不需要舍入
        System.out.println(b4.divide(b3,BigDecimal.ROUND_UNNECESSARY));
        //四舍五入保留整数
        System.out.println(b4.divide(b3,0,BigDecimal.ROUND_HALF_UP));

    }
}
