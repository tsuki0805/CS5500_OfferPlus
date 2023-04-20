# 5500Fit!
5500Fit! is a web application built with Java, Springboot, MongoDB, and React.js that helps users track their daily activities.

## Features Delivered
During the development process, the following features were implemented:
- Get the user’s daily activities detail by date and category
- Get the user’s daily activities summary by date and category
- Compare user’s activities distance on a specific day and get its environmental impact
- Get user's daily physical activity calorie consumption on a specific day
- Add or delete daily activities detail data
- Add or delete daily activities summary data

## Code/Test Metrics
The total number of lines of code in the project is ~1900, with a test coverage of 100% for class, 68.3% for method.

## Getting Started
### Database Setup
The MongoDB database is already setup online using MongoDB Atlas with MongoDB access information stored in `application.properties` (project/src/main/resources/application.properties). You do not have to do any additional setups.

### Prerequisites
- JDK version 15
- Maven installed

### Installing
To install the project, follow these steps:

- Clone the repository using the command `git clone https://github.com/tsuki0805/CS5500_OfferPlus.git`
- Navigate to the project directory `cd project`
- Build the project using `mvn clean install`

### Running
To run the project, follow these steps:

- Navigate to the project directory `cd project`
- Run the application by running `Application.java` (project/src/main/java/com/project/Application.java) or using `mvn spring-boot:run`
- Navigate to the React app directory `cd react-app`
- Run `npm install` to install the dependencies
- Run `npm start` to start the React app

## Known Problems
The deploy of the project https://five500-offer-plus2.onrender.com sometimes need some patience.

## Other Documents
[API Document](https://docs.google.com/document/d/1VFA4bvL453Qteau0EMPNv3REVsG9eINMTQ-SyOcWflU/edit?usp=sharing).
