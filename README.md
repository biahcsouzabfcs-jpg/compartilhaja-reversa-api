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

A forma recomendada é com **Docker Compose**: ele sobe o Oracle e a aplicação já configurados, sem instalar Java nem banco na máquina.

### 1. Pré-requisitos

| Ferramenta | Versão | Para quê |
|---|---|---|
| Docker Desktop (ou Docker Engine + Compose plugin) | Qualquer recente | Subir o banco e a aplicação |
| Git | Qualquer | Clonar o repositório |
| Java 21 | 21 | Só para executar fora do Docker ou rodar os testes localmente |

Portas que precisam estar livres: **8080** (aplicação) e **1521** (Oracle).

### 2. Obter o código

```bash
git clone https://github.com/biahcsouzabfcs-jpg/compartilhaja-reversa-api.git
cd compartilhaja-reversa-api
```

### 3. Definir a senha da API

A variável `API_PASSWORD` é **obrigatória**. Sem ela, o `docker compose` para com a mensagem *"Defina API_PASSWORD no ambiente"*. A senha não é salva em nenhum arquivo do projeto.

```bash
export API_PASSWORD='uma-senha-forte'
```

Opcionalmente, defina também `API_USERNAME` (o padrão é `igor`).

> Para não digitar a variável a cada terminal, você pode criar um arquivo `.env` na raiz do projeto com `API_PASSWORD=...`. O Docker Compose lê esse arquivo automaticamente. O `.env` já deve estar no `.gitignore`, para não ser enviado ao GitHub.

### 4. Subir os containers

```bash
docker compose up --build
```

Na primeira execução, o Docker baixa a imagem do Oracle (é grande) e compila a aplicação dentro de um container Maven. Isso pode levar vários minutos.

Para rodar em segundo plano, adicione `-d`:

```bash
docker compose up --build -d
```

### 5. Confirmar que está no ar

```bash
docker compose ps
```

Você deve ver:
- `oracle-db-compartilhaja`: `healthy` (o banco leva um tempo para ficar pronto)
- `app-compartilhaja`: `Up`

Teste a API com as credenciais configuradas:

```bash
curl -u igor:"$API_PASSWORD" http://localhost:8080/api/equipamentos
```

Resposta esperada: `200` com a lista (vazia no primeiro uso). Sem `-u`, a resposta deve ser `401`.

Na primeira execução, o Flyway cria as tabelas automaticamente, antes da aplicação aceitar requisições.

### 6. Acompanhar os logs

```bash
docker compose logs -f app          # logs da aplicação
docker compose logs -f oracle-db    # logs do banco
```

### 7. Parar e reiniciar

```bash
docker compose stop        # para os containers, mantém os dados
docker compose start       # volta a rodar com os mesmos dados
docker compose down        # remove os containers, mantém o volume com os dados
docker compose down -v     # remove containers e apaga TODOS os dados do banco
```

### 8. Aplicar alterações no código

Depois de mudar o código-fonte, recompile a imagem da aplicação:

```bash
docker compose up --build -d app
```

O banco continua rodando e mantém os dados.

### Solução de problemas

| Sintoma | Causa provável | O que fazer |
|---|---|---|
| `Defina API_PASSWORD no ambiente` | Variável não definida | Execute o passo 3 no mesmo terminal |
| `port is already allocated` (8080 ou 1521) | Outro programa usa a porta | Pare o outro programa ou altere o mapeamento de porta no `docker-compose.yml` |
| `ORA-12514` nos logs da aplicação | O banco ainda está abrindo o serviço `FREEPDB1` | Aguarde o `oracle-db` ficar `healthy` e rode `docker compose restart app` |
| `app-compartilhaja` reiniciando ou saindo | Falha na conexão ou nas migrações | Veja `docker compose logs app` |
| Resposta `401` com a senha certa | `API_PASSWORD` foi definida diferente da usada no `curl` | Confirme o valor com `echo $API_PASSWORD` |

### Executar sem Docker (aplicação local)

Use esta opção se quiser rodar a aplicação direto na sua máquina. Ela precisa de um Oracle acessível, por padrão em `localhost:1521/FREEPDB1`, com o usuário `compartilhaja_user`. A forma mais simples é subir só o banco pelo compose:

```bash
docker compose up -d oracle-db
export API_PASSWORD='uma-senha-forte'
./mvnw spring-boot:run
```

No Windows, use `mvnw.cmd spring-boot:run`.

As variáveis de conexão têm valores padrão e podem ser trocadas: `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME` e `SPRING_DATASOURCE_PASSWORD`.

### Testes automatizados

```bash
./mvnw test
```

O teste `CompartilhajaApplicationTests.contextLoads` sobe a aplicação completa e conecta no banco. Por isso, o Oracle precisa estar no ar (`docker compose up -d oracle-db`) e `API_PASSWORD` precisa estar definida. Os demais testes são unitários e não dependem do banco.

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
