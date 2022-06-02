package com.mindera.schoolmanagement.unit.service

import com.mindera.schoolmanagement.MockedData
import com.mindera.schoolmanagement.dto.employeeDTO.DetailsEmployeeDTO
import com.mindera.schoolmanagement.dto.employeeDTO.convertToDetailsEmployeeDTO
import com.mindera.schoolmanagement.exception.exceptions.ClassroomNotFoundException
import com.mindera.schoolmanagement.exception.exceptions.EmployeeNotFoundException
import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity
import com.mindera.schoolmanagement.persistence.repository.ClassroomRepository
import com.mindera.schoolmanagement.persistence.repository.EmployeeRepository
import com.mindera.schoolmanagement.service.employee.EmployeeServiceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.function.Executable
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import java.util.stream.Collectors


@ExtendWith(MockitoExtension::class)
class EmployeeServiceTest {

    @InjectMocks
    private var employeeService: EmployeeServiceImpl? = null

    @Mock
    private var employeeRepository: EmployeeRepository? = null

    @Mock
    private var classroomRepository: ClassroomRepository? = null

    private var employeeMock: MockedData.MockEmployee = MockedData().MockEmployee()

    private var classroomMock: MockedData.MockClassroom = MockedData().MockClassroom()


    @BeforeEach
    fun setup() {
        employeeService = EmployeeServiceImpl(employeeRepository!!, classroomRepository!!)
    }


    @Nested
    inner class GetAllEmployees {

        @Test
        fun test_getAllEmployees_shouldReturnSuccess() {
            val employeeList: List<DetailsEmployeeDTO> = employeeMock.getEmployeeEntityList().stream()
                .map { convertToDetailsEmployeeDTO(it) }
                .collect(Collectors.toList())

            `when`(employeeRepository?.findAll()).thenReturn(employeeMock.getEmployeeEntityList())

            val response = employeeService?.getEmployees()

            assertEquals(employeeList, response)

        }
    }


    @Nested
    inner class GetEmployeesById {

        @Test
        fun test_getEmployeeById_shouldReturnSuccess() {
            val employeeID = 1L
            val detailsEmployeeDTO: DetailsEmployeeDTO = convertToDetailsEmployeeDTO(employeeMock.getEmployeeEntity())

            `when`(employeeRepository?.findById(employeeID)).thenReturn(Optional.of(employeeMock.getEmployeeEntity()))

            val response: DetailsEmployeeDTO? = employeeService?.getEmployeeById(employeeID)

            assertEquals(detailsEmployeeDTO, response)
        }


        @Test
        fun test_getEmployeeById_shouldReturnEmployeeNotFoundException() {
            val employeeID = 1L
            `when`(employeeRepository?.findById(employeeID)).thenReturn(Optional.empty())

            val action = Executable { employeeService?.getEmployeeById(employeeID) }

            assertThrows(EmployeeNotFoundException::class.java, action)

        }
    }

    @Nested
    inner class CreateEmployee {

        @Test
        fun test_createEmployee_shouldReturnSuccess() {
            val createUser = employeeMock.getCreateOrUpdateEmployeeDTO()
            val employee = employeeMock.getEmployeeEntity()

            `when`(employeeRepository?.save(Mockito.any(EmployeeEntity::class.java))).thenAnswer { invocation ->
                val employeeEntity = invocation.getArgument(0) as EmployeeEntity
                employeeEntity.id = 1L
                employeeEntity
            }

            val response = employeeService?.createEmployee(createUser)

            assertEquals(convertToDetailsEmployeeDTO(employee), response)
        }
    }


    @Nested
    inner class UpdateEmployeeClassroom {

        @Test
        fun test_updateEmployeeClassroom_shouldReturnSuccess() {
            val employeeID = 1L
            val classroomID = 1L

            val mockEmployee = employeeMock.getEmployeeEntity()
            val mockClassroom = classroomMock.getClassroomEntity()
            mockEmployee.classroomEntity = mockClassroom

            `when`(employeeRepository?.findById(employeeID)).thenReturn(Optional.of(mockEmployee))
            `when`(classroomRepository?.findById(classroomID)).thenReturn(Optional.of(mockClassroom))

            `when`(employeeRepository?.save(Mockito.any(EmployeeEntity::class.java))).thenAnswer { invocation ->
                val employee = invocation.getArgument(0) as EmployeeEntity
                employee.id = 1L
                employee
            }


            val response = employeeService?.updateEmployeeClassroom(employeeID, classroomID)


            assertEquals(convertToDetailsEmployeeDTO(mockEmployee), response)


        }

        @Test
        fun test_updateEmployeeClassroom_shouldReturnClassroomNotFoundException() {
            val employeeID = 1L
            val classroomID = 1L

            `when`(classroomRepository?.findById(classroomID)).thenReturn(Optional.empty())

            val action = Executable { employeeService?.updateEmployeeClassroom(employeeID, classroomID) }

            assertThrows(ClassroomNotFoundException::class.java, action)
        }

        @Test
        fun test_updateEmployeeClassroom_shouldReturnEmployeeNotFoundException() {
            val employeeID = 1L
            val classroomID = 1L
            val mockClassroom = classroomMock.getClassroomEntity()

            `when`(employeeRepository?.findById(employeeID)).thenReturn(Optional.empty())
            `when`(classroomRepository?.findById(classroomID)).thenReturn(Optional.of(mockClassroom))

            val action = Executable { employeeService?.updateEmployeeClassroom(employeeID, classroomID) }

            assertThrows(EmployeeNotFoundException::class.java, action)

        }
    }
}