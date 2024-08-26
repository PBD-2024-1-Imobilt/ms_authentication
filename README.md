# ms_authentication
 Token authentication microservice for username and password

 * Create a new bank in postgresql with the name auth_jwt_bd
 * Make the appropriate database settings in the project's application.properties
 ## Project Documentation
 ![Documentation](assets\image.png)
 * [Link to documentation](http://localhost:8080/swagger-ui/index.html)
    * The application must be running
 ### Documentation Details
 * **POST** /api/v1/authentication/validation
      *  **Payload:**
         ```shell
            {
               "token": "string"
            }
         ```
 * **POST** /api/v1/authentication/token
      *  **Payload:**
         ```shell
            {
               "username": "string",
               "password": "string"
            }
         ```
      * Some valid usernames and passwords:
         * ('Alice' ,'123456'), ('Bob', 'abcdef'), ('Carol', 'qwerty')