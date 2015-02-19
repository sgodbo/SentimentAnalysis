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


import org.bson.BasicBSONObject;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;

public class NewKWMatch {
	
	public static void main (String args[]) throws MalformedURLException, IOException, NullPointerException, PathNotFoundException{
		
		String url = "https://graph.facebook.com/v2.2/marksandspencer?fields=posts{message,comments.limit(5){message}}&access_token=CAACEdEose0cBAFH001ZCCrNijSOIZA3FFHeBO0ZA7yStkn2g1cJNj8Sl8ktHXCQiZArYJOX3czDtgD45s8vBipaUGhdyil6uV1JspZALZBCUnZAZCZBOvWz8S5LcOQjSF2AZCqNGGjzL1dAaN5aAmN56lDBZBPV4cZCILAjZBEENJWUqFOXVFYZApl6E8amJGAtHloZAMiaTEMIpw6oO9wWCkxvKcb8bPzakl21FnoZD";
		URLConnection connection = new URL(url).openConnection();
		connection.setRequestProperty("Accept-Charset", "UTF-32");
		connection.connect();
		HttpURLConnection httpConn = (HttpURLConnection) connection;
		System.out.println(httpConn.getResponseCode());
		
		MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("test");
        DBCollection collection = db.createCollection("mycollection", new BasicDBObject("capped", false)
        .append("size", 1048576));
		
		
		InputStream response =  connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(response));
		String resp = "";
		String currentLine;
		while ( (currentLine = br.readLine()) != null){
			resp += currentLine + "\n";
		}
		
		
		String jsonObjectForMongoDB = "";
		
		for(int itr = 0;itr < 10;itr++){
			String jsonPathForMongoDB = "$.posts.data["+itr+"]";
		    jsonObjectForMongoDB = JsonPath.read(resp, jsonPathForMongoDB).toString();
		    if(null != jsonObjectForMongoDB){
		        DBObject dbo = (DBObject) JSON.parse(jsonObjectForMongoDB);
		        collection.insert(dbo);
		    }
		}
		
		mongoClient.close();
		//BasicDBObject bdo = (BasicDBObject) JSON.parse(resp);
		//DBCollection coll = db.createCollection("mycol", bdo);
		/*List<JsonPathResultObject> jproArr = new ArrayList<JsonPathResultObject>();
		
		String jsonPostMsgs;
		String jsonCommentMsgs;
		String jsptest;
		List<String> jsPathRes = new ArrayList<String>();
		StringTokenizer str;
		//System.out.println(resp);
		for(int i = 0;i < 10;i++){
			String jsonPathForPosts = "$.message";
			String jsonPathForComments = "$.comments.data[*].message";
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
		
		/*Iterator<JsonPathResultObject> iterator = jproArr.iterator();
		while(iterator.hasNext()){
			JsonPathResultObject jp = iterator.next();
			if(null != jp.getPostText())
				System.out.println(jp.getPostText());
			else
				System.out.println("null post");
			if(null != jp.getCommentText()){
			for (String string : jp.getCommentText()) {
				System.out.println(string);
			}}
		}*/
		
	}
	
	public void pushJsonDataIntoMongoDB(){}
	public void fetchDataFromMongoDB(){}
	public void fetchSentimentForComments(){}
	
	
	

}
