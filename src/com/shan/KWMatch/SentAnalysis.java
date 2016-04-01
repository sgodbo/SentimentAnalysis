package com.shan.KWMatch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

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
    	InputStream file = new FileInputStream("\resources\tokens_keys.properties");
		Properties prop = new Properties();
		prop.load(file);
    	AlchemyAPI alApi = AlchemyAPI.GetInstanceFromString(prop.getProperty("alchemy_key"));
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
