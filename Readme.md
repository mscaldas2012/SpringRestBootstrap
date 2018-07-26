# Intro
This is a bootstrap project for SpringBoot REST type projects.

It includes several basic configurations
* A basic Controller with method to retrieve metadata about the service and a basic ping method to validate server is up and running.
* About class is wired to read about.yml configuration file. Simply update this yml file for your project
* A WebMvcConfig to add versioning to your REST services.
** It uses a ApiVersion annotation that allows you to version the URLs.
* A docker file to generate docker container
* a apidoc.yaml for Swagger documentation.
* Deployment scripts template on deployment folder

* application.yml holds some basic information for your context, version prefix, port, etc.


The project also is setup to use RestAssured for testing purposes. A quick example is given on the included unit test.

# How to Use this project
In order to use this project as a bootstrap, you can start by cloning it to your local machine
(or simply download the zip file since this won't be your final git repo)

You can also create a new repostiory by "importing" this repo. Simply click on **"New Project"**
and choose **"Repo by URL"** under **Import from project**.

You can provide the URL of this repository, passing username and password along with it:

Ex.:
<code>https://mcaldas:mypassword@gitlab.sandbox.aimsplatform.com/eip/springboot_template.git</code>

Give a new name for your repository and don't cheche the "Mirror repository" checkbox,
since the two repos will diverge and don't need to be synced.

After cloning follow the necessary steps for your scenario:
* Rename SpringRESTBootstrap main class to a more appropriate name for your project
* Rename "services" package to a more appropriate name for your project
* Update application.yml with an appropriate context-path and port.
* Update about.yml with relevant information for your project.
* Update pom.xml with relevant names, groupID and artifactID for your project.
* Place your Swagger yml file under src/docs
* Update Dockerfile with appropriate information for your project.
* Reset Git (git init) and check the project under the appropriate git project.
* Rename file deployment\{serviceName}-services.xml to an appropriate name for your project.
* Replace all {{serviceName}} variables on deployment\deploy-service.ps1 with your appropriate service name.
(Make sure the filenames matches those in your project)

