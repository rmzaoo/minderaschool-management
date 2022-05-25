package com.mindera.schoolmanagement.exception.exceptions

import com.mindera.schoolmanagement.exception.SchoolManagementException
import lombok.extern.slf4j.Slf4j
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

class ClassroomNotFoundException: SchoolManagementException {
    constructor(message: String): super(message)
}





