# Checklist de Deploy - SGHSS

## 1. Verificações Pré-Deploy

### 1.1 Código
- [ ] Todos os testes passam (`mvn test`)
- [ ] Sem erros de compilação (`mvn clean compile`)
- [ ] Build bem-sucedido (`mvn package`)
- [ ] Sem avisos importantes
- [ ] Código pode ser deployado (`mvn clean install`)
- [ ] Sem secrets no código (senhas, tokens, etc.)
- [ ] Sem arquivos de teste em produção
- [ ] Sem logs excessivos em produção

### 1.2 Segurança
- [ ] Senhas criptografadas com BCrypt
- [ ] JWT secret configurado em variável de ambiente
- [ ] CORS permitindo apenas domínios confiáveis
- [ ] HTTPS habilitado em produção
- [ ] Sem dados sensíveis em logs
- [ ] SQL Injection prevenido (use parameterized queries)
- [ ] CSRF token verificado (se necessário)
- [ ] Autenticação obrigatória para endpoints sensíveis
- [ ] Rate limiting implementado
- [ ] WAF configurado (opcional, recomendado)

### 1.3 Banco de Dados
- [ ] Schema do banco criado
- [ ] Indices criados para melhor performance
- [ ] Backups configurados
- [ ] Procedure/Views revisadas
- [ ] Replicação configurada (se alta disponibilidade)
- [ ] Scripts de migração testados
- [ ] Dados sensíveis criptografados
- [ ] Logs de auditoria ativados

### 1.4 Documentação
- [ ] README.md atualizado
- [ ] API documentada no Swagger
- [ ] Arquitetura documentada
- [ ] DER documentado
- [ ] Guia de deploy criado
- [ ] Guia de troubleshooting criado
- [ ] Changelog atualizado

### 1.5 Performance
- [ ] Queries otimizadas
- [ ] Índices no banco configurados
- [ ] Cache implementado onde necessário
- [ ] Paginação implementada (se muitos dados)
- [ ] Lazy loading configurado
- [ ] Connection pool otimizado

### 1.6 Monitoramento
- [ ] Logging configurado (SLF4J)
- [ ] Alertas configurados
- [ ] Health check implementado
- [ ] Métricas de aplicação ativadas
- [ ] APM (Application Performance Monitoring) opcional

### 1.7 Configuração
- [ ] application.yml para produção criado
- [ ] Variáveis de ambiente definidas
- [ ] Secrets gerenciados corretamente
- [ ] Porta configurada
- [ ] Context path configurado
- [ ] Timezone configurado

## 2. Checklist de GitHub

### 2.1 Repositório
- [ ] Repositório criado no GitHub
- [ ] .gitignore configurado
- [ ] LICENSE adicionada (Apache 2.0)
- [ ] README.md com instruções
- [ ] Branches criadas (main, develop)
- [ ] Protected branches configuradas

### 2.2 Documentação GitHub
- [ ] README.md completo
- [ ] Wiki criada com documentação
- [ ] Issues template criado
- [ ] Pull request template criado
- [ ] Contributing.md criado
- [ ] Security.md criado

### 2.3 CI/CD
- [ ] GitHub Actions configurado
- [ ] Build automático no push
- [ ] Testes rodam automaticamente
- [ ] Deploy automático (opcional)
- [ ] Badge status no README

## 3. Checklist de Produção

### 3.1 Infraestrutura
- [ ] Servidor web preparado (Nginx/Apache)
- [ ] Servidor aplicação preparado (Tomcat/Java)
- [ ] SSL/TLS certificado instalado
- [ ] Firewall configurado
- [ ] Load balancer configurado (se necessário)
- [ ] CDN configurado (opcional)

### 3.2 Banco de Dados
- [ ] PostgreSQL instalado em produção
- [ ] Database criado
- [ ] Usuario do banco criado com permissões restritas
- [ ] Backup automático configurado
- [ ] Restore testado
- [ ] Replicação/HA configurada

### 3.3 Deployment
- [ ] JAR/WAR buildado corretamente
- [ ] Java 21 instalado no servidor
- [ ] Variáveis de ambiente configuradas
- [ ] Porta 8080 (ou outra) aberta no firewall
- [ ] Logs direcionados para arquivo
- [ ] Aplicação pode ser iniciada/parada

### 3.4 Testes Finais
- [ ] Testar login
- [ ] Testar CRUD de paciente
- [ ] Testar CRUD de profissional
- [ ] Testar agendamento de consulta
- [ ] Testar prontuário
- [ ] Testar erro handling
- [ ] Testar performance
- [ ] Testar com múltiplos usuários

### 3.5 Monitoramento Pós-Deploy
- [ ] Verificar logs regularmente
- [ ] Monitorar performance
- [ ] Monitorar erro rates
- [ ] Verificar disponibilidade (uptime)
- [ ] Validar backups estão rodando
- [ ] Verificar segurança regularmente

## 4. Troubleshooting Comum

### Problema: Aplicação não inicia
**Checklist:**
- [ ] Java 21 instalado?
- [ ] Variáveis de ambiente definidas?
- [ ] Banco de dados acessível?
- [ ] Porta 8080 disponível?
- [ ] Verificar logs para erro específico

### Problema: Erro de conexão com banco
**Checklist:**
- [ ] URL JDBC correta?
- [ ] Usuario/senha corretos?
- [ ] Banco de dados existe?
- [ ] Firewall permite conexão?
- [ ] PostgreSQL rodando?

### Problema: Erro 401 em endpoints
**Checklist:**
- [ ] Token JWT fornecido?
- [ ] Token expirado?
- [ ] Endpoint requer autenticação?
- [ ] Fazer login novamente

### Problema: Erro 422 na validação
**Checklist:**
- [ ] CPF tem 11 dígitos?
- [ ] Email tem formato válido?
- [ ] Campo obrigatório preenchido?
- [ ] Verificar mensagem de erro

## 5. Pós-Deploy

### 5.1 Monitoramento Contínuo
- [ ] Dashboard de logs configurado
- [ ] Alertas de erro funcionando
- [ ] Alertas de performance funcionando
- [ ] Verificações de segurança agendadas

### 5.2 Manutenção
- [ ] Scripts de backup testados
- [ ] Plano de atualização definido
- [ ] Dados antigos arquivados (se necessário)
- [ ] Logs rotacionados

### 5.3 Feedback
- [ ] Feedback de usuários coletado
- [ ] Issues registradas
- [ ] Melhorias planejadas
- [ ] Próximas versões definidas

## 6. Versões de Deploy

### Versão 1.0 (Atual)
- [x] MVP com 24 endpoints
- [x] Documentação completa
- [x] Testes unitários
- [x] Pronto para produção

### Versão 1.1 (Próximo)
- [ ] Paginação
- [ ] Filtros avançados
- [ ] Mais testes
- [ ] Performance otimizada

## 7. Contatos de Suporte

**Em caso de problemas em produção:**

1. **Desenvolvedor Principal**: Poliane Fernandes de Godoy (pollygodoycurso@gmail.com)
2. **Suporte Técnico**: suporte@vidaplus.com
3. **Emergências**: +55 (19) 984063748

**Escalation Path:**
- Tier 1: Suporte Técnico
- Tier 2: Desenvolvedor
- Tier 3: Arquiteto de Sistema

## 8. Rollback Plan

Se algo der errado em produção:

1. [ ] Backup anterior identificado
2. [ ] Plano de rollback definido
3. [ ] Versão anterior preparada
4. [ ] Teste de rollback executado
5. [ ] Comunicação com usuários preparada

**Tempo estimado de rollback**: 30-60 minutos

---

**Última Atualização**: 28/03/2026
**Versão**: 1.0
**Status**: ✅ Pronto para Deploy

Use este checklist cerca de 1 semana antes de fazer deploy em produção!

