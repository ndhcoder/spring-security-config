# Spring-security-config
Example Spring security config demo

1. Create a Database with name "demodb"
2. Run file DB on /database/demodb.sql
3. Run DemoApplication.java

# Demo in POST MAN
##1. Get token:  

* Link: http://localhost:8080/oauth/token
* Authorization: Basic (Base64 of your "clientId:secret")

You can get on __application.properties__ file:

> security.oauth2.client.client-id: __clientId__  
> security.oauth2.client.client-secret: __secret__

In __Post man__, You can setup like this:

Authorization:
![Alt text](demo/oauth.png?raw=true "Get Token")


Body:

![Alt text](demo/oauth_2.png?raw=true "Get Token")


Click to Send, You will receive response:

![Alt text](demo/oauth_3.png?raw=true "Get Token")

If you send with user __hieund4__ are block status, you will receive other response.

##2. GET PROFILE  

Now, we will using token above to get profile

* Link: http://localhost:8080/users/profile

Authorization:

![Alt text](demo/oauth_4.png?raw=true "Get Profile")


Response:

![Alt text](demo/oauth_5.png?raw=true "Get Profile")


That's all.

