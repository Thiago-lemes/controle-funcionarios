package com.funcionarios.service

import com.funcionarios.DTO.FuncionarioDTO
import com.funcionarios.entity.FuncionariosEntity
import com.funcionarios.repository.FuncionariosRepository
import jakarta.transaction.Transactional
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class FuncionariosService(private val repository: FuncionariosRepository) {
    private val mapper: ModelMapper = ModelMapper() // Injeção do ModelMapper
    @Transactional
    fun salvar(DTO: FuncionarioDTO) {
        val entity = mapper.map(DTO, FuncionariosEntity::class.java)
        repository.save(entity)
    }
    fun delete(id: Long) {
        val entity = repository.findById(id)
            .orElseThrow()
        repository.delete(entity)
    }
}