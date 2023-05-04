package com.example.webserver.rest;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;


@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        fillCors(responseContext.getHeaders());
    }

    public static void fillCors(MultivaluedMap<String, Object> m) {
        m.add("Access-Control-Allow-Origin", "*");
        m.add("Access-Control-Allow-Credentials", "true");
        m.add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        m.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }

}