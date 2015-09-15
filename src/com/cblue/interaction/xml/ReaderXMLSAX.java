package com.cblue.interaction.xml;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;




/**
 * 使用SAX，从网络上得到XML数据
 * 
 * sax解析的基本原理：
 * sax就像我们读书一样从上而下顺序读取。
 * sax定义了一个工具类，这个工具类包含了五个事件，这五个事件，实际上是五个方法。
 * 这五个方法的作用是：
 * 分别是遇到开始文档执行-startDocument()，遇到开始标签执行-startElement()
 * 遇到标签内容执行-charactors()
 * 遇到接收标签执行-endElement()
 * 遇到文档结束执行-endDocument()
 * 
 * sax解析步骤   
   1>定义一个工具类：查看API，org.xml.sax.helpers的继承DefaultHandler，实现五个方法。 
   2>得到解析工厂,使用解析器解析 javax.xml.parsers
 * 
 * @author Administrator
 *
 */

public class ReaderXMLSAX {

	public static void aaa() {
		String path = "http://10.211.55.8:8080/Android_W/book.xml";
		InputStream inputStream = HttpUtils.getXML(path);
		try {
			List<HashMap<String, String>> list = ReaderXMLSAX.readXML(inputStream,"book");
			for (HashMap<String, String> map : list) {
				Log.i("aaa", map.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<HashMap<String, String>> readXML(
			InputStream inputStream, String nodeName) {
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser parser = spf.newSAXParser();
			SaxHandler handler = new SaxHandler(nodeName);
			parser.parse(inputStream, handler);
			//inputStream.close();
			return handler.getList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
//处理器
class SaxHandler extends DefaultHandler {

	// 保存XML的数据
	private List<HashMap<String, String>> list = null;

	// 保存结点的数据
	private HashMap<String, String> map = null;

	// 当前标签
	private String currentTag = null;

	// 当前标签的值
	private String currentValue = null;

	// 根结点名
	private String objectNode = null;

	public SaxHandler(String objectNode) {
		this.objectNode = objectNode;
	}

	public List<HashMap<String, String>> getList() {
		return list;
	}

	@Override
	public void startDocument() throws SAXException {
		list = new ArrayList<HashMap<String, String>>();
	}

	/**
	 * qName 标签的值 Attributes 属性
	 */

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals(objectNode)) {
			map = new HashMap<String, String>();
		}
		// 结点的属性值
		if (attributes != null && map != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				map.put(attributes.getQName(i), attributes.getValue(i));
			}
		}
		//TODO 不能少
		currentTag = qName;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		if (currentTag != null && map != null) {
			currentValue = new String(ch, start, length);
			//TODO .trim()比较有用
			//
			if (currentValue != null && !currentValue.trim().equals("")
					&& !currentValue.trim().equals("\n")) {
				map.put(currentTag, currentValue);
			}
		}

		currentTag = null;
		currentValue = null;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// 当这个节点结束的时候，把节点中的map，放入到list中
		if (qName.equals(objectNode)) {
			list.add(map);
			map = null;
		}
	}
}




/**
 * 使用SAX，解析本地XML数据
 * @author Administrator
 *
 */


class SAXBasic {

	public static void main(String[] args)throws Exception {
		SAXParserFactory sFactory = SAXParserFactory.newInstance();
		SAXParser saxParser = sFactory.newSAXParser();
		saxParser.parse("G:"+File.separator+"stu.xml",new MySAXHandler());
	}

}
class MySAXHandler extends DefaultHandler{

	
	@Override
	public void startDocument() throws SAXException {
         System.out.println("<?xml verson=\"1.0\" encode=\"GBK\">");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
           System.out.print("<");
           System.out.print(qName);
           if(attributes!=null){
        	   for(int i=0;i<attributes.getLength();i++){
        		   System.out.print(" "+attributes.getQName(i)+"="+"\""+attributes.getValue(i)+"\"");
        	   }
           }
           System.out.print(">");
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		  System.out.println(new String(ch,start,length));
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.print("<");
        System.out.print(qName);
        System.out.print("/>");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("�ĵ�������");
	}
	
} 



