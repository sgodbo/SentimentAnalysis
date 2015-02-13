package com.shan.KWMatch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.json.JSONArray;
import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;
import org.apache.commons.json.utils.XML;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.alchemyapi.api.AlchemyAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
	@SuppressWarnings("null")
	public static void main(String[] args) throws MalformedURLException,
			IOException, JSONException, org.json.JSONException,
			ParserConfigurationException, SAXException,
			XPathExpressionException, TransformerException {
		String url = "https://graph.facebook.com/MarksAndSpencer/posts?since=1392681600&fields=message,comments.fields%28message%29&access_token=CAACEdEose0cBAEPBFtqxJGwKClm9Vdw6SoDCH37ZCzuQRZBxuxaSgLDZCLvU3cvKs6CvRUiDfCherrEBvOVrPvOrRbybPrO36tKQ358yOiyUyQudxHZCrhZCuNYZBxN4Dz6E2lVXhkjU9HMa1efZC3tsESYrReFms2JGnSUYXuDjoMKLtpMA0yYEB0ivQFcDRI3258ZBiZBqFuFxigWzaRGPJuvDDlUCxI70ZD";
		URLConnection connection = new URL(url).openConnection();
		connection.setRequestProperty("Accept-Charset", "UTF-32");
		connection.connect();
		HttpURLConnection httpConn = (HttpURLConnection) connection;
		System.out.println(httpConn.getResponseCode());
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		
		printDataFromStream(connection, builder);
		//printDataFromFile(builder);

	}

	private static void printDataFromFile(DocumentBuilder builder)
			throws SAXException, IOException, ParserConfigurationException,
			XPathExpressionException {

		// 
		// File f = new File("C:\\Users\\sgodbo\\Desktop\\xyz.xml");*/
		InputSource inpSrc = new InputSource(
				"C:\\Users\\SHANDEMETZ\\Desktop\\xyz.xml");
		inpSrc.setEncoding("ISO-8859-1");
		Document doc = builder.parse(inpSrc);
		printValuesUsingXpath(doc);
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("null")
	public static void printDataFromStream(URLConnection connection,
			DocumentBuilder builder) throws IOException, SAXException,
			TransformerException, ParserConfigurationException,
			XPathExpressionException, JSONException {

		InputStream response =  connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(response));
		String resp = "";
		String currentLine;
		int counter = 0;
		/*while ( (currentLine = br.readLine()) != null){
			resp += currentLine;
			counter++;
		}
		System.out.println(counter);
		/*String xml = XML.toXml(response);
		File f = new File("C:\\Users\\SHANDEMETZ\\Desktop\\xyz123.xml");
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		writer.write(xml);
		writer.close();
		InputSource inpSrc = new InputSource("C:\\Users\\SHANDEMETZ\\Desktop\\xyz123.xml");
		inpSrc.setEncoding("ISO-8859-1");*/
		
		/*TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		Document doc = builder.parse(response);
		DOMSource source = new DOMSource(doc);
		//StreamResult result = new StreamResult(System.out);
		//transformer.transform(source, result);
		/*parseViaDOM();
		parseViaSAX(response);*/
		//printValuesUsingXpath(doc);
		//printValuesUsingJsonObject(readableResponse);
		Gson gson = new GsonBuilder().create();
		//gson.toJson(resp,System.out);
		FBObject f1 = gson.fromJson(br,FBObject.class);
		System.out.println(f1);
		
	}

	
	private static void parseViaSAX(InputStream response) throws ParserConfigurationException, SAXException {
		// TODO Auto-generated method stub
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		
	}

	private static void parseViaDOM() {
		// TODO Auto-generated method stub
		
	}

	private static void printValuesUsingJsonObject(String readableResponse)
			throws JSONException {
		System.out.println(readableResponse);
		JSONObject jsonRespTotal = new JSONObject(readableResponse);
		JSONArray ja = jsonRespTotal.getJSONArray("data");
		JSONArray ja2;
		JSONObject jo2 = null;
		boolean postHasMsg;
		String postComment;
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

	private static void printValuesUsingXpath(Document doc)
			throws XPathExpressionException, IOException {
		// TODO Auto-generated method stub
		//AlchemyAPI alchemyObj = AlchemyAPI.GetInstanceFromFile("D:\\Web Project\\Alchemy\\testdir\\api_key.txt");
		
		XPathFactory xpfactory = XPathFactory.newInstance();
		XPath xpath = xpfactory.newXPath();
		String xPathXpr = "/entry_list/entry/def/dt";
		/*String xPathExpr = "/jsonObject/data";
		String xPathPosts = "/jsonObject/data/jsonObject";
		String xPathCommentsExpr = "/comments/data/jsonObject";
		String xMessage = "/@message";
		String commentsResponse;
		int itrPost = 1 ,itrComment = 1;
		int countPost = Integer.parseInt(xpath.compile("count("+xPathPosts+")").evaluate(doc));
		int countComment = 0;
		int commentRatingPositive = 0;
		int commentRatingNegative = 0;
		while(itrPost <= countPost){
			String commentMsg = "("+xPathPosts+")["+itrPost+"]";
			System.out.println(xpath.compile(commentMsg+xMessage).evaluate(doc));
			countComment = Integer.parseInt(xpath.compile("count(("+xPathPosts+")["+itrPost+"]"+xPathCommentsExpr+")").evaluate(doc));
			while(itrComment <= countComment){
				commentsResponse = xpath.compile("(("+commentMsg+")"+xPathCommentsExpr+")["+itrComment+"]"+xMessage).evaluate(doc);
				System.out.println(commentsResponse);
				//alchemyObj.get
					
				itrComment++;
			}
			System.out.println(commentRatingPositive);
			System.out.println(commentRatingNegative);
			itrPost++;
			itrComment = 1;
			System.out.println("\n\n\n");
		}*/
		
		System.out.println(xpath.compile(xPathXpr).evaluate(doc));
		 
	}

	

}