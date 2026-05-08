# 🔧 CORREÇÃO DO ERRO DE INICIALIZAÇÃO

## ✅ O que foi corrigido:

1. **Configuração do Spring Security 6+**
   - Arquivo: `WebSecurityConfig.java`
   - Problema: Usando API deprecated do Spring Security
   - Solução: Atualizado para a nova API (lambdas)

## 🚀 Como executar agora:

### **Opção 1: Via IntelliJ IDEA (RECOMENDADO)**

1. Abra o IntelliJ IDEA
2. Vá até a classe: `src/main/java/com/vidaplus/sghss/SghssApplication.java`
3. Clique no botão verde ▶ ao lado do método `main`
4. A aplicação iniciará na porta 8080

### **Opção 2: Limpar Cache do IntelliJ**

Se ainda tiver erro:

1. **File** → **Invalidate Caches**
2. Selecione **Invalidate and Restart**
3. Aguarde o IntelliJ reiniciar
4. Tente rodar novamente

### **Opção 3: Instalar Maven (caso queira usar terminal)**

Se quiser usar Maven via PowerShell:

```powershell
# Verificar se Maven está instalado
mvn -version

# Se não estiver, baixar em: https://maven.apache.org/download.cgi
# Adicionar ao PATH do Windows
```

## 📍 Após iniciar:

✅ A aplicação estará disponível em:
- **http://localhost:8080/swagger-ui/index.html** (Documentação)
- **http://localhost:8080/h2-console** (Banco H2)

✅ Credenciais padrão H2:
- **URL**: `jdbc:h2:mem:testdb`
- **Usuario**: `sa`
- **Senha**: (deixe em branco)

---

## 📋 Mudanças específicas:

### Antes (Deprecated):
```java
http
  .csrf().disable()
  .cors().and()
  .authorizeRequests()
```

### Depois (Correto):
```java
http
  .csrf(csrf -> csrf.disable())
  .cors(cors -> cors.configurationSource(corsConfigurationSource()))
  .authorizeHttpRequests(auth -> auth
```

---

**Status**: ✅ Corrigido e pronto para usar!

