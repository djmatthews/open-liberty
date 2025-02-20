/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others.
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
package com.ibm.ws.security.openidconnect.client.internal;

import java.io.File;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import com.ibm.websphere.ras.Tr;
import com.ibm.websphere.ras.TraceComponent;

/**
 *
 */
public class FileInfo {
    private static TraceComponent tc = Tr.register(FileInfo.class,
            TraceConstants.TRACE_GROUP,
            TraceConstants.MESSAGE_BUNDLE);

    final File file;
    boolean bExists = false;
    long lLength = -1;
    long lLastModified = -1;
    String path = null;

    private FileInfo(final File file) {
        this.file = file;
    }

    public static FileInfo getFileInfo(final File file) throws Exception {
        FileInfo fileInfo = null;
        try {
            fileInfo = (FileInfo) AccessController.doPrivileged(
                    new PrivilegedExceptionAction<Object>() {
                        @Override
                        public Object run() throws Exception {
                            FileInfo fileInfo = new FileInfo(file);
                            fileInfo.path = file.getPath();
                            fileInfo.bExists = file.exists();
                            fileInfo.lLength = file.length();
                            fileInfo.lLastModified = file.lastModified();
                            return fileInfo;
                        }
                    }
                    );
        } catch (PrivilegedActionException e) {
            throw new Exception(e);
        }
        return fileInfo;
    }

    public String getPath() {
        return path;
    }

    public boolean exists() {
        return bExists;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj instanceof FileInfo) {
            FileInfo newFileInfo = (FileInfo) obj;
            boolean result = newFileInfo.file.equals(this.file) &&
                    newFileInfo.lLastModified == this.lLastModified &&
                    newFileInfo.lLength == this.lLength;
            if (result == false && tc.isDebugEnabled()) {
                Tr.debug(tc, "Equals:" + newFileInfo.file.equals(this.file) + " " +
                        (newFileInfo.lLastModified == this.lLastModified) + " " +
                        (newFileInfo.lLength == this.lLength));
            }
            return result;
        }
        return false;
    }

    /**
     * Get the host name.
     * 
     * @return String value of the host name or "localhost" if not able to resolve
     */
    public static String getHostName() {
        try {
            String hostName = (String) AccessController.doPrivileged(
                    new PrivilegedExceptionAction<Object>() {
                        @Override
                        public Object run() throws Exception {
                            return java.net.InetAddress.getLocalHost().getCanonicalHostName().toLowerCase();
                        }
                    }
                    );
            return hostName;
        } catch (PrivilegedActionException e) {
            return "localhost";
        }

    }
}
