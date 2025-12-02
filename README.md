# Chef Manager API - Sistema de Gestão Gastronômica Compartilhada

## 📋 Sobre o Projeto

Chef Manager API é um sistema de gestão gastronômica desenvolvido como parte do Tech Challenge da Fase 2. O projeto implementa uma API RESTful para gerenciamento de restaurantes, permitindo o cadastro de estabelecimentos, gestão de cardápios e diferentes tipos de usuários.

---

## 🏗️ Arquitetura

O projeto utiliza Clean Architecture para garantir uma estrutura modular e de fácil manutenção:

```
src/
    ├── application/                  # Lógica de negócio e casos de uso
    ├── domain/                       # Entidades e regras de negócio puras
    └── infrastructure/               # Implementações de repositórios e serviços externos
```

### Principais Tecnologias

- Java 25
- Spring Boot 4.0.0
- Spring Data JPA
- PostgreSQL
- Docker & Docker Compose
- OpenAPI/Swagger
- Lombok
- MapStruct

---

## 🚀 Como Executar

### Pré-requisitos

- Docker e Docker Compose
- Java 25 (para desenvolvimento)
- Git

### Passos para Execução

#### 1. Clone o repositório:
```bash
git clone git@github.com:10ADJT-Tech-Challenge/chef-manager-api.git
cd chef-manager-api
```
#### 2. Configure as variáveis de ambiente:
```bash
cp .env.example .env
``` 
Edite o arquivo .env com suas configurações
####3. Inicie os serviços com Docker Compose:
```bash
docker-compose up --build
```

---

## 📚 Documentação da API

A documentação completa da API está disponível através do Swagger UI:
`http://localhost:8080/swagger-ui.html`

### 🔐 Autenticação
- **POST** `/api/v1/auth/login` - Autenticação de usuário

### 👥 Usuários
- **POST** `/api/v1/usuarios` - Cadastro de usuário
- **GET** `/api/v1/usuarios?nome={nome}` - Busca usuário por nome
- **GET** `/api/v1/usuarios/{id}` - Busca usuário por ID
- **PUT** `/api/v1/usuarios/{id}` - Atualiza dados do usuário
- **DELETE** `/api/v1/usuarios/{id}` - Remove usuário
- **PATCH** `/api/v1/usuarios/{id}/senha` - Altera senha
- **PATCH** `/api/v1/usuarios/{id}/tipo` - Atribui tipo ao usuário

### 🏷️ Tipos de Usuário
- **POST** `/api/v1/tipos-usuario` - Cadastra novo tipo
- **GET** `/api/v1/tipos-usuario` - Lista todos os tipos
- **GET** `/api/v1/tipos-usuario/{id}` - Busca tipo por ID
- **PUT** `/api/v1/tipos-usuario/{id}` - Atualiza tipo
- **DELETE** `/api/v1/tipos-usuario/{id}` - Remove tipo

### 🍽️ Restaurantes
- **POST** `/api/v1/restaurantes` - Cadastra restaurante
- **GET** `/api/v1/restaurantes` - Lista restaurantes
- **GET** `/api/v1/restaurantes/{id}` - Busca restaurante por ID
- **PUT** `/api/v1/restaurantes/{id}` - Atualiza restaurante
- **DELETE** `/api/v1/restaurantes/{id}` - Remove restaurante

### 📋 Cardápio
- **POST** `/api/v1/itens-cardapio/{idRestaurante}` - Adiciona item ao cardápio
- **GET** `/api/v1/itens-cardapio/{idRestaurante}` - Lista itens do cardápio
- **GET** `/api/v1/itens-cardapio/{id}` - Busca item por ID
- **PUT** `/api/v1/itens-cardapio/{id}` - Atualiza item
- **DELETE** `/api/v1/itens-cardapio/{id}` - Remove item

**Base URL**: `http://localhost:8080/api/v1`

**Nota**: Todos os IDs são no formato UUID.

---

## 🧪 Testes

O projeto inclui testes unitários e de integração. 
Para executar os testes:
```bash 
./gradlew test 
```

Para gerar relatório de cobertura de testes:

**TODO: implementar cobertura de testes e relatório com Jacoco**
```bash 
./gradlew jacocoTestReport
```
O relatório será gerado em: `build/reports/jacoco/test/html/index.html`

---

## 📝 Postman Collection

A collection do Postman com todos os endpoints está disponível em:
`/docs/ChefManager.postman_collection.json`

**TODO: incluir a collection**

---

## 🎥 Vídeo de Apresentação

[Link para o vídeo de apresentação do projeto]

**TODO: criar e incluir o vídeo**

---

## 🧱 Licença
Este projeto é de uso educacional, desenvolvido como parte de um estudo de **Tech Challenge 02 do curso de Pós-Graduação de Arquitetura de software com Java, na FIAP**.

