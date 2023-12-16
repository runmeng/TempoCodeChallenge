# TempoCodeChallenge
1.To run the code

1) Configuration 
  --Java version : 17 or newer
  --Spring Boot: v3.2.0 
  --Gradle: 8.5
  --Kotlin: 1.8.10

a. I have generate a jar file of my Kotlin project, after download it from gitHub, please open TempoCodeChallenge folder in your terminal and Run Command: java -jar TempoCodeAssessment-0.0.1-SNAPSHOT.jar to start the program, please make sure use java 17 or newer version to run this program.
b.If you want to start the program in your IDE directly, please first update the dependencies in build.gradle.kts file, then make sure your configuration matches with what I mentioned above, then you can run the the file TempoCodeAssessmentApplication.kt in src/main/kotlin/com/example/tempocodeassessment

After successfully run the project, you can open http://localhost:8080/teams or http://localhost:8080/users in your brower, if the project works well, you will get data same as https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/teams or https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/users  since I have write functions in my controller class to read data and show it through restful api.


2.My approach to solve the probelm

1).In this project, I created an enum class which contains three roles: Developer, Product Owner and Tester, with each role has its special abilities respectively. Here I use enum to make it easier for test, in real life project, I will build a data table to realize this function.

2).Since each user may belong to one or more teams, so basiclly I design the roles api like: first find the user in which teams.(Check if this user exist by its id string first, if cannot find match record in users database, terminate the query). If we have this user, then I will iterate through all teams, to check is this user in this team or not(either as the team leader or a team member) by compare id string, then add every team contains this user to an array.
In order to realize this function:
in the url to visit controller, I include two parameter in the url, the first one is userId, to identify the user. The second one is the user's role as the second parameter, if it is null or an value not in the enum, then the user will be assigned a default role: Developer. Through the user's role, I can get the user's abilities. 
All the function abouve could be test through REST API tests as below 

http://localhost:8080/users  // will return all users data

http://localhost:8080/teams  // will return all teams data

http://localhost:8080/users/userId  // will return the user data with this user id

http://localhost:8080/teams/teamId  // will return the team data with this team id

http://localhost:8080/user/userId/role  // will return the user's role, abilities and teams

http://localhost:8080/user/fd282131-d8aa-4819-b0c8-d9e0bfb1b75c/dev //will return user's role, abilities and teams, the result could like: {"name":"gianniWehner","role":"Developer","ability":["Develop","Release","Update"],"team":["Tall Apricot Bandicoot","Just Turquoise Turkey"]}

This final result could stands for, A user as a role, have abilities(a,b,c...) and help teams(1,2,3...)

3 Unit Test 
I put all test Case is src/test/kotlin/com.example/tempocodeassessment/controller/RoleControllerTest.kt, after run the application successfully, you can run the test cases.

4 Improve suggestion to users/teams API
When i execute the role and abilities API(http://localhost:8080/user/userId/role), the process takes long time to get the result(1~2 minutes!) that's because if I want to know if this user in a team, I need to iterate every team's datail information and then check teamMembers array to see if this user in the team? The time complexity is O(n^2), and as the users and teams numbers go up, the query time will be even longer. To address this issue, I have several suggestion:

1)For each user, add an teams column in the table, which contains all the teams this user belongs to. It will reduce the time complexity of query the user's teams to O(n)

2)In teams API, for each member in this team, also associate the user with an role. This approach could make the user's role more flexible, for example, same user could play different role in different teams.


5 Edge check: I have done checked some edge cases in my project:
1)If user or team not exist
2)If role not exists, set it to default value

6 REST API test
After run the application successfully, test all the apis in step 2

7 Architecture consideration
If the applications goes big, the database structure must optimize as step 4 to avoid time and resource cost.
