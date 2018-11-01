package com.taobao.rigel.rap.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlUtils {

	public static Document getDocument(File file)
	{
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			return document;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean ishasPackage(String name,Document doc)
	{
		
		
		NodeList nodeList = doc.getElementsByTagName("package");
		
		int num = name.split("/").length-1;
		int last = name.lastIndexOf("/");
		if(num > 1)
		{
			name = name.substring(0, last);
			
		}
		else
		{
			return false;
		}
		System.out.println(nodeList.getLength());
		for(int i=0;nodeList !=null && i<nodeList.getLength();i++)
		{
			Element ele = (Element) nodeList.item(i);
			String pacnamesp = ele.getAttribute("namespace");
			System.out.println(pacnamesp+"|");
			if(pacnamesp.equals(name))
			{
				return true;
			}
		}
		return false;
	}
	
	public static void addElement(Document doc,String namespace)
	{
	//	Element tem = doc.createElement("a");
		Element root = doc.getDocumentElement();
		
		Element pac_ele = doc.createElement("package");
		pac_ele.setAttribute("extends", "rap-default");
		int num = namespace.split("/").length-1;
		
		Element ac_ele = doc.createElement("action");
		
		ac_ele.setAttribute("class","com.taobao.rigel.rap.reqforward.action.ReqForwardAction");
		ac_ele.setAttribute("method","forward");
		
		Element result_ele = doc.createElement("result");
		result_ele.setAttribute("name", "success");
		result_ele.setAttribute("type", "dispatcher");
		result_ele.setTextContent("${path}");
		ac_ele.appendChild(result_ele);
		
		
		int i = namespace.lastIndexOf("/");
		if(num > 1)
		{
			System.out.println("come in >1");
			String ns = namespace.substring(0, i);
			pac_ele.setAttribute("namespace", ns);
			pac_ele.setAttribute("name", ns);
			ac_ele.setAttribute("name", "*");
		}
		else if(num == 1)
		{
			pac_ele.setAttribute("name", "");
			String actionName = namespace.substring(i+1,namespace.length());
			
			ac_ele.setAttribute("name", actionName);
			NodeList nodeList = doc.getElementsByTagName("package");
			for(int j=0;nodeList !=null && j<nodeList.getLength();j++)
			{
				Element ele = (Element) nodeList.item(j);
				String pacnamesp = ele.getAttribute("namespace");
				System.out.println("test is |"+pacnamesp);
				if(pacnamesp.equals(""))
				{
					System.out.println("come in");
					NodeList node = ele.getElementsByTagName("action");
					boolean b=true;
					for(int m=0;m<node.getLength();m++)
					{
						System.out.println(node.item(m).getNodeName());
						Element action = (Element) node.item(m);
						String acName= action.getAttribute("name");
						if(acName.equals(actionName))
						{
							b=false;
							break;
						}
						
					}
					if(b)
					{
						ele.appendChild(ac_ele);
					}
					
					break;
				}
				
					
			}
		}
		else
			return;
		
		
		
		
		
		if(num > 1)
		{
			
			pac_ele.appendChild(ac_ele);
			root.appendChild(pac_ele);
			System.out.println("come in append");
		}
		
		
	}
	
	public static void savXml(Document doc,String path)
	{
		try {
			DOMSource source = new DOMSource(doc);
			StreamResult res = new StreamResult(new File(path));
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(source, res);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		File file = new File("d:/struts.xml");
		Document doc = XmlUtils.getDocument(file);
		
		String url = "/query/worldServer/getdau";
		
		boolean b = XmlUtils.ishasPackage(url, doc);
		System.out.println(b);
		
		if(!XmlUtils.ishasPackage(url, doc))
		{
			XmlUtils.addElement(doc,url);
			XmlUtils.savXml(doc, "d:/struts.xml");
		}
	}
}
