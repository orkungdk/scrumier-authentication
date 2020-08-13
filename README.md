# Authentication Service
This microservice controls the **authentication**, **user management** and **group management**.

- ## Authentication
In the authentication of the application, spring security is used. Most of the services and object are wrapped to be able to make the authentication process align with the backend logic.
There are couple of security and validation checks for each authentication attemp. Authentication returns a JWT token and this token uses in the request header with the "x-Auth-Token" key. So that, [api-gateway](https://github.com/orkungdk/api-gateway) can trust received requests.

- ## User management
User management of this application is qutie strict forward. Each user may be a part of multiple group. Permissions are inherited from groups. Password of an user is stored in the database after it's encoded. You can see the password encoder details in this [link](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html).
Some of the user details such as avatarUrl are enriched on the run time. The data is retrieved from integrated Jira instance.
In order to create an user, **username** and **surname** must be the same with an already exised user in the Jira.

- ## Group management
Groups in the application do not refer the groups in the Jira. More likely, a group refers the set of permission that user has. The reason of this is, in the Jira there should not be specific groups such as Scrum Masters or Product Owners. That is why the logic behind a Jira Time Tracker group and Jira group are totally different.
