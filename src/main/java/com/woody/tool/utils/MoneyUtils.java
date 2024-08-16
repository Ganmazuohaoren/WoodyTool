package com.woody.tool.utils;


import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金额工具类
 *
 * @author Woody.Qiu
 * @Date 2024/8/16 14:55
 */
public class MoneyUtils {

    /**
     * 金额的小数位数
     */
    private static final int PRICE_SCALE = 2;

    /**
     * 百分比对应的 BigDecimal 对象
     */
    public static final BigDecimal PERCENT_100 = BigDecimal.valueOf(100);


    /**
     * 分转元
     *
     * @param fen 分
     * @return 元
     */
    public static BigDecimal fenToYuan(int fen) {
        return new BigDecimal(fen).divide(PERCENT_100, 2, RoundingMode.HALF_UP);
    }

    /**
     * 分转元 (用于处理字符串输入)
     *
     * @param fen 分
     * @return 元
     */
    public static BigDecimal fenToYuan(String fen) {
        try {
            return new BigDecimal(fen).divide(PERCENT_100, PRICE_SCALE, RoundingMode.HALF_UP);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input: " + fen, e);
        }
    }

    /**
     * 将元转换为分。
     *
     * @param yuan 元的字符串表示
     * @return 分数
     */
    public static int yuanToFen(String yuan) {
        if (yuan == null || yuan.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty.");
        }
        try {
            BigDecimal bigDecimalYuan = new BigDecimal(yuan);
            return yuanToFen(bigDecimalYuan);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + yuan, e);
        }
    }

    /**
     * 将元转换为分。
     *
     * @param yuan 元的BigDecimal表示
     * @return 分数
     */
    public static int yuanToFen(BigDecimal yuan) {
        // 使用setScale确保精度
        BigDecimal result = yuan.multiply(PERCENT_100).setScale(0, RoundingMode.HALF_UP);
        return result.intValue();
    }
}
