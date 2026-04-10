# \# Desafio Edson

# Aplicação Angular + Spring  para listar benefícios e realizar transferências.



\## Como executar

\### Backend

\- `mvn spring-boot:run`



\### Frontend

\- `npm install`

\- `ng serve`



\## Funcionalidades

\- Listagem de benefícios

\- Transferência entre benefícios

\- Validação de formulário no frontend

\- Tratamento de erros com mensagens claras



\## Endpoints

\- `GET /backend-module/api/beneficios` → lista todos os benefícios

\- `POST /backend-module/api/beneficios/transfer` → realiza transferência



\## Requisitos



Para executar este projeto, é necessário ter instalado:



\- \*\*Java JDK 23\*\*

\- \*\*WildFly 39.1\*\* (servidor de aplicação)

\- \*\*Maven 3.9+\*\* (para build do backend)

\- \*\*Node.js 20+\*\* e \*\*Angular CLI\*\* (para o frontend)



\## Configuração do servidor WildFly



1\. Baixe e instale o WildFly 39.1.

2\. Configure o standalone.xml para incluir o datasource do projeto (se necessário).

3\. Faça o deploy do arquivo `.war` gerado pelo backend:

&#x20;  ```bash

&#x20;  mvn clean package

&#x20;  cp target/backend-module.war $WILDFLY\_HOME/standalone/deployments/


## Configuração do Banco de Dados (MariaDB)

1. Instale o MariaDB.
2. Crie o banco de dados:
3. Utilize o sql da pasta db

