# Introduction #

Some time ago, I was trying to build a Java application that uses JavaXPCOM wrapper in order to load a web page and apply a XPath query. That was my first newbie goal, so I needed some  basic documentation about JavaXPCOM to start playing with that. But current and official JavaXPCOM documentation (http://developer.mozilla.org/en/docs/JavaXPCOM) is somewhat poor and obsolete. In an effort to help newbies like me to build some basic code that uses Gecko rendering engine through JavaXPCOM, using Eclipse as a development platform, I started this documentation project.

Well, that is one of the goals. The other one is to improve my poor english skills... so, your are warned ;-)


# Details #

_All cited source code is available in the Subversion repository_

**Test.java**:  load and visualize google.com webpage, extract the DOM document, and calculate number of link (<a href='..'>) elements.<br>
<br>
<b>Environment</b>:<br>
<br>
<ul><li>A Linux distro (Fedora Core 6 in my case)<br>
</li><li>Eclipse 3.2.1<br>
</li><li>XULRunner 1.8.4</li></ul>

How to reproduce:<br>
<ul><li>Download XULRunner</li></ul>

Add the following external JARs  (in Eclipse, select Project/Properties, then "Java Build Path" and the "Libraries" tab. From there, click on Add External JARs)<br>
<br>
Add the following jars from plugins forlder of your eclipse installation:<br>
<ul><li>org.eclipse.equinox.common_3.2.0<br>
</li><li>org.eclipse.swt.gtk.linux.x86_3.2.1<br>
</li><li>org.eclipse.core.commands_3.2.0<br>
</li><li>org.eclipse.core.runtime_3.2.0<br>
</li><li>org.eclipse.osgi_3.2.1<br>
</li><li>org.eclipse.equinox.registry_3.2.1</li></ul>


<ul><li>Mozilla.interfaces.jar <-- this is found in XULRunner folder, sdk/lib/MozillaInterfaces.jar</li></ul>

<ul><li>core.jar <-- this is from Eclipse ATF Project.<br>
<a href='http://www.eclipse.org/atf/downloads/index_0.1-20060713-1400.php'>http://www.eclipse.org/atf/downloads/index_0.1-20060713-1400.php</a></li></ul>

<i>Update</i>:<a href='http://www.mirrorservice.org/sites/download.eclipse.org/eclipseMirror/webtools/atf/atf-linux-sdk-0.1-20060713-1700.zip'>http://www.mirrorservice.org/sites/download.eclipse.org/eclipseMirror/webtools/atf/atf-linux-sdk-0.1-20060713-1700.zip</a>

Download the Linux version 0.1-20060713 of ATF's SDK (binary with source)<br>
Extract the contents. There is a org.eclipse.atf.core_1.0.1.jar archive. Extract the contents and you will see core.jar and it's source code, coresrc.jar.<br>
<br>
<ul><li>mozilla_swt.jar : from the same ATF package download (where you extracted from core.jar), there is also a org.eclipse.atf.mozilla.swt.browser_1.0.1.jar. Extract the contents and you will see mozilla_swt.jar and mozilla_swtsrc.jar.</li></ul>


<ul><li>Update 2007/04/10: org.eclipse.atf.mozilla.ide.core_1.0.1.jar is also a dependency. You have to extract this archive and import into Eclipse.</li></ul>

<b>XPathJVMCrash.java</b>:  based on some buggy code posted to mozilla.dev.tech.java ,  I fixed that and apply a XPath query against an ill-formed HTML webpage. It doesn't work as expected with that query on that page, but it works ok with others.<br>