package com.mindera.schoolmanagement.persistence.entity

import javax.persistence.*

@Entity
@Table(name = "absence")
class AbsenceEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "absences_id_generator")
    @SequenceGenerator(name = "absences_id_generator", sequenceName = "absences_id_generator", allocationSize = 1)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employees_id")
    var employeeEntity: EmployeeEntity? = null
)