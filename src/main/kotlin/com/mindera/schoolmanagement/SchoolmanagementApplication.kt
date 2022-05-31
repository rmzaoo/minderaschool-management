package com.mindera.schoolmanagement

import com.mindera.schoolmanagement.util.TokenGenerator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(TokenGenerator::class)
class SchoolmanagementApplication

fun main(args: Array<String>) {
    runApplication<SchoolmanagementApplication>(*args)
}

