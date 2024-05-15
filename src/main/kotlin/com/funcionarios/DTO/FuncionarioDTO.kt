package com.funcionarios.DTO

data class FuncionarioDTO(
    var id: Long,
    var nome: String,
    var cargo: String,
    var setor: String,
    var salario: Double
)
