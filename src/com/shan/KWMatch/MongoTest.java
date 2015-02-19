package com.shan.KWMatch;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.ListIterator;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoTest {

    /**
     * @param args
     * @throws UnknownHostException 
     */
    public static void main(
            String[] args) throws Exception {
        
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
        				if(null != commentsMessage){
        					messageToBeEvaluated = commentsMessage.get("message").toString();
        					if(!messageToBeEvaluated.isEmpty()){
        						score = sa.queryAlchemyAPIForTextSentiment(messageToBeEvaluated);
        					System.out.println(messageToBeEvaluated);
        					
        					if(!score.equals("ignore")){
        						commentsMessage.append("score",score);
        						searchQuery = new BasicDBObject().append("message", messageToBeEvaluated);
        					    collection.update(searchQuery, commentsMessage);
        					    collection.save(o);
        						System.out.println(score);
        					}
        					}
        				}
					}
        		}
        	}
        	System.out.println();
        	System.out.println();
        }
        mongoClient.close();
        //sa.queryAlchemyAPIForTextSentiment("Of course Michael would never ignore his mum! Our winner has now been selected but there's another chance to win coming your way in Episode 4 tomorrow at 10am!");
        /*BasicDBObject newDocument = new BasicDBObject();
    	newDocument.append("$set", new BasicDBObject().append("score", 32));
     
    	BasicDBObject searchQuery = new BasicDBObject().append("id", "67341283611_10152875414913612");
     
    	collection.update(searchQuery, newDocument);
        /*DBCollection collection = db.createCollection("mycollection", new BasicDBObject("capped", true)
        .append("size", 1048576));
        /*DBCollection coll = db.getCollection("test");
        System.out.println("Collection mycol selected successfully");
        DBCursor cursor = coll.find();
        int i=1;
        while (cursor.hasNext()) { 
           System.out.println("Inserted Document: "+i); 
           System.out.println(cursor.next()); 
           i++;
    }*/

}}
