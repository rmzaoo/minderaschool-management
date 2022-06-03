package com.mindera.schoolmanagement.unit.service

import com.mindera.schoolmanagement.MockedData
import com.mindera.schoolmanagement.dto.absence.convertToDetailsAbsenceDTO
import com.mindera.schoolmanagement.exception.exceptions.EmployeeNotFoundException
import com.mindera.schoolmanagement.persistence.entity.AbsenceEntity
import com.mindera.schoolmanagement.persistence.repository.AbsenceRepository
import com.mindera.schoolmanagement.persistence.repository.EmployeeRepository
import com.mindera.schoolmanagement.service.absence.AbsenceServiceImpl
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.function.Executable
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class AbsenceServiceTest {

    @InjectMocks
    private var absenceService: AbsenceServiceImpl? = null

    @Mock
    private var absenceRepository: AbsenceRepository? = null

    @Mock
    private var employeeRepository: EmployeeRepository? = null

    private var absenceMock: MockedData.MockAbsence = MockedData().MockAbsence()

    private var mockEmployee: MockedData.MockEmployee = MockedData().MockEmployee()

    @BeforeEach
    fun setup() {
        absenceService = AbsenceServiceImpl(absenceRepository!!, employeeRepository!!)
    }


    @Nested
    inner class CreateAbsence {

        @Test
        fun test_createAbsence_shouldReturnSuccess() {
            val dto = absenceMock.getCreateOrUpdateAbsenceDTO()

            `when`(employeeRepository?.findById(dto.employeeId)).thenReturn(Optional.of(mockEmployee.getEmployeeEntity()))
            `when`(absenceRepository?.save(Mockito.any(AbsenceEntity::class.java))).thenAnswer { invocation ->
                val absenceEntity: AbsenceEntity = invocation.getArgument(0) as AbsenceEntity
                absenceEntity.id = 1L
                absenceEntity
            }
            val response = absenceService?.createAbsence(dto)

            assertEquals(convertToDetailsAbsenceDTO(absenceMock.getAbsenceEntity()), response)

        }

        @Test
        fun test_createAbsence_shouldReturnEmployeeNotFoundException() {
            val dto = absenceMock.getCreateOrUpdateAbsenceDTO()

            `when`(employeeRepository?.findById(dto.employeeId)).thenReturn(Optional.empty())


            val action = Executable { absenceService?.createAbsence(dto)}

            assertThrows(EmployeeNotFoundException::class.java, action)
        }
    }


}