package com.mindera.schoolmanagement.service.classroom

import com.mindera.schoolmanagement.dto.classroomDto.CreateOrUpdateClassroomDTO
import com.mindera.schoolmanagement.dto.classroomDto.DetailsClassroomDTO
import com.mindera.schoolmanagement.dto.classroomDto.convertToClassroomEntity
import com.mindera.schoolmanagement.dto.classroomDto.convertToDetailsClassroomDTO
import com.mindera.schoolmanagement.exception.exceptions.ClassroomNotFoundException
import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import com.mindera.schoolmanagement.persistence.repository.ClassroomRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class ClassroomServiceImpl(private val classroomRepository: ClassroomRepository) : ClassroomService {

    override fun getAllClassrooms(): List<DetailsClassroomDTO> {
        val classrooms = classroomRepository.findAll()

        return classrooms.stream()
            .map { convertToDetailsClassroomDTO(it) }
            .collect(Collectors.toList())
    }

    override fun getClassroomById(id: Long): DetailsClassroomDTO {
        val classroomEntity: ClassroomEntity = classroomRepository.findById(id)
            .orElseThrow { throw ClassroomNotFoundException("Classroom with id $id not found") }

        return convertToDetailsClassroomDTO(classroomEntity)
    }

    override fun createClassroom(createClassroomDTO: CreateOrUpdateClassroomDTO): DetailsClassroomDTO {
        val classroomEntity = classroomRepository.save(convertToClassroomEntity(createClassroomDTO))
        return convertToDetailsClassroomDTO(classroomEntity)
    }

}