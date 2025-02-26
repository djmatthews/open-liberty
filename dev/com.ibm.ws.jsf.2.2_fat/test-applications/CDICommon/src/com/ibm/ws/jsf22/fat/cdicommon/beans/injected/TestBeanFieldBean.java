/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package com.ibm.ws.jsf22.fat.cdicommon.beans.injected;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
@TestBeanType
public class TestBeanFieldBean extends FieldBean {

    @Override
    public String getData() {
        return this.getClass() + (value == null ? ":" : ":" + value);
    }

}
