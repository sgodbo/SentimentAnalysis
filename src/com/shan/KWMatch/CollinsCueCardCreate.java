package com.shan.KWMatch;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

public class CollinsCueCardCreate {

    /**
     * @param args
     */
    @SuppressWarnings("deprecation")
    public static void main(
            String[] args) {
        // TODO Auto-generated method stub
        DefaultHttpClient httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());
        String baseUrl = "";
        String accessKey = "";
        callCollinsApi(baseUrl,accessKey,httpClient);
        /*SkPublishAPI api = new SkPublishAPI( + "/api/v1", accessKey, httpClient);
        api.setRequestHandler(new SkPublishAPI.RequestHandler() { public void prepareGetRequest(HttpGet request) { 
            System.out.println(request.getURI()); request.setHeader("Accept", "application/json"); } });*/
    }

    private static void callCollinsApi(
            String baseUrl, String accessKey, DefaultHttpClient httpClient) {
        // TODO Auto-generated method stub
        
    }

}
