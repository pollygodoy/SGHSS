# Índice de Documentação - SGHSS

Bem-vindo ao SGHSS! Este arquivo é um guia para navegar por toda a documentação do projeto.

## 📖 Documentação por Tipo

### 🚀 Para Quem Quer Começar Rápido

1. **[QUICKSTART.md](QUICKSTART.md)** - Primeiros 5 minutos
   - Como compilar e executar
   - Login teste
   - Primeiros endpoints
   - Para quem tem pressa!

2. **[README.md](README.md)** - Visão geral
   - O que é SGHSS
   - Tecnologias utilizadas
   - Como executar
   - Links principais

### 📚 Para Quem Quer Entender

3. **[ARQUITETURA.md](ARQUITETURA.md)** - Como funciona internamente
   - Arquitetura em camadas
   - Fluxo de requisições
   - Padrões de design
   - Segurança
   - Desempenho

4. **[DER.md](DER.md)** - Banco de dados
   - Diagrama Entidade-Relacionamento
   - Descrição de cada tabela
   - Relacionamentos
   - Índices
   - Scripts SQL

5. **[REQUISITOS.md](REQUISITOS.md)** - O que foi pedido vs o que foi feito
   - Requisitos funcionais (30 especificados)
   - Requisitos não-funcionais
   - Casos de uso críticos
   - Roadmap futuro

### 🔧 Para Quem Quer Usar

6. **[GUIA_DE_USO.md](GUIA_DE_USO.md)** - Manual completo da API
   - Autenticação
   - Como chamar cada endpoint
   - Exemplos com cURL
   - Padrão de respostas
   - Troubleshooting comum
   - Cenários de teste

7. **[SGHSS_API_Postman_Collection.json](SGHSS_API_Postman_Collection.json)** - Para testar na prática
   - Importar no Postman
   - 20+ requisições pré-configuradas
   - Organized por módulo
   - Testes de sucesso e erro

### 🧪 Para Quem Quer Testar

8. **[PLANO_DE_TESTES.md](PLANO_DE_TESTES.md)** - Testes completos
   - Testes unitários
   - Testes de integração
   - Testes de validação
   - Testes de segurança
   - Testes de performance
   - Checklist de cobertura

### 🚢 Para Quem Quer Colocar em Produção

9. **[CHECKLIST_DEPLOY.md](CHECKLIST_DEPLOY.md)** - Antes de fazer deploy
   - Verificações pré-deploy
   - Segurança
   - Banco de dados
   - Documentação
   - Performance
   - Testes finais
   - Pós-deploy

### 📋 Documentação Geral

10. **[SUMARIO.md](SUMARIO.md)** - Resumo executivo
    - Status do projeto
    - Arquivos criados
    - Funcionalidades
    - Tecnologias
    - Métricas
    - Próximos passos

11. **[CHANGELOG.md](CHANGELOG.md)** - Histórico de mudanças
    - O que foi implementado na v1.0
    - Em progresso
    - Pendente para futuro
    - Estatísticas do projeto

12. **[INDEX.md](INDEX.md)** - Este arquivo!
    - Mapa de documentação
    - Guia de navegação

## 📁 Estrutura de Arquivos do Projeto

```
SGHSS/
├── 📄 Documentação
│   ├── README.md
│   ├── QUICKSTART.md
│   ├── GUIA_DE_USO.md
│   ├── ARQUITETURA.md
│   ├── DER.md
│   ├── REQUISITOS.md
│   ├── PLANO_DE_TESTES.md
│   ├── CHECKLIST_DEPLOY.md
│   ├── SUMARIO.md
│   ├── CHANGELOG.md
│   └── INDEX.md (este arquivo)
│
├── 📊 Testes
│   └── SGHSS_API_Postman_Collection.json
│
├── 🔧 Código
│   ├── pom.xml
│   ├── .gitignore
│   └── src/
│       ├── main/
│       │   ├── java/com/vidaplus/sghss/
│       │   │   ├── controller/
│       │   │   ├── service/
│       │   │   ├── repository/
│       │   │   ├── model/
│       │   │   ├── dto/
│       │   │   ├── config/
│       │   │   ├── exception/
│       │   │   └── SghssApplication.java
│       │   └── resources/
│       │       ├── application.yml
│       │       └── data.sql
│       └── test/
│           └── java/com/vidaplus/sghss/
│               ├── controller/
│               └── service/
```

## 🎯 Escolha Seu Caminho

### Caminho 1: "Quero usar agora" ⚡ (5 min)
```
QUICKSTART.md → GUIA_DE_USO.md → Postman Collection
```

### Caminho 2: "Quero entender" 🧠 (30 min)
```
README.md → ARQUITETURA.md → DER.md → Código
```

### Caminho 3: "Quero testar tudo" 🧪 (1-2 h)
```
PLANO_DE_TESTES.md → Postman Collection → GUIA_DE_USO.md
```

### Caminho 4: "Vou colocar em produção" 🚀 (2-4 h)
```
ARQUITETURA.md → REQUISITOS.md → CHECKLIST_DEPLOY.md → Deploy
```

### Caminho 5: "Sou desenvolvedor" 👨‍💻 (4-8 h)
```
README.md → ARQUITETURA.md → Código → PLANO_DE_TESTES.md
```

## 🔑 Conceitos-Chave

### Para Iniciantes
- Leia primeiro: **QUICKSTART.md**
- Depois: **GUIA_DE_USO.md**
- Experimental: **SGHSS_API_Postman_Collection.json**

### Para Desenvolvedores
- Leia: **ARQUITETURA.md**
- Estude: **DER.md**
- Explore: Código em `src/main/java`
- Teste: **PLANO_DE_TESTES.md**

### Para DevOps/SRE
- Leia: **CHECKLIST_DEPLOY.md**
- Revise: **application.yml** em `src/main/resources`
- Prepare: Infraestrutura PostgreSQL
- Configure: Variáveis de ambiente

### Para Product Managers
- Leia: **SUMARIO.md**
- Revise: **REQUISITOS.md**
- Consulte: **CHANGELOG.md**

## 🌐 Acessos Rápidos

Quando a aplicação está rodando:

| Recurso | URL |
|---------|-----|
| API Base | http://localhost:8080/api |
| **Swagger UI** | http://localhost:8080/api/swagger-ui.html |
| **H2 Console** | http://localhost:8080/api/h2-console |
| OpenAPI JSON | http://localhost:8080/api/v3/api-docs |

## 📞 Contatos

| Tipo | Contato |
|------|---------|
| **Desenvolvedor** | poliane@vidaplus.com |
| **Suporte** | suporte@vidaplus.com |
| **Website** | https://vidaplus.com.br |
| **GitHub** | https://github.com/pollygodoy/sghss |

## ✅ Checklist de Leitura

### Mínimo (30 min)
- [ ] QUICKSTART.md
- [ ] README.md

### Recomendado (2 horas)
- [ ] QUICKSTART.md
- [ ] README.md
- [ ] GUIA_DE_USO.md
- [ ] ARQUITETURA.md

### Completo (4 horas)
- [ ] Todos os arquivos acima +
- [ ] DER.md
- [ ] REQUISITOS.md
- [ ] PLANO_DE_TESTES.md
- [ ] CHECKLIST_DEPLOY.md

## 🔍 Buscando Algo Específico?

### "Como faço para..."

**...começar rápido?**
→ [QUICKSTART.md](QUICKSTART.md)

**...usar a API?**
→ [GUIA_DE_USO.md](GUIA_DE_USO.md)

**...entender como funciona?**
→ [ARQUITETURA.md](ARQUITETURA.md)

**...testar?**
→ [PLANO_DE_TESTES.md](PLANO_DE_TESTES.md)

**...fazer deploy?**
→ [CHECKLIST_DEPLOY.md](CHECKLIST_DEPLOY.md)

**...ver o esquema do banco?**
→ [DER.md](DER.md)

**...saber o que está no projeto?**
→ [SUMARIO.md](SUMARIO.md) ou [CHANGELOG.md](CHANGELOG.md)

## 📊 Documentação por Tipo

### Manuais de Usuário 📖
- QUICKSTART.md
- GUIA_DE_USO.md
- README.md

### Documentação Técnica 🔧
- ARQUITETURA.md
- DER.md
- REQUISITOS.md

### Documentação de Teste 🧪
- PLANO_DE_TESTES.md
- SGHSS_API_Postman_Collection.json

### Documentação de Operação 🚀
- CHECKLIST_DEPLOY.md

### Documentação Administrativa 📋
- SUMARIO.md
- CHANGELOG.md
- INDEX.md

## 🎓 Glossário de Termos

| Termo | Significado | Ref. |
|-------|-----------|------|
| API | Interface de Programação de Aplicação | [README.md](README.md) |
| JWT | JSON Web Token para autenticação | [GUIA_DE_USO.md](GUIA_DE_USO.md) |
| DTO | Data Transfer Object | [ARQUITETURA.md](ARQUITETURA.md) |
| DER | Diagrama Entidade-Relacionamento | [DER.md](DER.md) |
| Soft Delete | Marcar como inativo em vez de deletar | [ARQUITETURA.md](ARQUITETURA.md) |
| CRUD | Create, Read, Update, Delete | [GUIA_DE_USO.md](GUIA_DE_USO.md) |
| Endpoint | URL da API | [GUIA_DE_USO.md](GUIA_DE_USO.md) |
| Spring Boot | Framework Java | [README.md](README.md) |

## 🆘 Precisa de Ajuda?

1. **Não acha informação?**
   - Use Ctrl+F para buscar no arquivo
   - Verifique este INDEX.md

2. **Está com erro?**
   - Veja GUIA_DE_USO.md seção "Troubleshooting"
   - Verifique PLANO_DE_TESTES.md

3. **Quer fazer deploy?**
   - Siga CHECKLIST_DEPLOY.md passo a passo

4. **Quer aprender mais?**
   - Leia ARQUITETURA.md
   - Explore o código em src/

## 📅 Última Atualização

| Documento | Data | Status |
|-----------|------|--------|
| QUICKSTART.md | 15/01/2024 | ✅ Ativo |
| GUIA_DE_USO.md | 15/01/2024 | ✅ Ativo |
| ARQUITETURA.md | 15/01/2024 | ✅ Ativo |
| DER.md | 15/01/2024 | ✅ Ativo |
| REQUISITOS.md | 15/01/2024 | ✅ Ativo |
| PLANO_DE_TESTES.md | 15/01/2024 | ✅ Ativo |
| CHECKLIST_DEPLOY.md | 15/01/2024 | ✅ Ativo |
| SUMARIO.md | 15/01/2024 | ✅ Ativo |
| CHANGELOG.md | 15/01/2024 | ✅ Ativo |

---

**Versão**: 1.0  
**Última Atualização**: 15 de Janeiro de 2024  
**Desenvolvedor**: Poliane  
**Empresa**: Vida Plus

---

## 🎉 Bem-vindo ao SGHSS!

Escolha seu caminho acima e comece agora!

**Primeiro acesso?** → [QUICKSTART.md](QUICKSTART.md)  
**Quer aprender?** → [ARQUITETURA.md](ARQUITETURA.md)  
**Quer usar?** → [GUIA_DE_USO.md](GUIA_DE_USO.md)  
**Quer testar?** → [PLANO_DE_TESTES.md](PLANO_DE_TESTES.md)  
**Quer fazer deploy?** → [CHECKLIST_DEPLOY.md](CHECKLIST_DEPLOY.md)

