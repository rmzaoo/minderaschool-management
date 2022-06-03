package com.mindera.schoolmanagement

import com.mindera.schoolmanagement.Enumerator.EmployeeType
import com.mindera.schoolmanagement.dto.absence.CreateOrUpdateAbsenceDTO
import com.mindera.schoolmanagement.dto.auth.LoginUserDTO
import com.mindera.schoolmanagement.dto.auth.ValidLoginDTO
import com.mindera.schoolmanagement.dto.classroomDto.CreateOrUpdateClassroomDTO
import com.mindera.schoolmanagement.dto.employeeDTO.CreateOrUpdateEmployeeDTO
import com.mindera.schoolmanagement.dto.employeeDTO.convertToDetailsEmployeeDTO
import com.mindera.schoolmanagement.persistence.entity.AbsenceEntity
import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity
import java.time.LocalDate
import java.util.*


class MockedData {


    inner class MockEmployee {

        fun getEmployeeEntityList(): List<EmployeeEntity> =
            Collections.singletonList(getEmployeeEntity())


        fun getTeacherEntityList(): List<EmployeeEntity> =
            Collections.singletonList(getTeacherEntity())


        fun getEmployeeEntity(): EmployeeEntity =
            EmployeeEntity(
                id = 1L,
                name = "Jubileu Dias",
                age = 23,
                email = "jubileu.dias@school.mindera.com",
                password = "12345",
                employeeType = EmployeeType.Student,

                )


        fun getTeacherEntity(): EmployeeEntity =
            EmployeeEntity(
                id = 2L,
                name = "Alberto Dias",
                age = 23,
                email = "alberto.dias@school.mindera.com",
                password = "12345",
                employeeType = EmployeeType.Teacher,

                )


        fun getCreateOrUpdateEmployeeDTO(): CreateOrUpdateEmployeeDTO =
            CreateOrUpdateEmployeeDTO(
                name = "Jubileu Dias",
                age = 23,
                email = "jubileu.dias@school.mindera.com",
                password = "12345",
                employeeType = EmployeeType.Student
            )


    }


    inner class MockClassroom {

        fun getClassroomEntityList(): List<ClassroomEntity> =
            Collections.singletonList(getClassroomEntity())


        fun getClassroomEntity(): ClassroomEntity =
            ClassroomEntity(
                1L,
                "classroom-2021-2022",
                null,

                )


        fun getCreateOrUpdateClassroomDTO(): CreateOrUpdateClassroomDTO =
            CreateOrUpdateClassroomDTO("classroom-2021-2022")


    }


    inner class MockAbsence {

        fun getAbsenceEntityList(): List<AbsenceEntity> =
            Collections.singletonList(getAbsenceEntity())


        fun getAbsenceEntity(): AbsenceEntity =
            AbsenceEntity(
                id = 1L,
                date = LocalDate.of(2022, 11, 15),
                reason = "I have a consulta on Medic and i need leeve at five. I need to leave, I need abandonar",
                employeeEntity = MockEmployee().getEmployeeEntity()
            )

        fun getCreateOrUpdateAbsenceDTO(): CreateOrUpdateAbsenceDTO =
            CreateOrUpdateAbsenceDTO(
                1L,
                LocalDate.of(2022, 11, 15),
                "I have a consulta on Medic and i need leeve at five. I need to leave, I need abandonar"
            )

    }

    inner class MockAuth {

        fun getLoginUserDTO(): LoginUserDTO =
            LoginUserDTO(
                "jubileu.dias@school.mindera.com",
                "12345"
            )

        fun getValidLoginDTO(): ValidLoginDTO =
            ValidLoginDTO(
                null,
                convertToDetailsEmployeeDTO(MockEmployee().getEmployeeEntity())
            )
    }

}

