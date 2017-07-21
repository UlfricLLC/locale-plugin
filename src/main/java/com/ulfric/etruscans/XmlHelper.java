package com.ulfric.etruscans;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.ulfric.tryto.Try;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.StringReader;

public class XmlHelper {

	private static final DocumentBuilder DOCUMENTS = Try.toGet(DocumentBuilderFactory.newInstance()::newDocumentBuilder);

	public static Document parseIncompleteDocument(String xml) {
		return XmlHelper.parseDocument("<doc>" + xml + "</doc>");
	}

	public static Document parseDocument(String xml) {
		InputSource source = new InputSource(new StringReader(xml));
		return Try.toGet(() -> DOCUMENTS.parse(source));
	}

	private XmlHelper() {
	}

}
