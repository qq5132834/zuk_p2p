package com.zuk.test.server.socket.client;
/**
 * Copyright (c) 2006 - 2008 Smaxe Ltd (www.smaxe.com).
 * All rights reserved.
 */

import java.util.Map;

import com.smaxe.uv.client.INetConnection;
import com.smaxe.uv.client.INetStream;
import com.smaxe.uv.client.License;
import com.smaxe.uv.client.NetStream;
import com.smaxe.uv.client.NetConnection;

/**
 * <code>ExPlayStream</code> - {@link UltraNetConnection} usage example.
 * <p> Note: The example shows how to 'play' server stream. The played stream
 * is saved to the local flv file.
 * 
 * @author Andrei Sochirca
 */
public final class Example extends Object
{
    /**
     * Entry point.
     * 
     * @param args
     */
    public static void main(final String[] args)
    {
        // NOTE:
        // you can get Evaluation Key at:
        // http://www.smaxe.com/order.jsf#request_evaluation_key
        // or buy at:
        // http://www.smaxe.com/order.jsf
//        License.setKey("24F11-06023-BE741-00D3A-798B8");
        
        Example app = new Example();
        
        app.start();
    }
    
    /**
     * <code>NetConnectionListener</code> - {@link UltraNetConnection} listener implementation.
     */
    public class NetConnectionListener extends NetConnection.ListenerAdapter
    {
        /**
         * Constructor.
         */
        public NetConnectionListener()
        {
        }
        
        @Override
        public void onAsyncError(final INetConnection source, final String message, final Exception e)
        {
            System.out.println("NetConnection#onAsyncError: " + message + " " + e);
        }
        
        @Override
        public void onIOError(final INetConnection source, final String message)
        {
            System.out.println("NetConnection#onIOError: " + message);
        }
        
        @Override
        public void onNetStatus(final INetConnection source, final Map<String, Object> info)
        {
            System.out.println("NetConnection#onNetStatus: " + info);
            
            final Object code = info.get("code");
            
            if (NetConnection.CONNECT_SUCCESS.equals(code))
            {
            }
            else
            {
                disconnected = true;
            }
        }
    }
    
    
    // fields
    private volatile boolean disconnected = false;
    
    /**
     * Constructor.
     */
    public Example()
    {
    }
    
    /**
     * Starts the example.
     */
    public void start()
    {
        final UltraNetConnection connection = new UltraNetConnection();
        
        connection.addEventListener(new NetConnectionListener());
			connection.client(this);
        
        connection.connect("rtmp://192.168.0.107/oflaDemo", 80);
        
        // wait till connected
        while (!connection.connected() && !disconnected)
        {
            try
            {
                Thread.sleep(100);
            }
            catch (Exception e) {e.printStackTrace();}
        }
        
        if (!disconnected)
        {
            UltraNetStream stream = new UltraNetStream(connection);
            
            stream.addEventListener(new NetStream.ListenerAdapter()
            {
                @Override
                public void onNetStatus(final INetStream source, final Map<String, Object> info)
                {
                    System.out.println("NetStream#onNetStatus: " + info);
                }
            });
            
        }
        
        while (!disconnected)
        {
            try
            {
                Thread.sleep(100);
            }
            catch (Exception e) {/*ignore*/}
        }
        
        connection.close();
    }
    
    public void getSystemOfflineMsg(String str) {
    	System.out.println(str);
    }
    
    public void getSystemMSG(String str) {
    	System.out.println(str);
    }
    
    public void updatePublicInfo(String str) {
    	System.out.println(str);
    }
    
    public void newMessageShow() {
    	System.out.println("新信件");
    }
    
    public void newUpdateSocialityInfo() {
    	System.out.println("新pk");
    }
    
    public void updateAutoUndercityInfo(String str) {
    	System.out.println("自动闯关完成" + str);
    }
    
}