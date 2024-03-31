# Price calculations API

Food delivery app price calculation based on city, vehicle and weather phenomenon.

## Running the app

1. Clone project into an IDE - i.e. IntelliJ IDEA
2. Let IDEA set up the project and install dependencies.
3. You can run the app from top right or from src/main/java/fooddeliveryapp/FoodDeliveryApp class main() method.

Note that IDEA might not be able to install all dependencies by itself but running the app should fix this issue.

## Technologies used
* Java 17
* Spring Boot framework
* H2 database
* Lombok- for reducing boilerplate code
* Jackson FasterXML- for converting Weather XML data to our models
* Java Persistence API- for mapping database tables into objects


## Some explanations
* This project has 3 layers - Controller Layer, Service Layer, Repository Layer.
* EnableScheduling annotation is used to allow cronjob scheduling for weather data. Cron job can be configured in the application.properties file.
* Testing - I know this projects testing is not in the best shape. Ideally some kind of database profiles should be implemented. This would allow to use different database for testing and allow for more comprehensive testing.
* For development, I mostly used the official documentation, stack overflow and learned from youtube courses.

## Author
 * **Leelo Aino Jürgenstein** - [LeeloAJ](https://github.com/leeloaj)