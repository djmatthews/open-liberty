/*******************************************************************************
 * Copyright (c) 2022 IBM Corporation and others.
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
package com.ibm.adapter.activationspec.jbv;

import javax.validation.constraints.Size;

public class JBVFATActSpecSuccessImpl extends JBVFATActSpecImpl {

    public Double getAsProperty4() {
        return asProperty4;
    }

    @Size(min = 2, max = 4)
    public String getAsProperty1() {
        return asProperty1;
    }

}
