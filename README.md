Tools Used
java 8 
Spring boot
Postman 

After Running the project navigate to url :- (post)    http://localhost:8080/api/auth/login 
id pass  
{
  "username": "neo",
  "password": "password"
}

and get the bearer token and user that token for further reference and can navigate to 
http://localhost:8080/api/neoTodo/5 (works with put and delete )

http://localhost:8080/api/neoTodo(fetch all the data getmapping)

http://localhost:8080/api/neoTodo  (with post mapping   insert new data )

