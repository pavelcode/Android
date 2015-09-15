package com.cblue.interaction.xml;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;
/**
 * 使用Pull，从网络上得到XML数据
 * @author Administrator
 *
 */
public class ReaderXMLPull {

	public static void bb() throws Exception {
		//readXML();
		
		String path = "http://10.211.55.8:8080/Android_W/book.xml";
		InputStream inputStream = HttpUtils.getXML(path);
		List<Book> books = null;
		try {
			books = ReaderXMLPull.parseXML(inputStream, "utf-8");
			for (Book book : books) {
				Log.i("aaa", book.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param inputStream
	 *       
	 * @param encode
	 *          
	 * @return
	 * @throws Exception
	 */
	public static List<Book> parseXML(InputStream inputStream, String encode) throws Exception {
		List<Book> list = null;
		Book book = null;
		//得到解析工厂
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		//得到解析对象
		XmlPullParser parser = factory.newPullParser();
		//给解析对象设置值
		parser.setInput(inputStream, encode);
        //得到解析的事件类型
		int eventType = parser.getEventType();
		//当这个文档没有结束的时候不断的循环
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Book>();
				break;
			case XmlPullParser.START_TAG:
				if ("book".equalsIgnoreCase(parser.getName())) {
					book = new Book();
					//得到属性值
					//parser.getAttributeValue(null, "id") 试试
					int id = Integer.parseInt(parser.getAttributeValue(0));
					book.setId(id);
				} else if ("name".equalsIgnoreCase(parser.getName())) {
					String name = parser.nextText();
					book.setName(name);
				} else if ("price".equalsIgnoreCase(parser.getName())) {
					float price = Float.parseFloat(parser.nextText());
					book.setPrice(price);
				}
				break;
			case XmlPullParser.END_TAG:
				//TODO 这里一定要判断结束标签，并把book清空
				if ("book".equalsIgnoreCase(parser.getName())) {
					list.add(book);
					book = null;
				}
				break;
			}
			//TODO 一定要重新赋值给事件类型
			eventType = parser.next();
		}
		return list;
	}


	/**
	 * pull解析本地XML
	 * @throws Exception
	 */
	public static void readXML() throws Exception {
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser pullParser = factory.newPullParser();

		FileReader reader = new FileReader(new File("F:"+File.separator+"book.xml"));
		pullParser.setInput(reader);

		int event = pullParser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				System.out.println("<?xml version=\"1.0\" encoding=\"gbk\"?>");
				break;
			case XmlPullParser.START_TAG:
				if ("book".equals(pullParser.getName())) {
					System.out.print("<book");
					System.out.print(" id=\"" + pullParser.getAttributeValue(0) + "\"");
					System.out.print(">");
					System.out.println();
				} else if ("name".equals(pullParser.getName())) {

					System.out.print("<name>");
					System.out.print(pullParser.nextText());
					System.out.print("</name>");
					System.out.println();
				} else if ("price".equals(pullParser.getName())) {
					System.out.print("<price>");
					System.out.print(pullParser.nextText());
					System.out.print("</price>");
					System.out.println();
					System.out.println("</book>");
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			event = pullParser.next();
		}

	}

}
