package com.cblue.interaction.xml;

import com.cblue.android.R;

import android.app.Activity;
import android.content.res.XmlResourceParser;

/**
 *  读取Android的XML目录下的文件
 * @author Administrator
 *
 */
public class ReaderXMLResouce
{

	public static String readXML(Object o)
	{
		Activity myacitivty = (Activity) o;
		XmlResourceParser xrp = myacitivty.getResources().getXml(R.xml.customers);
		StringBuilder sbBuilder = new StringBuilder();
		try
		{
			while(xrp.getEventType()!=XmlResourceParser.END_DOCUMENT)
			{
				if(xrp.getEventType()==XmlResourceParser.START_TAG)
				{
					String tagName = xrp.getName();
					if(tagName=="customer")
					{
					   sbBuilder.append(xrp.getAttributeValue(0)+"");
					   sbBuilder.append(xrp.getAttributeValue(1)+"");
					}
				}	
				
			}
		} catch (Exception e)
		{
			
		}
		return sbBuilder.toString();
	}
}
