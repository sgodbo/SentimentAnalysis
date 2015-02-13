package com.shan.KWMatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

import org.apache.commons.json.JSON;
import org.apache.commons.json.JSONException;
import org.bson.BasicBSONObject;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.sun.xml.internal.ws.client.MonitorRootClient;

public class NewKWMatch {
	
	public static void main (String args[]) throws MalformedURLException, IOException, NullPointerException, JSONException, PathNotFoundException{
		
		String url = "https://graph.facebook.com/v2.2/marksandspencer?fields=posts{message,comments.limit(5){message}}&access_token=CAACEdEose0cBAHQ1FJ68gLSDZA1AG9kVsJqjbYIUTAj7JLT4pK9ks2LtC1QHyh2EErw1HQktIeeJfBlR0e6OeoZBQZAgrrwwnUKWLj79W13dZCOsxIKuVkHclKIFjm9hWUW4qVFgtZCIogtw3sONoAZCIXadSZBb47RzwEGLkIZBJFtfW0ZCEXZCehZAhGYsp34AMXNf8M21oYbRR8JYBlrX7tZBi87IvpRvfDIZD";
		URLConnection connection = new URL(url).openConnection();
		connection.setRequestProperty("Accept-Charset", "UTF-32");
		connection.connect();
		HttpURLConnection httpConn = (HttpURLConnection) connection;
		System.out.println(httpConn.getResponseCode());
		
		//MongoClient mongoClient = new MongoClient("localhost", 27017);
		
		//DB db = mongoClient.getDB("mydb");
		
		
		InputStream response =  connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(response));
		String resp = "";
		String currentLine;
		while ( (currentLine = br.readLine()) != null){
			resp += currentLine + "\n";
		}
		//BasicDBObject bdo = (BasicDBObject) JSON.parse(resp);
		//DBCollection coll = db.createCollection("mycol", bdo);
		List<JsonPathResultObject> jproArr = new ArrayList<JsonPathResultObject>();
		
		String jsonPostMsgs;
		String jsonCommentMsgs;
		String jsptest;
		List<String> jsPathRes = new ArrayList<String>();
		StringTokenizer str;
		//System.out.println(resp);
		for(int i = 0;i < 10;i++){
			String jsonPathForPosts = "$.posts.data["+i+"].message";
			String jsonPathForComments = "$.posts.data["+i+"].comments.data[*].message";
			JsonPathResultObject jpro = new JsonPathResultObject();
			List<String> listComments = new ArrayList<String>();
			try{
				jsonPostMsgs = JsonPath.read(resp, jsonPathForPosts);
				if(null != jsonPostMsgs)
					jpro.setPostText(jsonPostMsgs);
			} catch (PathNotFoundException e){
				jpro.setPostText(null);
			}
			
			try{
				jsonCommentMsgs = JsonPath.read(resp, jsonPathForComments).toString();
				
				if(null != jsonCommentMsgs){				
					str = new StringTokenizer(jsonCommentMsgs,",");
					while (str.hasMoreElements())
						listComments.add(str.nextElement().toString());
					jpro.setCommentText(listComments);
				}
			} catch (PathNotFoundException e){
				jpro.setCommentText(null);
			}
			
				
			jproArr.add(jpro);
		}
		
		Iterator<JsonPathResultObject> itr = jproArr.iterator();
		while(itr.hasNext()){
			JsonPathResultObject jp = itr.next();
			if(null != jp.getPostText())
				System.out.println(jp.getPostText());
			else
				System.out.println("null post");
			if(null != jp.getCommentText()){
			for (String string : jp.getCommentText()) {
				System.out.println(string);
			}}
		}
		
	}
	
	

}
