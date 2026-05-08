# SGHSS - Sistema de Gestão Hospitalar e de Serviços de Saúde

## Visão Geral
SGHSS é um sistema REST API desenvolvido em Java 21 com Spring Boot 3.3 para gerenciamento de dados hospitalares e de serviços de saúde.

## Tecnologias Utilizadas
- **Backend**: Java 21 + Spring Boot 3.3.0
- **Banco de Dados**: H2 (desenvolvimento) / PostgreSQL (produção)
- **Build Tool**: Maven
- **Documentação**: Springdoc OpenAPI (Swagger)
- **Autenticação**: JWT (JSON Web Tokens)
- **Segurança**: Spring Security + BCrypt

## Estrutura do Projeto
```
src/main/java/com/vidaplus/sghss/
├── controller/     # REST Controllers
├── service/        # Lógica de negócio
├── repository/     # Acesso a dados (JPA)
├── model/          # Entidades JPA
├── dto/            # Data Transfer Objects
├── config/         # Configurações (Security, JWT, Swagger)
├── exception/      # Tratamento de exceções
└── SghssApplication.java
```

## Entidades Principais

### 1. **Paciente**
- ID (Long)
- Nome (String)
- CPF (String - único)
- Email (String)
- Telefone (String)
- Data Nascimento (LocalDate)
- Ativo (Boolean)

### 2. **Profissional de Saúde**
- ID (Long)
- Nome (String)
- Especialidade (String)
- CRM (String - único)
- Email (String)
- Ativo (Boolean)

### 3. **Consulta**
- ID (Long)
- Data e Hora (LocalDateTime)
- Status (AGENDADA, REALIZADA, CANCELADA)
- Paciente (Relacionamento)
- Profissional (Relacionamento)
- Observações (String)
- Ativo (Boolean)

### 4. **Prontuário**
- ID (Long)
- Descrição (String)
- Data Registro (LocalDateTime)
- Diagnóstico (String)
- Tratamento (String)
- Paciente (Relacionamento)
- Profissional (Relacionamento)
- Ativo (Boolean)

### 5. **Usuário**
- ID (Long)
- Nome (String)
- Email (String - único)
- Senha (BCrypt)
- Perfil (ADMIN, PROFISSIONAL, PACIENTE)
- Ativo (Boolean)

## Endpoints da API

### Autenticação
- `POST /api/auth/login` - Realizar login (retorna JWT)
- `POST /api/auth/registrar` - Registrar novo usuário

### Pacientes
- `POST /api/pacientes` - Criar novo paciente
- `GET /api/pacientes` - Listar todos os pacientes
- `GET /api/pacientes/{id}` - Buscar paciente por ID
- `PUT /api/pacientes/{id}` - Atualizar paciente
- `DELETE /api/pacientes/{id}` - Deletar paciente

### Profissionais de Saúde
- `POST /api/profissionais` - Criar novo profissional
- `GET /api/profissionais` - Listar todos os profissionais
- `GET /api/profissionais/{id}` - Buscar profissional por ID
- `GET /api/profissionais/especialidade/{especialidade}` - Buscar por especialidade
- `PUT /api/profissionais/{id}` - Atualizar profissional
- `DELETE /api/profissionais/{id}` - Deletar profissional

### Consultas
- `POST /api/consultas` - Agendar nova consulta
- `GET /api/consultas` - Listar todas as consultas
- `GET /api/consultas/{id}` - Buscar consulta por ID
- `GET /api/consultas/paciente/{pacienteId}` - Listar consultas de um paciente
- `GET /api/consultas/profissional/{profissionalId}` - Listar consultas de um profissional
- `PUT /api/consultas/{id}` - Atualizar consulta
- `PUT /api/consultas/{id}/cancelar` - Cancelar consulta
- `DELETE /api/consultas/{id}` - Deletar consulta

### Prontuários
- `POST /api/prontuarios` - Criar novo prontuário
- `GET /api/prontuarios` - Listar todos os prontuários
- `GET /api/prontuarios/{id}` - Buscar prontuário por ID
- `GET /api/prontuarios/paciente/{pacienteId}` - Listar prontuários de um paciente
- `PUT /api/prontuarios/{id}` - Atualizar prontuário
- `DELETE /api/prontuarios/{id}` - Deletar prontuário

## Execução Local

### Pré-requisitos
- Java 21 instalado
- Maven 3.8+

### Passos
1. Clone o repositório:
   ```bash
   git clone <seu-repositorio-git>
   ```

2. Entre no diretório do projeto:
   ```bash
   cd sghss
   ```

3. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse a API:
   - **URL Base**: http://localhost:8080/api
   - **Swagger**: http://localhost:8080/api/swagger-ui.html
   - **H2 Console**: http://localhost:8080/api/h2-console

### Credenciais H2
- Username: `sa`
- Password: (vazio)
- JDBC URL: `jdbc:h2:mem:testdb`

## Configuração de Segurança

### JWT Configuration
O token JWT é gerado no login e deve ser incluído no header Authorization:
```
Authorization: Bearer <token>
```

**Configuração no application.yml:**
```yaml
jwt:
  secret: mySecretKeyForJWTTokenGenerationAndValidation12345
  expiration: 86400000 # 24 horas em ms
```

## Validações

### Paciente
- Nome: obrigatório
- CPF: obrigatório, 11 dígitos, único
- Email: opcional, formato válido
- Telefone: opcional, 10-11 dígitos

### Profissional de Saúde
- Nome: obrigatório
- Especialidade: obrigatória
- CRM: obrigatório, único
- Email: opcional, formato válido

### Consulta
- Data/Hora: obrigatória
- Paciente: obrigatório
- Profissional: obrigatório

### Prontuário
- Descrição: obrigatória
- Data Registro: obrigatória
- Paciente: obrigatório
- Profissional: obrigatório

## Tratamento de Erros

Todos os erros retornam um JSON com o seguinte formato:
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Tipo do Erro",
  "message": "Descrição detalhada do erro"
}
```

### Códigos de Status HTTP
- `200 OK` - Sucesso
- `201 Created` - Recurso criado
- `204 No Content` - Deletado com sucesso
- `400 Bad Request` - Validação falhou
- `404 Not Found` - Recurso não encontrado
- `500 Internal Server Error` - Erro no servidor

## Testes

### Executar testes unitários:
```bash
mvn test
```

### Cobertura de testes:
```bash
mvn jacoco:report
```

## Deploymente

Para ambiente de produção, altere as configurações no `application.yml`:
- Banco de dados para PostgreSQL
- Desabilite o console H2
- Configure JWT secret em variáveis de ambiente
- Configure CORS conforme necessário

## Contribuição

1. Crie um branch para sua feature (`git checkout -b feature/AmazingFeature`)
2. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
3. Push para o branch (`git push origin feature/AmazingFeature`)
4. Abra um Pull Request

## Licença

Este projeto está licenciado sob a Apache License 2.0.

## Autor
Desenvolvido por: Poliane  
Email: poliane@vidaplus.com.br  
Empresa: Vida Plus

