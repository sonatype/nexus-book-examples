Welcome to Sonatype Nexus Professional

Installing
==================================

Getting Started with Nexus Professional 

Step 1: Download the Nexus Professional ZIP

Step 2: Uncompress the ZIP

Step 3: Run “start-nexus” to start Nexus Professional.

Nexus usually takes between two and three minutes to initialize
itself.  When Nexus is started, go to:

                          http://localhost:8081/nexus

Note: The ZIP archive contains the start-nexus scripts: On Linux or
OSX, running start-nexus will execute start-nexus.sh.  On Windows,
running start-nexus will execute start-nexus.bat.

Nexus requires Java 6 or higher to be installed.
 
Accessing Nexus for the First Time
==================================

If everything goes according to plan, you can access Nexus at

    http://localhost:8081/nexus.

On first access, Nexus checks for a valid license. If you do not have a valid
license installed, follow the on screen instructions to request and install an
evaluation license.

Once you have installed a license, log into Nexus as an Administrator. To do
this, go to http://localhost:8081/nexus and click on the Login text in the upper
right-hand corner of the interface. The default username and password are:

    Username: admin
    Password: admin123

    
Evaluating Nexus
==================================

So now that you're up and running, what is this thing?  How do I evaluate a
repository manager? To help answer these questions, Sonatype has assembled a set
of example projects. To download them go to:

    http://links.sonatype.com/products/nexus/pro-eval/eval-bundle.zip

Alongside this collection of example projects, we've developed an Evaluation
Guide to walk you through the evaluation project. To download the evaluation
guide, go to:

    http://links.sonatype.com/products/nexus/pro-eval/eval-guide.pdf    

Lastly, this distribution of Nexus comes with a special evaluation configuration
already intalled in:

    sonatype-trial/nexus/conf

    
Troubleshooting
==================================

In case something goes wrong, you can examine the Nexus log file to identify
problems.

    nexus-professional-trial-<version>/logs/wrapper.log 

Nexus tries to listen on port 8081. If you have another application listening on
this port, Nexus will not be able to start.

You can change the port Nexus listens on. Open this file

    nexus-professional-trial-<version>/conf/nexus.properties 

Edit the line that looks like this:

    application-port=8081

For example, to access Nexus on port 9090 instead, change the line to

    application-port=9090

Save the file and restart Nexus.

If you need further assistance, visit our support site:

    http://links.sonatype.com/products/nexus/pro-eval/support
