-- 1. SEQUÊNCIAS
CREATE SEQUENCE SEQ_PONTOS_COLETA
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE SEQ_EQUIPAMENTOS
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE SEQ_INSPECOES
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE SEQ_DESCARTES
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE SEQ_NOTIFICACOES
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- 2. TABELA: tbl_pontos_coleta (Entidade PontoColeta.java)

CREATE TABLE tbl_pontos_coleta (
                                   ponto_coleta_id      INTEGER DEFAULT SEQ_PONTOS_COLETA.NEXTVAL NOT NULL,
                                   nome                 VARCHAR2(150) NOT NULL,
                                   tipo                 VARCHAR2(30)  NOT NULL,
                                   residuo_aceito       VARCHAR2(30)  NOT NULL,
                                   cidade               VARCHAR2(100) NOT NULL,
                                   uf                   CHAR(2)       NOT NULL,
                                   email_contato        VARCHAR2(254) NOT NULL,
                                   capacidade_kg        NUMBER(10,3)  NOT NULL,
                                   peso_acumulado_kg    NUMBER(10,3)  DEFAULT 0 NOT NULL,
                                   limite_alerta_pct    NUMBER(3)     NOT NULL,
                                   status               VARCHAR2(20)  NOT NULL,
                                   CONSTRAINT pk_ponto_coleta PRIMARY KEY (ponto_coleta_id)
);


-- 3. TABELA: tbl_equipamentos (Entidade Equipamento.java)

CREATE TABLE tbl_equipamentos (
                                  equipamento_id       INTEGER DEFAULT SEQ_EQUIPAMENTOS.NEXTVAL NOT NULL,
                                  nome                 VARCHAR2(150) NOT NULL,
                                  categoria            VARCHAR2(30)  NOT NULL,
                                  marca                VARCHAR2(100) NOT NULL,
                                  modelo               VARCHAR2(100) NOT NULL,
                                  peso_kg              NUMBER(8,3)   NOT NULL,
                                  estado_convervacao   VARCHAR2(30)  NOT NULL,
                                  status               VARCHAR2(30)  NOT NULL,
                                  nome_proprietario    VARCHAR2(150) NOT NULL,
                                  email_proprietario   VARCHAR2(254) NOT NULL,
                                  data_cadastro        DATE          DEFAULT CURRENT_DATE NOT NULL,
                                  CONSTRAINT pk_equipamento PRIMARY KEY (equipamento_id)
);


-- 4. TABELA: tbl_inspecoes (Entidade Inspecao.java)

CREATE TABLE tbl_inspecoes (
                               inspecao_id          INTEGER DEFAULT SEQ_INSPECOES.NEXTVAL NOT NULL,
                               equipamento_id       INTEGER       NOT NULL,
                               data_inspecao        DATE          DEFAULT CURRENT_DATE NOT NULL,
                               responsavel          VARCHAR2(150) NOT NULL,
                               resultado            VARCHAR2(30)  NOT NULL,
                               possui_bateria       VARCHAR2(10)  NOT NULL,
                               descricao_avarias    VARCHAR2(1000),
                               CONSTRAINT pk_inspecao PRIMARY KEY (inspecao_id),
                               CONSTRAINT fk_inspecao_equipamento FOREIGN KEY (equipamento_id)
                                   REFERENCES tbl_equipamentos (equipamento_id)
);


-- 5. TABELA: tbl_descarte (Entidade Descarte.java)

CREATE TABLE tbl_descartes (
                              descarte_id          INTEGER DEFAULT SEQ_DESCARTES.NEXTVAL NOT NULL,
                              equipamento_id       INTEGER       NOT NULL,
                              inspecao_id          INTEGER       NOT NULL,
                              ponto_coleta_id      INTEGER       NOT NULL,
                              destinacao           VARCHAR2(30)  NOT NULL,
                              peso_kg              NUMBER(8,3)   NOT NULL,
                              status               VARCHAR2(20)  NOT NULL,
                              numero_certificado   VARCHAR2(50),
                              data_registro        DATE          DEFAULT CURRENT_DATE NOT NULL,
                              data_conclusao       DATE,
                              CONSTRAINT pk_descarte PRIMARY KEY (descarte_id),
                              CONSTRAINT uk_descarte_equipamento UNIQUE (equipamento_id),
                              CONSTRAINT fk_descarte_equipamento FOREIGN KEY (equipamento_id)
                                  REFERENCES tbl_equipamentos (equipamento_id),
                              CONSTRAINT fk_descarte_inspecao FOREIGN KEY (inspecao_id)
                                  REFERENCES tbl_inspecoes (inspecao_id),
                              CONSTRAINT fk_descarte_ponto_coleta FOREIGN KEY (ponto_coleta_id)
                                  REFERENCES tbl_pontos_coleta (ponto_coleta_id)
);


-- 6. TABELA: tbl_notificacoes (Entidade Notificacao.java)

CREATE TABLE tbl_notificacoes (
                                  notificacao_id       INTEGER DEFAULT SEQ_NOTIFICACOES.NEXTVAL NOT NULL,
                                  equipamento_id       INTEGER,
                                  ponto_coleta_id      INTEGER,
                                  descarte_id          INTEGER,
                                  tipo                 VARCHAR2(30)   NOT NULL,
                                  destinatario_email   VARCHAR2(254)  NOT NULL,
                                  assunto              VARCHAR2(200)  NOT NULL,
                                  mensagem             VARCHAR2(2000) NOT NULL,
                                  status_envio         VARCHAR2(20)   NOT NULL,
                                  data_criacao         DATE           DEFAULT CURRENT_DATE NOT NULL,
                                  CONSTRAINT pk_notificacao PRIMARY KEY (notificacao_id),
                                  CONSTRAINT fk_notif_equipamento FOREIGN KEY (equipamento_id)
                                      REFERENCES tbl_equipamentos (equipamento_id),
                                  CONSTRAINT fk_notif_ponto_coleta FOREIGN KEY (ponto_coleta_id)
                                      REFERENCES tbl_pontos_coleta (ponto_coleta_id),
                                  CONSTRAINT fk_notif_descarte FOREIGN KEY (descarte_id)
                                      REFERENCES tbl_descarte (descarte_id)
);