package com.funcionarios.controller

import com.funcionarios.DTO.FuncionarioDTO
import com.funcionarios.service.FuncionariosService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/funcionarios")
class FuncionariosController(private val service: FuncionariosService) {
    @PostMapping("/create")
    fun create(@RequestBody @Validated dto: FuncionarioDTO): ResponseEntity<FuncionarioDTO> {
        service.create(dto)
        return ResponseEntity.ok(dto)
    }

    @GetMapping(value = ["/{id}"])
    fun findById(@PathVariable(value = "id") id: Long): Optional<FuncionarioDTO> {
        return service.findById(id)
    }

    @GetMapping("find-all")
    fun findAll(): List<FuncionarioDTO> {
        return service.findAll()
    }

    @PutMapping("/update")
    fun update(@RequestBody @Validated dto: FuncionarioDTO): ResponseEntity<Unit> {
        service.update(dto)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable(value = "id") id: Long): ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }
}
