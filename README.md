<a name="readme-top"></a>

# Introdução

O projeto em questão é uma aplicação de gerenciamento de vídeos, e apresenta uma solução para atender às necessidades de upload, armazenamento e download de vídeos de maneira eficaz e intuitiva.


## Sumário
* [Instruções](#instruções)
* [Registro de Condutores](#registro-de-condutores)
* [Registro de Veículo](#registro-de-veículo)
* [Registro de Estacionamento](#registro-de-estacionamento)
* [Notificação](#notificação)
* [Tarifa](#tarifa)
* [Tecnologias](#tecnologias)
* [Desafios](#desafios)


## Instruções

- Maven: Para build do projeto. **Para buildar:** mvn clean install
- Foi utilizado Lombok, Validation e MongoDB, portanto é necessário adicionar os plugins na IDE

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------

<a name="funcionalidades-do-projeto"></a>
## 🔨  Funcionalidades do projeto

### Registro de Condutores

>[ Base URL: http://localhost:8080 ]

O registro de condutores permite que os usuários se cadastrem no sistema, fornecendo informações pessoais como nome, endereço e detalhes de contato.

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``POST``  
`*Para cadastro de Condutor`

```
	/condutores
```
<details>
  <summary>Exemplo Request:</summary>

```
curl --location 'http://localhost:8080/usuarios' \
--header 'Content-Type: application/json' \
--data '{
    "nome": "Pedro Gonçalves Nunes",
    "cpf": "041.276.747-33",
    "email": "pedro_nunes@gmail.com",
    "telefone": "(98)99764-0567",
    "endereco": {
        "rua": "Avenida Viana Vaz",
        "numero": "914",
        "bairro": "Centro",
        "cidade": "Timon",
        "estado": "MA",
        "cep": "65630-150"
    }
}'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

201 - _Created_
`- Será retornado o id do condutor`

```
6544027c1d769121eb36feb1
```

400 - _Bad Request_

```
{
    "code": "argumentNotValid",
    "message": "cpf:número do registro de contribuinte individual brasileiro (CPF) inválido;"
}
```

422 - _Unprocessable Entity_
`- Caso o CPF já esteja cadastrado`

```
{
    "code": "sgp.cpfJaCadastrado",
    "message": "CPF já cadastrado"
}
```

</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``GET``

```
	/condutores/{CPF}
```

<details>
  <summary>Exemplo Request:</summary>

```
curl --location 'http://localhost:8080/condutores/04127674733'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_
`- Será retornado o usuário`

```
{
    "id": "6544027c1d769121eb36feb1",
    "nome": "Pedro Gonçalves Nunes",
    "cpf": "04127674733",
    "email": "pedro_nunes@gmail.com",
    "telefone": "(98)99764-0567",
    "endereco": {
        "rua": "Avenida Viana Vaz",
        "numero": "914",
        "bairro": "Centro",
        "cidade": "Timon",
        "estado": "MA",
        "cep": "65630-150"
    }
}
```

404 - _Not Found_

```
{
    "code": "sgp.condutorNaoEncontrado",
    "message": "Condutor não encontrado"
}
```
</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``PUT``
`*Para alteração de dados do Condutor`

```
	/condutores/{id}
```

<details>
  <summary>Exemplo Request:</summary>

```
curl --location --request PUT 'http://localhost:8080/condutores/6544027c1d769121eb36feb1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome": "Pedro Alves Nunes",
    "cpf": "04127674733",
    "email": "pedro_nunes@nunes.com",
    "telefone": "(98)99764-0567",
    "endereco": {
        "rua": "Rua Viana Vaz",
        "numero": "15",
        "bairro": "Centro",
        "cidade": "Timon",
        "estado": "MA",
        "cep": "65630-150"
    }
}'
```
</details>
<details>
  <summary>Exemplo Responses:</summary>

204 - _No Content_

```
```
404 - _Not Found_  

```
{
    "code": "sgp.condutorNaoEncontrado",
    "message": "Condutor não encontrado"
}
```
</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``DELETE``
`*Para excluir Condutor`

```
	/condutores/{id}
```

<details>
  <summary>Exemplo Request:</summary>

```
curl --location --request DELETE 'http://localhost:8080/condutores/654404c222882b466fed9e00'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

204 - _No Content_

```
```
404 - _Not Found_  

```
{
    "code": "sgp.condutorNaoEncontrado",
    "message": "Condutor não encontrado"
}
```
</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------
### Registro de Veículo

>[ Base URL: http://localhost:8080 ]


A funcionalidade de registro de veículos permite que o condutor após o seu cadastro, inclua informações sobre seu(s) veículo(s). Essa etapa possibilita inclusão de detalhes de um ou vários veículos que o condutor possua ou opere.

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``POST``
`*Para cadastro de Veículo`

```
	/veiculos
```
<details>
  <summary>Exemplo Request Body:</summary>

```
curl --location 'http://localhost:8080/veiculos' \
--header 'Content-Type: application/json' \
--data '{
    "marca": "VW - VolksWagen",
    "modelo": "Gol Rallye 1.6 T. Flex 16V 5p",
    "placa": "MUU-2202",
    "condutor": {
        "id": "6544027c1d769121eb36feb1"
    }
}'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

201 - _Created_
`- Será retornado o id do veículo`

```
65441af928166b3360397af4
```
400 - _Bad Request

```
{
    "code": "argumentNotValid",
    "message": "placa:não deve estar em branco;"
}
```
</details>
<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``PUT``
`*Para alteração de dados do Veículo`


```
	veiculos/{idVeiculo}

```

<details>
  <summary>Exemplo Request Body:</summary>

```
curl --location --request PUT 'http://localhost:8080/veiculos/65441af928166b3360397af4' \
--header 'Content-Type: application/json' \
--data '{
    "marca": "VW - VolksWagen",
    "modelo": "Gol Rallye 1.6 T. Flex 16V 5p",
    "placa": "NEV-1252"
}'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

204 - _No Content_

```
```

404 - _Not Found_  

```
{
    "code": "sgp.veiculoNaoEncontrado",
    "message": "Veiculo não encontrado"
}
```
</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``GET``
```
	veiculos/{idVeiculo}
```
<details>
  <summary>Exemplo Request:</summary>
  
```
curl --location 'http://localhost:8080/veiculos/65441af928166b3360397af4'
```

</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_

```
{
    "id": "65441af928166b3360397af4",
    "marca": "VW - VolksWagen",
    "modelo": "Gol Rallye 1.6 T. Flex 16V 5p",
    "placa": "NEV-1252",
    "condutor": {
        "id": "6544027c1d769121eb36feb1",
        "nome": "Pedro Alves Nunes",
        "cpf": "04127674733",
        "email": "pedro_nunes@nunes.com",
        "telefone": "(98)99764-0567",
        "endereco": {
            "rua": "Rua Viana Vaz",
            "numero": "15",
            "bairro": "Centro",
            "cidade": "Timon",
            "estado": "MA",
            "cep": "65630-150"
        }
    }
}
```

404 - _Not Found_
`- Caso o veículo não exista.`

```
{
    "code": "sgp.veiculoNaoEncontrado",
    "message": "Veiculo não encontrado"
}
```

</details>


<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``DELETE``

```
	veiculos/{idVeiculo}
```

<details>
  <summary>Exemplo Request:</summary>

```
curl --location --request DELETE 'http://localhost:8080/veiculos/65440b88a6a7c64b08bd79b9'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

204 - _No Content_

```
```
404 - _Not Found_  
`- Caso o veículo não exista.`

```
{
    "code": "sgp.veiculoNaoEncontrado",
    "message": "Veiculo não encontrado"
}
```

</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------
### Registro de Estacionamento

>[ Base URL: http://localhost:8080 ]

Ao realizar o check-in no estacionamento, os condutores têm a opção de escolher entre dois tipos de cobrança, adaptando-se às suas necessidades:

1 - **Tempo Fixo:** Neste modelo, o estabelecimento determina um valor para um período específico, por exemplo, 4 horas por R$15,00. <br/> Independentemente do tempo real utilizado, o valor total correspondente ao período selecionado será cobrado.

2 - **Tempo Variável:** Aqui, a cobrança é feita por hora completa, por exemplo, R$5,00 por hora. <br/>	É importante notar que o valor não é fracionado caso o condutor não utilize uma hora completa.

Quando o condutor realiza o check-out, o SGP (Sistema de Gestão de Parquímetro) inicia o procedimento de pagamento enviando uma solicitação para um sistema de pagamento externo, como o Mercado Pago, por exemplo. O Mercado Pago responde ao SGP com um ID de solicitação de pagamento, o qual é armazenado no banco de dados.

Neste momento, a tela de check-out redireciona o usuário para a interface do Mercado Pago, permitindo que ele efetue o pagamento. O condutor pode escolher a forma de pagamento que melhor lhe convier: cartão de crédito, cartão de débito ou PIX. <br/>
Enquanto o Mercado Pago processa o pagamento, o condutor visualiza a tela de "processando pagamento", aguardando a conclusão da transação.

Durante esse processo, o frontend realiza automaticamente requisições ao SGP, enviando o ID do Registro de Estacionamento a intervalos regulares. Isso continua enquanto o status da transação estiver marcado como "Aguardando Pagamento", ou até serem feitas um número específico de tentativas. O condutor não precisa executar essa etapa, continuando a visualizar a tela de "processando pagamento".

Assim que o Mercado Pago confirma o sucesso da transação, envia essa confirmação para o SGP por meio de um endpoint **(Webhook)**, marcando o status como "PAGO". O SGP então atualiza o banco de dados com o status e o valor pago.

Após essa etapa, o sistema atualiza o status no frontend e direciona o condutor para uma tela de Sucesso ou Erro, dependendo do desfecho da transação.

Se o pagamento for bem-sucedido, o condutor terá a opção de solicitar um recibo correspondente ao pagamento do estacionamento.


<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``POST``
`*Para iniciar o registro de estacionamento (CHECK-IN)`

```
	estacionamentos/check-in
```
<details>
  <summary>Exemplo Request Body:</summary>  
  
- _Cobrança por TEMPO_FIXO_

```
curl --location 'http://localhost:8080/estacionamentos/check-in' \
--header 'Content-Type: application/json' \
--data '{
    "quantidadeHoras": 4,
    "veiculoId": "65441af928166b3360397af4",
    "tipoEstacionamento": "TEMPO_FIXO"
}'
```

- _Cobrança por TEMPO_VARIAVEL_

```
curl --location 'http://localhost:8080/estacionamentos/check-in' \
--header 'Content-Type: application/json' \
--data '{
    "veiculoId": "6539b580eefbc3231bd4c8be",
    "tipoEstacionamento": "TEMPO_VARIAVEL"
}'
```

</details>

<details>
  <summary>Responses:</summary>

201 - _Created_
`- Será retornado o id do registro estacionamento`

- _Cobrança por TEMPO_FIXO  ou  TEMPO_VARIAVEL_

```
6544269962cd7f53cfcbb87e
```

</details>
<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``GET``
`*Para obter informações do registro de estacionamento`

```
	estacionamentos/{id}
```
<details>
  <summary>Exemplo Request Body:</summary>

- _Cobrança por TEMPO_FIXO  ou  TEMPO_VARIAVEL_

```
curl --location 'http://localhost:8080/estacionamentos/6544269962cd7f53cfcbb87e'
```

</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_

- _Cobrança por TEMPO_FIXO_

```
{
    "id": "6544269962cd7f53cfcbb87e",
    "tipo": "FIXO",
    "dataHoraInicio": "2023-11-02T19:45:41.102",
    "dataHoraTermino": "2023-11-02T23:45:41.102",
    "valor": 15.0,
    "pagamento": null,
    "veiculo": {
        "id": "65441af928166b3360397af4",
        "placa": "NEV-1252",
        "condutor": {
            "id": "6544027c1d769121eb36feb1",
            "nome": "Pedro Alves Nunes",
            "cpf": "04127674733"
        }
    }
}
```

- _Cobrança por TEMPO_VARIAVEL_

```
{
    "id": "65442ec5e691fb37293402e5",
    "tipo": "VARIAVEL",
    "dataHoraInicio": "2023-11-02T18:20:37.585",
    "dataHoraTermino": null,
    "valor": 10.0,
    "pagamento": null,
    "veiculo": {
        "id": "6539b580eefbc3231bd4c8be",
        "placa": "NEV-4542",
        "condutor": {
            "id": "6539b3beeefbc3231bd4c8bd",
            "nome": "Ricardo Caio Oliveira",
            "cpf": "31253870780"
        }
    }
}
```


</details>
<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``PATCH``
`*Para encerrar o registro de estacionamento (CHECK-OUT)`

```
	estacionamentos/{id}/check-out
```
<details>
  <summary>Exemplo Request:</summary>
  
- _Cobrança por TEMPO_FIXO  ou  TEMPO_VARIAVEL_

```
curl --location --request PATCH 'http://localhost:8080/estacionamentos/6544269962cd7f53cfcbb87e/check-out'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_

- _Cobrança por TEMPO_FIXO  ou  TEMPO_VARIAVEL_

```
{
    "solicitacaoPagamentoId": "18bef041-fb37-4eb5-800e-9286501cfb7d",
    "sistemaPagamentoName": "MOCK",
    "sistemaPagamentoRedirectUrl": "https://mock/iniciar-pagamento/18bef041-fb37-4eb5-800e-9286501cfb7d"
}
```

</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``GET``
`*Para obter o RECIBO após concluir o pagamento`

```
	estacionamentos/{id}/recibo
```

<details>
  <summary>Exemplo Request:</summary>
  
- _Cobrança por TEMPO_FIXO  ou  TEMPO_VARIAVEL_

```
curl --location 'http://localhost:8080/estacionamentos/6544269962cd7f53cfcbb87e/recibo?status=PAGO'
```

</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_

- _Cobrança por TEMPO_FIXO_

```
{
    "registroEstacionamentoId": "6544269962cd7f53cfcbb87e",
    "pagamento": {
        "idSolicitacaoPagamento": "18bef041-fb37-4eb5-800e-9286501cfb7d",
        "status": "PAGO",
        "valorPago": 15.0
    },
    "veiculo": {
        "id": "65441af928166b3360397af4",
        "marca": "VW - VolksWagen",
        "modelo": "Gol Rallye 1.6 T. Flex 16V 5p",
        "placa": "NEV-1252",
        "condutor": {
            "id": "6544027c1d769121eb36feb1",
            "nome": "Pedro Alves Nunes",
            "cpf": "04127674733",
            "email": "pedro_nunes@nunes.com",
            "telefone": "(98)99764-0567"
        }
    }
}
```

- _Cobrança por TEMPO_VARIAVEL_

```
{
    "registroEstacionamentoId": "65442ec5e691fb37293402e5",
    "pagamento": {
        "idSolicitacaoPagamento": "5183febb-1cbe-42d2-b9bb-0b476e6c625c",
        "status": "PAGO",
        "valorPago": 10.0
    },
    "veiculo": {
        "id": "6539b580eefbc3231bd4c8be",
        "marca": "Peugeot",
        "modelo": "206 Passion 1.6",
        "placa": "NEV-4542",
        "condutor": {
            "id": "6539b3beeefbc3231bd4c8bd",
            "nome": "Ricardo Caio Oliveira",
            "cpf": "31253870780",
            "email": "ricardo-oliveira80@huios.com.br",
            "telefone": "(69) 99665-3809"
        }
    }
}
```

</details>
<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------
### Notificação

>[ Base URL: http://localhost:8080 ]

Indiferentemente da opção de cobrança selecionada, o sistema oferece uma funcionalidade de Notificação, alertando os condutores quando o tempo está prestes a expirar.

Para o método de Tempo Fixo, a notificação é enviada 10 minutos antes do término do período estipulado.

No caso do Tempo Variável, são enviadas notificações 10 minutos antes de completar 1 hora de estacionamento. Este alerta informa o condutor que o sistema estenderá automaticamente o estacionamento por mais 1 hora, oferecendo praticidade e assegurando que o veículo permaneça estacionado sem interrupções.

Atualmente, a funcionalidade de notificação está agendada pelo Spring, verificando a cada 10 segundos se há algum registro de estacionamento prestes a vencer (ou seja, 10 minutos antes do término). 

É importante ressaltar que, nesta versão, ainda não contamos com uma implementação à chamada de um serviço específico para o envio de e-mails e mensagens de texto (SMS).

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``PATCH``  
`*Para notificações`

```
	/notificacoes
```
<details>
  <summary>Exemplo Request:</summary>

```
curl --location --request PATCH 'http://localhost:8080/notificacoes'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

204 - _No Content_

```
```

</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------
### Tarifa

> TabelaPrecoDocument

Na versão atual do sistema, o administrador do estabelecimento insere manualmente no banco de dados os valores cobrados pelo estacionamento. Atualmente, o campo de vigência está null no código. No entanto, a ideia é permitir que o administrador defina uma data específica como vigência. Isso ajudará a registrar as mudanças de preços no banco de dados, fornecendo maior controle sobre a validade dos valores praticados no estacionamento, além de criar um histórico dessas alterações.


<details>
  <summary>Modelo JSON para criar o documento mencionado:</summary>

```
{
  "precoHora": 5,
  "precosHora": [
    {
      "hora": 4,
      "valor": 15
    },
    {
      "hora": 6,
      "valor": 25
    }
  ],
  "vigencia": null
}
```

</details>



<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------
<a name="tecnologias"></a>
## 📍️ Tecnologias

- As API's foram construídas em Java 17 utilizando Spring Framework 3.1.4
- Padrão REST na construção das rotas e retornos
- SLF4J para registro de logs
- Utilização de código limpo e princípios **SOLID**
- Boas práticas da Linguagem/Framework
- Clean architecture
- Banco de Dados MongoDB
- JUnit e Mockito para implementação de testes unitários

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------

<a name="desafios"></a>
## 📍️ Desafios

No decorrer do desenvolvimento do Sistema de Gestão de Parquímetro (SGP), enfrentamos desafios significativos, destacando-se a transição para o uso do MongoDB, que demandou uma mudança na abordagem do banco de dados. A adaptação da equipe a um modelo NoSQL, saindo do tradicional banco relacional, foi um processo desafiador que exigiu revisão de práticas e estratégias de desenvolvimento.

Além disso, um dos maiores obstáculos que enfrentamos foi relacionado ao deployment na AWS (Amazon Web Services) e suas configurações. A complexidade das configurações e otimizações necessárias para garantir um ambiente estável e escalável na nuvem representou um desafio adicional para a equipe. A curva de aprendizado para lidar com as peculiaridades do ambiente de nuvem AWS foi um processo desafiador que demandou tempo e esforço consideráveis.

Esses desafios, tanto na transição para o MongoDB quanto no deploy na AWS, exigiram que a equipe superasse obstáculos técnicos e se adaptasse a novos paradigmas tecnológicos, resultando em um aprendizado valioso e na capacidade aprimorada de lidar com complexidades técnicas em futuros projetos. 


<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>
