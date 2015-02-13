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
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.json.JSONException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

public class CueCardBuild {

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
	@SuppressWarnings("deprecation")
    public static void main(String[] args) throws MalformedURLException, IOException, ParserConfigurationException, XPathExpressionException, SAXException, TransformerException, JSONException {
	    DefaultHttpClient httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());
	    String baseUrl = "http://www.collinsdictionary.com";
	    String accessKey = "yKjHu26AUqS1HbUixPC4nNdnNJw9LoTlq0uCk4SerTUUaYFD1zdjgretCsdpNgha";
        SkPublishAPI api = new SkPublishAPI(baseUrl + "/api/v1", accessKey, httpClient);
        api.setRequestHandler(new SkPublishAPI.RequestHandler() {
            public void prepareGetRequest(HttpGet request) {
                System.out.println(request.getURI());
                request.setHeader("Accept", "application/json");
            }
        });
        
        try {
            System.out.println("*** Dictionaries");
            JSONArray dictionaries = new JSONArray(api.getDictionaries());
            System.out.println(dictionaries);

            JSONObject dict = dictionaries.getJSONObject(0);
            System.out.println(dict);
            String dictCode = dict.getString("dictionaryCode");

            System.out.println("*** Search");
            System.out.println("*** Result list");
            JSONObject results = new JSONObject(api.search(dictCode, "prosaic", 1, 1));
            System.out.println(results);

        } catch (Throwable t) {
            t.printStackTrace();
        }
	
	
	}
	

}
