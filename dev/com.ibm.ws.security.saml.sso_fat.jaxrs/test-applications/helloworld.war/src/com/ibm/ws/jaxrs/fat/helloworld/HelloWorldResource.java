/*******************************************************************************
 * Copyright (c) 2011, 2021 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package com.ibm.ws.jaxrs.fat.helloworld;

import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * <code>HelloWorldResource</code> is a simple POJO which is annotated with
 * JAX-RS annotations to turn it into a JAX-RS resource.
 * <p/>
 * This class has a {@link Path} annotation with the value "helloworld" which means the resource will be available at:
 * <code>http://&lt;hostname&gt;:&lt;port&gt/&lt;context root&gt;/&lt;servlet path&gt;/helloworld</code>
 * <p/>
 * Remember to add this resource class to the {@link HelloWorldApplication#getClasses()} method.
 */
//@Path("/{app: [a-z]?}helloworld")
//@Path("/{app: helloworld[.]*}")
@Path("/{app: helloworld.*}")
public class HelloWorldResource {

    /**
     * A static variable to hold a message. Note that for this sample, the field
     * is static because a new <code>HelloWorldResource</code> object is created
     * per request.
     */
    private static String message;

    /**
     * Processes a GET request and returns the stored message.
     * 
     * @return the stored message
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage(@Context HttpHeaders headers, @HeaderParam("saml_name") String tokenName) {

        HelloWorldResource.message = "Hello World!";

        MultivaluedMap<String, String> allHeaders = headers.getRequestHeaders();
        Iterator<String> it = allHeaders.keySet().iterator();
        while (it.hasNext()) {
            String theKey = (String) it.next();
            System.out.println("found header key/value: key: " + theKey + " value: " + allHeaders.getFirst(theKey));
        }

        if (tokenName == null) {
            tokenName = "saml_token";
        }

        System.out.println("SAML Token Name is: " + tokenName);
        String stoken = null;
        List<String> tokenFromHeader = headers.getRequestHeader(tokenName);
        if (tokenFromHeader == null) {
            System.out.println("Accessed helloworld with no SAML Token");
            HelloWorldResource.message = "Accessed helloworld with no SAML Token";
        } else {
            System.out.println("tokenFromHeader is NOT null");
            stoken = tokenFromHeader.get(0);
            if (!samlTokenValid(stoken)) {
                HelloWorldResource.message = "JAX-RS Hello World Web service: SAML token is not valid.";
                System.out.println("should be false");
            }
        }

        // Note that if null is returned from a resource method, a HTTP 204 (No
        // Content) status code response is sent.

        StringBuffer sb = new StringBuffer();
        SAMLCallerUtil.writeLine(sb, "\n");

        SAMLCallerUtil.writeLine(sb, "RealmName: " + SAMLCallerUtil.getRealmName());

        SAMLCallerUtil.printProgrammaticApiValues(sb);

        //        System.out.println(HelloWorldResource.message) ;

        SAMLCallerUtil.writeLine(sb, HelloWorldResource.message);
        System.out.println(sb.toString());

        return sb.toString();

    }

    private boolean samlTokenValid(String samlToken) {

        if (samlToken == null) {
            return false;
        } else {
            System.out.println("SAML Token value is: " + samlToken);

        }
        // Add code to validate received SAML token here.
        return true;

    }

    /**
     * Processes a POST request and returns the incoming request message.
     * 
     * @param incomingMessage
     *            the request body is mapped to the String by the
     *            JAX-RS runtime using a built-in entity provider
     * @return the original request body
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String postMessage(String incomingMessage) {
        // A plain Java parameter is used to represent the request body. The
        // JAX-RS runtime will map the request body to a String.
        HelloWorldResource.message = incomingMessage;
        return incomingMessage;
    }

    /**
     * Processes a PUT request and returns the incoming request message.
     * 
     * @param incomingMessage
     *            the request body is mapped to the byte[] by the
     *            JAX-RS runtime using a built-in entity provider
     * @return the original request body in a JAX-RS Response object
     */
    @PUT
    public Response putMessage(byte[] incomingMessage) {
        // Note that different Java types can be used to map the
        // incoming request body to a Java type.
        HelloWorldResource.message = new String(incomingMessage);

        // Note that a javax.ws.rs.core.Response object is returned. A Response
        // object can be built which contains additional HTTP headers, a status
        // code, and the entity body.
        return Response.ok(incomingMessage).type(MediaType.TEXT_PLAIN).build();
    }

    /**
     * Processes a DELETE request.
     * 
     * @return an empty response with a 204 status code
     */
    @DELETE
    public Response deleteMessage() {
        HelloWorldResource.message = null;
        // Note that a javax.ws.rs.core.Response object is returned. In this
        // method a HTTP 204 status code (No Content) is returned.
        return Response.noContent().build();
    }
}
