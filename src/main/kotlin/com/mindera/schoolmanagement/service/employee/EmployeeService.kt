package com.mindera.schoolmanagement.service.employee

import com.mindera.schoolmanagement.dto.absence.DetailsAbsenceDTO
import com.mindera.schoolmanagement.dto.employeeDTO.CreateOrUpdateEmployeeDTO
import com.mindera.schoolmanagement.dto.employeeDTO.DetailsEmployeeDTO
import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
interface EmployeeService {

    fun getEmployees(): List<DetailsEmployeeDTO>

    fun getEmployeeById(id: Long): DetailsEmployeeDTO

    fun createEmployee(createOrUpdateEmployeeDTO: CreateOrUpdateEmployeeDTO): DetailsEmployeeDTO

    fun updateEmployeeClassroom(id: Long, classroomId: Long): DetailsEmployeeDTO

    fun getStudentsByClassroomId(classroomId: Long): List<DetailsEmployeeDTO>

    fun getTeacherByClassroomId(classroomId: Long): List<DetailsEmployeeDTO>

    fun getEmployeeAbsences(employeeId: Long): List<DetailsAbsenceDTO>

    fun getEmployeeAbsencesBetweenDates(employeeId: Long, startDate: LocalDate, endDate: LocalDate): List<DetailsAbsenceDTO>

}