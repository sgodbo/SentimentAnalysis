package com.shan.KWMatch;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.json.JSONException;
import org.xml.sax.SAXException;

public class CreateCue {

    /**
     * @param args
     * @throws IOException 
     * @throws MalformedURLException 
     * @throws ParserConfigurationException 
     * @throws JSONException 
     * @throws TransformerException 
     * @throws SAXException 
     * @throws XPathExpressionException 
     */
    public static void main(
            String[] args) throws MalformedURLException, IOException, ParserConfigurationException, XPathExpressionException, SAXException, TransformerException, JSONException {
        // TODO Auto-generated method stub
        String url = "http://www.dictionaryapi.com/api/v1/references/collegiate/xml/valedictorian?key=7218490d-4bcd-4dfa-b02f-36fb5137df2f";
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", "UTF-32");
        connection.connect();
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        builder = factory.newDocumentBuilder();
        
        kwmatch.printDataFromStream(connection, builder);
        
    }

}
