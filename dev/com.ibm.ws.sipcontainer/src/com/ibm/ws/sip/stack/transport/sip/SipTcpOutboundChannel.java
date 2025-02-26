/*******************************************************************************
 * Copyright (c) 2008, 2009 IBM Corporation and others.
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
package com.ibm.ws.sip.stack.transport.sip;

import com.ibm.websphere.channelfw.ChannelData;
import com.ibm.wsspi.tcpchannel.TCPConnectRequestContext;
import com.ibm.wsspi.tcpchannel.TCPConnectionContext;

/**
 * an outbound tcp channel
 * 
 * @author ran
 */
public class SipTcpOutboundChannel extends SipOutboundChannel
{
	
	public final static String SipTcpOutboundChannelName = "SipTcpOutboundChannel";
	/**
	 * constructor
	 */
	public SipTcpOutboundChannel(ChannelData config) {
		super(config);
	}

	/**
	 * @see com.ibm.wsspi.channelfw.Channel#getDeviceInterface()
	 */
	public Class getDeviceInterface() {
		return TCPConnectionContext.class;
	}

	/**
	 * @see com.ibm.wsspi.channelfw.OutboundChannel#getDeviceAddress()
	 * @see SipTcpOutboundConnLink#createConnectRequestContext(String, int)
	 */
	public Class getDeviceAddress() {
		return TCPConnectRequestContext.class;
	}

}
