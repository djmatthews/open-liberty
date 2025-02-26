/*******************************************************************************
 * Copyright (c) 2006, 2007 IBM Corporation and others.
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
package com.ibm.ws.jpa.management;

import java.sql.SQLException;

/**
 * Indicates that a datasource was not found within the java:comp/env
 * namespace. It can also indicate that that there is no component
 * context on the thread.
 */
public class GlobalDataSourceNotFoundException extends SQLException
{
    private static final long serialVersionUID = 1816214284123404414L;

    public GlobalDataSourceNotFoundException(String reason)
    {
        super(reason);
    }

    public GlobalDataSourceNotFoundException()
    {
        super();
    }

}
