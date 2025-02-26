/*******************************************************************************
 * Copyright (c) 2014, 2021 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.security.saml.fat.jaxrs.IDPInitiated;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.ibm.websphere.simplicity.log.Log;
import com.ibm.ws.security.saml.fat.jaxrs.common.RSSamlTrustedIssuersTests;
import com.ibm.ws.security.saml20.fat.commonTest.SAMLConstants;
import com.ibm.ws.security.saml20.fat.commonTest.SAMLMessageConstants;

import componenttest.custom.junit.runner.FATRunner;
import componenttest.custom.junit.runner.Mode;
import componenttest.custom.junit.runner.Mode.TestMode;
import componenttest.topology.impl.LibertyServerWrapper;

@LibertyServerWrapper
@Mode(TestMode.FULL)
@RunWith(FATRunner.class)
public class RSSamlIDPInitiatedTrustedIssuers2ServerTests extends RSSamlTrustedIssuersTests {

    private static final Class<?> thisClass = RSSamlIDPInitiatedTrustedIssuers2ServerTests.class;

    @BeforeClass
    public static void setupBeforeTest() throws Exception {

        flowType = SAMLConstants.IDP_INITIATED;

        msgUtils.printClassName(thisClass.toString());
        Log.info(thisClass, "setupBeforeTest", "Prep for test");
        // add any additional messages that you want the "start" to wait for
        // we should wait for any providers that this test requires
        List<String> extraMsgs = getDefaultSAMLStartMsgs();

        List<String> extraMsgs2 = new ArrayList<String>();

        List<String> extraApps = new ArrayList<String>();
        extraApps.add(SAMLConstants.SAML_CLIENT_APP);
        List<String> extraApps2 = new ArrayList<String>();
        extraApps2.add("helloworld");

        copyMetaData = false;

        testAppServer = commonSetUp(APPServerName, "server_trustedIssuers.xml", SAMLConstants.SAML_ONLY_SETUP, SAMLConstants.APP_SERVER_TYPE, extraApps2, extraMsgs2, false);
        commonUtils.fixServer2Ports(testAppServer);
        startSPWithIDPServer(SPServerName, "server_1.xml", extraMsgs, extraApps, true);

        setActionsForFlowType(flowType);

        // set default values for jaxrs settings
        testSettings.setRSSettings();

        // set test app
        testSettings.setSpTargetApp(testAppServer.getServerHttpsString() + "/" + SAMLConstants.PARTIAL_HELLO_WORLD_URI);
        testSettings.setSpDefaultApp(testAppServer.getServerHttpsString() + "/" + SAMLConstants.PARTIAL_HELLO_WORLD_URI);
        testSettings.updatePartnerInSettings("sp1", true);

        // Allow the warning on the ignored attributes of samlWebSso20 inboundPropagation true or false
        testAppServer.addIgnoredServerException(SAMLMessageConstants.CWWKS5207W_SAML_CONFIG_IGNORE_ATTRIBUTES);
        testSAMLServer.addIgnoredServerException(SAMLMessageConstants.CWWKS5207W_SAML_CONFIG_IGNORE_ATTRIBUTES);
    }

}
