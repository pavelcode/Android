package com.cblue.common;

import android.R.integer;

public class BytesClass
{/*
	public static double BytesToDouble(byte[] paramArrayOfByte, int paramInt)
	{
		int i = paramInt + 8;
		int j = paramArrayOfByte.length;
		if (i > j)
			;
		long l13;
		long l14;
		for (double d = 0.0D;; d = Double.longBitsToDouble(l13 | l14)) {
			long l1 = paramArrayOfByte[paramInt] & 0xFF;
			int k = paramInt + 1;
			long l2 = paramArrayOfByte[k] << 8;
			long l3 = (l1 | l2) & 0xFFFF;
			int m = paramInt + 2;
			long l4 = paramArrayOfByte[m] << 16;
			long l5 = (l3 | l4) & 0xFFFFFF;
			long l6 = paramArrayOfByte[3] << 24;
			long l7 = (l5 | l6) & 0xFFFFFFFF;
			long l8 = paramArrayOfByte[4] << 32;
			long l9 = (l7 | l8) & 0xFFFFFFFF;
			long l10 = paramArrayOfByte[5] << 40;
			long l11 = (l9 | l10) & 0xFFFFFFFF;
			long l12 = paramArrayOfByte[6] << 48;
			l13 = l11 | l12;
			l14 = paramArrayOfByte[7] << 56;
			return d;
		}

	}

	public static int BytesToInt(byte[] paramArrayOfByte, int paramInt) throws NullPointerException
	{
		int i = paramInt + 4;
		int j = paramArrayOfByte.length;
		//int i7;
		int i7=0;
		if (i <= j) {
			int k = paramInt + 3;
			int m = (paramArrayOfByte[k] & 0xFF) << 24;
			int n = paramInt + 2;
			int i1 = (paramArrayOfByte[n] & 0xFF) << 16;
			int i2 = m + i1;
			int i3 = paramInt + 1;
			int i4 = (paramArrayOfByte[i3] & 0xFF) << 8;
			int i5 = i2 + i4;
			int i6 = paramArrayOfByte[paramInt] & 0xFF;
			i7 = i5 + i6;
			if (i7 == 2147483647)
				i7 = 0;
		}
		for (int i8 = i7;; i8 = 0)
			return i8;
	}

	public static short BytesToShort(byte[] paramArrayOfByte, int paramInt)
	{
		int i = paramInt + 2;
		int j = paramArrayOfByte.length;
//		int m;
//		int n;
		int m =0 ;
		int n=0;
		if (i <= j) {
			int k = paramInt + 1;
			m = (paramArrayOfByte[k] & 0xFF) << 8;
			n = paramArrayOfByte[paramInt] & 0xFF;
		}
		for (short s = (short) (m + n);; s = 0)
			return s;
	}
	
	

	public static String BytesToString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
	{
		int i = paramInt1 + paramInt2;
		String str = null;
		try {
			int j = paramArrayOfByte.length;
			if (i <= j) {
				str = new String(paramArrayOfByte, paramInt1, paramInt2, "GBK");
				return str;
			}
		} catch (Exception localException) {
			while (true) {
				localException.printStackTrace();
				
			}
		}
		return null;
	}

	public static byte[] DoubleToBytes(double paramDouble)
	{
		byte[] arrayOfByte = new byte[8];
		long l = Double.doubleToLongBits(paramDouble);
		int i = 0;
		while (true) {
			int j = arrayOfByte.length;
			if (i >= j){
			int k = new Long(l).byteValue();
			arrayOfByte[i] = k;
			l >>= 8;
			i += 1;
		}
		}
	}

	public static byte[] IntToBytes(int paramInt) throws NullPointerException
	{
		byte[] arrayOfByte = new byte[4];
		int i = (byte)(paramInt & 0xFF);
		arrayOfByte[0] = i;
		int j = (byte) (paramInt >> 8 & 0xFF);
		arrayOfByte[1] = j;
		int k = (byte) (paramInt >> 16 & 0xFF);
		arrayOfByte[2] = k;
		int m = (byte) (paramInt >> 24 & 0xFF);
		arrayOfByte[3] = m;
		return arrayOfByte;
	}

	public static short[] IntToShort(int paramInt) throws NullPointerException
	{
		short[] arrayOfShort = null;
		byte[] arrayOfByte = IntToBytes(paramInt);
		int i = BytesToShort(arrayOfByte, 0);
		arrayOfShort[0] = i;
		int j = BytesToShort(arrayOfByte, 2);
		arrayOfShort[1] = j;
		return arrayOfShort;
	}

	public static byte[] LongToBytes(long paramLong)
	{
		byte[] arrayOfByte = new byte[8];
		int i = 0;
		while (true) {
			int j = arrayOfByte.length;
			if (i >= j)
				return arrayOfByte;
			int k = i * 8;
			int m = (byte) (int) (paramLong >> k & 0xFF);
			arrayOfByte[i] = m;
			i += 1;
		}
	}

	
	public static byte[] ShortToBytes(short paramShort) throws NullPointerException
	{
		byte[] arrayOfByte = new byte[2];
		int i = (byte) paramShort;
		arrayOfByte[0] = i;
		int j = (byte) (paramShort >> 8 & 0xFF);
		arrayOfByte[1] = j;
		return arrayOfByte;
	}

	public static String TrimStr(String paramString)
	{
		int i = paramString.indexOf(0);
		if (i > 0)
			;
		for (String str = paramString.substring(0, i);; str = paramString)
			return str;
	}

	public static byte[] charToByteArray(char[] paramArrayOfChar)
	{
		byte[] arrayOfByte = new byte[paramArrayOfChar.length];
		int i = 0;
		while (true) {
			int j = paramArrayOfChar.length;
			if (i >= j)
				return arrayOfByte;
			int k = (byte) paramArrayOfChar[i];
			arrayOfByte[i] = k;
			i += 1;
		}
	}

	public static String convertString(String paramString)
	{
		Object localObject = "";
		try {
			byte[] arrayOfByte = gbk2utf8(paramString);
			String str = new String(arrayOfByte, "UTF-8");
			localObject = str;
			return localObject;
		} catch (Exception localException) {
			while (true)
				localException.printStackTrace();
		}
		return null;
	}

	public static byte[] gbk2utf8(String paramString)
	{
		char[] arrayOfChar = paramString.toCharArray();
		byte[] arrayOfByte1 = new byte[arrayOfChar.length * 3];
		int i = 0;
		int j = arrayOfChar.length;
		if (i >= j)
			return arrayOfByte1;
		String str1 = Integer.toBinaryString(arrayOfChar[i]);
		StringBuffer localStringBuffer1 = new StringBuffer();
		int k = str1.length();
		String str2 = 16 - k;
		paramString = 0;
		while (true) {
			if (paramString >= str2) {
				StringBuffer localStringBuffer2 = localStringBuffer1.append(str1);
				StringBuffer localStringBuffer3 = localStringBuffer1.insert(0, "1110");
				StringBuffer localStringBuffer4 = localStringBuffer1.insert(8, "10");
				StringBuffer localStringBuffer5 = localStringBuffer1.insert(16, "10");
				String str3 = localStringBuffer1.substring(0, 8);
				String str4 = localStringBuffer1.substring(8, 16);
				String str5 = localStringBuffer1.substring(16);
				int m = Integer.valueOf(str3, 2).byteValue();
				int n = Integer.valueOf(str4, 2).byteValue();
				int i1 = Integer.valueOf(str5, 2).byteValue();
				byte[] arrayOfByte2 = new byte[3];
				arrayOfByte2[0] = m;
				int i2 = i * 3;
				int i3 = arrayOfByte2[0];
				arrayOfByte1[i2] = i3;
				arrayOfByte2[1] = n;
				int i4 = i * 3 + 1;
				int i5 = arrayOfByte2[1];
				arrayOfByte1[i4] = i5;
				arrayOfByte2[2] = i1;
				int i6 = i * 3 + 2;
				int i7 = arrayOfByte2[2];
				arrayOfByte1[i6] = i7;
				i += 1;
				break;
			}
			StringBuffer localStringBuffer6 = localStringBuffer1.append("0");
			paramString += 1;
		}
	}

	public static String unicodeToBinary(String paramString)
	{
		String str1 = "";
		try {
			char[] arrayOfChar = paramString.toCharArray();
			int i = 0;
			int j = paramString.length();
			if (i >= j)
				return str1;
			Object localObject = Integer.toHexString(arrayOfChar[i]);
			if (((String) localObject).length() > 4)
				;
			String str4;
			for (localObject = ((String) localObject).substring(0, 4);; localObject = str4) {
				String str2 = String.valueOf(str1);
				str1 = str2 + "&#x" + (String) localObject + ";";
				i += 1;
				break;
				int k = ((String) localObject).length();
				int m = 4 - k;
				String str3 = String.valueOf("0000".substring(0, m));
				str4 = str3 + (String) localObject;
			}
		} catch (Exception localException) {
			while (true)
				str1 = "";
		}
	}
	
	 public static byte[] StringToKey(String paramString)
	  {
	    byte[] arrayOfByte = new byte[8];
	    int i = 0;
	    while (true)
	    {
	      int j = arrayOfByte.length;
	      if (i >= j)
	      int k = i * 2;
	      int m = i * 2 + 2;
	      int n = (byte)Integer.parseInt(paramString.substring(k, m), 16);
	      arrayOfByte[i] = n;
	      i += 1;
	      return arrayOfByte;
	    }
	  }
	 
	 public static String bytesToUNIString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
	  {
	    int i = paramInt1 + paramInt2 + -1;
	    if (paramArrayOfByte[i] == 0)
	      paramInt2 += -1;
	    try
	    {
	      str = new String(paramArrayOfByte, paramInt1, paramInt2, "UNICODE");
	      return str;
	    }
	    catch (Exception localException)
	    {
	      while (true)
	        String str = null;
	    }
	  }

	  public static String bytesToUTFString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
	  {
	    int i = paramInt1 + paramInt2 + -1;
	    if (paramArrayOfByte[i] == 0)
	      paramInt2 += -1;
	    try
	    {
	      str = new String(paramArrayOfByte, paramInt1, paramInt2, "UTF-8");
	      return str;
	    }
	    catch (Exception localException)
	    {
	      while (true)
	        String str = null;
	    }
	  }

	  public static String format(byte[] paramArrayOfByte)
	  {
	    StringBuffer localStringBuffer1 = new StringBuffer();
	    int i = 0;
	    int j = paramArrayOfByte.length;
	    if (i >= j)
	      return localStringBuffer1.toString().toUpperCase();
	    String str = Integer.toString(paramArrayOfByte[i] & 0xFF, 16);
	    if (str.length() == 2);
	    while (true)
	    {
	      StringBuffer localStringBuffer2 = localStringBuffer1.append(str);
	      i += 1;
	      break;
	      str = "0" + str;
	    }
	  }

	  public static float formatMoney(float paramFloat, int paramInt)
	  {
	    try
	    {
	      String str = "%." + paramInt + "f";
	      Object[] arrayOfObject = new Object[1];
	      Float localFloat = Float.valueOf(paramFloat);
	      arrayOfObject[0] = localFloat;
	      float f1 = Float.parseFloat(String.format(str, arrayOfObject));
	      float f2 = f1;
	      return f2;
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        log4j.fatal(localException, localException);
	        localException.printStackTrace();
	        int i = 0;
	      }
	    }
	  }

	  public static String formatMoney(long paramLong)
	  {
	    Object localObject;
	    if (paramLong == 0L)
	    {
	      localObject = "¡ª";
	      return localObject;
	    }
	    String str1;
	    if (Math.abs(paramLong) <= 10000L)
	      str1 = "1Íò";
	    while (true)
	    {
	      localObject = str1;
	      break;
	      if ((Math.abs(paramLong) > 10000L) && (Math.abs(paramLong) < 100000000L))
	      {
	        String str2 = String.valueOf(paramLong / 10000L);
	        str1 = str2 + "Íò";
	      }
	      else
	      {
	        double d = paramLong;
	        String str3 = String.valueOf(formatMoney(String.valueOf(1.0D * d / 100000000.0D), 3));
	        str1 = str3 + "ÒÚ";
	      }
	    }
	  }

	  public static String formatMoney(String paramString)
	  {
	    try
	    {
	      double d = Double.parseDouble(paramString);
	      if (d <= 1000000.0D)
	        paramString = formatMoney(paramString, 2);
	      while (true)
	      {
	        return paramString;
	        if ((d > 1000000.0D) && (d <= 10000000.0D))
	        {
	          paramString = formatMoney(paramString, 1);
	        }
	        else if (d > 10000000.0D)
	        {
	          String str = formatMoney(paramString, 0);
	          paramString = str;
	        }
	      }
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        log4j.fatal(localException, localException);
	        localException.printStackTrace();
	      }
	    }
	  }

	  public static String formatMoney(String paramString, int paramInt)
	  {
	    try
	    {
	      double d = Double.parseDouble(paramString);
	      String str1 = "%." + paramInt + "f";
	      Object[] arrayOfObject = new Object[1];
	      Double localDouble = Double.valueOf(d);
	      arrayOfObject[0] = localDouble;
	      String str2 = String.format(str1, arrayOfObject);
	      str3 = str2;
	      return str3;
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        localException.printStackTrace();
	        String str3 = paramString;
	      }
	    }
	  }

	  public static double[] getMaxAndMin(double[] paramArrayOfDouble, int paramInt1, double paramDouble1, double paramDouble2, int paramInt2)
	  {
	    double[] arrayOfDouble;
	    if (paramInt1 < 0)
	      arrayOfDouble = null;
	    int i;
	    while (true)
	    {
	      return arrayOfDouble;
	      arrayOfDouble = new double[2];
	      if (paramInt2 < paramInt1)
	      {
	        paramDouble1 = paramArrayOfDouble[paramInt2];
	        paramDouble2 = paramDouble1;
	        i = paramInt2 + 1;
	        if (i < paramInt1)
	          break;
	        arrayOfDouble[0] = paramDouble1;
	        arrayOfDouble[1] = paramDouble2;
	      }
	    }
	    long l1 = paramArrayOfDouble[i];
	    if (paramDouble2 < l1)
	    {
	      label69: long l2 = paramArrayOfDouble[i];
	      if (paramDouble1 <= l2)
	        break label100;
	    }
	    while (true)
	    {
	      i += 1;
	      break;
	      paramDouble2 = paramArrayOfDouble[i];
	      break label69;
	      label100: paramDouble1 = paramArrayOfDouble[i];
	    }
	  }

	  public static int[] getMaxAndMin(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
	  {
	    int[] arrayOfInt;
	    if (paramInt1 < 0)
	      arrayOfInt = null;
	    int i;
	    while (true)
	    {
	      return arrayOfInt;
	      arrayOfInt = new int[2];
	      paramInt2 = paramArrayOfInt[0];
	      paramInt3 = paramInt2;
	      i = 1;
	      if (i < paramInt1)
	        break;
	      arrayOfInt[0] = paramInt2;
	      arrayOfInt[1] = paramInt3;
	    }
	    int j = paramArrayOfInt[i];
	    if (paramInt3 < j)
	    {
	      label55: int k = paramArrayOfInt[i];
	      if (paramInt2 <= k)
	        break label84;
	    }
	    while (true)
	    {
	      i += 1;
	      break;
	      paramInt3 = paramArrayOfInt[i];
	      break label55;
	      label84: paramInt2 = paramArrayOfInt[i];
	    }
	  }

*/}
