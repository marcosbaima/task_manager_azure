# Azure Functions Project - Task Manager

Este projeto implementa um conjunto de Azure Functions para gerenciar tarefas. Ele utiliza Spring Cloud Function para abstração de funções em Java e integração com Azure Functions.

## Estrutura do Projeto

- **`src/main/java`**: Contém o código fonte das funções.
- **`META-INF/MANIFEST.MF`**: Arquivo de metadados que define a classe inicial.
- **`pom.xml`**: Gerencia as dependências e plugins Maven.
- **`local.settings.json`**: Configurações locais para executar as funções.

## Requisitos

1. **Java 17** ou superior.
2. **Maven** instalado.
3. **Azure Functions Core Tools** para executar as funções localmente.
4. **VS Code** ou outro editor de texto/IDE com suporte a Java.

## Passo a Passo para Configuração e Execução

### 1. Clone o Repositório
```bash
git clone <URL_DO_REPOSITORIO>
cd <NOME_DO_PROJETO>
```

### 2. Configure o Ambiente Local

Crie o arquivo `local.settings.json` na raiz do projeto com o seguinte conteúdo:

```json
{
  "IsEncrypted": false,
  "Values": {
    "AzureWebJobsStorage": "UseDevelopmentStorage=true",
    "FUNCTIONS_WORKER_RUNTIME": "java"
  }
}
```

### 3. Compile o Projeto

Use o Maven para compilar o projeto e criar o arquivo JAR:
```bash
mvn clean package
```

### 4. Verifique o Arquivo `MANIFEST.MF`

Certifique-se de que o arquivo `META-INF/MANIFEST.MF` contém a propriedade `Start-Class` com o valor correto:

```
Start-Class: com.example.DemoApplication
```

Se for diferente, atualize no `pom.xml`:
```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-jar-plugin</artifactId>
  <configuration>
    <archive>
      <manifest>
        <mainClass>com.example.DemoApplication</mainClass>
      </manifest>
    </archive>
  </configuration>
</plugin>
```

### 5. Inicie o Emulador do Azure Storage

Certifique-se de que o emulador de storage está ativo para testes locais:
```bash
azurite
```

### 6. Execute Localmente as Funções

Inicie o Azure Functions Core Tools para executar localmente:
```bash
func start
```

### 7. Teste as Funções

Use o Postman ou outra ferramenta para enviar requests HTTP para as funções. Exemplos:

#### Endpoint: Criar uma Tarefa
- **URL**: `http://localhost:7071/api/createTask`
- **Método**: POST
- **Body (JSON)**:
```json
{
  "title": "Nova tarefa",
  "description": "Detalhes da tarefa",
  "dueDate": "2025-01-15"
}
```

### 8. Publicação no Azure (Opcional)

Autentique no Azure CLI:
```bash
az login
```

Publique as funções no Azure:
```bash
func azure functionapp publish <NOME_DA_FUNCAO>
```

## Resolução de Problemas Comuns

### Erro: `Failed to locate main class`
- Verifique o valor de `Start-Class` no `MANIFEST.MF`.
- Confirme que a classe indicada no `Start-Class` contém o método `main`.

### Erro: `Emulador de Storage não encontrado`
- Certifique-se de que o Azurite está instalado e em execução.

### Erro: `java.lang.IllegalStateException`
- Verifique se todas as dependências foram instaladas corretamente com `mvn dependency:resolve`.

## Referências

- [Documentação do Azure Functions](https://learn.microsoft.com/azure/azure-functions/)
- [Spring Cloud Function](https://spring.io/projects/spring-cloud-function)

---

Pronto! Agora você pode configurar e executar seu projeto de Azure Functions para gerenciar tarefas.

