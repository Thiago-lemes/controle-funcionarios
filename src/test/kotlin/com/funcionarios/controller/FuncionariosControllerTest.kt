package com.funcionarios.controller

import com.funcionarios.DTO.FuncionarioDTO
import com.funcionarios.service.FuncionariosService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.ResponseEntity
import java.util.*

@ExtendWith(MockitoExtension::class)
class FuncionariosControllerTest {
    @InjectMocks
    private lateinit var controller: FuncionariosController
    private lateinit var dto: FuncionarioDTO

    @Mock
    private lateinit var service: FuncionariosService

    @BeforeEach
    fun setup() {
        dto = FuncionarioDTO(
            id = 1,
            nome = "teste",
            cargo = "DEV",
            setor = "TI",
            salario = 500.00
        )
    }

    @Test
    fun deveCriarNovoFuncionario() {
        val response = assertDoesNotThrow { controller.create(dto) };
        assertEquals(ResponseEntity.ok(dto), response)
    }

    @Test
    fun deveAtualizarFuncionario() {
        controller.update(dto)
        verify(service).update(dto)
    }

    @Test
    fun deveExcluirFuncionario() {
        val id = 1L
        controller.delete(id)
        verify(service).delete(id)
    }

    @Test
    fun deveRetornarFuncionarioPorId() {
        val id: Long = 1
        `when`(service.findById(id)).thenReturn(Optional.of(dto))
        val response: Optional<FuncionarioDTO> = controller.findById(id)
        assertEquals(dto, response.orElse(null))
    }

    @Test
    fun deveRetornarTodosFuncionarios() {
        val funcionarios = listOf(
            FuncionarioDTO(
                id = 1,
                nome = "Funcionario 1",
                cargo = "Desenvolvedor",
                setor= "RH",
                salario = 5555.0
            ),
            FuncionarioDTO(
                id = 2,
                nome = "Funcionario 2",
                cargo = "Gerente",
                setor= "RH",
                salario = 5555.0
            )
        )
        `when`(service.findAll()).thenReturn(funcionarios)
        val response: List<FuncionarioDTO> = controller.findAll()
        assertEquals(funcionarios, response)
    }
}