package com.shan.KWMatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.alchemyapi.api.AlchemyAPI;
import com.jayway.jsonpath.JsonPath;

public class SentAnalysis {

    /**
     * @param args
     * @throws IOException 
     * @throws MalformedURLException 
     * @throws ParserConfigurationException 
     * @throws SAXException 
     * @throws XPathExpressionException 
     */
    public String queryAlchemyAPIForTextSentiment(String textToBeQueried) throws MalformedURLException, IOException, XPathExpressionException, SAXException, ParserConfigurationException {
        // TODO Auto-generated method stub
    	AlchemyAPI alApi = AlchemyAPI.GetInstanceFromString("781f195a75cdb9de81b0f8f0f8346ea293a22ae1");
    	Document doc = alApi.TextGetTextSentiment(textToBeQueried);
 
    	NodeList nodeList = doc.getElementsByTagName("*");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // do something with the current element
            	if(node.getNodeName() == "score"){
            		return node.getTextContent();
            	}
                
            }
        }
        return("ignore");
    }
}
