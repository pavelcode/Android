package com.cblue.common;

public class Color
{
	private final int alpha;
	  private final int b;
	  private final int g;
	  private final int r;

	  private Color()
	  {
	    this.b = 0;
	    this.g = 0;
	    this.r = 0;
	    this.alpha = 0;
	  }

	  public Color(int paramInt)
	  {
	    int i = android.graphics.Color.red(paramInt);
	    this.r = i;
	    int j = android.graphics.Color.green(paramInt);
	    this.g = j;
	    int k = android.graphics.Color.blue(paramInt);
	    this.b = k;
	    int m = android.graphics.Color.alpha(paramInt);
	    this.alpha = m;
	  }

	  public Color(int paramInt1, int paramInt2, int paramInt3)
	  {
	    int i = normalizeRGBValue(paramInt1);
	    this.r = i;
	    int j = normalizeRGBValue(paramInt2);
	    this.g = j;
	    int k = normalizeRGBValue(paramInt3);
	    this.b = k;
	    this.alpha = 0;
	  }

	  public Color(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	  {
	    int i = normalizeRGBValue(paramInt1);
	    this.r = i;
	    int j = normalizeRGBValue(paramInt2);
	    this.g = j;
	    int k = normalizeRGBValue(paramInt3);
	    this.b = k;
	    int m = normalizeRGBValue(paramInt4);
	    this.alpha = m;
	  }

	  private int normalizeRGBValue(int paramInt)
	  {
	    int i = 255;
	    if (paramInt < 0)
	      i = 0;
	    while (true)
	    {
	      if (paramInt <= 255)
	        i = paramInt;
	      return i;
	    }
	   
	  }

	  int getAlpha()
	  {
	    return this.alpha;
	  }

	  int getBlue()
	  {
	    return this.b;
	  }

	  int getGreen()
	  {
	    return this.g;
	  }

	  int getRed()
	  {
	    return this.r;
	  }

	  public final int toAndroidHex()
	  {
	    int i = this.alpha;
	    int j = this.r;
	    int k = this.g;
	    int m = this.b;
	    return android.graphics.Color.argb(i, j, k, m);
	  }

	  public final int toHEX()
	  {
	    int i = this.r;
	    int j = this.g;
	    int k = this.b;
	    return android.graphics.Color.argb(0, i, j, k);
	  }
}
