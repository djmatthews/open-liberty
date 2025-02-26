/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
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
package com.ibm.ws.channelfw.testsuite.channels.outbound;

import java.util.HashMap;
import java.util.Map;

import com.ibm.websphere.channelfw.ChannelData;
import com.ibm.websphere.channelfw.ChannelFactoryData;
import com.ibm.websphere.channelfw.OutboundChannelDefinition;
import com.ibm.wsspi.channelfw.Channel;
import com.ibm.wsspi.channelfw.ChannelFactory;
import com.ibm.wsspi.channelfw.exception.ChannelException;
import com.ibm.wsspi.channelfw.exception.ChannelFactoryException;

/**
 * Factory for creating the outbound test app channel.
 */
@SuppressWarnings("unused")
public class GetterFactory implements ChannelFactory {
    /** Map of the existing channels for this factory. */
    private Map<String, Channel> existingChannels = null;
    /** Property map that may or may not exist for the factory. */
    private Map<Object, Object> commonProperties = null;

    /**
     * Constructor.
     */
    public GetterFactory() {
        this.existingChannels = new HashMap<String, Channel>();
    }

    public void destroy() {
        // nothing
    }

    public Class<?>[] getDeviceInterface() {
        return new Class<?>[] { OutboundDummyContext.class };
    }

    public void init(ChannelFactoryData data) throws ChannelFactoryException {
        // nothing
    }

    /*
     * @see com.ibm.wsspi.channelfw.ChannelFactory#findOrCreateChannel(ChannelData)
     */
    public synchronized Channel findOrCreateChannel(ChannelData channelData) throws ChannelException {
        String channelName = channelData.getName();
        Channel ret = this.existingChannels.get(channelName);
        if (ret == null) {
            // Create the new channel with the input configuration
            ret = new GetterChannel(channelData, this);
            this.existingChannels.put(channelName, ret);
        }
        return ret;
    }

    /**
     * Remove a channel from the existing channels list.
     * 
     * @param channelName
     */
    public synchronized void removeChannel(String channelName) {
        this.existingChannels.remove(channelName);
    }

    /*
     * @see com.ibm.wsspi.channelfw.ChannelFactory#getProperties()
     */
    public Map<Object, Object> getProperties() {
        return this.commonProperties;
    }

    /*
     * @see com.ibm.wsspi.channelfw.ChannelFactory#updateProperties(java.util.Map)
     */
    public void updateProperties(Map<Object, Object> properties) {
        this.commonProperties = properties;
    }

    /*
     * @see com.ibm.wsspi.channelfw.ChannelFactory#getApplicationInterface()
     */
    public final Class<?> getApplicationInterface() {
        throw new IllegalStateException("Not implemented and should not be used");
    }

    /*
     * @see com.ibm.wsspi.channelfw.ChannelFactory#getOutboundChannelDefinition(java.util.Map)
     */
    @Override
    public OutboundChannelDefinition getOutboundChannelDefinition(Map<Object, Object> props) {
        return null;
    }

}
