SGHSS - Sistema de Gestão Hospitalar e de Serviços de Saúde
Java Spring Boot Maven License

📋 Sobre o Projeto
O SGHSS é uma API REST completa para gestão de sistemas hospitalares e serviços de saúde, desenvolvida com Spring Boot 3.3.0 e Java 21. O sistema permite o gerenciamento completo de pacientes, profissionais de saúde, consultas médicas e prontuários médicos, com autenticação JWT e documentação interativa via Swagger.

🎯 Principais Funcionalidades

👥 Gestão de Pacientes: CRUD completo com validação de CPF único

👨‍⚕️ Gestão de Profissionais: Cadastro de médicos e profissionais por especialidade

📅 Agendamento de Consultas: Sistema completo de marcação e cancelamento

📋 Prontuários Médicos: Registro de diagnósticos, tratamentos e evoluções

🔐 Autenticação JWT: Sistema seguro com perfis de usuário (ADMIN, PROFISSIONAL, PACIENTE)

📚 Documentação Interativa: Swagger UI para exploração da API


🛠️ Tecnologias Utilizadas

Backend

Java 21 - Linguagem de programação

Spring Boot 3.3.0 - Framework principal

Spring Data JPA - Persistência de dados

Spring Security - Autenticação e autorização

JWT (JJWT) - Tokens de autenticação

H2 Database - Banco de dados para desenvolvimento

PostgreSQL - Banco de dados para produção

Ferramentas e Bibliotecas

Maven - Gerenciamento de dependências

Lombok - Redução de código boilerplate

SpringDoc OpenAPI - Documentação da API

Jakarta Validation - Validação de dados

JUnit 5 - Testes unitários


📋 Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:

Java 21 ou superior

Maven 3.8+

Git (opcional, para clonar o repositório)

IDE (IntelliJ IDEA, Eclipse ou VS Code recomendado)



🚀 Instalação e Execução

1. Clone o Repositório
git clone https://github.com/pollygodoy/sghss.git
cd sghss
2. Compile o Projeto
mvn clean install
3. Execute a Aplicação
mvn spring-boot:run
4. Acesse a Aplicação
API Base: http://localhost:8080/api
Swagger UI: http://localhost:8080/api/swagger-ui.html
H2 Console: http://localhost:8080/api/h2-console (usuário: sa, senha: vazia)


📖 Guia Rápido de Uso
Autenticação
Faça login com um usuário existente:

curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"joao@vidaplus.com","senha":"senha123"}'
Copie o token JWT retornado e use nas próximas requisições:

Authorization: Bearer SEU_TOKEN_AQUI
Usuários de Teste Pré-Cadastrados
Email	Senha	Perfil
joao@vidaplus.com	senha123	PACIENTE
carlos@vidaplus.com	senha123	PROFISSIONAL
poliane@vidaplus.com	senha123	ADMIN

Exemplos de Uso

Criar um Novo Paciente
curl -X POST http://localhost:8080/api/pacientes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN" \
  -d '{
    "nome": "João Silva",
    "cpf": "12345678901",
    "email": "joao@email.com",
    "telefone": "11999999999",
    "dataNascimento": "1990-01-15"
  }'
  
Agendar uma Consulta
curl -X POST http://localhost:8080/api/consultas \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN" \
  -d '{
    "dataHora": "2026-12-25T14:30:00",
    "pacienteId": 1,
    "profissionalId": 1,
    "observacoes": "Consulta de rotina"
  }'


  
📁 Estrutura do Projeto

sghss/
├── src/main/java/com/vidaplus/sghss/
│   ├── config/           # Configurações (Security, Swagger, JWT)
│   ├── controller/       # Controllers REST
│   ├── dto/             # Data Transfer Objects
│   ├── exception/       # Tratamento de exceções
│   ├── model/           # Entidades JPA
│   ├── repository/      # Repositórios de dados
│   └── service/         # Lógica de negócio
├── src/main/resources/
│   ├── application.properties  # Configurações da aplicação
│   └── data.sql               # Dados iniciais
└── src/test/                  # Testes unitários


🔗 API Endpoints Principais
Autenticação

POST /api/auth/login - Login de usuário
POST /api/auth/registrar - Registro de novo usuário


Pacientes

GET /api/pacientes - Listar todos os pacientes
GET /api/pacientes/{id} - Buscar paciente por ID
POST /api/pacientes - Criar novo paciente
PUT /api/pacientes/{id} - Atualizar paciente
DELETE /api/pacientes/{id} - Deletar paciente


Profissionais

GET /api/profissionais - Listar profissionais
GET /api/profissionais/{id} - Buscar profissional por ID
GET /api/profissionais/especialidade/{especialidade} - Filtrar por especialidade
POST /api/profissionais - Criar profissional
PUT /api/profissionais/{id} - Atualizar profissional
DELETE /api/profissionais/{id} - Deletar profissional


Consultas

GET /api/consultas - Listar todas as consultas
GET /api/consultas/{id} - Buscar consulta por ID
GET /api/consultas/paciente/{pacienteId} - Consultas de um paciente
POST /api/consultas - Agendar consulta
PUT /api/consultas/{id} - Atualizar consulta
PUT /api/consultas/{id}/cancelar - Cancelar consulta


Prontuários

GET /api/prontuarios - Listar prontuários
GET /api/prontuarios/{id} - Buscar prontuário por ID
GET /api/prontuarios/paciente/{pacienteId} - Prontuários de um paciente
POST /api/prontuarios - Criar prontuário
PUT /api/prontuarios/{id} - Atualizar prontuário
DELETE /api/prontuarios/{id} - Deletar prontuário


📚 Documentação e Referências

Documentação Interativa
Swagger UI: http://localhost:8080/api/swagger-ui.html
OpenAPI Specification: http://localhost:8080/api/v3/api-docs
Figura 1 — Documentação Swagger/OpenAPI do SGHSS.
Fonte: Elaborado pela autora (2026).


Documentação do Projeto

GUIA_DE_USO.md - Guia completo de uso da API
ARQUITETURA.md - Arquitetura do sistema
REQUISITOS.md - Requisitos funcionais e não funcionais
QUICKSTART.md - Início rápido em 5 minutos


🧪 Testes

Executar Testes Unitários
mvn test


Executar Testes com Cobertura
mvn test jacoco:report


📊 Status dos Requisitos
✅ Implementado (100%)
CRUD completo para todas as entidades
Autenticação JWT com perfis de usuário
Validação de dados e tratamento de erros
Documentação Swagger completa
Testes unitários básicos
Soft delete para segurança de dados


🔄 Em Desenvolvimento
Testes de integração
Paginação em listas grandes
Filtros avançados
Cache para performance


📋 Planejado
Deploy em produção
CI/CD com GitHub Actions
Aplicativo mobile
Notificações automáticas


🤝 Como Contribuir
Fork o projeto
Crie uma branch para sua feature (git checkout -b feature/AmazingFeature)
Commit suas mudanças (git commit -m 'Add some AmazingFeature')
Push para a branch (git push origin feature/AmazingFeature)
Abra um Pull Request
Padrões de Código
Siga os princípios SOLID e Clean Code
Use JavaDoc para documentar classes e métodos
Mantenha cobertura de testes acima de 80%
Siga convenções de nomenclatura do Java


📝 Licença
Este projeto está sob a licença Apache License 2.0 - veja o arquivo LICENSE para detalhes.


📞 Contato
Poliane Fernandes de Godoy - poliane@vidaplus.com

Projeto Link: https://github.com/pollygodoy/sghss


🙏 Agradecimentos
Spring Boot pela excelente documentação
SpringDoc pelo Swagger UI
JJWT pela implementação JWT
H2 Database pelo banco de desenvolvimento

⭐ Dê uma estrela se este projeto te ajudou!

Última atualização: 11 de maio de 2026
