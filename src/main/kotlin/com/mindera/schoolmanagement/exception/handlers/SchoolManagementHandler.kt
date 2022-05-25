package com.mindera.schoolmanagement.exception.handlers

import com.mindera.schoolmanagement.exception.exceptions.ClassroomNotFoundException
import com.mindera.schoolmanagement.exception.model.SchoolManagementError
import lombok.extern.slf4j.Slf4j
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.servlet.http.HttpServletRequest

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class SchoolManagementHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ClassroomNotFoundException::class)
    fun handleNotFoundException(ex: Exception, req: HttpServletRequest): ResponseEntity<SchoolManagementError> {
        val error: SchoolManagementError = SchoolManagementError(
            message = ex.message,
            exception = ex.javaClass.simpleName,
            path = req.requestURI
        )
        logger.error(ex.message)

        return ResponseEntity(error, HttpHeaders(), HttpStatus.NOT_FOUND)
    }
}