# Spring Stack

I've created this stack using Spring Boot. I've implemented token based authentication. 
You can login/register using email and password with this endpoints.

    /auth/login    
    /auth/register
    
Uses Token class to create token and User class to create new user. Also role-based authentication can be extended. But first you must assign ADMIN role to an administrator user.
Then can do CRUD operations at `/admin/role` endpoint.

By default, CORS and CSRF disabled.