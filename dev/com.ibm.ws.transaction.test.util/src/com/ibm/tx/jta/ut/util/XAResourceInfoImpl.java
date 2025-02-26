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
package com.ibm.tx.jta.ut.util;

import java.io.Serializable;

public class XAResourceInfoImpl implements Serializable {
    /**  */
    private static final long serialVersionUID = -4594199413503911796L;

    private final Integer _i;

    private final String _stateFile;

    public XAResourceInfoImpl(int i) {
        _i = i;
        _stateFile = XAResourceImpl.STATE_FILE;
        System.out.println("XAResourceInfo " + _i + " created. State file is " + _stateFile);
    }
    
    public String toString() {
    	return "XAResourceInfo: " + _i;
    }

    public int getKey() {
        return _i;
    }

    public String getStateFile() {
        return _stateFile;
    }

    @Override
    public int hashCode() {
        return _i.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof XAResourceInfoImpl) {
            return _i.equals(((XAResourceInfoImpl) o)._i);
        }

        return false;
    }
}