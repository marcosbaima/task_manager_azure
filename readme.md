# Azure Functions Project - Task Manager

Este projeto implementa um conjunto de Azure Functions para gerenciar tarefas. Ele utiliza o **Spring Cloud Function** para abstração das funções em Java, proporcionando uma integração eficiente com o Azure Functions e uma experiência de desenvolvimento otimizada. Além disso, o projeto é baseado em uma arquitetura serverless, permitindo escalabilidade automática e baixo custo operacional.

## Principais Recursos

- **Gerenciamento de Tarefas**: Cria, atualiza e deleta tarefas com endpoints HTTP.
- **Integração com o Azure**: Utiliza serviços como Azure Storage para persistência de dados.
- **Portabilidade**: Baseado no Spring Cloud Function, facilitando a execução local e em outros provedores cloud.
- **Configuração Simples**: Gerenciada por arquivos de configuração JSON e Maven.

## Estrutura do Projeto

- **`src/main/java`**: Contém o código-fonte das funções.
- **`META-INF/MANIFEST.MF`**: Arquivo de metadados que define a classe inicial.
- **`pom.xml`**: Gerencia dependências e plugins Maven.
- **`local.settings.json`**: Configurações locais para execução.

## Requisitos

1. **Java 17** (ou superior): Certifique-se de que o Java 17 está instalado e configurado como padrão. Utilize a distribuição LTS para maior estabilidade.
2. **Maven**: Gerenciador de dependências para compilar e executar o projeto.
3. **Azure Functions Core Tools**: Necessário para testar as funções localmente.
4. **VS Code** (ou IDE de sua preferência): Recomenda-se o uso da extensão para Azure Functions.

## Passo a Passo para Configuração e Execução

### 1. Clone o Repositório

```bash
git clone <URL_DO_REPOSITORIO>
cd <NOME_DO_PROJETO>
```
### Observação Adicional

Certifique-se de configurar as credenciais para o banco de dados **MS SQL Server Express 2022** no arquivo `application.properties` do projeto. Inclua as seguintes propriedades:

```
spring.datasource.url=jdbc:sqlserver://<HOST>:<PORT>;databaseName=<DATABASE>
spring.datasource.username=<USERNAME>
spring.datasource.password=<PASSWORD>
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
```
### 2. Compile o Projeto

Use o Maven para compilar o projeto:

```bash
mvn clean package
```

- **`mvn clean package`**: Remove arquivos temporários, compila o código-fonte e empacota o projeto em um JAR para execução.
- **`mvn clean install`**: Além de empacotar o projeto, instala o artefato no repositório Maven local para ser utilizado em outros projetos.

### 3. Execute Localmente as Funções

Execute o projeto localmente utilizando o Maven:

```bash
mvn azure-functions:run
```

### 4. Teste as Funções

Utilize o Postman ou outra ferramenta para enviar requisições HTTP aos endpoints. Exemplo:

#### Endpoint: Criar uma Tarefa

- **URL**: `http://localhost:7071/api/createTask`
- **Método**: POST
- **Body (JSON)**:

```json
{
  "name": "Estudar cloud azure",
  "description": "Aprender a configurar functions",
  "priority": "High",
  "status": "PENDING"
}
```

Uma **collection do Postman** está disponível na raiz do projeto para facilitar os testes dos endpoints. Importe a collection no Postman e ajuste as variáveis de ambiente conforme necessário para testar as funções rapidamente.

### 5. Publicação no Azure

A publicação no Azure será realizada automaticamente por meio de um pipeline CI/CD configurado com GitHub Workflows. O arquivo de configuração do pipeline está localizado no seguinte caminho dentro do repositório:

```
.github/workflows/<nome-do-arquivo>.yml
```

Para mais informações sobre a configuração de Workflows no GitHub, consulte a [documentação oficial](https://docs.github.com/actions).

## Observações Importantes

1. **Java 17**:

   - Garanta que a versão 17 está instalada. Versões anteriores não são suportadas devido a mudanças na plataforma.
   - Utilize distribuições como OpenJDK ou Amazon Corretto para compatibilidade com ambientes corporativos.

2. **Versão do Maven**:

   - Recomendado utilizar a versão 3.6 ou superior para suporte a recursos modernos.

3. **Configurações Locais**:

   - O arquivo `local.settings.json` não deve ser enviado ao repositório para evitar expor credenciais.

## Resolução de Problemas Comuns

### Erro: `Failed to locate main class`

- Verifique o valor de `Start-Class` no `MANIFEST.MF`.
- Confirme que a classe indicada no `Start-Class` contém o método `main`.

### Erro: `Emulador de Storage não encontrado`

- Certifique-se de que o Azurite está instalado e em execução.

### Erro: `java.lang.IllegalStateException`

- Verifique se todas as dependências foram instaladas corretamente com:

```bash
mvn dependency:resolve
```

## Referências

- [Documentação do Azure Functions](https://learn.microsoft.com/azure/azure-functions/)
- [Spring Cloud Function](https://spring.io/projects/spring-cloud-function)

