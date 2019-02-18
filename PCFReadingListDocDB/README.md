# SpringBoot Application and Azure DocumentDB for MongoDB Protocol
DocumentDB databases can now be used as the data store for apps written for MongoDB. This means that by using existing drivers for MongoDB databases, your application written for MongoDB can now communicate with DocumentDB and use DocumentDB databases instead of MongoDB databases. In many cases, you can switch from using MongoDB to DocumentDB by simply changing a connection string.

This is a SpringBoot application that uses Spring Data MongoDB to do CRUD operations on MongoDB. The same code base is used with both MongoDB and DocumentDB for MongoDB Protocol.

## Local
Following are the instructions to run the app locally against a local MongoDB instance and against Azure DocumentDB for MongoDB protocol instance.
1. Install Java and Maven
2. Install and start MongoDB
3. Clone the repository
4. Make the project base as the current directory 
5. Run the application by executing the follwoing command from the project base directory
      >  mvn package && java  -jar target/PCFReadingListDocDB-0.0.1.jar 
6. Access the appliction from the following URL:
      > http://localhost:7070    
7. Create a DocumentDB account for MongoDB protocol
     > Detailed instructions can be found [HERE](https://docs.microsoft.com/en-us/azure/documentdb/documentdb-create-account).
8. Retrieve and update the connection string for DocumentDB
     > Detailed instructions can be found [HERE](https://docs.microsoft.com/en-us/azure/documentdb/documentdb-connect-mongodb-account).
9. The updated connection string will be of the following format
      > mongodb://USERNAME:PASSWORD@HOST:PORT/DATABASE?ssl=true
10. From the project base directory run the following command:
      > mvn package && java -Dspring.data.mongodb.uri=CONNECTIONSTRING -jar target/PCFReadingListDocDB-0.0.1.jar
11. Access the appliction from the following URL:
      > http://localhost:7070 

## Docker Swarm
## kubernetes
## Pivotal Cloud Foundry
## RedHat OpenShift
