# FIAP – Desafio Testes Automatizados

Repositório: [fernandodamaso/fiap‑desafio‑testes‑automatizados](https://github.com/fernandodamaso/fiap-desafio-testes-automatizados?utm_source=chatgpt.com)

## 🎯 Visão Geral

Este projeto consiste em uma aplicação backend em Java, com foco em **testes automatizados** (BDD + API) e em infraestrutura via Docker.

O objetivo do desafio é demonstrar a construção de testes de contrato, integração e aceitação, bem como a configuração de containerização do serviço.

## 🚀 Funcionalidades Principais

- API REST para gestão de tipo de resíduos (cadastro, listagem, deleção)
- Testes comportamentais escritos com Gherkin/Cucumber para validar requisitos de negócio
- Validação de resposta de API contra esquemas JSON (contratos)
- Containerização da aplicação com Docker, facilitando execução isolada

## 🧪 Testes Automatizados

### Cenários principais

- **Cadastro de tipo de resíduo bem‑sucedido**: validado status 201, corpo conforme contrato JSON.
- Testes de limpeza de dados após execução usando hooks `@After`, garantindo ambiente limpo para cada cenário.
- Uso de biblioteca de validação de esquema JSON (`com.networknt.schema`) para garantir que a resposta da API *“está em conformidade com o contrato”*.
- Base de testes estruturada em Camadas: Steps (Gherkin), Service de chamada de API, DTOs, contração de schema.

### Estrutura de testes

- Feature files localizadas em `src/test/resources/features/`
- Esquemas JSON em `src/test/resources/schemas/`
- Steps e Services em `src/test/java/org/example/projetoesgfiap/steps` e `.../service`
- Integração com `io.restassured` para requisições HTTP, `cucumber-java` para BDD.

### Executando os testes localmente

```bash
# No diretório raiz do projeto:
mvn clean test

```

Os testes irão rodar e validar:

- se o endpoint responde com código esperado
- se os dados da resposta atendem ao esquema JSON definido
- se ocorre limpeza de dados após cada cenário para evitar interferência entre execuções

## 🐳 Docker & Containerização

O projeto inclui um arquivo `Dockerfile` na raiz do repositório, que permite criar uma imagem Docker da aplicação.

### Principais pontos

- Build da aplicação usando Maven (`mvn package`) e criação de JAR executável
- Imagem baseada em `openjdk:<versão>` para execução
- Exposição da porta padrão da API (ex: 8080)
- Execução encapsulada da aplicação pronta para produção ou homologação

### Como construir e executar com Docker

```bash
# No diretório raiz:
docker build -t projeto‑esg‑fiap:latest .

# Executar o container:
docker run -d -p 8080:8080 projeto‑esg‑fiap:latest

```

Depois disso, a API estará disponível em `http://localhost:8080`, e você pode rodar os testes ou integrar com outras ferramentas.

## 📁 Estrutura do Repositório

```
├── Dockerfile
├── mvnw, mvnw.cmd
├── pom.xml
├── src/
│   ├── main/
│   │   └── java/org/example/projetoesgfiap/…
│   └── test/
│       ├── java/org/example/projetoesgfiap/steps/
│       ├── java/org/example/projetoesgfiap/service/
│       └── resources/
│           ├── features/
│           └── schemas/
└── README.md

```

## 🛠️ Pré‑requisitos

- JDK 21+
- Maven 3.6+
- Docker (opcional, para executar via container)
- Ambiente de banco de dados configurado conforme `application.properties` (ou perfil local ajustado)

## ✅ Boas Práticas Neste Projeto

- Cada cenário BDD foca em um fluxo de valor claro, com pré‑condição, ação e verificação
- Uso de DTOs para separar camada de domínio da API
- Limpeza de dados após cada execução para manter idempotência dos testes
- Validação de contrato JSON para evitar regressões nos contratos de API
- Containerização permite replicar ambiente de forma consistente

## 👣 Próximos Passos / Evoluções Possíveis

- Adição de testes negativos / casos de erro
- Cobertura de endpoints adicionais (listagem, atualização, deleção em massa)
- Integração com pipeline CI/CD e relatórios de cobertura
- Suporte a múltiplos perfis de banco (ex: H2 in memory para testes)
- Monitoramento e métricas expostas via Actuator (se aplicável)

## 🎉 Conclusão

Este projeto demonstra uma abordagem moderna de desenvolvimento orientado a testes em Java, utilizando BDD, testes de contrato e Docker para entrega consistente. A estrutura permite fácil manutenção, extensão e integração em pipelines de CI / CD.
