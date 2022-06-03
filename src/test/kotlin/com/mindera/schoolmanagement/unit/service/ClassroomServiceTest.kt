package com.mindera.schoolmanagement.unit.service

import com.mindera.schoolmanagement.MockedData
import com.mindera.schoolmanagement.dto.classroomDto.convertToDetailsClassroomDTO
import com.mindera.schoolmanagement.exception.exceptions.ClassroomNotFoundException
import com.mindera.schoolmanagement.exception.exceptions.EmployeeNotFoundException
import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity
import com.mindera.schoolmanagement.persistence.repository.ClassroomRepository
import com.mindera.schoolmanagement.service.classroom.ClassroomServiceImpl
import com.mindera.schoolmanagement.service.employee.EmployeeServiceImpl
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
import java.util.stream.Collectors

@ExtendWith(MockitoExtension::class)
class ClassroomServiceTest {


    @InjectMocks
    private var classroomService: ClassroomServiceImpl? = null

    @Mock
    private var classroomRepository: ClassroomRepository? = null

    private var classroomMock: MockedData.MockClassroom = MockedData().MockClassroom()

    @BeforeEach
    fun setup() {
        classroomService = ClassroomServiceImpl(classroomRepository!!)
    }


    @Nested
    inner class GetAllClassrooms {

        @Test
        fun test_getAllClassrooms_shouldReturnSuccess() {
            val classroomList = classroomMock.getClassroomEntityList().stream()
                .map { convertToDetailsClassroomDTO(it) }
                .collect(Collectors.toList())

            `when`(classroomRepository?.findAll()).thenReturn(classroomMock.getClassroomEntityList())
            val response = classroomService?.getAllClassrooms()

            assertEquals(classroomList, response)
        }

    }

    @Nested
    inner class GetClassroomById {

        @Test
        fun test_getClassroomById_shouldReturnSuccess() {
            val classroomId = 1L
            val mockEntityDTO = convertToDetailsClassroomDTO(classroomMock.getClassroomEntity())

            `when`(classroomRepository?.findById(classroomId)).thenReturn(Optional.of(classroomMock.getClassroomEntity()))
            val response = classroomService?.getClassroomById(classroomId)

            assertEquals(mockEntityDTO, response)
        }


        @Test
        fun test_getClassroomById_shouldReturnClassroomNotFoundException() {
            val classroomId = 1L

            `when`(classroomRepository?.findById(classroomId)).thenReturn(Optional.empty())
            val action = Executable { classroomService?.getClassroomById(classroomId) }

            assertThrows(ClassroomNotFoundException::class.java, action)

        }
    }

    @Nested
    inner class createClassroom {

        @Test
        fun test_createClassroom_shouldReturnSuccess() {
            val dto = classroomMock.getCreateOrUpdateClassroomDTO()
            val classroom = classroomMock.getClassroomEntity()

            `when`(classroomRepository?.save(Mockito.any(ClassroomEntity::class.java))).thenAnswer { invocation ->
                val classroomEntity = invocation.getArgument(0) as ClassroomEntity
                classroomEntity.id = 1L
                classroomEntity
            }

            val response = classroomService?.createClassroom(dto)

            assertEquals(convertToDetailsClassroomDTO(classroom), response)
        }

    }
}