<div id="top"></div>

<div align="center">
  
<h3 align="center">Super Simple Workflows</h3>

  <p align="center">
   Super Simple Workflows is a workflow orchestrator in Java.
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

Super Simple Workflow is a low-code, lightweight workflow orchestrator for Java to automate business workflows involving sequential, parallel, and conditional workflows with in-built parallelization so that developers can focus on higher-value business logic while abstracting workflow management.
	
Here's why:
* While there are good open-source workflow engines such as Activiti, Bonita, and jBPM, most concepts and features may not be necessary and would only complicate workflow management. To emphasize the complexity, the documentation of these tools is over 300 pages.
* On the other hand, Cloud services such as AWS Step Functions and Simple Workflows (SWF) are confined to AWS, which is an essential factor to consider to have the flexibility to change the cloud service provider.

A workflow can be complex to comprehend, and readability is an essential factor achieved using Builder Pattern; in the developer's perspective, the usage of SSWF would be abstracted and as simple as constructing a Builder. For example, a workflow builder would take a unique workflow ID, version, list of tasks, or nested workflows, a predicate, and subsequent action(s).


### Built With

[Java](https://www.java.com/)
<br />
JAVA Version: JDK 17
- Compatible with Java 1.8 or higher
- You can use this article : https://www.cs.dartmouth.edu/~scot/cs10/windows_install/windows_install.html

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

Install required Libraries to execute the program are:
[Java](https://www.java.com/)
<br />
JAVA Version: JDK 17 
- Compatible with Java 1.8 or higher
- You can use this article : https://www.cs.dartmouth.edu/~scot/cs10/windows_install/windows_install.html

### Installation

#### Setting up the project (MAC OS) 
##### Install HomeBrew
```sh
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```
---
##### JAVA Version: JDK 17 
- Compatible with Java 1.8 or higher
- You can use this article : [https://www.cs.dartmouth.edu/~scot/cs10/mac_install/mac_install.html](https://www.cs.dartmouth.edu/~scot/cs10/mac_install/mac_install.html)

##### JAVA JDK Installation Steps
1. check with this command if JAVA_HOME environment variable is available in your system:
```sh
echo $JAVA_HOME
```
2. If it does not return something lets set its value , run this command in another terminal :
```sh
/usr/libexec/java_home -v 17
```
3. the output will look something like :
```sh
/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home
```
4. copy this and replace the path in command below :
```sh
echo 'export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home' >> ~/.bash_profile
```
5. open new terminal and run this command to verify if our JAVA_HOME is working
```sh
$JAVA_HOME/bin/java -version
```
6. *It should return your java version (17) *

7. IntelliJ Start Application:
   ![Application](https://thenextbigproject.com/wp-content/uploads/2021/10/sswf-project-setup.png)

#### Setting up the project (WINDOWS OS)
##### JAVA Version: JDK 17 
- Compatible with Java 1.8 or higher
- You can use this article : https://www.cs.dartmouth.edu/~scot/cs10/windows_install/windows_install.html


##### JAVA JDK Post Installation Steps 

Post JDK installation need to setup the PATH and CLASSPATH under Environment Variables.
1. Navigate to advanced system settings
2. Click on Environment Variables
3. Click on new Button of User variables
4. Type PATH in the Variable name
5. Copy the path of bin folder which is installed in JDK folder
6. Paste Path of bin folder in Variable value. Click on OK Button
   Note: In case you already have a PATH variable created in your PC, edit the PATH variable to
```sh
PATH = <JDK installation directory>\bin;%PATH%;
```
Here, %PATH% appends the existing path variable to our new value
7. You can follow a similar process to set CLASSPATH.
   Note: In case you java installation does not work after installation, change classpath to
```sh
CLASSPATH = <JDK installation directory>\lib\tools.jar;
```
8. Go to command prompt and type javac -version command to check if java 17 version is displayed 

For further reference you can use this article : https://www.guru99.com/install-java.html

<!-- CONTACT -->
## Contact

Contributors Names and Contact Info

* Parthkumar Patel
* Adesh Nalpet Adimurthy
* Sherrin Raji
* Sai Sandeep Mutyala

<p align="right">(<a href="#top">back to top</a>)</p>
