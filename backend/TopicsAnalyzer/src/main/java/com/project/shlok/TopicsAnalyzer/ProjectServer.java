package com.project.shlok.TopicsAnalyzer;

import java.io.IOException;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
/**
 * Polarity of Topics on Social Media
 * CIS 700 - Text Mining for Social Media
 * ProjectServer.java
 * Purpose: Driver program. Creates an HTTP Server and relays GET requests 
 *
 * @author Shlok Desai
 * @version 1.0 04/23/2016
 */

public class ProjectServer {
	static final String BASE_URL = "http://localhost:9998/";
	@SuppressWarnings("unchecked")
	
	

	/**
	   * Main Method of ProjectServer class
	   * Starts an HTTP Server and uses Jersey API to relay GET requests to proper paths
	   * @param args Unused
	   * @return None
	   * 
	   */
	public static void main(String[] args) throws IOException{
		try {
			ResourceConfig rConfig = new PackagesResourceConfig("com.project.shlok.TopicsAnalyzer");
			rConfig.getContainerResponseFilters().add(CORSFilter.class);
            HttpServer server = HttpServerFactory.create(BASE_URL,rConfig);
            server.start();
            System.out.println("Server started at: "+BASE_URL);
            System.out.println("Press Enter to stop the server. ");
            System.in.read();
            server.stop(0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	
		
	}
}



	
