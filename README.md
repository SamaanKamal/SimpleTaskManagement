
# Task Management App with Google Calendar Integration

This project is a simple Task management app implemented using Java Spring Boot. Users can add both online and offline, edit, and delete Tasks offline, with synchronization to Google Calendar while addign Tasks.


## Installation

```bash
  Clone the repository: https://github.com/SamaanKamal/SimpleTaskManagement

  cd SimpleTaskManagement
  run the sql scirpt in database folder in MYSQL workbench
  configure a service account for google cloud project
  enable google calender api in your project
  make sure to save the path of private key Json file
  open the porject in your IDE
  create env.properties file next to application.properties
  open env.properties file and the following pattern:

  DB_DATABASE=jdbc:mysql://localhost:3306/[your schema name]
  DB_USER=[your user database username]
  DB_PASSWORD=[your user database password]
  PATH=[the path to your private key Json file]

  then you can run the project successfully :)
  
```
    
## Usage

```usage
Navigate to the project directory
Build the project
Run the application
Access the application at http://localhost:8080 or any port you configured 
```


## Screenshots

Navigate inro the project directory in the tests folder all the Screenshotsfor all the endpoints
