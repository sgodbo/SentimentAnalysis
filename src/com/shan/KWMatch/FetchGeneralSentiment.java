package com.shan.KWMatch;

import java.net.UnknownHostException;
import java.util.Iterator;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class FetchGeneralSentiment {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {

		MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("test");
        //System.out.println("Authentication: ");
        DBCollection collection = db.getCollection("mycollection");
        SentAnalysis sa = new SentAnalysis();
        DBCursor cursor = collection.find();
        BasicDBObject comments;
        BasicDBList commentsList;
        String score;
        String messageToBeEvaluated;
        BasicDBObject commentsMessage;
        BasicDBObject searchQuery;
        while (cursor.hasNext()){
        	BasicDBObject o = (BasicDBObject)cursor.next();
        	if(null != (comments = (BasicDBObject)o.get("comments"))){
        		if(null != (commentsList = (BasicDBList)comments.get("data"))){
        			Iterator itr = commentsList.iterator();
        			for (int i = 0;i < commentsList.size();i++) {
        				commentsMessage = (BasicDBObject)commentsList.get(i);
        				if(null != commentsMessage && null != commentsMessage.get("score")){
        					messageToBeEvaluated = commentsMessage.get("score").toString();
        					System.out.println(messageToBeEvaluated);
        					}}
        				}
        		
        		System.out.println();
        		System.out.println();
        		System.out.println();
        		System.out.println();

	}

}}}
