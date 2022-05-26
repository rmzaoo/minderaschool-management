package com.mindera.schoolmanagement.persistence.repository

import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<EmployeeEntity, Long> {

    fun findAllByClassroomEntityId(classroomId: Long): List<EmployeeEntity>
}