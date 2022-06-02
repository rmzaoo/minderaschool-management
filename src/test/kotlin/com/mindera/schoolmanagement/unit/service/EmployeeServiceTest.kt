package com.mindera.schoolmanagement.unit.service

import com.mindera.schoolmanagement.Enumerator.EmployeeType
import com.mindera.schoolmanagement.MockedData
import com.mindera.schoolmanagement.dto.absence.DetailsAbsenceDTO
import com.mindera.schoolmanagement.dto.absence.convertToDetailsAbsenceDTO
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
import java.time.LocalDate
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

    private var absenceMock: MockedData.MockAbsence = MockedData().MockAbsence()


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

    @Nested
    inner class GetStudentsAndTeachersByClassroomId {

        @Test
        fun test_getStudentsByClassroomId_shouldReturnSuccess() {
            val classroomId = 1L
            val studentList = employeeMock.getEmployeeEntityList().stream()
                .filter { it.employeeType == EmployeeType.Student }
                .map { convertToDetailsEmployeeDTO(it) }
                .collect(Collectors.toList())

            `when`(employeeRepository?.findAllByClassroomEntityId(classroomId)).thenReturn(employeeMock.getEmployeeEntityList())

            val response = employeeService?.getStudentsByClassroomId(classroomId)

            assertEquals(studentList, response)
        }

        @Test
        fun test_getTeachersByClassroomId_shouldReturnSuccess() {
            val classroomId = 1L
            val teacherList = employeeMock.getTeacherEntityList().stream()
                .filter { it.employeeType == EmployeeType.Teacher }
                .map { convertToDetailsEmployeeDTO(it) }
                .collect(Collectors.toList())

            `when`(employeeRepository?.findAllByClassroomEntityId(classroomId)).thenReturn(employeeMock.getTeacherEntityList())

            val response = employeeService?.getTeacherByClassroomId(classroomId)

            assertEquals(teacherList, response)
        }

    }

    @Nested
    inner class GetEmployeeAbsences() {

        @Test
        fun test_getEmployeeAbsencesById_shouldReturnSuccess() {
            val employeeID = 1L
            val absenceList: List<DetailsAbsenceDTO> = absenceMock.getAbsenceEntityList().stream()
                .map { convertToDetailsAbsenceDTO(it) }
                .collect(Collectors.toList())
            val employeeEntity = employeeMock.getEmployeeEntity().apply {
                this.absenceEntities = absenceMock.getAbsenceEntityList()
            }


            `when`(employeeRepository?.findById(employeeID)).thenReturn(Optional.of(employeeEntity))

            val response = employeeService?.getEmployeeAbsences(employeeID)


            assertEquals(absenceList, response)
        }

        @Test
        fun test_getEmployeeAbsencesById_shouldReturnEmployeeNotFoundException() {
            val employeeID = 1L

            `when`(employeeRepository?.findById(employeeID)).thenReturn(Optional.empty())

            val action = Executable { employeeService?.getEmployeeAbsences(employeeID) }

            assertThrows(EmployeeNotFoundException::class.java, action)
        }

    }

    @Nested
    inner class GetEmployeeAbsencesBetweenDates() {
        @Test
        fun test_getEmployeeAbsencesBetweenDates_shouldReturnSuccess() {
            val employeeID = 1L
            val startDate = LocalDate.of(2020, 1, 1)
            val endDate = LocalDate.of(2020, 1, 31)
            val absenceList: List<DetailsAbsenceDTO> = absenceMock.getAbsenceEntityList().stream()
                .filter { it.date!!.isAfter(startDate) && it.date!!.isBefore(endDate) }
                .map { convertToDetailsAbsenceDTO(it) }
                .collect(Collectors.toList())
            val employeeEntity = employeeMock.getEmployeeEntity().apply {
                this.absenceEntities = absenceMock.getAbsenceEntityList()
            }

            `when`(employeeRepository?.findById(employeeID)).thenReturn(Optional.of(employeeEntity))

            val response = employeeService?.getEmployeeAbsencesBetweenDates(employeeID, startDate, endDate)

            assertEquals(absenceList, response)

        }

        @Test
        fun test_getEmployeeAbsencesBetweenDates_shouldReturnEmployeeNotFoundException() {
            val employeeID = 1L
            val startDate = LocalDate.of(2020, 1, 1)
            val endDate = LocalDate.of(2020, 1, 31)

            `when`(employeeRepository?.findById(employeeID)).thenReturn(Optional.empty())

            val action = Executable { employeeService?.getEmployeeAbsencesBetweenDates(employeeID, startDate, endDate) }

            assertThrows(EmployeeNotFoundException::class.java, action)
        }

    }

}