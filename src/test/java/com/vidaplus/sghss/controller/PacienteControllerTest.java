package com.vidaplus.sghss.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vidaplus.sghss.dto.PacienteDTO;
import com.vidaplus.sghss.service.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PacienteService pacienteService;

    private PacienteDTO pacienteDTO;

    @BeforeEach
    void setUp() {
        pacienteDTO = new PacienteDTO(
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
    void testCriarPaciente() throws Exception {
        when(pacienteService.criar(any(PacienteDTO.class))).thenReturn(pacienteDTO);

        mockMvc.perform(post("/api/pacientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pacienteDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João Silva"));

        verify(pacienteService, times(1)).criar(any(PacienteDTO.class));
    }

    @Test
    void testListarPacientes() throws Exception {
        List<PacienteDTO> pacientes = Arrays.asList(pacienteDTO);
        when(pacienteService.listar()).thenReturn(pacientes);

        mockMvc.perform(get("/api/pacientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("João Silva"));

        verify(pacienteService, times(1)).listar();
    }

    @Test
    void testBuscarPacientePorId() throws Exception {
        when(pacienteService.buscarPorId(1L)).thenReturn(pacienteDTO);

        mockMvc.perform(get("/api/pacientes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João Silva"));

        verify(pacienteService, times(1)).buscarPorId(1L);
    }

    @Test
    void testAtualizarPaciente() throws Exception {
        when(pacienteService.atualizar(eq(1L), any(PacienteDTO.class))).thenReturn(pacienteDTO);

        mockMvc.perform(put("/api/pacientes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pacienteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João Silva"));

        verify(pacienteService, times(1)).atualizar(eq(1L), any(PacienteDTO.class));
    }

    @Test
    void testDeletarPaciente() throws Exception {
        doNothing().when(pacienteService).deletar(1L);

        mockMvc.perform(delete("/api/pacientes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(pacienteService, times(1)).deletar(1L);
    }
}

