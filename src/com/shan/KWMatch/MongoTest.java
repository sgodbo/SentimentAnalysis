package com.shan.KWMatch;

import java.net.UnknownHostException;

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
        System.out.println("Authentication: "); 
        DBCollection collection = db.createCollection("mycollection", new BasicDBObject("capped", true)
        .append("size", 1048576));
        /*DBCollection coll = db.getCollection("test");
        System.out.println("Collection mycol selected successfully");
        DBCursor cursor = coll.find();
        int i=1;
        while (cursor.hasNext()) { 
           System.out.println("Inserted Document: "+i); 
           System.out.println(cursor.next()); 
           i++;
    }*/System.out.println("mycollection created");

}}
