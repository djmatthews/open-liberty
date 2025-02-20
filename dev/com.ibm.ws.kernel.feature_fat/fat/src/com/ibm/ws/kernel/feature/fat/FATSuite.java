/*******************************************************************************
 * Copyright (c) 2019, 2020 IBM Corporation and others.
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
package com.ibm.ws.kernel.feature.fat;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
                // Disabled FeatureProcessTypeTest.class,
                MinifiedServerEnforceSingletonTest.class,
                BundleOriginTest.class,
                ActivationTypeTest.class,
                AutoFeaturesTest.class,
                FeatureTest.class,
                ProductFeatureTest.class,
                UserFeatureTest.class,
                SPIIsolationTest.class,
                SimpleMinifiedServerTest.class,
                MinifyIconsTest.class,
                FeatureConflictUpgradeTest.class,
                IgnoreAPITest.class,
                SimpleFeatureUpdateTest.class,
                SystemBundleOverrideTest.class,
                FeatureAPITest.class,
                RegionProvisioningTest.class,
                RemoteServerInclude.class,
                CompatibleFeatureTest.class,
                AlternateFeatureNamesTest.class,
                DisableAllFeaturesOnConflictTest.class,
                EECompatibilityTest.class
})
/**
 * Purpose: This suite collects and runs all known good test suites.
 */
public class FATSuite {

}
