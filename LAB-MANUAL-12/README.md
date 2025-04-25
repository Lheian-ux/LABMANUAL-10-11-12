# Student Management System

A Java application for managing student records using JDBC and MySQL.

## Requirements

- Java JDK 8 or higher
- MySQL Server (version 5.7 or higher)
- MySQL Connector/J 9.3.0 (included in the lib folder)

## Database Setup

The application will automatically:

1. Create a database named `school` if it doesn't exist
2. Create a table named `students` with the necessary fields

## Running the Application

### 1. Start MySQL Server

Make sure your MySQL server is running on localhost:3306 before running the application.

### 2. Compile the Java Files

```bash
javac -cp ".;lib/mysql-connector-j-9.3.0/mysql-connector-j-9.3.0.jar" *.java
```

### 3. Run the Application

You can run the following Java classes:

1. **DatabaseConnection** - Test the database connection

   ```bash
   java -cp ".;lib/mysql-connector-j-9.3.0/mysql-connector-j-9.3.0.jar" DatabaseConnection
   ```

2. **InsertStudent** - Add new student records

   ```bash
   java -cp ".;lib/mysql-connector-j-9.3.0/mysql-connector-j-9.3.0.jar" InsertStudent
   ```

3. **RetrieveStudent** - Retrieve student records by ID

   ```bash
   java -cp ".;lib/mysql-connector-j-9.3.0/mysql-connector-j-9.3.0.jar" RetrieveStudent
   ```

4. **UpdateStudent** - Update student records by ID

   ```bash
   java -cp ".;lib/mysql-connector-j-9.3.0/mysql-connector-j-9.3.0.jar" UpdateStudent
   ```

5. **DeleteStudent** - Delete student records by ID
   ```bash
   java -cp ".;lib/mysql-connector-j-9.3.0/mysql-connector-j-9.3.0.jar" DeleteStudent
   ```

## Troubleshooting

If you encounter any issues:

1. **Database Connection Error**

   - Check if your MySQL server is running
   - Verify the database credentials in `DatabaseConnection.java`
   - Make sure the MySQL Connector JAR is correctly referenced

2. **Compiler Errors**

   - Ensure you have the JDK installed and properly set up
   - Make sure the classpath includes the MySQL connector

3. **Runtime Errors**
   - Check console messages for specific error details
   - Verify you're using the correct command format for your operating system

## Note for Linux/Mac Users

If you're using Linux or Mac, replace the classpath separator `;` with `:` in the commands above:

```bash
javac -cp ".:lib/mysql-connector-j-9.3.0/mysql-connector-j-9.3.0.jar" *.java
java -cp ".:lib/mysql-connector-j-9.3.0/mysql-connector-j-9.3.0.jar" ClassName
```
