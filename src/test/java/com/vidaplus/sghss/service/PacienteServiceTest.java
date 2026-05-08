package com.vidaplus.sghss.service;

import com.vidaplus.sghss.dto.PacienteDTO;
import com.vidaplus.sghss.model.Paciente;
import com.vidaplus.sghss.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    private PacienteDTO pacienteDTO;
    private Paciente paciente;

    @BeforeEach
    void setUp() {
        pacienteDTO = new PacienteDTO(
            null,
            "João Silva",
            "12345678901",
            "joao@email.com",
            "11999999999",
            LocalDate.of(1990, 1, 1),
            true
        );

        paciente = new Paciente(
            1L,
            "João Silva",
            "12345678901",
            "joao@email.com",
            "11999999999",
            LocalDate.of(1990, 1, 1),
            true
        );
    }

    @Test
    void testCriarPaciente() {
        when(pacienteRepository.findByCpf(pacienteDTO.getCpf())).thenReturn(Optional.empty());
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        PacienteDTO resultado = pacienteService.criar(pacienteDTO);

        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getNome());
        assertEquals("12345678901", resultado.getCpf());
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }

    @Test
    void testCriarPacienteDuplicado() {
        when(pacienteRepository.findByCpf(pacienteDTO.getCpf())).thenReturn(Optional.of(paciente));

        assertThrows(IllegalArgumentException.class, () -> pacienteService.criar(pacienteDTO));
        verify(pacienteRepository, never()).save(any(Paciente.class));
    }

    @Test
    void testBuscarPorId() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        PacienteDTO resultado = pacienteService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getNome());
        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorIdNaoEncontrado() {
        when(pacienteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> pacienteService.buscarPorId(99L));
        verify(pacienteRepository, times(1)).findById(99L);
    }

    @Test
    void testListarPacientes() {
        Paciente paciente2 = new Paciente(
            2L,
            "Maria Silva",
            "98765432101",
            "maria@email.com",
            "11988888888",
            LocalDate.of(1995, 5, 15),
            true
        );

        when(pacienteRepository.findByAtivoTrue()).thenReturn(Arrays.asList(paciente, paciente2));

        List<PacienteDTO> resultado = pacienteService.listar();

        assertEquals(2, resultado.size());
        verify(pacienteRepository, times(1)).findByAtivoTrue();
    }

    @Test
    void testAtualizarPaciente() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        PacienteDTO atualizado = new PacienteDTO(
            1L,
            "João Silva Atualizado",
            "12345678901",
            "joao.novo@email.com",
            "11999999999",
            LocalDate.of(1990, 1, 1),
            true
        );

        PacienteDTO resultado = pacienteService.atualizar(1L, atualizado);

        assertNotNull(resultado);
        verify(pacienteRepository, times(1)).findById(1L);
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }

    @Test
    void testDeletarPaciente() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        pacienteService.deletar(1L);

        assertFalse(paciente.getAtivo());
        verify(pacienteRepository, times(1)).findById(1L);
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }
}

