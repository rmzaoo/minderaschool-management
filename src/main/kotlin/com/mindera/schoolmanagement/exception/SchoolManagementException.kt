package com.mindera.schoolmanagement.exception

abstract class SchoolManagementException: RuntimeException {

    constructor()

    constructor(message: String): super(message)

    constructor(message: String, cause: Throwable): super(message, cause)


}