package com.shan.KWMatch;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.alchemyapi.api.AlchemyAPI;
import com.alchemyapi.api.AlchemyAPI_Params;

public class AlchemyCustomized extends AlchemyAPI{
	@Override
	public Document POST(String callName, String callPrefix, AlchemyAPI_Params params)
	        throws IOException, SAXException,
	               ParserConfigurationException, XPathExpressionException
	    {
	        URL url = new URL(_requestUri + callPrefix + "/" + callName);

	        HttpURLConnection handle = (HttpURLConnection) url.openConnection();
	        handle.setDoOutput(true);

	        StringBuilder data = new StringBuilder();

	        data.append("apikey=").append(this._apiKey);
	        data.append(params.getParameterString());

	        handle.addRequestProperty("Content-Length", Integer.toString(data.length()));

	        DataOutputStream ostream = new DataOutputStream(handle.getOutputStream());
	        ostream.write(data.toString().getBytes());
	        ostream.close();

	        return doRequest(handle, params.getOutputMode());
	    }



}
