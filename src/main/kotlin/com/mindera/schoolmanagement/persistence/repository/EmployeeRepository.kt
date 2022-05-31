package com.mindera.schoolmanagement.persistence.repository

import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface EmployeeRepository : JpaRepository<EmployeeEntity, Long> {

    fun findAllByClassroomEntityId(classroomId: Long): List<EmployeeEntity>

    fun findByEmail(email: String): Optional<EmployeeEntity>
}