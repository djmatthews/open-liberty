-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=io.openliberty.cdi4.0-batch2.1
visibility=private
IBM-App-ForceRestart: install, \
 uninstall
IBM-Provision-Capability: osgi.identity; filter:="(&(type=osgi.subsystem.feature)(osgi.identity=io.openliberty.cdi-4.0))", \
 osgi.identity; filter:="(&(type=osgi.subsystem.feature)(osgi.identity=io.openliberty.batch-2.1))"
-bundles=io.openliberty.jbatch.2.1.cdi
IBM-Install-Policy: when-satisfied
kind=ga
edition=base
WLP-Activation-Type: parallel
