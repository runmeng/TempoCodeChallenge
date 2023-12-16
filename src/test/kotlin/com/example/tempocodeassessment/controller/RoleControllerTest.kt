package com.example.tempocodeassessment.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*
/**
 * @Author Run Meng
 * @date 12/15/23  2:20 PM
 */

@SpringBootTest
@AutoConfigureMockMvc

internal class RoleControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
){
    val allUsersUrl ="/users"
    @Nested
    @DisplayName("GET /users")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class getUsers {
        @Test
        fun `should return all users`() {
            mockMvc.get(allUsersUrl) {
                accept = MediaType.APPLICATION_JSON
            }.andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].id") { value("fd282131-d8aa-4819-b0c8-d9e0bfb1b75c") }
                }
            }

    }

    @Nested
    @DisplayName("GET /users/{userId}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class getUser {
        @Test
        fun `should return user with give id`() {

            val id = "db8ebb6c-2d46-4e10-a639-30afc3d3feb5"

            mockMvc.get("$allUsersUrl/$id") {
                accept = MediaType.APPLICATION_JSON
            }.andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.firstName") { value("Josiane") }
                    jsonPath("$.location") { value("North Amani") }
                }
        }

    }

    val allTeamsUrl ="/teams"
    @Nested
    @DisplayName("GET /teams")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class getTeams {
        @Test
        fun `should return all teams`() {
            mockMvc.get(allTeamsUrl) {
                accept = MediaType.APPLICATION_JSON
            }.andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[1].name") { value("Weekly Peach Wildebeest") }
                }
        }

    }

    @Nested
    @DisplayName("GET /teams/{teamsId}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class getTeam {
        @Test
        fun `should return team with give id`() {

            val id = "634a4204-401c-4192-a4c9-6654513e4486"

            mockMvc.get("$allTeamsUrl/$id") {
                accept = MediaType.APPLICATION_JSON
            }.andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.name") { value("Olympic Brown Skunk") }
                    jsonPath("$.teamLeadId") { value("9f2c07db-d5e6-4126-b883-e28a2bd52b52") }
                }
        }

    }

    val singleUserUrl = "/user"
    @Nested
    @DisplayName("GET /user/{userId}/{Role}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class getUserAndRole {
        @Test
        fun `should return user teams and abilities with give id and role`() {

            val id = "0ca05425-2087-47a6-8dfe-3769e7bdbb4f"
            val role = "Teacher"

            mockMvc.get("$singleUserUrl/$id/$role") {
                accept = MediaType.APPLICATION_JSON
            }.andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.role") { value("Developer") }
                    jsonPath("$.ability[1]") { value("Release") }
                }
        }

    }
}