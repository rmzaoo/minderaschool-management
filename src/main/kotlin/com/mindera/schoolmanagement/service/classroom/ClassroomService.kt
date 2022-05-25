package com.mindera.schoolmanagement.service.classroom

import com.mindera.schoolmanagement.dto.classroomDto.CreateOrUpdateClassroomDTO
import com.mindera.schoolmanagement.dto.classroomDto.DetailsClassroomDTO
import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import org.springframework.stereotype.Service

@Service
interface ClassroomService {
    fun getAllClassrooms(): List<DetailsClassroomDTO>

    fun getClassroomById(id: Long): DetailsClassroomDTO

    fun createClassroom(createClassroomDTO: CreateOrUpdateClassroomDTO): DetailsClassroomDTO
}