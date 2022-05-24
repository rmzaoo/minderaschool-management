package com.mindera.schoolmanagement.persistence.repository

import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ClassroomRepository : JpaRepository<ClassroomEntity, Long> {
}