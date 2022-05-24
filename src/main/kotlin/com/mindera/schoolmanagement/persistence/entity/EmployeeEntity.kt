package com.mindera.schoolmanagement.persistence.entity

import com.mindera.schoolmanagement.Enumerator.EmployeeType
import lombok.*
import javax.persistence.*

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persons")
open class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_id_generator")
    @SequenceGenerator(name = "employees_id_generator", allocationSize = 1, sequenceName = "employees_id_generator")
    open var id: Long? = null

    @Column(nullable = false)
    open var name: String? = null

    @Column(nullable = false)
    open var age: Int? = null

    @Column(nullable = false)
    open var email: String? = null

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    open var employeeType: EmployeeType = EmployeeType.Student

    @OneToMany(mappedBy = "employeeEntity", cascade = [CascadeType.ALL])
    val absenceEntities: List<AbsenceEntity>? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    val classroomEntity: ClassroomEntity? = null
}