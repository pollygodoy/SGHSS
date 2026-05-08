# Requisitos Funcionais e Não Funcionais

## 1. Requisitos Funcionais (RF)

### 1.1 Módulo de Pacientes

| ID | Descrição | Prioridade | Status |
|----|-----------|-----------|--------|
| RF-001 | Criar novo paciente | Alta | ✅ Implementado |
| RF-002 | Listar todos os pacientes | Alta | ✅ Implementado |
| RF-003 | Buscar paciente por ID | Alta | ✅ Implementado |
| RF-004 | Atualizar dados do paciente | Alta | ✅ Implementado |
| RF-005 | Deletar paciente (soft delete) | Alta | ✅ Implementado |
| RF-006 | Validar CPF único | Alta | ✅ Implementado |
| RF-007 | Validar dados de entrada | Alta | ✅ Implementado |

**Descrição Detalhada:**

- **RF-001**: Sistema deve permitir criar um novo paciente com nome, CPF, email, telefone e data de nascimento
- **RF-002**: Sistema deve listar todos os pacientes ativos
- **RF-003**: Sistema deve retornar os dados de um paciente específico pelo ID
- **RF-004**: Sistema deve permitir atualizar nome, email, telefone e data de nascimento
- **RF-005**: Sistema deve marcar paciente como inativo (soft delete, não removê-lo do BD)
- **RF-006**: Sistema deve garantir que não existam dois pacientes com mesmo CPF
- **RF-007**: Sistema deve validar formato de email, telefone, CPF e outros dados

### 1.2 Módulo de Profissionais de Saúde

| ID | Descrição | Prioridade | Status |
|----|-----------|-----------|--------|
| RF-008 | Criar novo profissional | Alta | ✅ Implementado |
| RF-009 | Listar profissionais | Alta | ✅ Implementado |
| RF-010 | Buscar profissional por ID | Alta | ✅ Implementado |
| RF-011 | Filtrar por especialidade | Média | ✅ Implementado |
| RF-012 | Atualizar profissional | Alta | ✅ Implementado |
| RF-013 | Deletar profissional | Alta | ✅ Implementado |
| RF-014 | Validar CRM único | Alta | ✅ Implementado |

### 1.3 Módulo de Consultas

| ID | Descrição | Prioridade | Status |
|----|-----------|-----------|--------|
| RF-015 | Agendar nova consulta | Alta | ✅ Implementado |
| RF-016 | Listar consultas | Alta | ✅ Implementado |
| RF-017 | Buscar consulta por ID | Alta | ✅ Implementado |
| RF-018 | Buscar consultas por paciente | Alta | ✅ Implementado |
| RF-019 | Buscar consultas por profissional | Alta | ✅ Implementado |
| RF-020 | Cancelar consulta | Alta | ✅ Implementado |
| RF-021 | Atualizar consulta | Média | ✅ Implementado |
| RF-022 | Status de consulta | Alta | ✅ Implementado |

**Estados de Consulta:**
- AGENDADA: Consulta planejada
- REALIZADA: Consulta que ocorreu
- CANCELADA: Consulta cancelada

### 1.4 Módulo de Prontuários

| ID | Descrição | Prioridade | Status |
|----|-----------|-----------|--------|
| RF-023 | Criar prontuário | Alta | ✅ Implementado |
| RF-024 | Listar prontuários | Alta | ✅ Implementado |
| RF-025 | Buscar prontuário por ID | Alta | ✅ Implementado |
| RF-026 | Buscar prontuário por paciente | Alta | ✅ Implementado |
| RF-027 | Atualizar prontuário | Alta | ✅ Implementado |
| RF-028 | Deletar prontuário | Alta | ✅ Implementado |
| RF-029 | Registrar diagnóstico | Alta | ✅ Implementado |
| RF-030 | Registrar tratamento | Alta | ✅ Implementado |

### 1.5 Módulo de Autenticação

| ID | Descrição | Prioridade | Status |
|----|-----------|-----------|--------|
| RF-031 | Registrar novo usuário | Alta | ✅ Implementado |
| RF-032 | Realizar login | Alta | ✅ Implementado |
| RF-033 | Gerar token JWT | Alta | ✅ Implementado |
| RF-034 | Validar token JWT | Alta | ✅ Implementado |
| RF-035 | Definir perfil de usuário | Alta | ✅ Implementado |

**Perfis de Usuário:**
- ADMIN: Acesso total
- PROFISSIONAL: Pode registrar consultas e prontuários
- PACIENTE: Pode visualizar seus próprios dados

## 2. Requisitos Não Funcionais (RNF)

### 2.1 Performance

| ID | Descrição | Critério | Status |
|----|-----------|----------|--------|
| RNF-001 | Tempo de resposta GET | < 1 segundo | ✅ Atende |
| RNF-002 | Tempo de resposta POST | < 500ms | ✅ Atende |
| RNF-003 | Tempo de resposta PUT | < 800ms | ✅ Atende |
| RNF-004 | Tempo de resposta DELETE | < 300ms | ✅ Atende |
| RNF-005 | Capacidade de usuários | 100+ simultâneos | ⚠️ Em validação |
| RNF-006 | Throughput | 1000+ req/min | ⚠️ Em validação |

### 2.2 Segurança

| ID | Descrição | Implementação | Status |
|----|-----------|----------------|--------|
| RNF-007 | Criptografia de senha | BCrypt (10 rounds) | ✅ Implementado |
| RNF-008 | Autenticação | JWT (HS512) | ✅ Implementado |
| RNF-009 | Autorização | Spring Security | ✅ Implementado |
| RNF-010 | Validação de entrada | Jakarta Validation | ✅ Implementado |
| RNF-011 | HTTPS | SSL/TLS em produção | ⚠️ Pendente |
| RNF-012 | CORS | Configurado | ✅ Implementado |
| RNF-013 | Rate limiting | API Gateway | ⚠️ Pendente |
| RNF-014 | Auditoria | Logging detalhado | ✅ Parcial |
| RNF-015 | Proteção contra SQL Injection | JPA Parameterizado | ✅ Implementado |

### 2.3 Disponibilidade

| ID | Descrição | SLA | Status |
|----|-----------|-----|--------|
| RNF-016 | Uptime mínimo | 99.9% | ⚠️ Pendente |
| RNF-017 | Recovery Time Objective (RTO) | < 1 hora | ⚠️ Pendente |
| RNF-018 | Recovery Point Objective (RPO) | < 15 min | ⚠️ Pendente |
| RNF-019 | Backup automático | Diário | ⚠️ Pendente |

### 2.4 Escalabilidade

| ID | Descrição | Estratégia | Status |
|----|-----------|-----------|--------|
| RNF-020 | Crescimento de usuários | Horizontal scaling | ⚠️ Planejado |
| RNF-021 | Crescimento de dados | Índices, particionamento | ✅ Parcial |
| RNF-022 | Cache | Redis/Memcached | ⚠️ Futuro |
| RNF-023 | Microserviços | Possível refatoração | ⚠️ Planejado |

### 2.5 Confiabilidade

| ID | Descrição | Implementação | Status |
|----|-----------|----------------|--------|
| RNF-024 | Tratamento de exceções | GlobalExceptionHandler | ✅ Implementado |
| RNF-025 | Logging | SLF4J + Logback | ✅ Implementado |
| RNF-026 | Validação de dados | Jakarta Validation + Service | ✅ Implementado |
| RNF-027 | Transações | @Transactional | ✅ Implementado |
| RNF-028 | Integridade referencial | Foreign Keys | ✅ Implementado |

### 2.6 Manutenibilidade

| ID | Descrição | Implementação | Status |
|----|-----------|----------------|--------|
| RNF-029 | Código limpo | Clean Code + SOLID | ✅ Aplicado |
| RNF-030 | Documentação | Javadoc + Markdown | ✅ Implementado |
| RNF-031 | Testes unitários | JUnit 5 | ✅ Implementado |
| RNF-032 | Testes de integração | Spring Boot Test | ✅ Planejado |
| RNF-033 | Cobertura de testes | Target: 80%+ | ⚠️ Em progresso |
| RNF-034 | CI/CD | GitHub Actions | ⚠️ Futuro |

### 2.7 Compatibilidade

| ID | Descrição | Versão Suportada | Status |
|----|-----------|------------------|--------|
| RNF-035 | Java | 21+ | ✅ Aplicado |
| RNF-036 | Spring Boot | 3.3+ | ✅ Aplicado |
| RNF-037 | Maven | 3.8+ | ✅ Testado |
| RNF-038 | Banco de Dados | H2 (dev), PostgreSQL (prod) | ✅ Suportado |
| RNF-039 | Navegador | Chrome, Firefox, Edge (2023+) | ✅ Suportado |

### 2.8 Usabilidade

| ID | Descrição | Implementação | Status |
|----|-----------|----------------|--------|
| RNF-040 | API self-documenting | Swagger UI | ✅ Implementado |
| RNF-041 | Mensagens de erro | Claras e úteis | ✅ Implementado |
| RNF-042 | Padronização de respostas | JSON consistente | ✅ Implementado |
| RNF-043 | Códigos HTTP corretos | RFC 7231 | ✅ Implementado |

## 3. Casos de Uso Críticos

### Caso 1: Agendar Consulta
```
Pré-condição: Paciente e Profissional existem no sistema
1. Usuário seleciona paciente
2. Usuário seleciona profissional
3. Usuário seleciona data e hora
4. Sistema valida disponibilidade
5. Sistema cria consulta com status AGENDADA
6. Sistema retorna ID da consulta
Pós-condição: Consulta criada e gravada no BD
```

### Caso 2: Login
```
1. Usuário entra com email e senha
2. Sistema busca usuário por email
3. Sistema valida senha com BCrypt
4. Sistema gera token JWT
5. Sistema retorna token
6. Cliente armazena token
7. Cliente usa token em Authorization header
```

### Caso 3: Criar Prontuário
```
Pré-condição: Consulta foi realizada
1. Profissional entra descrição
2. Profissional entra diagnóstico e tratamento
3. Sistema valida dados
4. Sistema cria prontuário
5. Sistema associa a paciente e profissional
6. Sistema retorna prontuário criado
```

## 4. Restrições Técnicas

1. **Soft Delete**: Nenhum registro é fisicamente deletado, apenas marcado como inativo
2. **Transações**: Todas as operações críticas são transacionais
3. **Validação**: Validação em dois níveis (DTO e Service)
4. **Status codes**: Sempre retornar código HTTP apropriado
5. **Paginação**: TODO - Implementar paginação em listas grandes
6. **Filtros**: TODO - Implementar filtros avançados

## 5. Métricas de Sucesso

- ✅ 100% dos endpoints funcionam
- ⚠️ 80% de cobertura de testes (meta)
- ✅ 0% de vulnerabilidades críticas
- ✅ Tempo de resposta < 1s em 95% dos casos
- ⚠️ 99.5% de uptime (produção)

## 6. Roadmap Futuro

### V1.1 (Próximas semanas)
- [ ] Implementar paginação
- [ ] Adicionar filtros avançados
- [ ] Melhorar testes de integração
- [ ] Deploy em servidor

### V2.0 (Planejado)
- [ ] App mobile
- [ ] Notificações de consultas
- [ ] Relatórios analíticos
- [ ] Integração com calendário
- [ ] Videoconferência para consultas remotas

---

**Data de Atualização**: 15/01/2024
**Responsável**: Poliane
**Versão**: 1.0

