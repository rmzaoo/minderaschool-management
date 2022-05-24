package com.mindera.schoolmanagement.service.classroom

import com.mindera.schoolmanagement.dto.classroomDto.CreateOrUpdateClassroomDTO
import com.mindera.schoolmanagement.dto.classroomDto.DetailsClassroomDTO
import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import com.mindera.schoolmanagement.persistence.repository.ClassroomRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ClassroomServiceImpl(private val classroomRepository: ClassroomRepository) : ClassroomService {

    override fun getAllClassrooms(): List<DetailsClassroomDTO> {
        return classroomRepository.findAll()
    }

    override fun getClassroomById(id: Long): DetailsClassroomDTO {
        return classroomRepository.findById(id).get()
    }

    override fun createClassroom(createClassroomDTO: CreateOrUpdateClassroomDTO): DetailsClassroomDTO {
        TODO("Not yet implemented")
    }
}