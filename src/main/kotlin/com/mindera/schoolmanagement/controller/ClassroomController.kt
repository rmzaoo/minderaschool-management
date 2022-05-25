package com.mindera.schoolmanagement.controller

import com.mindera.schoolmanagement.dto.classroomDto.CreateOrUpdateClassroomDTO
import com.mindera.schoolmanagement.dto.classroomDto.DetailsClassroomDTO
import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import com.mindera.schoolmanagement.service.classroom.ClassroomService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
@Slf4j
class ClassroomController(private val classroomService: ClassroomService) {


    @GetMapping("/classroom")
    fun getAllClassrooms(): ResponseEntity<List<DetailsClassroomDTO>> {
        return ResponseEntity.ok(classroomService.getAllClassrooms())
    }

    @GetMapping("/classroom/{id}")
    fun getClassroomById(@PathVariable id: Long): ResponseEntity<DetailsClassroomDTO> {
        return ResponseEntity.ok(classroomService.getClassroomById(id))
    }

    @PostMapping("/classroom")
    fun createClassroom(@RequestBody classroom: CreateOrUpdateClassroomDTO): ResponseEntity<DetailsClassroomDTO> {
        return ResponseEntity.ok(classroomService.createClassroom(classroom))
    }


}