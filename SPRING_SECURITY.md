# Spring Security

Todos os endpoints exigem HTTP Basic. Requisições sem credenciais ou com senha incorreta recebem HTTP 401. A API não cria sessão.

Antes de iniciar, configure `API_PASSWORD` no ambiente. `API_USERNAME` é opcional (padrão: `igor`). A senha não fica salva no código. Com Docker Compose, repasse essas duas variáveis para o serviço da API.

No Postman: Authorization → Basic Auth → informe o usuário e a senha configurados. Use HTTPS quando publicado, pois Basic Auth não criptografa as credenciais no transporte.

Teste primeiro GET /api/equipamentos sem autenticação (401), depois com senha incorreta (401), depois com as credenciais corretas (resposta normal do endpoint). Teste também POST/PUT/DELETE com autenticação.
