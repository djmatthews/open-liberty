/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.webcontainer.servlet_31_fat.multinbreadapp.war.test.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.ws.webcontainer.servlet_31_fat.multinbreadapp.war.test.listeners.TestAsyncNBReadListener;

/**
 * Servlet implementation class NBReadPostDataFromInputStreamServlet
 */
@WebServlet(
            urlPatterns = { "/ReadParameterFilter/NBReadLargePostDataInputStreamServlet",
                            "/ReadPostDataFromInputStreamFilter/NBReadLargePostDataInputStreamServlet",
                            "/Nofilter/NBReadLargePostDataInputStreamServlet" },
            asyncSupported = true)
public class NBReadLargePostDataInputStreamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(NBReadLargePostDataInputStreamServlet.class.getName());
    private final String classname = "NBReadLargePostDataInputStreamServlet";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NBReadLargePostDataInputStreamServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        this.doWork(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doWork(request, response);
    }

    private void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AsyncContext ac = this.setupAsyncListener(request, response);

        java.io.InputStream input = request.getInputStream();

        LOG.info("--------------------------------------------------------------");
        LOG.info(classname + " input ->" + input);
        ReadListener readListener = null;

        readListener = new TestAsyncNBReadListener((ServletInputStream) input, request, response, ac, "NBReadLargePostDataInputStreamServlet");
        ((ServletInputStream) input).setReadListener(readListener);
    }

    private AsyncContext setupAsyncListener(ServletRequest req, ServletResponse res) {
        // set up ReadListener to read data for processing

        // start async
        AsyncContext ac = req.startAsync();
        // set up async listener
        ac.addListener(new AsyncListener() {

            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                LOG.info(classname + " my asyncListener TestAsyncNBReadListener Complete");
                LOG.info("--------------------------------------------------------------");
            }

            @Override
            public void onError(AsyncEvent event) {
                LOG.info(classname + event.getThrowable().toString());
                LOG.info("--------------------------------------------------------------");
            }

            @Override
            public void onStartAsync(AsyncEvent event) {
                LOG.info(classname + " my asyncListener TestAsyncNBReadListener.onStartAsync");
                LOG.info("--------------------------------------------------------------");
            }

            @Override
            public void onTimeout(AsyncEvent event) {
                LOG.info(classname + " my asyncListener TestAsyncNBReadListener.onTimeout");
                LOG.info("--------------------------------------------------------------");
            }
        }, req, res);

        return ac;
    }

}
