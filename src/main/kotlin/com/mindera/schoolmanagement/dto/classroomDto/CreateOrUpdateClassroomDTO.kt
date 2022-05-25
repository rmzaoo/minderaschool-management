package com.mindera.schoolmanagement.dto.classroomDto

import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity

data class CreateOrUpdateClassroomDTO(
    var name: String? = null,
)


fun convertToClassroomEntity(dto: CreateOrUpdateClassroomDTO): ClassroomEntity {
    return ClassroomEntity(
        name = dto.name
    )
}