package com.cblue.interaction.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;
import android.content.res.AssetManager;


public class ReaderXMLDom
{
	private Context context;
	public ReaderXMLDom(Context context){
		this.context = context;
	}
	
	
	public InputStream readAssetFile(String fileName)throws Exception{
		AssetManager assetManager = context.getResources().getAssets();
		return assetManager.open(fileName);		
		
	}
	public static List<Book> readXML(InputStream inStream)
	{
		List<Book> books = new ArrayList<Book>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document dom = builder.parse(inStream);
			Element root = dom.getDocumentElement();
			NodeList items = root.getElementsByTagName("book");// 查找对象根节点集合（多少个对象）
			for (int i = 0; i < items.getLength(); i++) {
				Book book = new Book();
				// 得到第一个book节点
				Element item = (Element) items.item(i);
				// 获取person节点的id属性值
				//book.setId(new Integer(item.getAttribute("id")));
				// 获取person节点下的所有子节点(标签之间的空白节点和name/age元素)
				NodeList childsNodes = item.getChildNodes();
				for (int j = 0; j < childsNodes.getLength(); j++) {
					Node node = (Node) childsNodes.item(j); // 判断是否为元素类型
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element childNode = (Element) node;
						if("id".equals(childNode.getNodeName())){
							book.setId(Integer.valueOf(childNode.getFirstChild().getNodeValue()));
						}else if ("name".equals(childNode.getNodeName())) {
							// 获取name元素下Text节点,然后从Text节点获取数据
							book.setName(childNode.getFirstChild().getNodeValue());
						} else if ("price".equals(childNode.getNodeName())) {
							book.setPrice(Float.valueOf(childNode.getFirstChild().getNodeValue()));
						}
					}
				}
				books.add(book);
			}
			inStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}
}