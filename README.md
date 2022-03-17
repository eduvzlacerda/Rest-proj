# Rest-proj
A project i am working on to expand my REST api skills and also implement some spring security feutures 

I will very soon "dockerize" the project so its easieier to run it without having the usual database connection problems 

To have access to the endpoints the user must first login 
  send a POST request to  : http://localhost:8080/users/login 
  body : 
    {
    "email": "testemail1@gmail.com",
    "password" : "password"
    }
    
    and include the provided bearer token in the next requests to have access to the endpoints 
