# DER - Diagrama Entidade-Relacionamento

## 1. Diagrama Visual (Texto)

```
                              ┌─────────────────────┐
                              │     USUARIO         │
                              ├─────────────────────┤
                              │ PK │ id (Long)      │
                              │    │ nome (String)  │
                              │    │ email (String) │ UNIQUE
                              │    │ senha (String) │
                              │    │ perfil (String)│
                              │    │ ativo (Boolean)│
                              └─────────────────────┘


┌─────────────────────┐                    ┌──────────────────────────┐
│     PACIENTE        │◄──────── 1:N ──────│       CONSULTA           │
├─────────────────────┤                    ├──────────────────────────┤
│ PK │ id (Long)      │                    │ PK │ id (Long)          │
│    │ nome (String)  │                    │    │ dataHora (LDT)     │
│    │ cpf (String)   │ UNIQUE             │    │ status (String)    │
│    │ email (String) │ UNIQUE             │    │ observacoes (Str)  │
│    │ telefone (Str) │                    │    │ ativo (Boolean)    │
│    │ dataNasc (LD)  │                    │ FK │ paciente_id (Long) │
│    │ ativo (Boolean)│                    │ FK │ profissional_id (L)│
└─────────────────────┘                    └──────────────────────────┘


┌─────────────────────────┐                ┌──────────────────────────┐
│  PROFISSIONAL_SAUDE     │◄──── N:1 ──────│       CONSULTA           │
├─────────────────────────┤                └──────────────────────────┘
│ PK │ id (Long)          │
│    │ nome (String)      │
│    │ especialidade (Str)│
│    │ crm (String)       │ UNIQUE
│    │ email (String)     │ UNIQUE
│    │ ativo (Boolean)    │
└─────────────────────────┘
         │
         │ 1:N
         ▼
┌──────────────────────────┐
│      PRONTUARIO          │
├──────────────────────────┤
│ PK │ id (Long)          │
│    │ descricao (TEXT)   │
│    │ dataRegistro (LDT) │
│    │ diagnostico (Str)  │
│    │ tratamento (Str)   │
│    │ ativo (Boolean)    │
│ FK │ paciente_id (Long) │◄─────────────┐
│ FK │ profissional_id(L) │◄─────────────┤
└──────────────────────────┘              │
                          ┌──────────────┴──────────┐
                          │ Relacionamentos M:N    │
                          │ via Consulta/Prontuario│
                          └───────────────────────┘
```

## 2. Descrição das Entidades

### 2.1 USUARIO
**Propósito**: Armazenar dados de autenticação e autorização

| Campo | Tipo | Constraints | Descrição |
|-------|------|-------------|-----------|
| id | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| nome | VARCHAR(255) | NOT NULL | Nome completo do usuário |
| email | VARCHAR(255) | NOT NULL, UNIQUE | Email para login |
| senha | VARCHAR(255) | NOT NULL | Senha criptografada em BCrypt |
| perfil | VARCHAR(50) | NOT NULL | ADMIN, PROFISSIONAL, PACIENTE |
| ativo | BOOLEAN | NOT NULL, DEFAULT TRUE | Flag de usuário ativo |

### 2.2 PACIENTE
**Propósito**: Registrar informações de pacientes

| Campo | Tipo | Constraints | Descrição |
|-------|------|-------------|-----------|
| id | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| nome | VARCHAR(255) | NOT NULL | Nome completo do paciente |
| cpf | VARCHAR(11) | NOT NULL, UNIQUE | CPF (11 dígitos) |
| email | VARCHAR(255) | UNIQUE | Email de contato |
| telefone | VARCHAR(15) | | Telefone com DDD |
| data_nascimento | DATE | | Data de nascimento |
| ativo | BOOLEAN | NOT NULL, DEFAULT TRUE | Flag de paciente ativo |

### 2.3 PROFISSIONAL_SAUDE
**Propósito**: Registrar profissionais de saúde

| Campo | Tipo | Constraints | Descrição |
|-------|------|-------------|-----------|
| id | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| nome | VARCHAR(255) | NOT NULL | Nome completo do profissional |
| especialidade | VARCHAR(100) | NOT NULL | Ex: Cardiologia, Dermatologia |
| crm | VARCHAR(50) | NOT NULL, UNIQUE | CRM do profissional |
| email | VARCHAR(255) | UNIQUE | Email para contato |
| ativo | BOOLEAN | NOT NULL, DEFAULT TRUE | Flag de profissional ativo |

### 2.4 CONSULTA
**Propósito**: Registrar agendamentos e histórico de consultas

| Campo | Tipo | Constraints | Descrição |
|-------|------|-------------|-----------|
| id | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| data_hora | TIMESTAMP | NOT NULL | Data e hora da consulta |
| status | VARCHAR(50) | NOT NULL | AGENDADA, REALIZADA, CANCELADA |
| paciente_id | BIGINT | NOT NULL, FK | Referência a PACIENTE |
| profissional_id | BIGINT | NOT NULL, FK | Referência a PROFISSIONAL_SAUDE |
| observacoes | VARCHAR(500) | | Observações da consulta |
| ativo | BOOLEAN | NOT NULL, DEFAULT TRUE | Flag de consulta ativa |

**Índices:**
- `idx_consulta_paciente` ON (paciente_id)
- `idx_consulta_profissional` ON (profissional_id)
- `idx_consulta_status` ON (status)
- `idx_consulta_data` ON (data_hora)

### 2.5 PRONTUARIO
**Propósito**: Registrar prontuários médicos de pacientes

| Campo | Tipo | Constraints | Descrição |
|-------|------|-------------|-----------|
| id | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| descricao | TEXT | NOT NULL | Descrição detalhada do atendimento |
| data_registro | TIMESTAMP | NOT NULL | Data de registro do prontuário |
| diagnostico | VARCHAR(500) | | Diagnóstico realizado |
| tratamento | VARCHAR(500) | | Tratamento recomendado |
| paciente_id | BIGINT | NOT NULL, FK | Referência a PACIENTE |
| profissional_id | BIGINT | NOT NULL, FK | Referência a PROFISSIONAL_SAUDE |
| ativo | BOOLEAN | NOT NULL, DEFAULT TRUE | Flag de prontuário ativo |

**Índices:**
- `idx_prontuario_paciente` ON (paciente_id)
- `idx_prontuario_profissional` ON (profissional_id)
- `idx_prontuario_data` ON (data_registro)

## 3. Relacionamentos

### 3.1 Paciente → Consulta (1:N)
- Um paciente pode ter muitas consultas
- Uma consulta pertence a um paciente
- **Foreign Key**: consulta.paciente_id → paciente.id
- **Operação em Cascata**: Paciente deletado marca consultas como inativas

### 3.2 Profissional_Saude → Consulta (1:N)
- Um profissional pode ter muitas consultas
- Uma consulta é realizada por um profissional
- **Foreign Key**: consulta.profissional_id → profissional.id
- **Operação em Cascata**: Profissional deletado marca consultas como inativas

### 3.3 Paciente → Prontuario (1:N)
- Um paciente pode ter muitos prontuários
- Um prontuário pertence a um paciente
- **Foreign Key**: prontuario.paciente_id → paciente.id
- **Operação em Cascata**: Paciente deletado marca prontuários como inativos

### 3.4 Profissional_Saude → Prontuario (1:N)
- Um profissional pode registrar muitos prontuários
- Um prontuário é registrado por um profissional
- **Foreign Key**: prontuario.profissional_id → profissional.id
- **Operação em Cascata**: Profissional deletado marca prontuários como inativos

### 3.5 Relacionamento Implícito: Paciente ↔ Profissional (M:N)
- Através de **CONSULTA**: Múltiplos pacientes podem consultar múltiplos profissionais
- Através de **PRONTUARIO**: Múltiplos profissionais podem registrar prontuários de múltiplos pacientes

## 4. Script SQL DDL (Create Tables)

```sql
CREATE TABLE usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    perfil VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE paciente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(255) UNIQUE,
    telefone VARCHAR(15),
    data_nascimento DATE,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE profissional_saude (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    crm VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(255) UNIQUE,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE consulta (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    data_hora TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    paciente_id BIGINT NOT NULL,
    profissional_id BIGINT NOT NULL,
    observacoes VARCHAR(500),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    FOREIGN KEY (profissional_id) REFERENCES profissional_saude(id)
);

CREATE TABLE prontuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    descricao TEXT NOT NULL,
    data_registro TIMESTAMP NOT NULL,
    diagnostico VARCHAR(500),
    tratamento VARCHAR(500),
    paciente_id BIGINT NOT NULL,
    profissional_id BIGINT NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    FOREIGN KEY (profissional_id) REFERENCES profissional_saude(id)
);

-- Índices
CREATE INDEX idx_consulta_paciente ON consulta(paciente_id);
CREATE INDEX idx_consulta_profissional ON consulta(profissional_id);
CREATE INDEX idx_consulta_status ON consulta(status);
CREATE INDEX idx_consulta_data ON consulta(data_hora);

CREATE INDEX idx_prontuario_paciente ON prontuario(paciente_id);
CREATE INDEX idx_prontuario_profissional ON prontuario(profissional_id);
CREATE INDEX idx_prontuario_data ON prontuario(data_registro);
```

## 5. Integridade do Banco

### 5.1 Validações de Integridade
- **Entity Integrity**: Todas as tabelas têm PK
- **Referential Integrity**: FK validadas
- **Domain Integrity**: Tipos de dados corretos
- **User-Defined Integrity**: CHECK constraints e validações em aplicação

### 5.2 Regras de Negócio
1. CPF é único (não pode ter dois pacientes com mesmo CPF)
2. CRM é único (não pode ter dois profissionais com mesmo CRM)
3. Email é único em Usuario e Paciente
4. CPF e CRM devem ser válidos (validação em aplicação)
5. Status de consulta pode ser: AGENDADA, REALIZADA, CANCELADA
6. Perfil de usuário pode ser: ADMIN, PROFISSIONAL, PACIENTE
7. Soft delete: ativo = false (não remove registro)

## 6. Análise de Capacidade

### 6.1 Estimativa de Tamanho
Assumindo 100.000 registros de cada tabela:

| Tabela | Registros | Tamanho Estimado |
|--------|-----------|------------------|
| USUARIO | 10.000 | ~5 MB |
| PACIENTE | 100.000 | ~30 MB |
| PROFISSIONAL_SAUDE | 1.000 | ~1 MB |
| CONSULTA | 500.000 | ~100 MB |
| PRONTUARIO | 300.000 | ~150 MB |
| **Total** | | **~286 MB** |

### 6.2 Backup Strategy
- Full backup: Diariamente
- Incremental backup: A cada 6 horas
- Retenção: 30 dias

---

**Data de Atualização**: 15/01/2024
**Responsável**: Poliane

