package com.funcionarios.controller

import com.funcionarios.DTO.FuncionarioDTO
import com.funcionarios.service.FuncionariosService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI


@RestController
@RequestMapping("/funcionarios")
class FuncionariosController(private val service: FuncionariosService) {

    @PostMapping("/salva")
    fun salvar(@RequestBody @Validated dto: FuncionarioDTO): ResponseEntity<FuncionarioDTO> {
        service.salvar(dto)
        return ResponseEntity.created(URI("/funcionarios/${dto.id}")).body(null)
    }
    @DeleteMapping(value = ["/{id}"] )
    fun delete(@PathVariable(value = "id") id: Long): ResponseEntity<*>{
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }
}
