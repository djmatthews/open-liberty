/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
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
package com.ibm.ws.micrprofile.faulttolerance10.cdi.services;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.ibm.ws.microprofile.faulttolerance.cdi.FTEnablementConfig;
import com.ibm.ws.microprofile.faulttolerance.cdi.config.impl.FTEnablementConfig10Impl;

@Component(service = FTEnablementConfig.class, configurationPolicy = ConfigurationPolicy.IGNORE)
public class FTEnablementConfigComponent extends FTEnablementConfig10Impl {}
