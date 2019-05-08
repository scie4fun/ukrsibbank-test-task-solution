package com.ukrsibbank.test.task.solution.utils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;

public class XmlDocumentAnalizer {

    public static int getElementCount(String inputFilePath, String elementName) {
        int result = 0;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document doc = factory.newDocumentBuilder().parse(inputFilePath);
            NodeList nodes = doc.getElementsByTagName(elementName);

            result = nodes.getLength();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
