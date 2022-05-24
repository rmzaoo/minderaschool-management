package com.mindera.schoolmanagement.dto.classroomDto

import lombok.Builder

@Builder
data class CreateOrUpdateClassroomDTO(
    var id: Int,
    var name: String,
)