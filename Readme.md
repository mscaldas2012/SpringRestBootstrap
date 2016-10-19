This is a bootstrap project for SpringBoot REST type projects.

It includes several basic configurations
* A basic Controller with one method to retrieve metadata about the service.
* About class is wired to read about.yml configuration file. Simply update this yml file for your project
* A WebMvcConfig to add versioning to your REST services.
** It uses a ApiVersion annotation that allows you to version the URLs.

Versioning URLs is not the best approach is is a good approach if everything else fails.
 For better versioning, use the Media versioning approach.

* application.properties holds some basic information for your context, version prefix, port, etc.


The project also is setup to use RestAssured for testing purposes. A quick example is given on the included unit test.
