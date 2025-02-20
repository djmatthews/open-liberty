/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation and others.
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
package com.ibm.ws.security.registry;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 *
 */
public class EntryNotFoundExceptionTest {

    /**
     * Test method for {@link com.ibm.ws.security.registry.EntryNotFoundException#EntryNotFoundException(java.lang.String)}.
     */
    @Test
    public void consturctor() {
        assertNotNull("Constructor(String) should succeed",
                      new EntryNotFoundException("msg"));
    }

    /**
     * Test method for {@link com.ibm.ws.security.registry.EntryNotFoundException#EntryNotFoundException(java.lang.String, java.lang.Throwable)}.
     */
    @Test
    public void causeConsturctor() {
        assertNotNull("Constructor(String,Throwable) should succeed",
                      new EntryNotFoundException("msg", new Throwable()));
    }

}
