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

package io.openliberty.ejbcontainer.fat.checkpoint.other.web;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import io.openliberty.ejbcontainer.fat.checkpoint.ejb.CheckpointLocal;
import io.openliberty.ejbcontainer.fat.checkpoint.ejb.CheckpointStatistics;

/**
 * A singleton bean with the following configuration:
 * <ul>
 * <li>Startup : no</li>
 * <li>DependsOn : none</li>
 * <li>StartAtAppStart : false</li>
 * </ul
 *
 * The expected checkpoint phase startup behavior is:
 * <ul>
 * <li>FEATURES : initialized and constructed on first use; may reference other beans in application</li>
 * <li>DEPLOYMENT : initialized and constructed on first use; may reference other beans in application</li>
 * <li>APPLICATIONS : initialized and constructed on first use; may reference other beans in application</li>
 * </ul>
 *
 * Checkpoint causes no behavior difference since StartAtAppStart is explicitly disabled. <p>
 *
 * Note: may reference any other bean in the application since this is not a startup bean;
 * will not be constructed until first use.
 */
@Singleton
public class SGCheckpointBeanW implements CheckpointLocal {
    private static final String BEAN_NAME = SGCheckpointBeanW.class.getSimpleName();
    private static final Logger logger = Logger.getLogger(SGCheckpointBeanW.class.getName());

    static {
        logger.info(BEAN_NAME + ": static initializer");
        CheckpointStatistics.beanClassInitialized(BEAN_NAME, 1);
    }

    @EJB(beanName = "SGCheckpointBeanA")
    CheckpointLocal sgBeanA;

    @EJB(beanName = "SGCheckpointBeanG")
    CheckpointLocal sgBeanG;

    @EJB(beanName = "SGCheckpointBeanM")
    CheckpointLocal sgBeanM;

    @EJB(beanName = "SGCheckpointBeanS")
    CheckpointLocal sgBeanS;

    @EJB(beanName = "SLCheckpointBeanA")
    CheckpointLocal slBeanA;

    @EJB(beanName = "SLCheckpointBeanG")
    CheckpointLocal slBeanG;

    @EJB(beanName = "SLCheckpointBeanM")
    CheckpointLocal slBeanM;

    @EJB(beanName = "SLCheckpointBeanS")
    CheckpointLocal slBeanS;

    public SGCheckpointBeanW() {
        logger.info(BEAN_NAME + ": constructor");
        CheckpointStatistics.incrementInstanceCount(BEAN_NAME);
    }

    @PostConstruct
    public void postConstruct() {
        logger.info(BEAN_NAME + ": postConstruct");
        CheckpointStatistics.incrementPostConstructCount(BEAN_NAME);
    }

    @Override
    public String getBeanName() {
        return BEAN_NAME;
    }

    @Override
    public void verify() {
        assertEquals("SGCheckpointBeanA", sgBeanA.getBeanName());
        assertEquals("SGCheckpointBeanG", sgBeanG.getBeanName());
        assertEquals("SGCheckpointBeanM", sgBeanM.getBeanName());
        assertEquals("SGCheckpointBeanS", sgBeanS.getBeanName());

        assertEquals("SLCheckpointBeanA", slBeanA.getBeanName());
        assertEquals("SLCheckpointBeanG", slBeanG.getBeanName());
        assertEquals("SLCheckpointBeanM", slBeanM.getBeanName());
        assertEquals("SLCheckpointBeanS", slBeanS.getBeanName());
    }

}
