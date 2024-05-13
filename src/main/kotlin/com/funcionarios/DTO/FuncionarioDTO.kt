package com.funcionarios.DTO

data class FuncionarioDTO(
    val id: Long,
    val nome: String,
    val cargo: String,
    val setor: String,
    val salario: Double
)
