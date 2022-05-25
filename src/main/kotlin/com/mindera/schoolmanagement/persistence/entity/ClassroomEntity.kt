package com.mindera.schoolmanagement.persistence.entity

import javax.persistence.*

@Entity
@Table(name = "classrooms")
class ClassroomEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classroom_id_generator")
    @SequenceGenerator(name = "classroom_id_generator", allocationSize = 1, sequenceName = "classroom_id_generator")
    var id: Long? = null,

    @Column(nullable = false)
    var name: String? = null,

    @OneToMany(mappedBy = "classroomEntity", cascade = [CascadeType.ALL])
    var employeeEntities: List<EmployeeEntity>? = null
)