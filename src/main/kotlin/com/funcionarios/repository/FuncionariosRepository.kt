package com.funcionarios.repository

import com.funcionarios.entity.FuncionariosEntity
import org.springframework.data.jpa.repository.JpaRepository


interface FuncionariosRepository : JpaRepository<FuncionariosEntity, Long> {}