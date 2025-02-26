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
// POA Tie class generated by rmic, do not edit.
// Contents subject to change without notice.

package com.ibm.ws.clientcontainer.remote.server;

import com.ibm.ws.container.service.naming.RemoteObjectInstance;
import java.io.Serializable;
import java.lang.ClassCastException;
import java.lang.String;
import java.lang.Throwable;
import java.rmi.Remote;
import java.util.Collection;
import java.util.Set;
import javax.naming.NamingException;
import javax.rmi.CORBA.Tie;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.BAD_PARAM;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.ResponseHandler;
import org.omg.CORBA.portable.UnknownException;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class _ClientSupportImpl_Tie extends org.omg.PortableServer.Servant implements Tie {
    
    private ClientSupportImpl target = null;
    
    private static final String[] _type_ids = {
        "RMI:com.ibm.ws.clientcontainer.remote.common.ClientSupport:0000000000000000"
    };
    
    public void setTarget(Remote target) {
        this.target = (ClientSupportImpl) target;
    }
    
    public Remote getTarget() {
        return target;
    }
    
    public Object thisObject() {
        return _this_object();
    }
    
    public void deactivate() {
        try {
            _poa().deactivate_object(_poa().servant_to_id(this));
        }
        catch(WrongPolicy e) { }
        catch(ObjectNotActive e) { }
        catch(ServantNotActive e) { }
    }
    
    public ORB orb() {
        return _orb();
    }
    
    public void orb(ORB orb) {
        try {
            ((org.omg.CORBA_2_3.ORB)orb).set_delegate(this);
        }
        catch(ClassCastException e) {
            throw new BAD_PARAM("POA Servant needs an org.omg.CORBA_2_3.ORB");
        }
    }
    
    public String[] _all_interfaces(POA poa, byte[] objectId) { 
        return (String [] )  _type_ids.clone();
    }
    
    public OutputStream _invoke(String method, InputStream _in, ResponseHandler reply) throws SystemException {
        try {
            org.omg.CORBA_2_3.portable.InputStream in = 
                (org.omg.CORBA_2_3.portable.InputStream) _in;
            switch (method.length()) {
                case 17: 
                    if (method.equals("findEJBByBeanName")) {
                        return findEJBByBeanName(in, reply);
                    }
                case 18: 
                    if (method.equals("findEJBByInterface")) {
                        return findEJBByInterface(in, reply);
                    }
                case 19: 
                    if (method.equals("listRemoteInstances")) {
                        return listRemoteInstances(in, reply);
                    }
                case 23: 
                    if (method.equals("getRemoteObjectInstance")) {
                        return getRemoteObjectInstance(in, reply);
                    }
                case 25: 
                    if (method.equals("hasRemoteObjectWithPrefix")) {
                        return hasRemoteObjectWithPrefix(in, reply);
                    }
                case 27: 
                    if (method.equals("getEJBRmicCompatibleClasses")) {
                        return getEJBRmicCompatibleClasses(in, reply);
                    }
                case 69: 
                    if (method.equals("createEJB__CORBA_WStringValue__CORBA_WStringValue__CORBA_WStringValue")) {
                        return createEJB__CORBA_WStringValue__CORBA_WStringValue__CORBA_WStringValue(in, reply);
                    }
                case 89: 
                    if (method.equals("createEJB__CORBA_WStringValue__CORBA_WStringValue__CORBA_WStringValue__CORBA_WStringValue")) {
                        return createEJB__CORBA_WStringValue__CORBA_WStringValue__CORBA_WStringValue__CORBA_WStringValue(in, reply);
                    }
            }
            throw new BAD_OPERATION();
        } catch (SystemException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw new UnknownException(ex);
        }
    }
    
    private OutputStream getRemoteObjectInstance(org.omg.CORBA_2_3.portable.InputStream in , ResponseHandler reply) throws Throwable {
        String arg0 = (String) in.read_value(String.class);
        String arg1 = (String) in.read_value(String.class);
        String arg2 = (String) in.read_value(String.class);
        String arg3 = (String) in.read_value(String.class);
        String arg4 = (String) in.read_value(String.class);
        RemoteObjectInstance result;
        try {
            result = target.getRemoteObjectInstance(arg0, arg1, arg2, arg3, arg4);
        } catch (NamingException ex) {
            String id = "IDL:javax/naming/NamingEx:1.0";
            org.omg.CORBA_2_3.portable.OutputStream out = 
                (org.omg.CORBA_2_3.portable.OutputStream) reply.createExceptionReply();
            out.write_string(id);
            out.write_value(ex,NamingException.class);
            return out;
        }
        org.omg.CORBA_2_3.portable.OutputStream out = 
            (org.omg.CORBA_2_3.portable.OutputStream) reply.createReply();
        out.write_value((Serializable)result,RemoteObjectInstance.class);
        return out;
    }
    
    private OutputStream hasRemoteObjectWithPrefix(org.omg.CORBA_2_3.portable.InputStream in , ResponseHandler reply) throws Throwable {
        String arg0 = (String) in.read_value(String.class);
        String arg1 = (String) in.read_value(String.class);
        String arg2 = (String) in.read_value(String.class);
        String arg3 = (String) in.read_value(String.class);
        String arg4 = (String) in.read_value(String.class);
        boolean result;
        try {
            result = target.hasRemoteObjectWithPrefix(arg0, arg1, arg2, arg3, arg4);
        } catch (NamingException ex) {
            String id = "IDL:javax/naming/NamingEx:1.0";
            org.omg.CORBA_2_3.portable.OutputStream out = 
                (org.omg.CORBA_2_3.portable.OutputStream) reply.createExceptionReply();
            out.write_string(id);
            out.write_value(ex,NamingException.class);
            return out;
        }
        OutputStream out = reply.createReply();
        out.write_boolean(result);
        return out;
    }
    
    private OutputStream listRemoteInstances(org.omg.CORBA_2_3.portable.InputStream in , ResponseHandler reply) throws Throwable {
        String arg0 = (String) in.read_value(String.class);
        String arg1 = (String) in.read_value(String.class);
        String arg2 = (String) in.read_value(String.class);
        String arg3 = (String) in.read_value(String.class);
        String arg4 = (String) in.read_value(String.class);
        Collection result;
        try {
            result = target.listRemoteInstances(arg0, arg1, arg2, arg3, arg4);
        } catch (NamingException ex) {
            String id = "IDL:javax/naming/NamingEx:1.0";
            org.omg.CORBA_2_3.portable.OutputStream out = 
                (org.omg.CORBA_2_3.portable.OutputStream) reply.createExceptionReply();
            out.write_string(id);
            out.write_value(ex,NamingException.class);
            return out;
        }
        org.omg.CORBA_2_3.portable.OutputStream out = 
            (org.omg.CORBA_2_3.portable.OutputStream) reply.createReply();
        out.write_value((Serializable)result,Collection.class);
        return out;
    }
    
    private OutputStream getEJBRmicCompatibleClasses(org.omg.CORBA_2_3.portable.InputStream in , ResponseHandler reply) throws Throwable {
        String arg0 = (String) in.read_value(String.class);
        Set result = target.getEJBRmicCompatibleClasses(arg0);
        org.omg.CORBA_2_3.portable.OutputStream out = 
            (org.omg.CORBA_2_3.portable.OutputStream) reply.createReply();
        out.write_value((Serializable)result,Set.class);
        return out;
    }
    
    private OutputStream createEJB__CORBA_WStringValue__CORBA_WStringValue__CORBA_WStringValue(org.omg.CORBA_2_3.portable.InputStream in , ResponseHandler reply) throws Throwable {
        String arg0 = (String) in.read_value(String.class);
        String arg1 = (String) in.read_value(String.class);
        String arg2 = (String) in.read_value(String.class);
        RemoteObjectInstance result;
        try {
            result = target.createEJB(arg0, arg1, arg2);
        } catch (NamingException ex) {
            String id = "IDL:javax/naming/NamingEx:1.0";
            org.omg.CORBA_2_3.portable.OutputStream out = 
                (org.omg.CORBA_2_3.portable.OutputStream) reply.createExceptionReply();
            out.write_string(id);
            out.write_value(ex,NamingException.class);
            return out;
        }
        org.omg.CORBA_2_3.portable.OutputStream out = 
            (org.omg.CORBA_2_3.portable.OutputStream) reply.createReply();
        out.write_value((Serializable)result,RemoteObjectInstance.class);
        return out;
    }
    
    private OutputStream createEJB__CORBA_WStringValue__CORBA_WStringValue__CORBA_WStringValue__CORBA_WStringValue(org.omg.CORBA_2_3.portable.InputStream in , ResponseHandler reply) throws Throwable {
        String arg0 = (String) in.read_value(String.class);
        String arg1 = (String) in.read_value(String.class);
        String arg2 = (String) in.read_value(String.class);
        String arg3 = (String) in.read_value(String.class);
        RemoteObjectInstance result;
        try {
            result = target.createEJB(arg0, arg1, arg2, arg3);
        } catch (NamingException ex) {
            String id = "IDL:javax/naming/NamingEx:1.0";
            org.omg.CORBA_2_3.portable.OutputStream out = 
                (org.omg.CORBA_2_3.portable.OutputStream) reply.createExceptionReply();
            out.write_string(id);
            out.write_value(ex,NamingException.class);
            return out;
        }
        org.omg.CORBA_2_3.portable.OutputStream out = 
            (org.omg.CORBA_2_3.portable.OutputStream) reply.createReply();
        out.write_value((Serializable)result,RemoteObjectInstance.class);
        return out;
    }
    
    private OutputStream findEJBByBeanName(org.omg.CORBA_2_3.portable.InputStream in , ResponseHandler reply) throws Throwable {
        String arg0 = (String) in.read_value(String.class);
        String arg1 = (String) in.read_value(String.class);
        String arg2 = (String) in.read_value(String.class);
        RemoteObjectInstance result;
        try {
            result = target.findEJBByBeanName(arg0, arg1, arg2);
        } catch (NamingException ex) {
            String id = "IDL:javax/naming/NamingEx:1.0";
            org.omg.CORBA_2_3.portable.OutputStream out = 
                (org.omg.CORBA_2_3.portable.OutputStream) reply.createExceptionReply();
            out.write_string(id);
            out.write_value(ex,NamingException.class);
            return out;
        }
        org.omg.CORBA_2_3.portable.OutputStream out = 
            (org.omg.CORBA_2_3.portable.OutputStream) reply.createReply();
        out.write_value((Serializable)result,RemoteObjectInstance.class);
        return out;
    }
    
    private OutputStream findEJBByInterface(org.omg.CORBA_2_3.portable.InputStream in , ResponseHandler reply) throws Throwable {
        String arg0 = (String) in.read_value(String.class);
        String arg1 = (String) in.read_value(String.class);
        RemoteObjectInstance result;
        try {
            result = target.findEJBByInterface(arg0, arg1);
        } catch (NamingException ex) {
            String id = "IDL:javax/naming/NamingEx:1.0";
            org.omg.CORBA_2_3.portable.OutputStream out = 
                (org.omg.CORBA_2_3.portable.OutputStream) reply.createExceptionReply();
            out.write_string(id);
            out.write_value(ex,NamingException.class);
            return out;
        }
        org.omg.CORBA_2_3.portable.OutputStream out = 
            (org.omg.CORBA_2_3.portable.OutputStream) reply.createReply();
        out.write_value((Serializable)result,RemoteObjectInstance.class);
        return out;
    }
}
