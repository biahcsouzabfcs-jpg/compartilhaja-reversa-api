# CompartilhaJá - API de Logística Reversa

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.2-green)
![Oracle](https://img.shields.io/badge/Database-Oracle-red)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)

## 📋 Sobre o Projeto

**CompartilhaJá** é uma plataforma de **Gestão de Resíduos e Reciclagem** que implementa o conceito de **Logística Reversa**. O projeto visa facilitar o descarte correto de equipamentos eletrônicos, conectando donos de equipamentos com pontos de coleta especializados e garantindo destinação ambientalmente correta.

### 🌱 Tema ESG
Este projeto atende ao tema **"Gestão de resíduos e reciclagem"** da atividade avaliativa, implementando soluções de:
- ✅ Rastreamento de coleta seletiva
- ✅ Alertas automáticos para coleta
- ✅ Notificações sobre destinação correta
- ✅ Registro de descartes adequados
- ✅ Integração com ecopontos e cooperativas

---

## 🚀 Funcionalidades Implementadas

### ✅ Core Features
- **25+ Endpoints REST** para gerenciamento completo
- **40+ Regras de Validação** com mensagens em português
- **Tratamento robusto de exceções** com HTTP status apropriados
- **Banco de dados Oracle** em contêiner Docker
- **Migrações automáticas** com Flyway
- **Documentação interativa** com Collection Insomnia

### 📦 Módulos

#### 1. **Autenticação** 🔐
- Registro de usuários (ADMIN e USER)
- Login com tokens
- Controle de acesso por role

#### 2. **Pontos de Coleta** 🏪
- Cadastro de ecopontos e cooperativas
- Monitoramento de capacidade
- Alertas de limite atingido
- Contacto e localização

#### 3. **Equipamentos** 🖥️
- Cadastro de equipamentos eletrônicos
- Histórico de estado de conservação
- Rastreamento de proprietários
- Categorização por tipo

#### 4. **Inspeções** 🔍
- Triagem técnica de equipamentos
- Detecção de avarias e componentes perigosos
- Decisão de descarte vs. reuso
- Responsabilidade técnica

#### 5. **Descartes** ♻️
- Registro de descarte correto
- Geração de certificados
- Rastreamento de destinação
- Integração com pontos de coleta

#### 6. **Notificações** 📧
- Avisos a proprietários
- Confirmação de destinação
- Alertas de capacidade
- Status de processamento

---

## 📊 Endpoints Disponíveis

### Autenticação (3)
```
POST   /auth/register      Registrar novo usuário
POST   /auth/login         Login e obter token
GET    /auth/profile       Perfil do usuário logado
```

### Equipamentos (5)
```
GET    /api/equipamentos           Listar todos
GET    /api/equipamentos/{id}      Buscar por ID
POST   /api/equipamentos           Criar novo
PUT    /api/equipamentos           Atualizar
DELETE /api/equipamentos/{id}      Deletar
```

### Pontos de Coleta (5)
```
GET    /api/pontos-coleta          Listar todos
GET    /api/pontos-coleta/{id}     Buscar por ID
POST   /api/pontos-coleta          Criar novo
PUT    /api/pontos-coleta          Atualizar
DELETE /api/pontos-coleta/{id}     Deletar
```

### Inspeções (5)
```
GET    /api/inspecoes              Listar todas
GET    /api/inspecoes/{id}         Buscar por ID
POST   /api/inspecoes              Criar nova
PUT    /api/inspecoes              Atualizar
DELETE /api/inspecoes/{id}         Deletar
```

### Descartes (5)
```
GET    /api/descartes              Listar todos
GET    /api/descartes/{id}         Buscar por ID
POST   /api/descartes              Criar novo
PUT    /api/descartes              Atualizar
DELETE /api/descartes/{id}         Deletar
```

### Notificações (5)
```
GET    /api/notificacoes           Listar todas
GET    /api/notificacoes/{id}      Buscar por ID
POST   /api/notificacoes           Criar nova
PUT    /api/notificacoes           Atualizar
DELETE /api/notificacoes/{id}      Deletar
```

---

## 🛠️ Tecnologias Utilizadas

| Componente | Tecnologia | Versão |
|-----------|-----------|---------|
| **Linguagem** | Java | 21 |
| **Framework** | Spring Boot | 4.1.2 |
| **ORM** | Hibernate + JPA | 7.4.13 |
| **Validação** | Jakarta Validation | Latest |
| **Banco de Dados** | Oracle Free | 23.x |
| **Migrações** | Flyway | Latest |
| **Container** | Docker | Latest |
| **Build** | Maven | 3.9 |

---

## 📋 Requisitos

### Pré-requisitos
- Docker & Docker Compose instalados
- Java 21 (para desenvolvimento local)
- Maven 3.9+

### Portas Necessárias
- `8080` - Aplicação Spring Boot
- `1521` - Oracle Database

---

## 🚀 Como Executar

### 1. Clonar o Repositório
```bash
git clone https://github.com/biahcsouzabfcs-jpg/compartilhaja-reversa-api.git
cd compartilhaja-reversa-api
```

### 2. Executar com Docker Compose
```bash
docker compose up --build
```

Isso irá:
- ✅ Construir a imagem da aplicação
- ✅ Iniciar o Oracle Database
- ✅ Executar as migrações Flyway automaticamente
- ✅ Aplicação disponível em `http://localhost:8080`

### 3. Verificar Status
```bash
docker compose ps
```

Você deve ver dois contêineres rodando:
- `oracle-db-compartilhaja` - Banco de dados (saudável)
- `app-compartilhaja` - Aplicação (rodando)

---

## 🧪 Como Testar os Endpoints

### Opção 1: Com Insomnia (Recomendado)

#### Importar Collection
1. Abra o Insomnia
2. Vá em `File > Import > From File`
3. Selecione: `insomnia-collection/insomnia-collection.json`
4. Collection "Logística Reversa API" será importada

#### Executar Testes
1. **Autenticação** (execute nesta ordem):
   - Registrar usuário ADMIN
   - Registrar usuário USER
   - Login (obter token)

2. **CRUD Operations** (com token):
   - Criar recursos (POST)
   - Listar recursos (GET)
   - Atualizar recursos (PUT)
   - Deletar recursos (DELETE)

3. **Testes de Segurança**:
   - Tentar acessar sem token (deve retornar 403)

### Opção 2: Com cURL

#### Login
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@compartilhaja.com",
    "senha": "admin123"
  }'
```

#### Listar Equipamentos (com token)
```bash
curl -X GET http://localhost:8080/api/equipamentos \
  -H "Authorization: Bearer <seu-token>"
```

#### Criar Equipamento (com token)
```bash
curl -X POST http://localhost:8080/api/equipamentos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <seu-token>" \
  -d '{
    "nome": "Computador",
    "categoria": "Eletrônico",
    "marca": "Dell",
    "modelo": "XPS-13",
    "pesoKg": 1.25,
    "estadoConservacao": "Bom"
  }'
```

---

## 🔐 Credenciais de Teste

### Usuário ADMIN
- **Email**: `admin@compartilhaja.com`
- **Senha**: `admin123`
- **Role**: ADMIN (criar, atualizar, deletar)

### Usuário USER
- **Email**: `usuario@compartilhaja.com`
- **Senha**: `user1234`
- **Role**: USER (consultas apenas)

---

## 📁 Estrutura do Projeto

```
src/main/java/br/com/fiap/compartilhaja/
├── model/                    # Entidades JPA
│   ├── Equipamento.java
│   ├── Descarte.java
│   ├── PontoColeta.java
│   ├── Inspecao.java
│   └── Notificacao.java
├── dto/                      # Data Transfer Objects (com validações)
│   ├── EquipamentoRequestDTO.java
│   ├── DescarteRequestDTO.java
│   ├── PontoColetaRequestDTO.java
│   ├── InspecaoRequestDTO.java
│   └── NotificacaoRequestDTO.java
├── controller/               # REST Controllers
│   ├── EquipamentoController.java
│   ├── DescarteController.java
│   ├── PontoColetaController.java
│   ├── InspecaoController.java
│   └── NotificacaoController.java
├── service/                  # Business Logic
│   ├── EquipamentoService.java
│   ├── DescarteService.java
│   ├── PontoColetaService.java
│   ├── InspecaoService.java
│   └── NotificacaoService.java
├── repository/               # Data Access
│   ├── EquipamentoRepository.java
│   ├── DescarteRepository.java
│   ├── PontoColetaRepository.java
│   ├── InspecaoRepository.java
│   └── NotificacaoRepository.java
├── exception/                # Custom Exceptions
│   └── ResourceNotFoundException.java
├── advice/                   # Global Exception Handler
│   └── GlobalExceptionHandler.java
└── CompartilhajaApplication.java

src/main/resources/
├── application.properties     # Configurações Spring
└── db/migration/
    └── V1__create-tables-residuos.sql  # Schema do BD

docker/
├── Dockerfile                # Build multi-stage
└── docker-compose.yml        # Orquestração

insomnia-collection/
└── insomnia-collection.json  # Collection completa para testes
```

---

## ✨ Recursos Implementados

### ✅ Validação de Dados
- **40+ regras de validação** em DTOs
- Mensagens de erro **em português**
- Validações customizadas (email, datas, intervalos)
- HTTP 400 Bad Request com detalhes dos erros

### ✅ Tratamento de Exceções
- `GlobalExceptionHandler` centralizado
- `ResourceNotFoundException` para recursos não encontrados
- `DataIntegrityViolationException` para conflitos
- Respostas estruturadas e claras

### ✅ Banco de Dados
- **Oracle Free** rodando em Docker
- **5 entidades** com relacionamentos FK
- **Flyway migrations** automáticas
- **Índices** para performance

### ✅ Docker
- **Dockerfile multi-stage** para otimização
- **docker-compose.yml** com BD integrado
- **Health checks** para garantir disponibilidade
- **Volume persistente** para dados

---

## 📚 Documentação Adicional

### Validações
Veja [VALIDACAO_EXPLICADA.md](VALIDACAO_EXPLICADA.md) para:
- Explicação de cada anotação usada
- Exemplos de validação
- Como funciona o tratamento de erros

### Exemplos de Requisições
Veja [EXEMPLOS_VALIDACAO.md](EXEMPLOS_VALIDACAO.md) para:
- 15+ exemplos de requisições
- Requisições válidas vs inválidas
- Respostas esperadas

### Sumário Executivo
Veja [SUMARIO_VALIDACOES.md](SUMARIO_VALIDACOES.md) para:
- Resumo das validações
- Estatísticas de implementação
- Checklist de requisitos

---

## 🚧 Em Desenvolvimento

### Próximas Features
- [ ] Spring Security (autenticação JWT)
- [ ] Swagger/OpenAPI (documentação interativa)
- [ ] Paginação nos endpoints GET
- [ ] Filtros de busca avançada
- [ ] Auditoria (criado_em, atualizado_por)
- [ ] Soft delete para dados sensíveis
- [ ] Relatórios de ESG

---

## 🤝 Contribuidores

| Membro | Responsabilidade |
|--------|-----------------|
| **Bianca** | Validações, Tratamento de Exceções, DTOs |
| **Richard** | Collection Insomnia, Testes |
| **[Outro membro]** | Spring Security (em andamento) |

---

## 📞 Suporte e Dúvidas

Para dúvidas sobre:
- **Validações**: Veja [VALIDACAO_EXPLICADA.md](VALIDACAO_EXPLICADA.md)
- **Testes**: Use [insomnia-collection/insomnia-collection.json](insomnia-collection/insomnia-collection.json)
- **Estrutura**: Consulte o diagrama de pastas acima
- **Execução**: Siga as instruções de "Como Executar"

---

## 📄 Licença

Este projeto é parte de uma atividade avaliativa da FIAP.

---

## ✅ Checklist de Entrega

- [x] 25+ Endpoints REST
- [x] Oracle Database
- [x] Flyway Migrations
- [x] Docker & docker-compose
- [x] Validações (40+ regras)
- [x] Tratamento de Exceções
- [x] Collection Insomnia
- [x] README.md completo
- [ ] Spring Security (em progresso)
- [ ] ZIPs para entrega

---

**Última atualização**: 2026-11-03  
**Status**: Em desenvolvimento - Fase final 🚀
