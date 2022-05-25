package com.mindera.schoolmanagement.persistence.entity

import com.mindera.schoolmanagement.Enumerator.EmployeeType
import lombok.*
import javax.persistence.*

@Entity
@Table(name = "persons")
class EmployeeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_id_generator")
    @SequenceGenerator(name = "employees_id_generator", allocationSize = 1, sequenceName = "employees_id_generator")
    var id: Long? = null,

    @Column(nullable = false)
    var name: String? = null,

    @Column(nullable = false)
    var age: Int? = null,

    @Column(nullable = false)
    var email: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var employeeType: EmployeeType = EmployeeType.Student,

    @OneToMany(mappedBy = "employeeEntity", cascade = [CascadeType.ALL])
    var absenceEntities: List<AbsenceEntity>? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    var classroomEntity: ClassroomEntity? = null
)