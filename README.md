# Personal Finance API

API REST para controle financeiro pessoal, criada para demonstrar fundamentos de backend Java com regras de negocio, persistencia e organizacao profissional.

## Funcionalidades

- Cadastro e listagem de categorias.
- Cadastro, listagem, atualizacao e remocao de transacoes.
- Filtros por tipo e periodo.
- Resumo mensal com entradas, saidas e saldo.
- Validacao entre tipo da categoria e tipo da transacao.
- Documentacao via Swagger UI.
- Execucao com H2 local ou PostgreSQL via Docker.

## Tecnologias

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 Database
- PostgreSQL
- Docker e Docker Compose
- Maven
- Swagger/OpenAPI

## Como Executar Localmente

```bash
mvn clean package
java -jar target/personal-finance-api-0.0.1-SNAPSHOT.jar
```

Swagger:

```text
http://localhost:8080/docs
```

## Como Executar com Docker

```bash
cp .env.example .env
docker compose up --build
```

API:

```text
http://localhost:8080
```

## Perfil PostgreSQL sem Docker

```bash
mvn clean package
java -jar target/personal-finance-api-0.0.1-SNAPSHOT.jar --spring.profiles.active=postgres
```

Variaveis:

```text
DB_URL=jdbc:postgresql://localhost:5432/personal_finance
DB_USER=postgres
DB_PASSWORD=sua_senha
```

## Endpoints Principais

```http
POST /api/categories
GET /api/categories
POST /api/transactions
GET /api/transactions
PUT /api/transactions/{id}
DELETE /api/transactions/{id}
GET /api/transactions/summary?year=2026&month=5
```

## Exemplos

Criar categoria:

```json
{
  "name": "Salario",
  "type": "INCOME"
}
```

Criar transacao:

```json
{
  "description": "Salario mensal",
  "amount": 3500.00,
  "type": "INCOME",
  "transactionDate": "2026-05-21",
  "categoryId": 1
}
```
