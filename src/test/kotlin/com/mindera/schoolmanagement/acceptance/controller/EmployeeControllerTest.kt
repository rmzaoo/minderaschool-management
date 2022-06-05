package com.mindera.schoolmanagement.acceptance.controller

import com.mindera.schoolmanagement.persistence.repository.EmployeeRepository
import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @MockBean
    private var employeeRepository: EmployeeRepository? = null

    @LocalServerPort
    private var port: Int? = null

    @BeforeEach
    fun setup() {
        RestAssured.port = port!!
    }

    @Nested
    inner class GetAllEmployees {

        @Test
        fun test_getAllEmployees_shouldReturn200(){
            RestAssured.get("/employees").then().statusCode(200)
        }
    }


}

