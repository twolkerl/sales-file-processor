# Sales File Processor

Sistema de análise de dados de venda capaz de ler arquivos e montar um relatório com os dados contidos para cada arquivo.

### Tecnologias utilizadas
Java 8, SpringBoot e MongoDB

Instruções para instalação do **MongoDB**: [https://docs.mongodb.com/manual/installation/](https://docs.mongodb.com/manual/installation/)

## Funcionalidades e instruções
### Tipos de dados
#### Dados do vendedor
Os dados do vendedor possuem o identificador **001** e seguem o seguinte formato:
> 001çCPFçNameçSalary

#### Dados do cliente
Os dados do cliente possuem o identificador **002** e seguem o seguinte formato:
> 002çCNPJçNameçBusiness Area

#### Dados de venda
Os dados de venda possuem o identificador **003** e seguem o seguinte formato:
> 003çSale IDç[Item ID-Item Quantity-Item003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name

### Arquivos e conteúdos utilizados para teste:

---
#### teste1.txt
001ç1234567891234çPedroç50000

001ç3245678865434çPauloç40000.99

002ç2345675434544345çJose da SilvaçRural

002ç2345675433444345çEduardo PereiraçRural

003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro

003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo

---
#### teste2.txt
001ç1234567891234çPedroç50000

001ç3245678865434çPauloç40000.99

001ç3245678865433çJoaoç40000

001ç3245678865435çJurandirç3000

002ç2345675434544345çJose da SilvaçRural

002ç2345675433444345çEduardo PereiraçRural

002ç2345675433444555çMaria SouzaçUrbano

002ç2345675433333555çEnzo SouzaçUrbano

003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro

003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo

003ç08ç[1-34-10,2-33-1.50,3-40-1000.50]çJoao

003ç08ç[1-32-6,2-33-0.50,3-40-0.5]çJurandir

---

*Obs.: O leitor de arquivos foi configurado para aceitar apenas extensões '.txt' e charset UTF-8*

O sistema lê continuamente todos os novos arquivo inseridos no diretório:
> data/in

Ao identificar um novo arquivo, o sistema irá processar o mesmo, gerando um relatório contendo as seguintes informações:
1. Quantidade de clientes no arquivo de entrada
2. Quantidade de vendedores no arquivo de entrada
3. ID da venda mais cara
4. O pior vendedor

Estes relatórios serão salvos no diretório:
> data/out

Para cada arquivo, o sistema armazena no MongoDB temporariamente os dados informados para no final gerar o relatório do arquivo lido. Após processado o arquivo, o sistema realizará a limpeza da base de dados e começará o processo novamente para o próximo arquivo, assim gerando um relatório para cada.