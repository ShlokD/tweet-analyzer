package com.project.shlok.TopicsAnalyzer;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


/**
 * Polarity of Topics on Social Media
 * CIS 700 - Text Mining for Social Media
 * ReaderApi.java
 * Purpose: API Endpoint for GET /read.Starts reading stream of tweets regarding a topic
 *
 * @author Shlok Desai
 * @version 1.0 04/23/2016
 */
@Path("read")
public class ReaderApi {
	 
	public static String query="";
	public static TwitterReader twitterReader;
	
	

	/**
	   * getQuery
	   * Start reading tweets regarding a topic from TwitterStream API
	   * @param String q - from API Call
	   * @return String - result of the operation
	   * 
	   */
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getQuery(@QueryParam("query") String q) {
    	if(twitterReader==null){
    		twitterReader= new TwitterReader();
    		
    	}
    	try{
    		query=q;
    		if(q.equals("stop") || query.equals("stop")){ // To Stop Reading Tweets
    			twitterReader.stop();
    		}
    		else{
    		twitterReader.readTweets(q);
    		}
            return "200";
        	}
        	catch(Exception e){
        		e.printStackTrace();
        		return "500 Error";
        	}
    }
}
