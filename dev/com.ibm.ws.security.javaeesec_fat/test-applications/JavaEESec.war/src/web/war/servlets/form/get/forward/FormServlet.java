/*******************************************************************************
 * Copyright (c) 2017, 2018 IBM Corporation and others.
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
package web.war.servlets.form.get.forward;

import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import javax.servlet.annotation.WebServlet;

import web.jar.base.FlexibleBaseServlet;

@FormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(errorPage = "/loginError.jsp", loginPage = "/login.jsp", useForwardToLogin = true,
                                                                          useForwardToLoginExpression = ""))
@WebServlet("/FormServlet")
@ServletSecurity(value = @HttpConstraint(EmptyRoleSemantic.DENY), httpMethodConstraints = { @HttpMethodConstraint(value = "GET", rolesAllowed = "grantedgroup") })
public class FormServlet extends FlexibleBaseServlet {
    private static final long serialVersionUID = 1L;

    public FormServlet() {
        super("FormServlet");

        mySteps.add(new WriteRequestBasicsStep());
        mySteps.add(new WritePrincipalStep());
        mySteps.add(new WriteRolesStep());
        mySteps.add(new WriteSubjectStep());
        mySteps.add(new WritePublicCredentialsStep());
        mySteps.add(new WriteRunAsSubjectStep());
        mySteps.add(new WriteCookiesStep());
        mySteps.add(new WriteJSR375Step());
    }
}
