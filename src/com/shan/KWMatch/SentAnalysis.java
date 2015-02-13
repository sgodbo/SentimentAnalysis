package com.shan.KWMatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.jayway.jsonpath.JsonPath;

public class SentAnalysis {

    /**
     * @param args
     * @throws IOException 
     * @throws MalformedURLException 
     */
    public static void main(
            String[] args) throws MalformedURLException, IOException {
        // TODO Auto-generated method stub
        String url = "https://graph.facebook.com/v2.2/marksandspencer?fields=posts{message,comments{message}}&access_token=CAACEdEose0cBANza2ZB6EUCj9BEYoKHf931svbvNBb3d0ZBok2WkzitHlEo8tPVNtYl9WkOHKZAKQapCGHyHUA2OWPpaxp5lzxGYZB5MSgn6lY7bcToKqB72hVoYOE9vAQdTpB0JiRRcj3Tt50sEwsZAKx05MJ0XwWIwUpbn61TNZC0OQ5iTvHbQVy1yZAtUZBcc8wJ6G6LwR1EEb7sgW7tN";
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", "UTF-32");
        connection.connect();
        HttpURLConnection httpConn = (HttpURLConnection) connection;
        System.out.println(httpConn.getResponseCode());
        
        InputStream response =  connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(response));
        String newLine = "";
        Object jsonPathResult;
        while((newLine = br.readLine()) != null){
             
        }
        jsonPathResult = JsonPath.read(json, jsonPath, filters)
    }

}
