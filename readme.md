#Aplore Server

Aplore Server is a web application responsible to show all the API's collected by Aplore Client. It categorizes all projects and versions into different pages and organize them in four tabs:

* REST
* SOAP
* Clinical Events
* OSGi

##How to setup the project?

a. Check out this project (aplorerepository);

b. Import this project as a Maven Project into Eclipse;

c. Deploy it to Tomcat (7 or higher);

d. Configure aplore.properties. There's four steps to configure:

* MongoDB (hostname, port, username, password, dbname)
* Static Login (usually admin:b)
* Login Method (At the moment, it can be 'static' or 'ldap')
* LDAP Config

##How to access the app?

a. Check if the project is already deployed to Tomcat and if Tomcat is on;

b. Go to http://localhost:8080/aplore/ (or the address which you deployed it);

c. You can login using your internal LDAP credentials or using a static login (user: admin, password: b), depending on your setup.