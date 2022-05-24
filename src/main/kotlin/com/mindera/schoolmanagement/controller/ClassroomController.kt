package com.mindera.schoolmanagement.controller

import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import com.mindera.schoolmanagement.service.classroom.ClassroomService
import org.springframework.web.bind.annotation.RestController
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@RestController
@RequiredArgsConstructor
@Slf4j
class ClassroomController(private val classroomService: ClassroomService) {


    @GetMapping("/classroom")
    fun getAllClassrooms(): ResponseEntity<List<ClassroomEntity>> {
        return ResponseEntity.ok(classroomService.getAllClassrooms())
    }

    @GetMapping("/classroom/{id}")
    fun getClassroomById(@PathVariable id: Long): ResponseEntity<ClassroomEntity> {
        return ResponseEntity.ok(classroomService.getClassroomById(id))
    }

    @PostMapping("/classroom")
    fun createClassroom(classroom: ClassroomEntity): ResponseEntity<ClassroomEntity> {
        return ResponseEntity.ok(classroomService.createClassroom(classroom))
    }


}