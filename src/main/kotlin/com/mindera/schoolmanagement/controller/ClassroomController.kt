package com.mindera.schoolmanagement.controller

import com.mindera.schoolmanagement.dto.classroomDto.CreateOrUpdateClassroomDTO
import com.mindera.schoolmanagement.dto.classroomDto.DetailsClassroomDTO
import com.mindera.schoolmanagement.dto.employeeDTO.DetailsEmployeeDTO
import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import com.mindera.schoolmanagement.service.classroom.ClassroomService
import com.mindera.schoolmanagement.service.employee.EmployeeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ClassroomController(private val classroomService: ClassroomService, private val employeeService: EmployeeService) {

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

    @GetMapping("/classroom/{id}/students")
    fun getStudentsByClassroomId(@PathVariable id: Long): ResponseEntity<List<DetailsEmployeeDTO>> {
        return ResponseEntity.ok(employeeService.getStudentsByClassroomId(id))
    }

    @GetMapping("/classroom/{id}/teachers")
    fun getTeachersByClassroomId(@PathVariable id: Long): ResponseEntity<List<DetailsEmployeeDTO>> {
        return ResponseEntity.ok(employeeService.getTeacherByClassroomId(id))
    }


}