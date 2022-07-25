package com.epam.shchehlov.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.shchehlov.constant.Constant.MESSAGE_CANT_PARSE_XML;

public class AccessXmlHandler {

    private static final Logger logger = Logger.getLogger(AccessXmlHandler.class);

    private static final String CONSTRAINT = "constraint";
    private static final String ROLE = "role";
    private static final String URL = "url";

    private AccessXmlHandler() {
    }

    public static Map<String, List<String>> parseXML(String xmlPath) {
        InputStream xmlStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlPath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        Map<String, List<String>> accessMap = new HashMap<>();

        try {
            dbFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlStream);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName(CONSTRAINT);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    accessMap.put(getUrl(element), getRoles(element));
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error(MESSAGE_CANT_PARSE_XML + ExceptionUtils.getStackTrace(e));
        }

        return accessMap;
    }

    private static List<String> getRoles(Element element) {
        List<String> roles = new ArrayList<>();
        NodeList roleNodes = element.getElementsByTagName(ROLE);

        for (int i = 0; i < roleNodes.getLength(); i++) {
            Node node = roleNodes.item(i);
            Element roleElement = (Element) node;
            roles.add(roleElement.getTextContent());
        }
        return roles;
    }

    private static String getUrl(Element element) {
        return element.getElementsByTagName(URL).item(0).getTextContent();
    }
}
