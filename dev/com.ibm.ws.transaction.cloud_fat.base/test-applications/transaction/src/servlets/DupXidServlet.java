/*******************************************************************************
 * Copyright (c) 2017, 2020 IBM Corporation and others.
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
package servlets;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.annotation.Resource.AuthenticationType;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.transaction.Transaction;

import com.ibm.tx.jta.ExtendedTransactionManager;
import com.ibm.tx.jta.TransactionManagerFactory;
import com.ibm.tx.jta.ut.util.LastingXAResourceImpl;
import com.ibm.tx.jta.ut.util.XAResourceFactoryImpl;
import com.ibm.tx.jta.ut.util.XAResourceImpl;

import componenttest.app.FATServlet;

@SuppressWarnings("serial")
@WebServlet("/DupXidServlet")
public class DupXidServlet extends FATServlet {

    @Resource(name = "jdbc/tranlogDataSource", shareable = true, authenticationType = AuthenticationType.APPLICATION)
    DataSource ds;

    public void cleanDatabase(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("DupXidServlet: drive cleanDatabase");

        Connection con = ds.getConnection();
        try {
            // Statement used to drop table
            Statement stmt = con.createStatement();

            try {
                System.out.println("DupXidServlet: drop WAS_TRAN_LOGtest2");
                stmt.executeUpdate("drop table WAS_TRAN_LOGtest2");
            } catch (SQLException x) {
                // didn't exist
            }

            try {
                System.out.println("DupXidServlet: drop WAS_PARTNER_LOGtest2");
                stmt.executeUpdate("drop table WAS_PARTNER_LOGtest2");
            } catch (SQLException x) {
                // didn't exist
            }

            try {
                System.out.println("DupXidServlet: drop WAS_XA_RESOURCES");
                stmt.executeUpdate("drop table WAS_XA_RESOURCES");
            } catch (SQLException x) {
                // didn't exist
            }

            System.out.println("DupXidServlet: commit changes to database");
            con.commit();
        } catch (Exception ex) {
            System.out.println("DupXidServlet: caught exception in testSetup: " + ex);
        }
        System.out.println("DupXidServlet: testControlSetup complete");
    }

    public void commitSuicide(HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Runtime.getRuntime().halt(0);
    }

    public void setupDupXid001(HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        final ExtendedTransactionManager tm = TransactionManagerFactory
                        .getTransactionManager();

        try {
            tm.begin();

            final Transaction tx = tm.getTransaction();

            final LastingXAResourceImpl xares1 = XAResourceFactoryImpl.instance().getLastingXAResourceImpl();
            xares1.setCommitAction(XAResourceImpl.DIE);
            final LastingXAResourceImpl xares2 = XAResourceFactoryImpl.instance().getLastingXAResourceImpl();
            final LastingXAResourceImpl xares3 = XAResourceFactoryImpl.instance().getLastingXAResourceImpl();
            tx.enlistResource(xares1);
            tx.enlistResource(xares2);
            tx.enlistResource(xares3);

            tm.commit();
        } catch (Exception e) {
            System.out.println("NYTRACE: ImplodeServlet caught exc: " + e);
            e.printStackTrace();
        }
    }

    public void setupDupXid002(HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        final ExtendedTransactionManager tm = TransactionManagerFactory
                        .getTransactionManager();

        try {
            tm.begin();

            final Transaction tx = tm.getTransaction();

            final LastingXAResourceImpl xares1 = XAResourceFactoryImpl.instance().getLastingXAResourceImpl();
            xares1.setCommitAction(XAResourceImpl.DIE);
            final LastingXAResourceImpl xares2 = XAResourceFactoryImpl.instance().getLastingXAResourceImpl();
            final LastingXAResourceImpl xares3 = XAResourceFactoryImpl.instance().getLastingXAResourceImpl();
            tx.enlistResource(xares1);
            tx.enlistResource(xares2);
            tx.enlistResource(xares3);

            tm.commit();
        } catch (Exception e) {
            System.out.println("NYTRACE: ImplodeServlet caught exc: " + e);
            e.printStackTrace();
        }
    }

}
