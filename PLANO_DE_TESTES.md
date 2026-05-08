# Plano de Testes - SGHSS

## 1. Testes Unitários

### 1.1 PacienteService
- [x] Criar paciente com sucesso
- [x] Criar paciente com CPF duplicado (deve lançar exceção)
- [x] Buscar paciente por ID com sucesso
- [x] Buscar paciente com ID inválido (deve lançar exceção)
- [x] Listar todos os pacientes
- [x] Atualizar paciente com sucesso
- [x] Deletar paciente (soft delete)

### 1.2 PacienteController
- [x] POST /pacientes - Criar novo paciente
- [x] GET /pacientes - Listar pacientes
- [x] GET /pacientes/{id} - Buscar por ID
- [x] PUT /pacientes/{id} - Atualizar paciente
- [x] DELETE /pacientes/{id} - Deletar paciente

## 2. Testes de Integração

### 2.1 Autenticação
- [ ] Login com credenciais válidas
- [ ] Login com credenciais inválidas
- [ ] Token JWT gerado corretamente
- [ ] Validação de token JWT

### 2.2 Pacientes (API)
- [ ] Criar paciente via POST
- [ ] CPF com caracteres inválidos
- [ ] Email inválido
- [ ] Telefone com formato incorreto
- [ ] Listar pacientes retorna lista vazia inicialmente
- [ ] Criar múltiplos pacientes

### 2.3 Profissionais (API)
- [ ] Criar profissional via POST
- [ ] CRM duplicado (deve falhar)
- [ ] Listar profissionais por especialidade
- [ ] Buscar profissional por ID

### 2.4 Consultas (API)
- [ ] Agendar consulta com paciente e profissional válidos
- [ ] Agendar consulta com paciente inválido (deve falhar)
- [ ] Cancelar consulta existente
- [ ] Listar consultas de um paciente
- [ ] Listar consultas de um profissional

### 2.5 Prontuários (API)
- [ ] Criar prontuário via POST
- [ ] Listar prontuários de um paciente
- [ ] Atualizar prontuário com diagnóstico
- [ ] Deletar prontuário

## 3. Testes de Validação

### 3.1 Validações em Paciente
| Campo | Teste | Esperado |
|-------|-------|----------|
| Nome | Vazio | Erro 422 |
| CPF | Menos de 11 dígitos | Erro 422 |
| CPF | Duplicado | Erro 400 |
| Email | Formato inválido | Erro 422 |
| Telefone | Menos de 10 dígitos | Erro 422 |

### 3.2 Validações em Profissional
| Campo | Teste | Esperado |
|-------|-------|----------|
| Nome | Vazio | Erro 422 |
| Especialidade | Vazio | Erro 422 |
| CRM | Duplicado | Erro 400 |
| Email | Formato inválido | Erro 422 |

### 3.3 Validações em Consulta
| Campo | Teste | Esperado |
|-------|-------|----------|
| Data/Hora | Vazio | Erro 422 |
| Paciente ID | Inválido | Erro 400 |
| Profissional ID | Inválido | Erro 400 |

## 4. Testes de Segurança

### 4.1 Autenticação
- [ ] Endpoint /auth/login funciona sem autenticação
- [ ] Endpoints protegidos retornam 401 sem token
- [ ] Token JWT expirado é rejeitado
- [ ] Token JWT inválido é rejeitado
- [ ] Senha é criptografada com BCrypt

### 4.2 Autorização
- [ ] Usuário com perfil PACIENTE pode acessar seus dados
- [ ] Usuário com perfil PROFISSIONAL pode registrar consultas
- [ ] Usuário com perfil ADMIN pode acessar dados de todos

## 5. Testes de Desempenho

### 5.1 Tempo de Resposta
- [ ] GET /pacientes com 1000 registros < 2s
- [ ] GET /consultas/paciente/{id} < 1s
- [ ] POST /pacientes < 500ms

### 5.2 Carga
- [ ] Sistema suporta 100 requisições simultâneas
- [ ] Sem vazamento de memória
- [ ] Conexões de banco de dados são liberadas

## 6. Testes de Tratamento de Erros

### 6.1 Erros HTTP
| Cenário | Status | Resposta |
|---------|--------|----------|
| Recurso não existe | 404 | JSON com mensagem |
| Validação falhou | 422 | JSON com erros de campo |
| Erro genérico | 500 | JSON com mensagem |
| CORS inválido | 403 | Bloqueado |

### 6.2 Resposta de Erro
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Erro de Validação",
  "message": "Descrição do erro"
}
```

## 7. Testes no Postman

### Collection Importar
1. Abrir Postman
2. Clicar em "Import"
3. Importar arquivo: `SGHSS_API_Postman_Collection.json`

### Cenários de Teste

#### Cenário 1: Criar Paciente Completo
1. POST /auth/login (obter token)
2. POST /pacientes (criar paciente)
3. GET /pacientes/{id} (verificar criação)
4. PUT /pacientes/{id} (atualizar)
5. DELETE /pacientes/{id} (deletar)

#### Cenário 2: Agendar Consulta
1. POST /pacientes (criar paciente)
2. POST /profissionais (criar profissional)
3. POST /consultas (agendar consulta)
4. GET /consultas/paciente/{pacienteId}
5. PUT /consultas/{id}/cancelar

#### Cenário 3: Prontuário Médico
1. POST /prontuarios (criar prontuário)
2. GET /prontuarios/paciente/{pacienteId}
3. PUT /prontuarios/{id} (atualizar)

## 8. Cobertura de Código

### Meta de Cobertura
- Serviços: 90%+
- Controllers: 85%+
- Repositories: 100%
- Modelos: 80%+

### Ferramentas
- JUnit 5
- Mockito
- JaCoCo (cobertura)

## 9. Testes de Banco de Dados

### 9.1 H2 (Desenvolvimento)
- [ ] Criar tabelas automaticamente
- [ ] Dados persistem entre requisições
- [ ] Transações funcionam corretamente

### 9.2 PostgreSQL (Produção)
- [ ] Conectar em servidor remoto
- [ ] Replicação de dados funciona
- [ ] Backup automático funciona

## 10. Checklist de Entrega

- [ ] Todos os testes unitários passam
- [ ] Testes de integração passam
- [ ] Cobertura de código > 80%
- [ ] Sem vulnerabilidades de segurança
- [ ] Documentação Swagger atualizada
- [ ] README completo
- [ ] Collection Postman funcional
- [ ] Git commits explicativos

---

**Data de Atualização**: 15/01/2024
**Responsável**: Poliane
**Status**: Em Progresso

