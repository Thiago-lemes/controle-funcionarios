package com.funcionarios.service

import com.funcionarios.DTO.FuncionarioDTO
import com.funcionarios.entity.FuncionariosEntity
import com.funcionarios.repository.FuncionariosRepository
import jakarta.transaction.Transactional
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException

@Service
class FuncionariosService(private val repository: FuncionariosRepository) {
    private val mapper: ModelMapper = ModelMapper() // Injeção do ModelMapper

    @Transactional
    fun create(dto: FuncionarioDTO) {
        val entity = mapper.map(dto, FuncionariosEntity::class.java)
        repository.save(entity)
    }

    fun findAll(): List<FuncionarioDTO> {
        val funcionario = repository.findAll()
        val funcionariosDTOs = funcionario.map { entity ->
            val dto = FuncionarioDTO(
                id = entity.id,
                nome = entity.nome,
                setor = entity.setor,
                salario = entity.salario,
                cargo = entity.cargo
            )
            dto
        }
        return funcionariosDTOs
    }

    fun findById(id: Long): Optional<FuncionarioDTO> {
        val funcionarioEntityOptional = repository.findById(id)
        return if (funcionarioEntityOptional.isPresent) {
            val entity = funcionarioEntityOptional.get()
            val dto = FuncionarioDTO(
                id = entity.id,
                nome = entity.nome,
                setor = entity.setor,
                salario = entity.salario,
                cargo = entity.cargo
            )
            Optional.of(dto)
        } else {
            Optional.empty()
        }
    }


    fun update(dto: FuncionarioDTO) {
        if (repository.findById(dto.id).isEmpty || repository.findById(dto.id).equals(0)) {
            throw NoSuchElementException("usuario não existe")
        }
        val update = mapper.map(dto, FuncionariosEntity::class.java)
        update.apply {
            nome = dto.nome
            cargo = dto.cargo
            setor = dto.setor
            salario = dto.salario
        }
        repository.save(update)
    }

    fun delete(id: Long) {
        val entity = repository.findById(id)
            .orElseThrow()
        repository.delete(entity)
    }
}