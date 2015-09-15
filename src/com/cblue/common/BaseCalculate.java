package com.cblue.common;

import java.math.BigDecimal;

/**
 * 数字的基本运算 加减乘除  约等 截取
 * @author Administrator
 *
 */
public class BaseCalculate
{
  private static final int DEF_DIV_SCALE = 10;

  public static double add(double paramDouble1, double paramDouble2)
  {
    String str1 = Double.toString(paramDouble1);
    BigDecimal localBigDecimal1 = new BigDecimal(str1);
    String str2 = Double.toString(paramDouble2);
    BigDecimal localBigDecimal2 = new BigDecimal(str2);
    return localBigDecimal1.add(localBigDecimal2).doubleValue();
  }

  public static double divide(double paramDouble1, double paramDouble2)
  {
    return divide(paramDouble1, paramDouble2, 10);
  }

  public static double divide(double paramDouble1, double paramDouble2, int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("The scale must be a positive integer or zero");
    String str1 = Double.toString(paramDouble1);
    BigDecimal localBigDecimal1 = new BigDecimal(str1);
    String str2 = Double.toString(paramDouble2);
    BigDecimal localBigDecimal2 = new BigDecimal(str2);
    return localBigDecimal1.divide(localBigDecimal2, paramInt, 4).doubleValue();
  }

  public static double multiply(double paramDouble1, double paramDouble2)
  {
    String str1 = Double.toString(paramDouble1);
    BigDecimal localBigDecimal1 = new BigDecimal(str1);
    String str2 = Double.toString(paramDouble2);
    BigDecimal localBigDecimal2 = new BigDecimal(str2);
    return localBigDecimal1.multiply(localBigDecimal2).doubleValue();
  }

  public static double round(double paramDouble, int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("The scale must be a positive integer or zero");
    String str = Double.toString(paramDouble);
    BigDecimal localBigDecimal1 = new BigDecimal(str);
    BigDecimal localBigDecimal2 = new BigDecimal("1");
    return localBigDecimal1.divide(localBigDecimal2, paramInt, 4).doubleValue();
  }

  public static double substract(double paramDouble1, double paramDouble2)
  {
    String str1 = Double.toString(paramDouble1);
    BigDecimal localBigDecimal1 = new BigDecimal(str1);
    String str2 = Double.toString(paramDouble2);
    BigDecimal localBigDecimal2 = new BigDecimal(str2);
    return localBigDecimal1.subtract(localBigDecimal2).doubleValue();
  }
}