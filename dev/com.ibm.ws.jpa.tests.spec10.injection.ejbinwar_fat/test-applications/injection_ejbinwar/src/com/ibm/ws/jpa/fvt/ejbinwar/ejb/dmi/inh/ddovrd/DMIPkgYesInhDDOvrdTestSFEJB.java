/*******************************************************************************
 * Copyright (c) 2020 IBM Corporation and others.
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

package com.ibm.ws.jpa.fvt.ejbinwar.ejb.dmi.inh.ddovrd;

import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;

@Stateful(name = "DMIPkgYesInhDDOvrdTestSFEJB")
@Local(DMIPkgYesInhDDOvrdTestSFEJBLocal.class)
@TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class DMIPkgYesInhDDOvrdTestSFEJB extends DMIPkgYesInhDDOvrdTestSuperclass {
    @Override
    @Remove
    public void release() {

    }
}
