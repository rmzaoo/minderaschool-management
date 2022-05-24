package com.mindera.schoolmanagement.persistence.entity

import lombok.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "classrooms")
open class ClassroomEntity() {

    constructor(id: Long, name: String,employeeEntity: EmployeeEntity): this() {
        this.id = id
        this.name = name
        this.employeeEntity = employeeEntity
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classroom_id_generator")
    @SequenceGenerator(name = "classroom_id_generator", allocationSize = 1, sequenceName = "classroom_id_generator")
    open var id: Long? = null

    @Column(nullable = false)
    open var name: String? = null

    @OneToMany(mappedBy = "classroomEntity", cascade = [CascadeType.ALL])
    val employeeEntities: List<EmployeeEntity>? = null


    fun ClassroomEntity.build(id: Long, name: String,employeeEntity: EmployeeEntity): ClassroomEntity {
        return this
    }
}
