package com.mindera.schoolmanagement.persistence.entity

import lombok.*
import javax.persistence.*

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "absence")
open class AbsenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "absences_id_generator")
    @SequenceGenerator(name = "absences_id_generator", sequenceName = "absences_id_generator", allocationSize = 1)
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employees_id")
    val employeeEntity: EmployeeEntity? = null
}