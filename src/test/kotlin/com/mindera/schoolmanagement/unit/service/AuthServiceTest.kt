package com.mindera.schoolmanagement.unit.service

import com.mindera.schoolmanagement.MockedData
import com.mindera.schoolmanagement.exception.exceptions.EmployeeNotFoundException
import com.mindera.schoolmanagement.persistence.repository.EmployeeRepository
import com.mindera.schoolmanagement.service.auth.AuthServiceImpl
import com.mindera.schoolmanagement.util.PasswordEncoder
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.function.Executable
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class AuthServiceTest {

    @InjectMocks
    private var authService: AuthServiceImpl? = null


    @Mock
    private var employeeRepository: EmployeeRepository? = null


    private var mockAuth: MockedData.MockAuth = MockedData().MockAuth()

    private var mockEmployee: MockedData.MockEmployee = MockedData().MockEmployee()

    @BeforeEach
    fun setup() {
        authService = AuthServiceImpl(employeeRepository!!)
    }


    @Nested
    inner class login {

        @Test
        fun test_login_shouldReturnSuccess() {
            val loginData = mockAuth.getLoginUserDTO()
            val validLogin = mockAuth.getValidLoginDTO()
            val employee = mockEmployee.getEmployeeEntity()

            employee.password = PasswordEncoder().encode(employee.password)

            `when`(employeeRepository?.findByEmail(loginData.email!!)).thenReturn(Optional.of(employee))
            val result = authService?.login(loginData)


            assertEquals(validLogin.employeeDetails, result?.employeeDetails)
        }


        /**
         * account not exist
         */

        @Test
        fun test_login_shouldReturnEmployeeNotFoundException() {
            val loginData = mockAuth.getLoginUserDTO()


            `when`(employeeRepository?.findByEmail(loginData.email!!)).thenReturn(Optional.empty())

            val action = Executable { authService?.login(loginData) }


            assertThrows(EmployeeNotFoundException::class.java, action)
        }

        /**
         * password error
         */

        @Test
        fun test_login_shouldReturnEmployeeNotFoundException2() {
            val loginData = mockAuth.getLoginUserDTO()
            val employee = mockEmployee.getEmployeeEntity()

            `when`(employeeRepository?.findByEmail(loginData.email!!)).thenReturn(Optional.of(employee))
            val action = Executable { authService?.login(loginData) }

            assertThrows(EmployeeNotFoundException::class.java, action)
        }
    }


}