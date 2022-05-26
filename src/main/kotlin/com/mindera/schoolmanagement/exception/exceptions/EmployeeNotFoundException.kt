package com.mindera.schoolmanagement.exception.exceptions

import com.mindera.schoolmanagement.exception.SchoolManagementException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

class EmployeeNotFoundException(message: String) : SchoolManagementException(message)





