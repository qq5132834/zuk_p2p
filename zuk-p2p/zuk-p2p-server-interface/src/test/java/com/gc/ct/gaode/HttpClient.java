/**
 * 
 */
package com.gc.ct.gaode;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
 

public class HttpClient {
	
    public static String httpGet(final String url) {  
    	
        CloseableHttpClient httpclient = HttpClients.createDefault(); 
        try {  
            HttpGet httpget = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
            httpget.setConfig(requestConfig);
            System.out.println("getURL:" + httpget.getURI());  
            CloseableHttpResponse response = httpclient.execute(httpget);  
            try {  
                HttpEntity entity = response.getEntity(); 
                System.out.println(response.getStatusLine());  
                if (entity != null) {  
                	String content = EntityUtils.toString(entity);
                    System.out.println("getURL-res-content:" + content);
                    return content;
                }  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {
            	if(httpclient!=null){
            		httpclient.close();
            	}
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
        return null;
    }  
     
    /**
     * post方法
     */
    public static String httpPost(String url, Map<String,String> params) {  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        HttpPost httppost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
        httppost.setConfig(requestConfig);
        System.out.println("postURL:" + httppost.getURI());  
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        if(params!=null && params.size()>0){
        	for(Map.Entry<String, String> entry : params.entrySet()){
        		formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
        	}
        }
        UrlEncodedFormEntity uefEntity;  
        try {
            uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");  
            httppost.setEntity(uefEntity);
            System.out.println("executing request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                	String content = EntityUtils.toString(entity, "UTF-8");
                    System.out.println("postUrl-res-content:" + content);
                    return content;
                }  
            } finally {
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
            	if(httpclient!=null){
            		httpclient.close();
            	}
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
        return null;
    }  

}
