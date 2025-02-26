/*******************************************************************************
 * Copyright (c) 2019, 2023 IBM Corporation and others.
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
package web.wsatAppWithoutAssertion.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import web.wsatAppWithoutAssertion.utils.CommonUtils;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

    private static String TEST_NAME_PARAM = "testName";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Servlet: " + request.getRequestURI());
        System.out.println("Test: " + request.getParameter(TEST_NAME_PARAM));

        final Enumeration<?> params = request.getParameterNames();

        while (params.hasMoreElements()) {
            final String param = (String) params.nextElement();

            if (!TEST_NAME_PARAM.equals(param)) {
                System.out.println(param + ": " + request.getParameter(param));
            }
        }

        final String result = get(request);
        
        response.getWriter().println(result);
    }

	protected String get(HttpServletRequest request) throws ServletException, IOException {

		String server = request.getParameter("server");
		String method = request.getParameter("method");

		StringBuilder sb = new StringBuilder();

		try {
			if (method != null) {
				if (method.equals("init")) {
					CommonUtils.initTable(server);
				}
			}
			
			if (server != null) {
				sb.append(CommonUtils.getDBCount(server));
			} else {
				sb.append("Pease input parameter like: ?server=client&method=init for initialize or ?server=server1 for count");
			}
		} catch (Exception e) {
			sb.append("Get Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
		return sb.toString();
	}
}
