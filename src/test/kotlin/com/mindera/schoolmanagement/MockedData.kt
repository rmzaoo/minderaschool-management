package com.mindera.schoolmanagement

import com.mindera.schoolmanagement.Enumerator.EmployeeType
import com.mindera.schoolmanagement.dto.employeeDTO.CreateOrUpdateEmployeeDTO
import com.mindera.schoolmanagement.persistence.entity.AbsenceEntity
import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity
import java.time.LocalDate
import java.util.*


class MockedData {


    inner class MockEmployee {

        fun getEmployeeEntityList(): List<EmployeeEntity> {
            return Collections.singletonList(getEmployeeEntity())
        }

        fun getTeacherEntityList(): List<EmployeeEntity> {
            return Collections.singletonList(getTeacherEntity())
        }

        fun getEmployeeEntity(): EmployeeEntity {
            return EmployeeEntity(
                id = 1L,
                name = "Jubileu Dias",
                age = 23,
                email = "jubileu.dias@school.mindera.com",
                password = "12345",
                employeeType = EmployeeType.Student,

                )
        }

        fun getTeacherEntity(): EmployeeEntity {
            return EmployeeEntity(
                id = 2L,
                name = "Alberto Dias",
                age = 23,
                email = "alberto.dias@school.mindera.com",
                password = "12345",
                employeeType = EmployeeType.Teacher,

                )
        }

        fun getCreateOrUpdateEmployeeDTO(): CreateOrUpdateEmployeeDTO {
            return CreateOrUpdateEmployeeDTO(
                name = "Jubileu Dias",
                age = 23,
                email = "jubileu.dias@school.mindera.com",
                password = "12345",
                employeeType = EmployeeType.Student
            )
        }

    }


    inner class MockClassroom {

        fun getClassroomEntityList(): List<ClassroomEntity> {
            return Collections.singletonList(getClassroomEntity())
        }

        fun getClassroomEntity(): ClassroomEntity {
            return ClassroomEntity(
                id = 1L,
                name = "classroom-2021-2022",
                employeeEntities = MockEmployee().getEmployeeEntityList()

            )
        }
    }


    inner class MockAbsence {

        fun getAbsenceEntityList(): List<AbsenceEntity> {
            return Collections.singletonList(getAbsenceEntity())
        }

        fun getAbsenceEntity(): AbsenceEntity {
            return AbsenceEntity(
                id = 1L,
                date = LocalDate.of(2022,11,15),
                reason = "I have a consulta on Medic and i need leeve at five. I need to leave, I need abandonar",
                employeeEntity = MockEmployee().getEmployeeEntity()
            )
        }
    }

}

