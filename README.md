# Personal Finance API

API REST para controle financeiro pessoal, criada para demonstrar fundamentos de backend Java com regras de negócio simples e organização profissional.

## Funcionalidades

- Cadastro e listagem de categorias.
- Cadastro, listagem, atualização e remoção de transações.
- Filtros por tipo e período.
- Resumo mensal com entradas, saídas e saldo.
- Validação entre tipo da categoria e tipo da transação.
- Documentação via Swagger UI.

## Tecnologias

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 Database
- PostgreSQL
- Maven
- Swagger/OpenAPI

## Como Executar

```bash
mvn spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

Documentação Swagger:

```text
http://localhost:8080/docs
```

## Perfil PostgreSQL

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=postgres
```

Variáveis:

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
  "name": "Salário",
  "type": "INCOME"
}
```

Criar transação:

```json
{
  "description": "Salário mensal",
  "amount": 3500.00,
  "type": "INCOME",
  "transactionDate": "2026-05-21",
  "categoryId": 1
}
```
