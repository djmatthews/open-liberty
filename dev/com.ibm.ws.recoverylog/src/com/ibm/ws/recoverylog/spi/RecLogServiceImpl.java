/*******************************************************************************
 * Copyright (c) 1997, 2023 IBM Corporation and others.
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

package com.ibm.ws.recoverylog.spi;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import org.osgi.service.component.ComponentContext;

import com.ibm.websphere.ras.Tr;
import com.ibm.websphere.ras.TraceComponent;
import com.ibm.ws.ffdc.FFDCFilter;
import com.ibm.ws.ffdc.annotation.FFDCIgnore;

//------------------------------------------------------------------------------
//Class: RecLogServiceImpl
//------------------------------------------------------------------------------
/**
 * Service intiailizer for the recovery log service. This class is referenced
 * in the ws\code\recovery.log.impl\src\META-INF\ws-applicationserver-startup.xml
 * file that gets packed up into the reclogImpl.jar file. The service runtime
 * uses this xml file to determine the name of this class and drives it
 * during the server startup cycle.
 */
public class RecLogServiceImpl {
    private static TraceComponent tc = Tr.register(RecLogServiceImpl.class,
                                                   TraceConstants.TRACE_GROUP, TraceConstants.NLS_FILE);

    public RecLogServiceImpl() {
        if (tc.isDebugEnabled())
            Tr.debug(tc, "RecLogServiceImpl", this);
    }

    //------------------------------------------------------------------------------
    // Method: RecLogServiceImpl.initialize
    //------------------------------------------------------------------------------
    /**
     * Driven by the runtime during server startup. Although this method itself does
     * nothing here, other components that wish to use the recovery log service will
     * register with it during their initialize methods. The result is that we can
     * drive local recovery from within the start method below (called after this
     * method)
     */
    public void initialize(String serverName) {

        if (tc.isEntryEnabled())
            Tr.entry(tc, "initialize", serverName);

        // Lookup configuration information and store this away in the configuration class.

        String installDirectory = "dir";
        Configuration.serverName(serverName);

        Configuration.WASInstallDirectory(installDirectory);
        Configuration.localFailureScope(new FileFailureScope());

        // Componentization config required to remove WAS dependencies
        Configuration.setRecoveryLogComponent(new RecoveryLogComponentImpl());

        // Register the file based FailureScope factory.
        FailureScopeManager.registerFailureScopeFactory(FailureScopeFactory.FILE_FAILURE_SCOPE_ID, FileFailureScope.class, new FileFailureScopeFactory());

        if (tc.isEntryEnabled())
            Tr.exit(tc, "initialize");
    }

    /**
     * Called by DS to activate service
     */
    protected void activate(ComponentContext cc) {
        if (tc.isDebugEnabled())
            Tr.debug(tc, "activate", this);
    }

    public void unsetRecoveryLogFactory(RecoveryLogFactory fac) {
        if (tc.isDebugEnabled())
            Tr.debug(tc, "unsetRecoveryLogFactory, factory: " + fac, this);
    }

    //------------------------------------------------------------------------------
    // Method: RecLogServiceImpl.start
    //------------------------------------------------------------------------------
    /**
     * Driven by the runtime during server startup. This 'hook' is used to perform
     * recovery log service initialization.
     *
     * @throws RecoveryFailedException
     * @throws InternalLogException
     */
    @FFDCIgnore({ RecoveryFailedException.class })
    public void startRecovery(RecoveryLogFactory fac) throws RecoveryFailedException, InternalLogException {
        if (tc.isEntryEnabled())
            Tr.entry(tc, "startRecovery", fac);

        // This is a stand alone server. HA can never effect this server so direct local recovery now.
        RecoveryDirector director = null;
        try {
            director = RecoveryDirectorFactory.recoveryDirector(); /* @LI1578-22A */
            director.setRecoveryLogFactory(fac);
            ((RecoveryDirectorImpl) director).driveLocalRecovery();/* @LI1578-22C */
        } catch (RecoveryFailedException exc) {
            if (tc.isDebugEnabled())
                Tr.debug(tc, "Local recovery failed.");
            if (tc.isEntryEnabled())
                Tr.exit(tc, "start", "RuntimeError");
            throw exc;
        } catch (InternalLogException ile) {
            FFDCFilter.processException(ile, "com.ibm.ws.recoverylog.spi.RecLogServiceImpl.startRecovery", "478", this);
            if (tc.isDebugEnabled())
                Tr.debug(tc, "Local recovery not attempted.", ile);
            if (tc.isEntryEnabled())
                Tr.exit(tc, "start", "RuntimeError");
            throw ile;
        }

        startPeerRecovery(director);

        if (tc.isEntryEnabled())
            Tr.exit(tc, "startRecovery");
    }

    @FFDCIgnore({ RecoveryFailedException.class })
    public void startPeerRecovery(RecoveryDirector director) {
        if (tc.isEntryEnabled())
            Tr.entry(tc, "startPeerRecovery", director);

        if (director != null && Configuration.HAEnabled()) {
            if (checkPeersAtStartup()) {
                try {
                    if (director instanceof RecoveryDirectorImpl) {
                        ((RecoveryDirectorImpl) director).drivePeerRecovery();
                    }
                } catch (RecoveryFailedException exc) {
                    if (tc.isDebugEnabled())
                        Tr.debug(tc, "Peer recovery failed.");
                }
            }
        }

        if (tc.isEntryEnabled())
            Tr.exit(tc, "startPeerRecovery");
    }

    //------------------------------------------------------------------------------
    // Method: RecLogServiceImpl.stop
    //------------------------------------------------------------------------------
    /**
     * Driven by the runtime during server shutdown. This 'hook' is not used.
     */
    public void stop() {
    }

    //------------------------------------------------------------------------------
    // Method: RecLogServiceImpl.destroy
    //------------------------------------------------------------------------------
    /**
     * Driven by the runtime during server shutdown. This 'hook' is not used.
     */
    public void destroy() {
    }

    /**
     * @param isPeerRecoverySupported the _isPeerRecoverySupported to set
     */
    public void setPeerRecoverySupported(boolean isPeerRecoverySupported) {
        Configuration.HAEnabled(isPeerRecoverySupported);
    }

    /**
     * This method retrieves a system property named com.ibm.ws.recoverylog.spi.CheckPeersAtStartup
     * which allows the check to see if peer servers are stale to be bypassed at server startup. The checks
     * will subsequently be performed through the spun-off timer thread.
     *
     * @return
     */
    private boolean checkPeersAtStartup() {
        if (tc.isEntryEnabled())
            Tr.entry(tc, "checkPeersAtStartup");

        boolean checkAtStartup;

        try {
            checkAtStartup = AccessController.doPrivileged(
                                                           new PrivilegedExceptionAction<Boolean>() {
                                                               @Override
                                                               public Boolean run() {
                                                                   return Boolean.getBoolean("com.ibm.ws.recoverylog.spi.CheckPeersAtStartup");
                                                               }
                                                           });
        } catch (PrivilegedActionException e) {
            if (tc.isDebugEnabled())
                Tr.debug(tc, "checkPeersAtStartup", e);
            checkAtStartup = false;
        }

        if (tc.isEntryEnabled())
            Tr.exit(tc, "checkPeersAtStartup", checkAtStartup);
        return checkAtStartup;
    }
}
