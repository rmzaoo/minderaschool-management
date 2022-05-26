package com.mindera.schoolmanagement.persistence.repository

import com.mindera.schoolmanagement.persistence.entity.AbsenceEntity
import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface AbsenceRepository : JpaRepository<AbsenceEntity, Long> {

    fun findByEmployeeEntity(id: Long?): List<AbsenceEntity>

}