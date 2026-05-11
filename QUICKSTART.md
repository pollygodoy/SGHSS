# Quick Start - SGHSS em 5 Minutos

## Passo 1: Clonar e Preparar (1 minuto)

```bash
# Clone o repositório
git clone https://github.com/pollygodoy/sghss.git
cd sghss

# Compile o projeto
mvn clean install
```

## Passo 2: Iniciar a Aplicação (30 segundos)

```bash
# Inicie o servidor
mvn spring-boot:run

# A aplicação estará disponível em:
# http://localhost:8080/api
```

## Passo 3: Acessar a Documentação (30 segundos)

Abra no navegador:
- **Swagger UI**: http://localhost:8080/api/swagger-ui.html
- **H2 Console**: http://localhost:8080/api/h2-console (usuário: sa, sem senha)

## Passo 4: Fazer Seu Primeiro Login (1 minuto)

**Opção 1: Usando Swagger**
1. Clique em "Autenticação"
2. Clique em "Try it out" no endpoint POST /auth/login
3. Preencha com:
   ```json
   {
     "email": "joao@vidaplus.com",
     "senha": "senha123"
   }
   ```
4. Clique em "Execute"
5. Copie o token retornado

**Opção 2: Usando cURL**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"joao@vidaplus.com","senha":"senha123"}'
```

## Passo 5: Testar a API (1 minuto)

**Listar Pacientes:**
```bash
curl -X GET http://localhost:8080/api/pacientes \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

**Criar Novo Paciente:**
```bash
curl -X POST http://localhost:8080/api/pacientes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -d '{
    "nome": "Novo Paciente",
    "cpf": "11122233344",
    "email": "novo@email.com",
    "telefone": "11999999999",
    "dataNascimento": "1995-06-20"
  }'
```

## Dados de Teste Pré-Carregados

### Usuários
| Email | Senha | Perfil |
|-------|-------|--------|
| joao@vidaplus.com | senha123 | PACIENTE |
| carlos@vidaplus.com | senha123 | PROFISSIONAL |
| poliane@vidaplus.com | senha123 | ADMIN |

### Pacientes (Já no Sistema)
- João Silva (ID: 1)
- Maria Santos (ID: 2)
- Carlos Oliveira (ID: 3)
- Ana Costa (ID: 4)

### Profissionais (Já no Sistema)
- Dr. Carlos Alberto - Cardiologia (ID: 1)
- Dra. Fernanda Gomes - Dermatologia (ID: 2)
- Dr. Roberto Silva - Clínica Geral (ID: 3)
- Dra. Patricia Martins - Pediatria (ID: 4)
- Dr. Lucas Mendes - Ortopedia (ID: 5)

## Endpoints Principais Para Começar

### 1. Autenticação
```
POST /api/auth/login
POST /api/auth/registrar
```

### 2. Pacientes
```
GET /api/pacientes              # Listar todos
GET /api/pacientes/1            # Buscar um específico
POST /api/pacientes             # Criar novo
PUT /api/pacientes/1            # Atualizar
DELETE /api/pacientes/1         # Deletar
```

### 3. Consultas
```
GET /api/consultas              # Listar todas
POST /api/consultas             # Agendar nova
GET /api/consultas/paciente/1   # Consultas de um paciente
```

### 4. Profissionais
```
GET /api/profissionais          # Listar todos
GET /api/profissionais/1        # Buscar um
```

### 5. Prontuários
```
GET /api/prontuarios            # Listar todos
POST /api/prontuarios           # Criar novo
GET /api/prontuarios/paciente/1 # Prontuários de um paciente
```

## Troubleshooting Rápido

### "Connection refused"
A porta 8080 está em uso. Altere em `src/main/resources/application.yml`:
```yaml
server:
  port: 8081  # Mude para uma porta livre
```

### "Email já existe"
Use email diferente ou faça login como usuário, não registre novo.

### "Pacient não encontrado"
Use IDs que existem. Comece com ID 1, 2, 3 ou 4 que já vêm pré-carregados.

### Erro 401
Você esqueceu o token! Sempre inclua:
```
Authorization: Bearer SEU_TOKEN
```

## Próximos Passos

1. **Explore a Documentação**
   - Leia [README.md](README.md) para visão geral
   - Leia [GUIA_DE_USO.md](GUIA_DE_USO.md) para usar a API
   - Leia [ARQUITETURA.md](ARQUITETURA.md) para entender como funciona

2. **Teste a API**
   - Use Postman: importe `SGHSS_API_Postman_Collection.json`
   - Teste todos os endpoints
   - Crie seus próprios dados

3. **Customize**
   - Altere dados de teste em `src/main/resources/data.sql`
   - Configure banco de dados em `application.yml`
   - Ajuste configurações conforme necessário

4. **Deploy**
   - Siga [CHECKLIST_DEPLOY.md](CHECKLIST_DEPLOY.md)
   - Prepare para ambiente de produção

## Perguntas Frequentes

**P: Como faço para desabilitar os dados de teste?**
R: Comente as linhas em `data.sql` ou delete o arquivo.

**P: Como mudo a porta?**
R: Em `application.yml`, altere `server.port: 8081`

**P: Como conecto em PostgreSQL?**
R: Altere `application.yml` com detalhes de conexão PostgreSQL.

**P: Como gero um novo token JWT?**
R: Faça login novamente via POST /auth/login

**P: Posso chamar a API de um aplicativo web?**
R: Sim! CORS está habilitado.

## Estrutura de Resposta da API

**Sucesso (2xx):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "cpf": "12345678901",
  ...
}
```

**Erro (4xx/5xx):**
```json
{
  "timestamp": "2026-03-29T14:30:00",
  "status": 400,
  "error": "Erro de Validação",
  "message": "Descrição do erro"
}
```

## Contatos

- **Swagger**: http://localhost:8080/api/swagger-ui.html (melhor lugar para explorar)
- **GitHub**: https://github.com/seu-usuario/sghss
- **Suporte**: poliane@vidaplus.com
- **Documentação**: Veja pasta raiz do projeto

---

**Pronto para começar? Siga os 5 passos acima e em 5 minutos você terá a API rodando! 🚀**

Mais dúvidas? Consulte:
- [GUIA_DE_USO.md](GUIA_DE_USO.md) - Guia completo de uso
- [README.md](README.md) - Visão geral do projeto
- [ARQUITETURA.md](ARQUITETURA.md) - Entender a arquitetura

