package com.funcionarios.entity

import jakarta.persistence.*

@Entity
@Table(name = "colaboradores")
data class FuncionariosEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(nullable = false, length = 100)
    var nome: String = " ",
    @Column(nullable = false, name = "cargo")
    var cargo: String = " ",
    @Column(nullable = false, name = "setor")
    var setor: String = " ",
    @Column(name = "salario")
    var salario: Double
)
