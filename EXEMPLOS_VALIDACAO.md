# 🧪 EXEMPLOS DE VALIDAÇÃO - REQUISIÇÕES E RESPOSTAS

## 1️⃣ EQUIPAMENTO - Exemplos

### ✅ Requisição VÁLIDA

**POST** `/api/equipamentos`
```json
{
  "nome": "Computador Dell XPS 13",
  "categoria": "Eletrônico",
  "marca": "Dell",
  "modelo": "XPS-13-9370",
  "pesoKg": 1.25,
  "estadoConservacao": "Excelente"
}
```

**Resposta:** `HTTP 201 CREATED`
```json
{
  "nome": "Computador Dell XPS 13",
  "categoria": "Eletrônico",
  "marca": "Dell",
  "modelo": "XPS-13-9370",
  "pesoKg": 1.25,
  "estadoConservacao": "Excelente"
}
```

---

### ❌ Requisição INVÁLIDA - Nome Vazio

**POST** `/api/equipamentos`
```json
{
  "nome": "",
  "categoria": "Eletrônico",
  "marca": "Dell",
  "modelo": "XPS",
  "pesoKg": 1.25,
  "estadoConservacao": "Bom"
}
```

**Resposta:** `HTTP 400 BAD REQUEST`
```json
{
  "nome": "Nome do equipamento é obrigatório"
}
```

---

### ❌ Requisição INVÁLIDA - Peso Negativo

**POST** `/api/equipamentos`
```json
{
  "nome": "Computador",
  "categoria": "Eletrônico",
  "marca": "Dell",
  "modelo": "XPS",
  "pesoKg": -5.0,
  "estadoConservacao": "Bom"
}
```

**Resposta:** `HTTP 400 BAD REQUEST`
```json
{
  "pesoKg": "Peso deve ser um valor positivo"
}
```

---

### ❌ Requisição INVÁLIDA - Múltiplos Erros

**POST** `/api/equipamentos`
```json
{
  "nome": "A",
  "categoria": "",
  "marca": "D",
  "modelo": "X",
  "pesoKg": null,
  "estadoConservacao": ""
}
```

**Resposta:** `HTTP 400 BAD REQUEST`
```json
{
  "nome": "Nome deve ter entre 3 e 255 caracteres",
  "categoria": "Categoria é obrigatória",
  "marca": "Marca deve ter entre 2 e 255 caracteres",
  "pesoKg": "Peso em kg é obrigatório",
  "estadoConservacao": "Estado de conservação é obrigatório"
}
```

---

## 2️⃣ PONTO DE COLETA - Exemplos

### ✅ Requisição VÁLIDA

**POST** `/api/pontos-coleta`
```json
{
  "nome": "Ponto de Coleta Centro",
  "tipo": "Comercial",
  "residuoAceito": "Eletrônico",
  "cidade": "São Paulo",
  "uf": "SP",
  "emailContato": "contato@pontocentro.com.br",
  "capacidadeKg": 1000.0,
  "pesoAcumuladoKg": 250.5,
  "limiteAlertaPct": 80,
  "status": "Ativo"
}
```

**Resposta:** `HTTP 201 CREATED` ✅

---

### ❌ Requisição INVÁLIDA - Email Inválido

**POST** `/api/pontos-coleta`
```json
{
  "nome": "Ponto de Coleta",
  "tipo": "Comercial",
  "residuoAceito": "Eletrônico",
  "cidade": "São Paulo",
  "uf": "SP",
  "emailContato": "emailinvalido",
  "capacidadeKg": 1000.0,
  "pesoAcumuladoKg": 250.5,
  "limiteAlertaPct": 80,
  "status": "Ativo"
}
```

**Resposta:** `HTTP 400 BAD REQUEST`
```json
{
  "emailContato": "Email deve ser válido"
}
```

---

### ❌ Requisição INVÁLIDA - Limite Fora do Intervalo

**POST** `/api/pontos-coleta`
```json
{
  "nome": "Ponto de Coleta",
  "tipo": "Comercial",
  "residuoAceito": "Eletrônico",
  "cidade": "São Paulo",
  "uf": "SP",
  "emailContato": "contato@ponto.com.br",
  "capacidadeKg": 1000.0,
  "pesoAcumuladoKg": 250.5,
  "limiteAlertaPct": 150,
  "status": "Ativo"
}
```

**Resposta:** `HTTP 400 BAD REQUEST`
```json
{
  "limiteAlertaPct": "Limite deve ser no máximo 100%"
}
```

---

### ❌ Requisição INVÁLIDA - Peso Acumulado Negativo

**POST** `/api/pontos-coleta`
```json
{
  "nome": "Ponto de Coleta",
  "tipo": "Comercial",
  "residuoAceito": "Eletrônico",
  "cidade": "São Paulo",
  "uf": "SP",
  "emailContato": "contato@ponto.com.br",
  "capacidadeKg": 1000.0,
  "pesoAcumuladoKg": -50.0,
  "limiteAlertaPct": 80,
  "status": "Ativo"
}
```

**Resposta:** `HTTP 400 BAD REQUEST`
```json
{
  "pesoAcumuladoKg": "Peso acumulado deve ser zero ou positivo"
}
```

---

## 3️⃣ DESCARTE - Exemplos

### ✅ Requisição VÁLIDA

**POST** `/api/descartes`
```json
{
  "equipamentoId": 1,
  "inspecaoId": 1,
  "pontoColetaId": 1,
  "destinacao": "Reciclagem",
  "pesoKg": 2.5,
  "status": "Concluído",
  "numeroCertificado": "CERT-2024-001",
  "dataRegistro": "2024-11-03",
  "dataConclusao": "2024-11-03"
}
```

**Resposta:** `HTTP 201 CREATED` ✅

---

### ❌ Requisição INVÁLIDA - Data no Futuro

**POST** `/api/descartes`
```json
{
  "equipamentoId": 1,
  "inspecaoId": 1,
  "pontoColetaId": 1,
  "destinacao": "Reciclagem",
  "pesoKg": 2.5,
  "status": "Concluído",
  "numeroCertificado": "CERT-2024-001",
  "dataRegistro": "2099-11-03",
  "dataConclusao": null
}
```

**Resposta:** `HTTP 400 BAD REQUEST`
```json
{
  "dataRegistro": "Data de registro não pode ser no futuro"
}
```

---

### ❌ Requisição INVÁLIDA - IDs Inválidos

**POST** `/api/descartes`
```json
{
  "equipamentoId": -1,
  "inspecaoId": 0,
  "pontoColetaId": null,
  "destinacao": "Reciclagem",
  "pesoKg": 2.5,
  "status": "Concluído",
  "numeroCertificado": null,
  "dataRegistro": "2024-11-03",
  "dataConclusao": null
}
```

**Resposta:** `HTTP 400 BAD REQUEST`
```json
{
  "equipamentoId": "ID do equipamento deve ser um número positivo",
  "inspecaoId": "ID da inspeção deve ser um número positivo",
  "pontoColetaId": "ID do ponto de coleta é obrigatório"
}
```

---

## 4️⃣ INSPEÇÃO - Exemplos

### ✅ Requisição VÁLIDA

**POST** `/api/inspecoes`
```json
{
  "equipamentoId": 1,
  "dataInspecao": "2024-11-03",
  "responsavel": "João Silva",
  "resultado": "Aprovado",
  "possuiBateria": "Sim",
  "descricaoAvarias": "Nenhuma avaria detectada"
}
```

**Resposta:** `HTTP 201 CREATED` ✅

---

### ❌ Requisição INVÁLIDA - Responsável Muito Curto

**POST** `/api/inspecoes`
```json
{
  "equipamentoId": 1,
  "dataInspecao": "2024-11-03",
  "responsavel": "Jo",
  "resultado": "Aprovado",
  "possuiBateria": "Sim",
  "descricaoAvarias": null
}
```

**Resposta:** `HTTP 400 BAD REQUEST`
```json
{
  "responsavel": "Nome do responsável deve ter entre 3 e 255 caracteres"
}
```

---

## 5️⃣ NOTIFICAÇÃO - Exemplos

### ✅ Requisição VÁLIDA

**POST** `/api/notificacoes`
```json
{
  "equipamentoId": 1,
  "pontoColetaId": 1,
  "descarteId": 1,
  "tipo": "Email",
  "destinatarioEmail": "usuario@example.com",
  "assunto": "Coleta de Equipamento Confirmada",
  "mensagem": "Sua coleta foi registrada com sucesso no sistema!",
  "statusEnvio": "Enviado",
  "dataCriacao": "2024-11-03"
}
```

**Resposta:** `HTTP 201 CREATED` ✅

---

### ❌ Requisição INVÁLIDA - Mensagem Muito Curta

**POST** `/api/notificacoes`
```json
{
  "equipamentoId": 1,
  "pontoColetaId": 1,
  "descarteId": 1,
  "tipo": "Email",
  "destinatarioEmail": "usuario@example.com",
  "assunto": "Coleta Confirmada",
  "mensagem": "Coleta OK",
  "statusEnvio": "Enviado",
  "dataCriacao": "2024-11-03"
}
```

**Resposta:** `HTTP 400 BAD REQUEST`
```json
{
  "mensagem": "Mensagem deve ter entre 10 e 255 caracteres"
}
```

---

### ❌ Requisição INVÁLIDA - Email Inválido e Assunto Curto

**POST** `/api/notificacoes`
```json
{
  "equipamentoId": 1,
  "pontoColetaId": 1,
  "descarteId": 1,
  "tipo": "Email",
  "destinatarioEmail": "emailinvalido",
  "assunto": "Oi",
  "mensagem": "Uma mensagem com tamanho apropriado que atende ao mínimo de caracteres",
  "statusEnvio": "Enviado",
  "dataCriacao": "2024-11-03"
}
```

**Resposta:** `HTTP 400 BAD REQUEST`
```json
{
  "destinatarioEmail": "Email deve ser válido",
  "assunto": "Assunto deve ter entre 5 e 255 caracteres"
}
```

---

## 📊 TABELA DE STATUS HTTP

| Status | Significado | Quando Ocorre |
|--------|-----------|---------------|
| **201** | Created | Requisição válida e recurso criado ✅ |
| **200** | OK | Requisição válida (GET, PUT) |
| **204** | No Content | Requisição válida sem resposta (DELETE) |
| **400** | Bad Request | Validação falhou ❌ |
| **404** | Not Found | Recurso não encontrado |
| **409** | Conflict | Violação de integridade (registro duplicado) |
| **500** | Internal Server | Erro no servidor |

---

## 🧪 TESTE RÁPIDO COM CURL

### Teste Válido
```bash
curl -X POST http://localhost:8080/api/equipamentos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Notebook Lenovo",
    "categoria": "Eletrônico",
    "marca": "Lenovo",
    "modelo": "ThinkPad",
    "pesoKg": 1.8,
    "estadoConservacao": "Bom"
  }'
```

### Teste Inválido
```bash
curl -X POST http://localhost:8080/api/equipamentos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "",
    "categoria": "E",
    "marca": "Lenovo",
    "modelo": "ThinkPad",
    "pesoKg": -1.8,
    "estadoConservacao": ""
  }'
```

---

## ✅ CONCLUSÃO

As validações foram implementadas com sucesso em todos os DTOs, garantindo que:

1. ✅ **Dados obrigatórios** são sempre preenchidos
2. ✅ **Formato de dados** está correto (emails, datas, etc)
3. ✅ **Valores numéricos** estão dentro dos intervalos permitidos
4. ✅ **Mensagens de erro** são claras e em português
5. ✅ **Segurança** é garantida na entrada de dados

**Status:** 🟢 Implementação Completa

