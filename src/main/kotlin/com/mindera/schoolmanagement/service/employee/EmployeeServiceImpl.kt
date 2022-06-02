package com.mindera.schoolmanagement.service.employee

import com.mindera.schoolmanagement.dto.absence.DetailsAbsenceDTO
import com.mindera.schoolmanagement.dto.absence.convertToDetailsAbsenceDTO
import com.mindera.schoolmanagement.dto.employeeDTO.CreateOrUpdateEmployeeDTO
import com.mindera.schoolmanagement.dto.employeeDTO.DetailsEmployeeDTO
import com.mindera.schoolmanagement.dto.employeeDTO.convertToDetailsEmployeeDTO
import com.mindera.schoolmanagement.dto.employeeDTO.convertToEmployeeEntity
import com.mindera.schoolmanagement.exception.exceptions.ClassroomNotFoundException
import com.mindera.schoolmanagement.exception.exceptions.EmployeeNotFoundException
import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity
import com.mindera.schoolmanagement.persistence.repository.AbsenceRepository
import com.mindera.schoolmanagement.persistence.repository.ClassroomRepository
import com.mindera.schoolmanagement.persistence.repository.EmployeeRepository
import com.mindera.schoolmanagement.util.PasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.stream.Collector
import java.util.stream.Collectors

@Service
class EmployeeServiceImpl(
    private val employeeRepository: EmployeeRepository,
    private val classroomRepository: ClassroomRepository
) : EmployeeService {

    override fun getEmployees(): List<DetailsEmployeeDTO> {
        val employees = employeeRepository.findAll()

        return employees.stream()
            .map { convertToDetailsEmployeeDTO(it) }
            .collect(Collectors.toList())
    }

    override fun getEmployeeById(id: Long): DetailsEmployeeDTO {
        val employee = employeeRepository.findById(id)
            .orElseThrow { throw EmployeeNotFoundException("Employee with id $id not found") }

        return convertToDetailsEmployeeDTO(employee)
    }

    override fun createEmployee(createOrUpdateEmployeeDTO: CreateOrUpdateEmployeeDTO): DetailsEmployeeDTO {
        val employeeEntity = employeeRepository.save(
            convertToEmployeeEntity(createOrUpdateEmployeeDTO).let {
                it.password = PasswordEncoder().encode(it.password)
                it
            }
        )

        return convertToDetailsEmployeeDTO(employeeEntity)
    }

    override fun updateEmployeeClassroom(id: Long, classroomId: Long): DetailsEmployeeDTO {
        val classroom = classroomRepository.findById(classroomId)
            .orElseThrow { throw ClassroomNotFoundException("Classroom with id $classroomId not found") }

        val employee = employeeRepository.findById(id)
            .orElseThrow { throw EmployeeNotFoundException("Employee with id $id not found") }

        employee.classroomEntity = classroom

        val employeeEntity = employeeRepository.save(employee)

        return convertToDetailsEmployeeDTO(employeeEntity)

    }

    override fun getStudentsByClassroomId(classroomId: Long): List<DetailsEmployeeDTO> {
        val employees = employeeRepository.findAllByClassroomEntityId(classroomId)
        return employees.stream()
            .filter { it.employeeType?.name == "Student" }
            .map { convertToDetailsEmployeeDTO(it) }
            .collect(Collectors.toList())
    }

    override fun getTeacherByClassroomId(classroomId: Long): List<DetailsEmployeeDTO> {
        val employees = employeeRepository.findAllByClassroomEntityId(classroomId)
        return employees.stream()
            .filter { it.employeeType?.name == "Teacher" }
            .map { convertToDetailsEmployeeDTO(it) }
            .collect(Collectors.toList())
    }

    override fun getAbsences(employeeId: Long): List<DetailsAbsenceDTO> {
        val student = employeeRepository.findById(employeeId).orElseThrow {
            throw EmployeeNotFoundException("Student with id $employeeId not found")
        }

        return student.absenceEntities?.stream()
            ?.map { convertToDetailsAbsenceDTO(it) }
            ?.collect(Collectors.toList()) ?: listOf()
    }

    override fun getEmployeeAbsencesBetweenDates(
        employeeId: Long,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<DetailsAbsenceDTO> {
        val student = employeeRepository.findById(employeeId).orElseThrow {
            throw EmployeeNotFoundException("Student with id $employeeId not found")
        }

        return student.absenceEntities?.stream()
            ?.filter { it.date!! in startDate..endDate }
            ?.map { convertToDetailsAbsenceDTO(it) }
            ?.collect(Collectors.toList()) ?: listOf()
    }

}
