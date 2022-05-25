package com.mindera.schoolmanagement.service.classroom

import com.mindera.schoolmanagement.dto.classroomDto.*
import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import com.mindera.schoolmanagement.persistence.repository.ClassroomRepository
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.lang.String
import java.util.stream.Collectors

@Service
@RequiredArgsConstructor
class ClassroomServiceImpl(private val classroomRepository: ClassroomRepository) : ClassroomService {

    override fun getAllClassrooms(): List<DetailsClassroomDTO> {
        val classrooms = classroomRepository.findAll()

        return classrooms.stream()
            .map { convertToDetailsClassroomDTO(it) }
            .collect(Collectors.toList())
    }

    override fun getClassroomById(id: Long): DetailsClassroomDTO {
        val classroomEntity: ClassroomEntity = classroomRepository.findById(id)
            .orElseThrow { throw RuntimeException("Classroom not found") }

        return convertToDetailsClassroomDTO(classroomEntity)
    }

    override fun createClassroom(createClassroomDTO: CreateOrUpdateClassroomDTO): DetailsClassroomDTO {
        val classroomEntity = classroomRepository.save(convertToClassroomEntity(createClassroomDTO))
        return convertToDetailsClassroomDTO(classroomEntity)
    }

}