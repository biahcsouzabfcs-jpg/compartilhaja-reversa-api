# CompartilhaJá - API de Logística Reversa

API REST para **gestão de resíduos e reciclagem** (logística reversa de equipamentos eletrônicos), desenvolvida como atividade avaliativa da FIAP, tema ESG "Gestão de resíduos e reciclagem".

## Tecnologias

| Componente | Tecnologia |
|---|---|
| Linguagem | Java 21 |
| Framework | Spring Boot 4.1.2 (Web, Data JPA, Validation, Security) |
| Banco de dados | Oracle Free 23 (Docker) |
| Migrações | Flyway |
| Build | Maven (Maven Wrapper incluso) |
| Containers | Docker e Docker Compose |

## Módulos e endpoints

Todos os endpoints estão sob `/api` e exigem autenticação HTTP Basic.

| Módulo | Método | Caminho | Descrição |
|---|---|---|---|
| Equipamentos | GET | `/api/equipamentos` | Listar |
| | GET | `/api/equipamentos/{id}` | Buscar por ID |
| | POST | `/api/equipamentos` | Cadastrar |
| | PUT | `/api/equipamentos` | Atualizar |
| | DELETE | `/api/equipamentos/{id}` | Excluir |
| Pontos de coleta | GET, POST, PUT | `/api/pontos-coleta` | Listar, cadastrar, atualizar |
| | GET, DELETE | `/api/pontos-coleta/{id}` | Buscar por ID, excluir |
| Inspeções | GET, POST, PUT | `/api/inspecoes` | Listar, cadastrar, atualizar |
| | GET, DELETE | `/api/inspecoes/{id}` | Buscar por ID, excluir |
| Descartes | GET, POST, PUT | `/api/descartes` | Listar, cadastrar, atualizar |
| | GET, DELETE | `/api/descartes/{id}` | Buscar por ID, excluir |
| Notificações | GET, POST, PUT | `/api/notificacoes` | Listar, cadastrar, atualizar |
| | GET, DELETE | `/api/notificacoes/{id}` | Buscar por ID, excluir |

## Segurança

A API usa **HTTP Basic**. Não há sessão nem token: cada requisição deve enviar usuário e senha.

- Sem credenciais, ou com senha incorreta: **HTTP 401**.
- Usuário: `API_USERNAME` (padrão `igor`).
- Senha: `API_PASSWORD`. É obrigatória e **não fica salva no código**. Defina-a no ambiente antes de subir a aplicação.

Como usar no Insomnia: aba **Auth** → **Basic Auth** → usuário e senha configurados. A coleção já está preparada para isso (veja a seção de testes).

Use HTTPS quando a API for publicada, porque o Basic Auth não criptografa as credenciais no transporte.

## Validações

Os dados de cadastro são validados com Jakarta Validation (`@NotBlank`, `@Size`, `@NotNull`, `@Positive`, `@Email` etc.). Quando uma regra falha, a API responde **HTTP 400** com a mensagem de cada campo em português.

Exemplo de resposta para um cadastro sem `status`:

```json
{ "status": "Status é obrigatório" }
```

Violações de integridade do banco (por exemplo, campo obrigatório nulo) são retornadas como **HTTP 409**.

## Como executar

### Pré-requisitos
- Docker e Docker Compose
- Portas livres: `8080` (aplicação) e `1521` (Oracle)

### Passos

```bash
git clone https://github.com/biahcsouzabfcs-jpg/compartilhaja-reversa-api.git
cd compartilhaja-reversa-api

# Defina a senha da API (não é salva em nenhum arquivo)
export API_PASSWORD='uma-senha-forte'

docker compose up --build
```

Quando os dois containers estiverem no ar:
- `oracle-db-compartilhaja` (banco, saudável)
- `app-compartilhaja` (aplicação em `http://localhost:8080`)

As migrações Flyway criam as tabelas automaticamente na primeira execução.

### Executar sem Docker

Com Java 21 e um Oracle acessível, configure as variáveis `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD` e `API_PASSWORD` e rode:

```bash
./mvnw spring-boot:run
```

### Testes automatizados

```bash
./mvnw test
```

## Testando com o Insomnia

1. Abra o Insomnia e importe `insomnia-collection/insomnia-collection.json` (**Application > Import > From File**).
2. No canto superior, selecione o ambiente **Base Environment**.
3. Preencha `api_password` com a mesma senha definida em `API_PASSWORD` (Manage Environments).
4. Execute as requisições pela ordem dos módulos. Todas usam Basic Auth.

Exemplo de cadastro (POST `/api/equipamentos`):

```json
{
  "nome": "Furadeira de Impacto",
  "categoria": "FERRAMENTA",
  "marca": "Bosch",
  "modelo": "GSB 13 RE",
  "pesoKg": 1.8,
  "estadoConservacao": "SEM_CONSERTO",
  "status": "AGUARDANDO_INSPECAO",
  "nomeProprietario": "Maria Souza",
  "emailProprietario": "maria.souza@email.com"
}
```

Resposta esperada: **201 Created**.

Também há um teste de segurança na pasta **06 - Testes de Segurança**: sem credenciais, a resposta deve ser **401**.

### Teste com cURL

```bash
curl -u igor:"$API_PASSWORD" http://localhost:8080/api/equipamentos
```

## Estrutura do projeto

```
├── src/main/java/br/com/fiap/compartilhaja/
│   ├── advice/          Tratamento global de exceções
│   ├── config/          Configuração de segurança (Spring Security)
│   ├── controller/      Endpoints REST
│   ├── dto/             Objetos de entrada e saída, com validações
│   ├── exception/       Exceções de negócio
│   ├── model/           Entidades JPA
│   ├── repository/      Acesso a dados
│   └── service/         Regras de negócio
├── src/main/resources/
│   ├── application.properties
│   └── db/migration/    Scripts Flyway (schema do banco)
├── src/test/            Testes automatizados
├── insomnia-collection/ Coleção para testes manuais
├── Dockerfile           Imagem da aplicação
├── docker-compose.yml   Aplicação + Oracle
└── pom.xml              Dependências e build
```

## Equipe

| Membro | Responsabilidade |
|---|---|
| Bianca | Validações, tratamento de exceções, DTOs |
| Richard | Coleção do Insomnia, testes |
| Igor | Spring Security |
