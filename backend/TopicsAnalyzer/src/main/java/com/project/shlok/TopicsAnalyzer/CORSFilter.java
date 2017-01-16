package com.project.shlok.TopicsAnalyzer;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 * Polarity of Topics on Social Media
 * CIS 700 - Text Mining for Social Media
 * CORSFilter.java
 * Purpose: Adds a filter on the server to allow cross-origin requests
 *
 * @author Shlok Desai
 * @version 1.0 04/23/2016
 */
public class CORSFilter implements ContainerResponseFilter {
	public ContainerResponse filter(ContainerRequest req, ContainerResponse contResp) {

        ResponseBuilder resp = Response.fromResponse(contResp.getResponse());
        resp.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "API,GET, PUT, POST, DELETE, HEAD, OPTIONS");

        String reqHead = req.getHeaderValue("Access-Control-Request-Headers");

        if(null != reqHead && !reqHead.equals(null)){
            resp.header("Access-Control-Allow-Headers", reqHead);
        }

        contResp.setResponse(resp.build());
            return contResp;
    }
}
