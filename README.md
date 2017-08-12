# Spring Stack

I've created this stack using Spring Boot. I've implemented token based authentication and role based access control (RBAC). 
You can login/register using email and password with this endpoints:

    /user/login    
    /user/register
    
Uses `Token` class to create token and `User` class to create new user. Also role-based authentication can be extended. But first you must assign ADMIN role to an administrator user.
Then you can test `/user/list` route which protected with 'ADMIN' role.

`Lombok` utilized for entity classes.

# Domains
* `User`: To store users
* `Token`: To store tokens associated with users
* `Role`: To store roles
* `SecurityHistory`: To store login,register,password_change events of user

# Intellij Usage
* Go to File | Settings | Build, Execution, Deployment 
* Enable annotation processing
* Done