/*******************************************************************************
 * Copyright (c) 2019 IBM Corporation and others.
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
package io.openliberty.microprofile.config.internal.serverxml;

/**
 *
 */
public class InvalidFrameworkStateException extends Exception {

    /**
     * @param message
     */
    public InvalidFrameworkStateException() {
        super("Invalid OSGi Framework State");
    }

    /**  */
    private static final long serialVersionUID = 1L;

}
