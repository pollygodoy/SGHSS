# Guia de Uso da API SGHSS

## 1. Começando

### 1.1 Pré-requisitos
- Java 21 instalado
- Maven ou IDE com suporte Maven
- Postman (opcional, para testes visuais)

### 1.2 Instalação Rápida

```bash
# 1. Clone o repositório
git clone https://github.com/pollygodoy/sghss.git
cd sghss

# 2. Compile
mvn clean install

# 3. Execute
mvn spring-boot:run

# 4. Acesse a documentação
# Swagger: http://localhost:8080/api/swagger-ui.html
# H2 Console: http://localhost:8080/api/h2-console (sa/senha vazia)
```

## 2. Autenticação e Autorização

### 2.1 Fluxo de Login

**Endpoint**: `POST /api/auth/login`

**Request:**
```json
{
  "email": "joao@vidaplus.com",
  "senha": "senha123"
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "nome": "João Paciente",
  "email": "joao@vidaplus.com",
  "perfil": "PACIENTE",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2FvQHZpZGFwbHVzLmNvbSIsInVzZXJJZCI6MSwiYXV0aCI6IlBBQ0lFTlRFIiwiaWF0IjoxNjczNzY2NDAwLCJleHAiOjE2NzM4NTI4MDB9.signature"
}
```

### 2.2 Usando o Token

Adicione o token no header de todas as requisições:

```
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9...
```

### 2.3 Registrar Novo Usuário

**Endpoint**: `POST /api/auth/registrar?senha=senha123`

**Request:**
```json
{
  "nome": "Maria Silva",
  "email": "maria@vidaplus.com",
  "perfil": "PACIENTE"
}
```

**Response (201 CREATED):**
```json
{
  "id": 4,
  "nome": "Maria Silva",
  "email": "maria@vidaplus.com",
  "perfil": "PACIENTE",
  "ativo": true
}
```

## 3. Módulo de Pacientes

### 3.1 Criar Paciente

**Endpoint**: `POST /api/pacientes`

**Request:**
```json
{
  "nome": "João Silva",
  "cpf": "12345678901",
  "email": "joao@email.com",
  "telefone": "11999999999",
  "dataNascimento": "1990-01-15"
}
```

**Response (201 CREATED):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "cpf": "12345678901",
  "email": "joao@email.com",
  "telefone": "11999999999",
  "dataNascimento": "1990-01-15",
  "ativo": true
}
```

**Erros Possíveis:**
- `400 Bad Request`: CPF dupli cado ou email já em uso
- `422 Unprocessable Entity`: Validação falhou (formato inválido)

### 3.2 Listar Pacientes

**Endpoint**: `GET /api/pacientes`

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "cpf": "12345678901",
    "email": "joao@email.com",
    "telefone": "11999999999",
    "dataNascimento": "1990-01-15",
    "ativo": true
  },
  {
    "id": 2,
    "nome": "Maria Santos",
    "cpf": "98765432101",
    "email": "maria@email.com",
    "telefone": "11988888888",
    "dataNascimento": "1992-05-20",
    "ativo": true
  }
]
```

### 3.3 Buscar Paciente por ID

**Endpoint**: `GET /api/pacientes/{id}`

**Response (200 OK):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "cpf": "12345678901",
  "email": "joao@email.com",
  "telefone": "11999999999",
  "dataNascimento": "1990-01-15",
  "ativo": true
}
```

**Erros Possíveis:**
- `404 Not Found`: Paciente não existe

### 3.4 Atualizar Paciente

**Endpoint**: `PUT /api/pacientes/{id}`

**Request:**
```json
{
  "nome": "João Silva Atualizado",
  "cpf": "12345678901",
  "email": "joao.novo@email.com",
  "telefone": "11999999999",
  "dataNascimento": "1990-01-15"
}
```

**Response (200 OK):** Mesmo objeto com dados atualizados

### 3.5 Deletar Paciente

**Endpoint**: `DELETE /api/pacientes/{id}`

**Response (204 No Content):** Sem corpo de resposta

## 4. Módulo de Profissionais

### 4.1 Criar Profissional

**Endpoint**: `POST /api/profissionais`

**Request:**
```json
{
  "nome": "Dr. Carlos Alberto",
  "especialidade": "Cardiologia",
  "crm": "123456789",
  "email": "carlos@vidaplus.com"
}
```

**Response (201 CREATED):**
```json
{
  "id": 1,
  "nome": "Dr. Carlos Alberto",
  "especialidade": "Cardiologia",
  "crm": "123456789",
  "email": "carlos@vidaplus.com",
  "ativo": true
}
```

### 4.2 Listar Profissionais

**Endpoint**: `GET /api/profissionais`

**Response**: Array de profissionais

### 4.3 Buscar por Especialidade

**Endpoint**: `GET /api/profissionais/especialidade/Cardiologia`

**Response**: Array de profissionais naquela especialidade

## 5. Módulo de Consultas

### 5.1 Agendar Consulta

**Endpoint**: `POST /api/consultas`

**Request:**
```json
{
  "dataHora": "2026-03-29T14:00:00",
  "pacienteId": 1,
  "profissionalId": 1,
  "observacoes": "Consulta de rotina"
}
```

**Response (201 CREATED):**
```json
{
  "id": 1,
  "dataHora": "2026-03-29T14:00:00",
  "status": "AGENDADA",
  "pacienteId": 1,
  "profissionalId": 1,
  "observacoes": "Consulta de rotina",
  "ativo": true
}
```

**Erros Possíveis:**
- `400 Bad Request`: Paciente ou profissional não existem
- `422 Unprocessable Entity`: Data ou outro campo inválido

### 5.2 Listar Consultas de um Paciente

**Endpoint**: `GET /api/consultas/paciente/1`

**Response**: Array de consultas do paciente 1

### 5.3 Cancelar Consulta

**Endpoint**: `PUT /api/consultas/{id}/cancelar`

**Response (200 OK):**
```json
{
  "id": 1,
  "dataHora": "2026-03-29T14:00:00",
  "status": "CANCELADA",
  "pacienteId": 1,
  "profissionalId": 1,
  "observacoes": "Consulta de rotina",
  "ativo": true
}
```

## 6. Módulo de Prontuários

### 6.1 Criar Prontuário

**Endpoint**: `POST /api/prontuarios`

**Request:**
```json
{
  "descricao": "Paciente apresenta pressão alta. Ausculta cardíaca normal.",
  "diagnostico": "Hipertensão Arterial Estágio 1",
  "tratamento": "Prescrever Losartana 50mg. Reduzir sódio. Atividade física.",
  "pacienteId": 1,
  "profissionalId": 1
}
```

**Response (201 CREATED):**
```json
{
  "id": 1,
  "descricao": "Paciente apresenta pressão alta...",
  "dataRegistro": "2026-03-29T14:30:00",
  "diagnostico": "Hipertensão Arterial Estágio 1",
  "tratamento": "Prescrever Losartana 50mg...",
  "pacienteId": 1,
  "profissionalId": 1,
  "ativo": true
}
```

### 6.2 Listar Prontuários de um Paciente

**Endpoint**: `GET /api/prontuarios/paciente/1`

**Response**: Array de prontuários do paciente

### 6.3 Buscar Prontuário por ID

**Endpoint**: `GET /api/prontuarios/{id}`

**Response**: Objeto prontuário

## 7. Padrão de Respostas de Erro

### 7.1 Erro de Validação (422)

```json
{
  "timestamp": "2026-03-29T10:30:00",
  "status": 422,
  "error": "Validação Falhou",
  "errors": {
    "nome": "Nome é obrigatório",
    "cpf": "CPF deve ter 11 dígitos",
    "email": "Email inválido"
  }
}
```

### 7.2 Erro de Negócio (400)

```json
{
  "timestamp": "2026-03-29T10:30:00",
  "status": 400,
  "error": "Erro de Validação",
  "message": "Paciente com este CPF já existe"
}
```

### 7.3 Recurso não Encontrado (404)

```json
{
  "timestamp": "2026-03-29T10:30:00",
  "status": 404,
  "error": "Recurso Não Encontrado",
  "message": "Paciente não encontrado"
}
```

### 7.4 Erro Interno (500)

```json
{
  "timestamp": "2026-03-29T10:30:00",
  "status": 500,
  "error": "Erro Interno do Servidor",
  "message": "erro específico..."
}
```

## 8. Cenários de Teste Completos

### Cenário 1: Fluxo Completo de Paciente

```bash
# 1. Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"joao@vidaplus.com","senha":"senha123"}'

# Guardar o token retornado

# 2. Criar paciente
curl -X POST http://localhost:8080/api/pacientes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer TOKEN_AQUI" \
  -d '{
    "nome": "Novo Paciente",
    "cpf": "11122233344",
    "email": "novo@email.com",
    "telefone": "11999999999",
    "dataNascimento": "1995-06-20"
  }'

# 3. Buscar paciente
curl -X GET http://localhost:8080/api/pacientes/1 \
  -H "Authorization: Bearer TOKEN_AQUI"

# 4. Atualizar paciente
curl -X PUT http://localhost:8080/api/pacientes/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer TOKEN_AQUI" \
  -d '{"nome": "Novo Nome", ...}'

# 5. Listar pacientes
curl -X GET http://localhost:8080/api/pacientes \
  -H "Authorization: Bearer TOKEN_AQUI"
```

### Cenário 2: Agendar e Registrar Consulta

```bash
# 1. Criar paciente (se necessário)
# 2. Criar profissional (se necessário)

# 3. Agendar consulta
curl -X POST http://localhost:8080/api/consultas \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer TOKEN_AQUI" \
  -d '{
    "dataHora": "2026-03-29T14:30:00",
    "pacienteId": 1,
    "profissionalId": 1,
    "observacoes": "Revisão cardiológica"
  }'

# 4. Criar prontuário após consulta
curl -X POST http://localhost:8080/api/prontuarios \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer TOKEN_AQUI" \
  -d '{
    "descricao": "Paciente avaliado, sem queixa.",
    "diagnostico": "Sem alterações",
    "tratamento": "Manter acompanhamento anual",
    "pacienteId": 1,
    "profissionalId": 1
  }'
```

## 9. Importar Collection no Postman

1. Abra Postman
2. Clique em "Import"
3. Cole o conteúdo de `SGHSS_API_Postman_Collection.json` ou faça upload do arquivo
4. As requisições ficarão organizadas por módulo
5. Configure as variáveis de ambiente conforme necessário

## 10. Troubleshooting

### Problema: "Connection refused" na porta 8080
**Solução**: Verifique se a porta 8080 está livre ou altere em `application.yml`

### Problema: "Email já existe"
**Solução**: O email já foi registrado. Use outro email ou selecione um usuário existente

### Problema: "CPF deve ter 11 dígitos"
**Solução**: Envie um CPF válido com exatamente 11 dígitos numéricos

### Problema: "Paciente não encontrado"
**Solução**: Verifique o ID. Use GET /api/pacientes para listar os IDs disponíveis

### Problema: Token expirado
**Solução**: Faça login novamente e obtenha um novo token

---

**Última Atualização**: 28/03/2026
**Versão**: 2.0

