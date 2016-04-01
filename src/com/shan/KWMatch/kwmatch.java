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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.xml.sax.SAXException;

public class kwmatch {
	
	static Map<String, Integer> mapSentiments = new HashMap<String,Integer>();

	/**
	 * @param args
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws JSONException
	 * @throws org.json.JSONException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws XPathExpressionException
	 * @throws TransformerException
	 */
	public static void main(String[] args) throws MalformedURLException,
			IOException, JSONException,
			ParserConfigurationException, SAXException,
			XPathExpressionException, TransformerException {
		InputStream file = new FileInputStream("resources/tokens_keys.properties");
		Properties prop = new Properties();
		prop.load(file);
		String access_token = "access_token=" + prop.getProperty("fb_graph_token");
		String baseUrl = "https://graph.facebook.com/v2.5/MarksAndSpencer/posts";
		String params = "since=1392681600&fields=message,comments";
		String url = baseUrl + "?" + params + "&" + access_token;
		URLConnection connection = new URL(url).openConnection();
		connection.setRequestProperty("Accept-Charset", "UTF-32");
		connection.connect();
		HttpURLConnection httpConn = (HttpURLConnection) connection;
		System.out.println(httpConn.getResponseCode());
		printValuesUsingJsonObject(connection);
	}
	
	private static void printValuesUsingJsonObject(URLConnection connection)
			throws JSONException, IOException {
		StringBuffer sb = new StringBuffer();
		String readableResponse = "";
		InputStream response =  connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(response));
		
		sb.append(br.readLine());
		readableResponse = sb.toString();
		System.out.println(readableResponse);
		JSONObject jsonRespTotal = new JSONObject(readableResponse);
		JSONArray ja = jsonRespTotal.getJSONArray("data");
		JSONArray ja2;
		JSONObject jo2 = null;
		System.out.println(ja.length());
		for (int i = 0; i < ja.length(); i++) {
			if (ja.getJSONObject(i).has("message"))
				System.out.println(ja.getJSONObject(i).getString("message"));

			if (ja.getJSONObject(i).has("comments"))
				jo2 = new JSONObject(ja.getJSONObject(i).getString("comments"));

			ja2 = jo2.getJSONArray("data");
			System.out.println(ja2.length());
			for (int j = 0; j < ja2.length(); j++) {
				if (ja2.getJSONObject(j).has("message"))
					System.out.println(ja2.getJSONObject(j)
							.getString("message"));
			}
			System.out.println("\n");
		}
		// TODO Auto-generated method stub

	}

	

}