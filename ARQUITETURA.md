# Arquitetura - SGHSS

## 1. Visão Geral da Arquitetura

O SGHSS segue uma arquitetura em camadas (Layered Architecture) com separação clara de responsabilidades:

```
┌─────────────────────────────────────────────────────┐
│                   Controller Layer                   │
│              (REST Endpoints - HTTP)                │
└────────────────┬────────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────────┐
│                   Service Layer                      │
│          (Business Logic & Validations)             │
└────────────────┬────────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────────┐
│                Repository Layer                      │
│           (Data Access - JPA/Hibernate)            │
└────────────────┬────────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────────┐
│                Database Layer                        │
│         (H2 em dev / PostgreSQL em prod)            │
└─────────────────────────────────────────────────────┘
```

## 2. Padrões de Design Utilizados

### 2.1 MVC (Model-View-Controller)
- **Model**: Entidades JPA (@Entity)
- **View**: DTOs (Data Transfer Objects)
- **Controller**: REST Controllers

### 2.2 DTO (Data Transfer Object)
Todas as chamadas HTTP usam DTOs para:
- Validação de entrada
- Segurança (não expor IDs internos)
- Desacoplamento

Exemplo:
```java
PacienteDTO → Controller → Service → Paciente (Entity) → Repository
```

### 2.3 Service Pattern
- Lógica de negócio centralizada
- Transações gerenciadas automaticamente
- Reutilização de código

### 2.4 Repository Pattern
- Abstração de acesso a dados via JPA
- Queries customizadas quando necessário
- Facilita testes (mock)

### 2.5 Dependency Injection
- Spring gerencia todas as dependências
- @Autowired e constructor injection
- Facilita testes

## 3. Fluxo de Requisição

### 3.1 Criar Paciente

```
1. Cliente envia POST /api/pacientes
   ↓
2. PacienteController recebe a requisição
   ↓
3. Valida o PacienteDTO
   ↓
4. Chama PacienteService.criar()
   ↓
5. Service valida regras de negócio
   - Verifica se CPF já existe
   - Criptografa/valida dados sensíveis
   ↓
6. Chama PacienteRepository.save()
   ↓
7. Hibernate converte para SQL INSERT
   ↓
8. Banco de dados salva o registro
   ↓
9. Service converte Paciente em PacienteDTO
   ↓
10. Controller retorna 201 CREATED com DTO
```

### 3.2 Login

```
1. Cliente envia POST /auth/login com LoginDTO
   ↓
2. UsuarioController recebe
   ↓
3. UsuarioService.login()
   - Busca usuário por email
   - Valida senha com BCryptPasswordEncoder.matches()
   - Gera token JWT com JwtUtil.generateToken()
   ↓
4. Retorna resposta com token
   ↓
5. Cliente usa token em Authorization header
```

## 4. Entidades e Relacionamentos

### 4.1 Diagrama de Classes

```
┌──────────────────┐
│     Usuario      │
├──────────────────┤
│ - id: Long       │
│ - nome: String   │
│ - email: String  │
│ - senha: String  │
│ - perfil: String │
│ - ativo: Boolean │
└──────────────────┘

┌──────────────────────┐
│      Paciente        │
├──────────────────────┤
│ - id: Long          │
│ - nome: String      │
│ - cpf: String (UK)  │
│ - email: String     │
│ - telefone: String  │
│ - dataNascimento    │
│ - ativo: Boolean    │
└──────────────────────┘
         │
         │ 1:N
         ▼
┌──────────────────────┐
│     Consulta         │
├──────────────────────┤
│ - id: Long          │
│ - dataHora: LDT     │
│ - status: String    │
│ - pacienteId: Long  │ (FK)
│ - profissionalId: L │ (FK)
│ - observacoes: Str  │
│ - ativo: Boolean    │
└──────────────────────┘
         │
         │ M:N com derivação
         ▼
┌────────────────────────┐
│    Prontuario          │
├────────────────────────┤
│ - id: Long            │
│ - descricao: String   │
│ - dataRegistro: LDT   │
│ - diagnostico: String │
│ - tratamento: String  │
│ - pacienteId: Long    │ (FK)
│ - profissionalId: Lon │ (FK)
│ - ativo: Boolean      │
└────────────────────────┘

┌──────────────────────────┐
│  ProfissionalSaude       │
├──────────────────────────┤
│ - id: Long              │
│ - nome: String          │
│ - especialidade: String │
│ - crm: String (UK)      │
│ - email: String         │
│ - ativo: Boolean        │
└──────────────────────────┘
```

## 5. Configurações

### 5.1 Security (WebSecurityConfig)
- CSRF desabilitado (API REST stateless)
- CORS configurado para múltiplas origens
- Session Management: STATELESS (sem sessão)
- Basic Auth desabilitado

### 5.2 JWT (JwtUtil)
- Algoritmo: HS512 (HMAC-SHA512)
- Tempo de expiração: 24 horas (configurável)
- Claims: userId, email
- Secret: armazenado em application.yml

### 5.3 Database (application.yml)
**Desenvolvimento (H2):**
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
  jpa:
    hibernate:
      ddl-auto: update
```

**Produção (PostgreSQL):**
```yaml
spring:
  datasource:
    url: jdbc:postgresql://host:5432/sghss
    username: ${DB_USER}
    password: ${DB_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: validate
```

## 6. Tratamento de Exceções

### 6.1 Exception Handler
```
Exceção
   ↓
GlobalExceptionHandler.@ExceptionHandler
   ↓
Identifica tipo de exceção
   ↓
Retorna resposta HTTP apropriada
   ↓
Cliente recebe JSON com erro
```

### 6.2 Tipos de Exceção

| Exceção | Status HTTP | Mensagem |
|---------|------------|----------|
| ResourceNotFoundException | 404 | Recurso não encontrado |
| IllegalArgumentException | 400 | Erro de validação |
| MethodArgumentNotValidException | 422 | Validação de campos |
| Exception (genérica) | 500 | Erro interno |

## 7. Validação de Dados

### 7.1 Validações em Camadas

```
Request Body (JSON)
   ↓
DTO Validations (@NotNull, @Email, @Pattern, etc.)
   ↓
Service Business Rules (CPF único, etc.)
   ↓
Database Constraints (UNIQUE, NOT NULL, etc.)
```

### 7.2 Validações de Paciente
```java
@NotBlank(message = "Nome é obrigatório")
private String nome;

@Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos")
private String cpf;

@Email(message = "Email inválido")
private String email;
```

## 8. Transações

Configuração padrão:

```java
@Transactional  // Por padrão em @Service
public void criar(PacienteDTO dto) {
    // Se exceção lançada → ROLLBACK
    // Se sucesso → COMMIT
}
```

**Propagação**: REQUIRED (padrão)
**Isolamento**: READ_COMMITTED (padrão)
**Timeout**: Padrão do banco

## 9. Desempenho

### 9.1 Lazy Loading vs Eager Loading

```java
// Relacionamento com @ManyToOne
@ManyToOne(fetch = FetchType.EAGER)
private Paciente paciente;  // Carrega sempre
```

**Estratégia atual**: EAGER (simples, mas pode ser lento)
**Alternativa**: LAZY + @Transactional (mais complexo)

### 9.2 Índices de Banco
```sql
CREATE INDEX idx_paciente_cpf ON pacientes(cpf);
CREATE INDEX idx_paciente_email ON pacientes(email);
CREATE INDEX idx_consulta_paciente ON consultas(paciente_id);
CREATE INDEX idx_consulta_profissional ON consultas(profissional_id);
```

## 10. Segurança

### 10.1 Estratégias
- ✅ Senhas com BCrypt
- ✅ JWT para autenticação
- ✅ CORS configurado
- ✅ CSRF desabilitado (REST stateless)
- ✅ Validação de entrada
- ⚠️ TODO: Rate limiting
- ⚠️ TODO: Auditoria de logs
- ⚠️ TODO: Criptografia de dados sensíveis

### 10.2 Fluxo de Autenticação

```
Cliente → Envia (email, senha)
          ↓
   UsuarioService.login()
          ↓
   BCrypt.matches(senha, senhaHash)
          ↓
   JwtUtil.generateToken()
          ↓
   Retorna token
          ↓
Cliente → Usa token em Authorization header
          ↓
   Acessa endpoints protegidos
```

## 11. Escalabilidade Futura

### 11.1 Microserviços
```
API Gateway
├── Paciente Microservice
├── Profissional Microservice
├── Consulta Microservice
└── Prontuario Microservice
```

### 11.2 Cache
```java
@Cacheable(value = "pacientes", key = "#id")
public PacienteDTO buscarPorId(Long id) { ... }
```

### 11.3 Message Queue
- RabbitMQ/Kafka para eventos assíncronos
- Notificações de consultas

---

**Data de Atualização**: 28/03/2026
**Arquiteto**: Poliane Fernandes de Godoy

