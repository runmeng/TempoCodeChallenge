package com.example.tempocodeassessment.controller

import com.example.tempocodeassessment.models.*
import kotlinx.serialization.json.Json
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.net.URL

/**
 * @Author Run Meng
 * @date 12/14/23  10:19 PM
 */

@RestController
class RolesController {


        //Get all users
        @GetMapping("/users")
        fun getAllUsers() : Any? {
                var wantedURL = "https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/users/"

                var data = URL(wantedURL).readText()
                val users = Json.decodeFromString<List<simplifiedUser>>(data)

                return users
        }
        //get user with id
        @GetMapping("/users/{id}")
        fun getOneUser(@PathVariable id: String) : Any? {
            val usersURL = "https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/users/$id"

            var data = URL(usersURL).readText()
            val user = Json.decodeFromString<User>(data)

            return user
        }

        //Get all teams
        @GetMapping("/teams")
        fun getAllTeams() : Any? {
                var wantedURL = "https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/teams/"

                var data = URL(wantedURL).readText()
                val teams = Json.decodeFromString<List<simplifiedTeam>>(data)

                return teams
        }

        //Get team with Id
        @GetMapping("/teams/{id}")
        fun getOneTeam(@PathVariable id: String) : Any? {
            val teamURL = "https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/teams/$id"

            var data = URL(teamURL).readText()
            val team = Json.decodeFromString<Team>(data)

            return team
        }

        //Get user's abilities and which teams the user is in
        @GetMapping("/user/{id}/{roles}")
        fun getUserAndRole(@PathVariable id: String,@PathVariable roles: String) : Any? {
            val userId = id
            var userRole = roles

            val userURL = "https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/users/"

            //  fetch user data
            val userData = URL(userURL).readText()
            val users = Json.decodeFromString<List<simplifiedUser>>(userData)

            //check is this user exists or not
            var queryUser = simplifiedUser()
            for (user in users) {
                if (user.id==userId) {
                    queryUser.id = user.id
                    queryUser.displayName = user.displayName
                }
            }
            //Terminate when user not exist
            if(queryUser.displayName=="")
                return "There is no such user in our company!"


            val teamURL = "https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/teams/"
            //  fetch team data
            var teamData = URL(teamURL).readText()
            val teams = Json.decodeFromString<List<simplifiedTeam>>(teamData)

            var teamsOfUser= mutableListOf<String>()

            for (team in teams){
                var teamId = team.id

                var theDetailedTeamURL = "https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/teams/$teamId"

                var detailedTeamData = URL(theDetailedTeamURL).readText()
                val detailedTeam = Json.decodeFromString<Team>(detailedTeamData)

                if(detailedTeamData != null && detailedTeamData.isNotEmpty()){
                    if(detailedTeam.teamLeadId==queryUser.id || queryUser.id in  detailedTeam.teamMemberIds)
                        teamsOfUser.add(detailedTeam.name)
                }
            }

            userRole =if (userRole.isEmpty() || userRole !in setOf("Developer", "Product_Owner", "Tester"))
                "Developer"
            else
                userRole

            var abilities =  roleAbilities.fromRoleName(userRole)

            val roleTeamResult = roleAndTeam().apply {
                name = queryUser.displayName
                role = userRole
                ability = abilities
                team = teamsOfUser.toTypedArray()
            }

            return roleTeamResult
        }
    }




