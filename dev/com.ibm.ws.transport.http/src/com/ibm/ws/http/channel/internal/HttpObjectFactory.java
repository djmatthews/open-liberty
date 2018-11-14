/*******************************************************************************
 * Copyright (c) 2004, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.http.channel.internal;

import com.ibm.websphere.ras.Tr;
import com.ibm.websphere.ras.TraceComponent;
import com.ibm.ws.kernel.service.util.ConcurrentObjectPool;
import com.ibm.wsspi.http.channel.inbound.HttpInboundServiceContext;
import com.ibm.wsspi.http.channel.outbound.HttpOutboundServiceContext;

/**
 * Factory for all of the pooled objects used in the HTTP channel.
 *
 */
public class HttpObjectFactory {

    /** RAS tracing variable */
    private static final TraceComponent tc = Tr.register(HttpObjectFactory.class, HttpMessages.HTTP_TRACE_NAME, HttpMessages.HTTP_BUNDLE);

    /** Size of the pool */
    private static final int POOL_SIZE = 100;

    /** Pool of http request objects */
    private final ConcurrentObjectPool<HttpRequestMessageImpl> reqPool = new ConcurrentObjectPool<>(POOL_SIZE);
    /** Pool of http response objects */
    private final ConcurrentObjectPool<HttpResponseMessageImpl> respPool = new ConcurrentObjectPool<>(POOL_SIZE);
    /** Pool of http trailer objects */
    private final ConcurrentObjectPool<HttpTrailersImpl> hdrPool = new ConcurrentObjectPool<>(POOL_SIZE);

    /**
     * Constructor of the object factory.
     */
    public HttpObjectFactory() {
        // trigger the FFDC registration if it hasn't already happened
        if (TraceComponent.isAnyTracingEnabled() && tc.isDebugEnabled()) {
            Tr.debug(tc, "New HTTP object factory created: " + this);
        }
    }

    /**
     * Retrieve an uninitialized request message.
     *
     * @return HttpRequestMessageImpl
     */
    public HttpRequestMessageImpl getRequest() {
        HttpRequestMessageImpl req = this.reqPool.get();
        if (null == req) {
            req = new HttpRequestMessageImpl();
        }
        if (TraceComponent.isAnyTracingEnabled() && tc.isDebugEnabled()) {
            Tr.debug(tc, "getRequest(): " + req);
        }
        return req;
    }

    /**
     * Retrieve an incoming request object from the factory.
     *
     * @param hsc
     * @return HttpRequestMessageImpl
     */
    public HttpRequestMessageImpl getRequest(HttpInboundServiceContext hsc) {
        HttpRequestMessageImpl req = getRequest();
        req.init(hsc);
        return req;
    }

    /**
     * Retrieve an outgoing request object from the factory.
     *
     * @param hsc
     * @return HttpRequestMessageImpl
     */
    public HttpRequestMessageImpl getRequest(HttpOutboundServiceContext hsc) {
        HttpRequestMessageImpl req = getRequest();
        req.init(hsc);
        return req;
    }

    /**
     * Return a request object to the factory for pooling.
     *
     * @param request
     */
    public void releaseRequest(HttpRequestMessageImpl request) {
        if (TraceComponent.isAnyTracingEnabled() && tc.isDebugEnabled()) {
            Tr.debug(tc, "releaseRequest: " + request);
        }
        this.reqPool.put(request);
    }

    /**
     * Retrieve an uninitialized response message.
     *
     * @return HttpResponseMessageImpl
     */
    public HttpResponseMessageImpl getResponse() {
        HttpResponseMessageImpl resp = this.respPool.get();
        if (null == resp) {
            resp = new HttpResponseMessageImpl();
        }
        if (TraceComponent.isAnyTracingEnabled() && tc.isDebugEnabled()) {
            Tr.debug(tc, "getResponse(): " + resp);
        }
        return resp;
    }

    /**
     * Retrieve an outgoing response object from the factory.
     *
     * @param hsc
     * @return HttpResponseMessageImpl
     */
    public HttpResponseMessageImpl getResponse(HttpInboundServiceContext hsc) {
        HttpResponseMessageImpl resp = getResponse();
        resp.init(hsc);
        return resp;
    }

    /**
     * Retrieve an incoming response object from the factory.
     *
     * @param hsc
     * @return HttpResponseMessageImpl
     */
    public HttpResponseMessageImpl getResponse(HttpOutboundServiceContext hsc) {
        HttpResponseMessageImpl resp = getResponse();
        resp.init(hsc);
        return resp;
    }

    /**
     * Return a response object to the factory for pooling.
     *
     * @param response
     */
    public void releaseResponse(HttpResponseMessageImpl response) {
        if (TraceComponent.isAnyTracingEnabled() && tc.isDebugEnabled()) {
            Tr.debug(tc, "releaseResponse: " + response);
        }
        this.respPool.put(response);
    }

    /**
     * Get a new trailers object. init() is not called on the object so the
     * default values for certain variables are used (byte cache size, etc).
     *
     * @return HttpTrailersImpl
     */
    public HttpTrailersImpl getTrailers() {

        Object o = this.hdrPool.get();
        HttpTrailersImpl hdrs = (null == o) ? new HttpTrailersImpl() : (HttpTrailersImpl) o;
        hdrs.setFactory(this);
        if (TraceComponent.isAnyTracingEnabled() && tc.isDebugEnabled()) {
            Tr.debug(tc, "getTrailers: " + hdrs);
        }
        return hdrs;
    }

    /**
     * Return a trailers object to the pool.
     *
     * @param h
     */
    public void releaseTrailers(HttpTrailersImpl h) {
        if (TraceComponent.isAnyTracingEnabled() && tc.isDebugEnabled()) {
            Tr.debug(tc, "releaseTrailers: " + h);
        }
        this.hdrPool.put(h);
    }
}