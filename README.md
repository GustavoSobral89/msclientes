# API de Gestão de Clientes

Esta é uma API RESTful para gerenciamento de clientes, permitindo criar, consultar, atualizar e excluir registros de clientes em um banco de dados MySQL. A API foi construída utilizando o Spring Boot, com foco em simplicidade e clareza.

## Funcionalidades

- **Criar Cliente**: Adiciona um novo cliente no sistema.
- **Consultar Cliente por ID**: Retorna os dados de um cliente pelo seu ID.
- **Listar Todos os Clientes**: Retorna uma lista com todos os clientes registrados.
- **Atualizar Cliente**: Atualiza os dados de um cliente já existente.
- **Excluir Cliente**: Remove um cliente do sistema pelo seu ID.

## Tecnologias

- **Spring Boot**: Framework principal para construção da API.
- **Spring Data JPA**: Para interação com o banco de dados.
- **MySQL**: Banco de dados utilizado para persistência dos dados.
- **Java 17**: Linguagem de programação utilizada.

## Pré-requisitos

- Java 17 ou superior.
- MySQL instalado e rodando.
- Maven para gerenciamento de dependências.

## Configuração

1. Clone o repositório para sua máquina local:

    ```bash
    git clone <URL_DO_REPOSITORIO>
    cd gestao-clientes
    ```

2. Configure as credenciais de banco de dados no arquivo `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/mydatabase?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    spring.datasource.username=myuser
    spring.datasource.password=password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

   Certifique-se de que o banco de dados MySQL esteja criado e acessível com as credenciais fornecidas.

3. Para compilar e rodar a aplicação, utilize o Maven:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

   A aplicação será iniciada na porta **8081**.

## Endpoints da API

A API possui os seguintes endpoints:

### 1. Criar um Cliente
- **Método**: `POST`
- **URL**: `/clientes`
- **Body**: Um objeto JSON com as informações do cliente.

Exemplo de corpo da requisição:

```json
{
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "endereco": "Rua A, 123"
}
```

2. Consultar um Cliente por ID

Método: GET

URL: /clientes/{id}
Parâmetro: id (ID do cliente)

Exemplo de resposta:

```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "endereco": "Rua A, 123"
}

```

3. Listar Todos os Clientes

   Método: GET

   URL: /clientes
   
Exemplo de resposta:
```json
{
[
  {
    "id": 1,
    "nome": "João Silva",
    "email": "joao.silva@email.com",
    "endereco": "Rua A, 123"
  },
  {
    "id": 2,
    "nome": "Maria Oliveira",
    "email": "maria.oliveira@email.com",
    "endereco": "Rua B, 456"
  }
]

}

```

4. Atualizar um Cliente

   Método: PUT

   URL: /clientes/{id}

   Parâmetro: id (ID do cliente)

   Body: Um objeto JSON com os dados atualizados do cliente.

   Exemplo de corpo da requisição:
```
   {
   "nome": "João Silva Atualizado",
   "email": "joao.silva.atualizado@email.com",
   "endereco": "Rua A, 789"
   }
```
5. Excluir um Cliente

   Método: DELETE

   URL: /clientes/{id}

   Parâmetro: id (ID do cliente a ser excluído)