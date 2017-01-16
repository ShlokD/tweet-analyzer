package com.project.shlok.TopicsAnalyzer;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import twitter4j.JSONObject;


/**
 * Polarity of Topics on Social Media
 * CIS 700 - Text Mining for Social Media
 * AnalysisApi.java
 * Purpose: API Endpoint for GET /analytics. Gets results of analysis on tweets
 *
 * @author Shlok Desai
 * @version 1.0 04/23/2016
 */

@Path("analytics")
public class AnalysisApi {
	public static String query="";
	public static TweetAnalytics twitterAnalytics;
	
	
	/**
	   * getEntity
	   * Returns the analysis of tweets collected on a specific query
	   * @param String q - from API Call
	   * @return String - json encoded string containing analysis results
	   * 
	   */
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEntity(@QueryParam("query") String q) {
		query =q ;
		if(twitterAnalytics==null){
		twitterAnalytics = new TweetAnalytics();
		}
		ArrayList<Integer> scores = twitterAnalytics.getSentimentScores(query);
		ArrayList<HashMap<String, Integer>> poskeywords = twitterAnalytics.getPositiveSentimentKeywords(query);
		ArrayList<HashMap<String, Integer>> negkeywords = twitterAnalytics.getNegativeSentimentKeywords(query);
		ArrayList<HashMap<String, Integer>> entities = twitterAnalytics.getNamedEntities(query);
		ArrayList<HashMap<String, Integer>> stories = twitterAnalytics.getStories(query);
		JSONObject object = new JSONObject();
		try{
			object.put("posCount", scores.get(0));
			object.put("negCount", scores.get(1));
			object.put("poskeywords", poskeywords);
			object.put("negkeywords", negkeywords);
			object.put("entities", entities);
			object.put("stories", stories);
			
		}
		catch(Exception e){
			
		}
		
		System.out.println(object.toString());
		return object.toString();
	}
    
}
