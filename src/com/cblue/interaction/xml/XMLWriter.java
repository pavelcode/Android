package com.cblue.interaction.xml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

public class XMLWriter
{
	public static String XMLContent(List<Book> books, Writer writer)
	{
		XmlSerializer serializer = Xml.newSerializer();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.startTag("", "books");
			for (Book book : books) {
				serializer.startTag("", "book");
				serializer.attribute("", "id", String.valueOf(book.getId()));
				serializer.startTag("", "name");
				serializer.text(book.getName());
				serializer.endTag("", "name");
				serializer.startTag("", "price");
				serializer.text(String.valueOf(book.getPrice()));
				serializer.endTag("", "price");
				serializer.endTag("", "book");
			}
			serializer.endTag("", "books");
			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	
}
