package com.mindera.schoolmanagement.dto.classroomDto

import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity


data class DetailsClassroomDTO(
    var name: String,
    var teachers: List<EmployeeEntity>,
    var students: List<EmployeeEntity>
) {
    fun toEntity(): ClassroomEntity


}


