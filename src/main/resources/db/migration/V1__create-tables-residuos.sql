CREATE SEQUENCE SEQ_PONTO_COLETA
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE SEQ_EQUIPAMENTO
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE SEQ_INSPECAO
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE SEQ_DESCARTE
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE SEQ_NOTIFICACAO
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE TB_PONTO_COLETA (
                                 ID_PONTO_COLETA    INTEGER DEFAULT SEQ_PONTO_COLETA.NEXTVAL NOT NULL,
                                 NOME               VARCHAR2(150) NOT NULL,
                                 TIPO               VARCHAR2(30)  NOT NULL,
                                 RESIDUO_ACEITO     VARCHAR2(30)  NOT NULL,
                                 CIDADE             VARCHAR2(100) NOT NULL,
                                 UF                 CHAR(2)       NOT NULL,
                                 EMAIL_CONTATO      VARCHAR2(254) NOT NULL,
                                 CAPACIDADE_KG      NUMBER(10,3)  NOT NULL,
                                 PESO_ACUMULADO_KG  NUMBER(10,3)  DEFAULT 0 NOT NULL,
                                 LIMITE_ALERTA_PCT  NUMBER(3)     NOT NULL,
                                 STATUS             VARCHAR2(20)  NOT NULL,
                                 CONSTRAINT PK_PONTO_COLETA PRIMARY KEY (ID_PONTO_COLETA)
);

CREATE TABLE TB_EQUIPAMENTO (
                                ID_EQUIPAMENTO     INTEGER DEFAULT SEQ_EQUIPAMENTO.NEXTVAL NOT NULL,
                                NOME               VARCHAR2(150) NOT NULL,
                                CATEGORIA          VARCHAR2(30)  NOT NULL,
                                MARCA              VARCHAR2(100) NOT NULL,
                                MODELO             VARCHAR2(100) NOT NULL,
                                PESO_KG            NUMBER(8,3)   NOT NULL,
                                ESTADO_CONSERVACAO VARCHAR2(30)  NOT NULL,
                                STATUS             VARCHAR2(30)  NOT NULL,
                                NOME_PROPRIETARIO  VARCHAR2(150) NOT NULL,
                                EMAIL_PROPRIETARIO VARCHAR2(254) NOT NULL,
                                DATA_CADASTRO      TIMESTAMP     DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                CONSTRAINT PK_EQUIPAMENTO PRIMARY KEY (ID_EQUIPAMENTO)
);

CREATE TABLE TB_INSPECAO (
                             ID_INSPECAO        INTEGER DEFAULT SEQ_INSPECAO.NEXTVAL NOT NULL,
                             ID_EQUIPAMENTO     INTEGER       NOT NULL,
                             DATA_INSPECAO      TIMESTAMP     DEFAULT CURRENT_TIMESTAMP NOT NULL,
                             RESPONSAVEL        VARCHAR2(150) NOT NULL,
                             RESULTADO          VARCHAR2(30)  NOT NULL,
                             POSSUI_BATERIA     CHAR(1)       NOT NULL,
                             DESCRICAO_AVARIAS  VARCHAR2(1000),
                             CONSTRAINT PK_INSPECAO PRIMARY KEY (ID_INSPECAO),
                             CONSTRAINT FK_INSPECAO_EQUIPAMENTO FOREIGN KEY (ID_EQUIPAMENTO)
                                 REFERENCES TB_EQUIPAMENTO (ID_EQUIPAMENTO),
                             CONSTRAINT CHK_INSPECAO_BATERIA CHECK (POSSUI_BATERIA IN ('S', 'N', 'Y', 'T', 'F', '0', '1'))
);

CREATE TABLE TB_DESCARTE (
                             ID_DESCARTE        INTEGER DEFAULT SEQ_DESCARTE.NEXTVAL NOT NULL,
                             ID_EQUIPAMENTO     INTEGER       NOT NULL,
                             ID_INSPECAO        INTEGER       NOT NULL,
                             ID_PONTO_COLETA    INTEGER       NOT NULL,
                             DESTINACAO         VARCHAR2(30)  NOT NULL,
                             PESO_KG            NUMBER(8,3)   NOT NULL,
                             STATUS             VARCHAR2(20)  NOT NULL,
                             NUMERO_CERTIFICADO VARCHAR2(50),
                             DATA_REGISTRO      TIMESTAMP     DEFAULT CURRENT_TIMESTAMP NOT NULL,
                             DATA_CONCLUSAO     TIMESTAMP,
                             CONSTRAINT PK_DESCARTE PRIMARY KEY (ID_DESCARTE),
                             CONSTRAINT UK_DESCARTE_EQUIPAMENTO UNIQUE (ID_EQUIPAMENTO),
                             CONSTRAINT FK_DESCARTE_EQUIPAMENTO FOREIGN KEY (ID_EQUIPAMENTO)
                                 REFERENCES TB_EQUIPAMENTO (ID_EQUIPAMENTO),
                             CONSTRAINT FK_DESCARTE_INSPECAO FOREIGN KEY (ID_INSPECAO)
                                 REFERENCES TB_INSPECAO (ID_INSPECAO),
                             CONSTRAINT FK_DESCARTE_PONTO_COLETA FOREIGN KEY (ID_PONTO_COLETA)
                                 REFERENCES TB_PONTO_COLETA (ID_PONTO_COLETA)
);