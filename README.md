# Começando
Para executar o projeto, será necessário instalar os seguintes programas:
- [JDK 8: Necessário para executar o projeto Java](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)
- [Maven 3.5.3: Necessário para realizar o build do projeto Java](http://mirror.nbtelecom.com.br/apache/maven/maven-3/3.5.3/binaries/apache-maven-3.5.3-bin.zip)

### Construção

Para construir o projeto com o Maven, executar os comando abaixo:

```shell
mvn clean install
```
O comando irá baixar todas as dependências do projeto e criar um diretório *target* com os artefatos construídos, que incluem o arquivo jar do projeto. Além disso, serão executados os testes unitários, e se algum falhar, o Maven exibirá essa informação no console.

## Testes

Para rodar os testes, utilize o comando abaixo:

```
mvn test
```

## Executando

Para executar o programa utilizando Maven, basta digitar o seguinte comando:

```
mvn spring-boot:run
```

## Execução via docker
```
git clone https://github.com/hickmann/Data-Analysis-System.git
cd Data-Analysis-System
docker-compose up
```

## Considerações

Inicialmente eu pensei em utilizar kafka + Spring Cloud Stream mas depois resolvi usar o approach clássico de mensageria e fui para o JMS activeMQ, acho que não seria necessário todas as usabilidades do Kafka e no final eu só utilizaria ele como uma mensageria e não como uma plataforma de streaming. Ao utilizar o Kafka eu conseguiria fazer uso das suas funções como DLQs que me ajudaríam a montar um esquema de retrys para os arquivos, mas escolhi até por motivos de demonstração criar o padrão Saga Pattern que é amplamente utilizado para controle de erros entre microsserviços.

O sistema basicamente consiste em duas partes, separadas logicamente dentro do projeto as quais facilmente poderiam virar dois microsserviços em uma evolução do projeto. A primeira parte orquestra os arquivos lidos para que não sejam processados mais de uma vez  utilizando Camel e uma Service de Idempotência criada e a segunda efetivamente processa os arquivos.

A Saga que eu criei tem quatro steps descritas no arquivo SagaStatusEnum.java, FILE_READ (O arquivo ja foi lido e esta aguardando para ser processado), FILE_PROCESSED (O arquivo já foi processado), COMPLETE (O arquivo foi processado e ja existe um arquivo equivalente na pasta out) e ERROR, que neste momento não faz nada, mas que facilmente poderia ser ou criado um retry, ou apresentado em tela que existe um erro e solicitar para o usuário a ação que ele gostaria de fazer.

Como o sistema foi dividido desta forma lógica a escalabilidade dele é simples, basta rodar mais de uma instância que ele escalará facilmente.