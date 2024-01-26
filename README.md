<a name="readme-top"></a>

# Introdução

O projeto em questão é uma aplicação de gerenciamento de videos, e apresenta uma solução para atender às necessidades de upload, armazenamento e download de videos de maneira eficaz e intuitiva.


## Sumário
* [Instruções](#instruções)
* [CRUD de Video](#crud-de-video)
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

### CRUD de Video

>[ Base URL: http://localhost:8080 ]

O CRUD de video contempla as funcionalidades de criar, atualizar, listar e excluir videos.

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``POST``  
`*Para cadastrar Video`

```
	/videos
```
<details>
  <summary>Exemplo Request:</summary>

```
curl --location 'http://localhost:8080/videos' \
--header 'Content-Type: application/json' \
--data '{
    "titulo": "Robocop",
    "descricao": "Policial fatalmente ferido é usado como cobaia por uma empresa de tecnologia robótica.",
    "categoria": "FICCAO"
}'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

201 - _Created_
`- Será retornado o id do video`

```
65a5b14ba6bf7a1d75bbc83d
```

400 - _Bad Request_

```
{
    "code": "tc.argumentNotValid",
    "message": "titulo:não deve estar em branco;"
}
```

</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``GET``
`*Para obter Video por id`

```
	/videos/{id}
```

<details>
  <summary>Exemplo Request:</summary>

```
curl --location 'http://localhost:8080/videos/65a5b14ba6bf7a1d75bbc83d'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_
`- Será retornado o video`

```
{
    "id": "65a5b14ba6bf7a1d75bbc83d",
    "titulo": "Robocop",
    "descricao": "Policial fatalmente ferido é usado como cobaia por uma empresa de tecnologia robótica",
    "dataPublicacao": "2024-01-08",
    "quantidadeVisualizacao": 2,
    "categoria": "FICCAO"
}
```

404 - _Not Found_

```
{
    "code": "tc.videoNaoEncontrado",
    "message": "Video não encontrado."
}
```
</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``GET``
`*Para obter a lista de videos paginado e ordenado por data publicação`

```
	/videos
```

<details>
  <summary>Exemplo Request:</summary>

```
curl --location 'http://localhost:8080/videos?pagina=0&tamanho=5'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_
`- Será retornada a lista de videos`

```
{
    "content": [
        {
            "id": "65b0474705db02742c9d0bba",
            "titulo": "Monstros",
            "descricao": "Pessoas são obrigadas a atravessar o território hostil infectado por aliens.",
            "dataPublicacao": "2024-01-23",
            "quantidadeVisualizacao": 0,
            "categoria": "TERROR"
        },
        {
            "id": "65ac5e17bc5f92526a7184b7",
            "titulo": "A Origem",
            "descricao": "Em um mundo onde é possível entrar na mente humana e roubar segredos valiosos do inconsciente",
            "dataPublicacao": "2024-01-20",
            "quantidadeVisualizacao": 0,
            "categoria": "FICCAO"
        },
        {
            "id": "65ac5d60bc5f92526a7184b6",
            "titulo": "Distrito 9",
            "descricao": "Há 20 anos uma gigantesca nave espacial pairou sobre a capital da África do Sul",
            "dataPublicacao": "2024-01-20",
            "quantidadeVisualizacao": 3,
            "categoria": "FICCAO"
        },
        {
            "id": "65ac5e6ebc5f92526a7184b8",
            "titulo": "Interestelar",
            "descricao": "Um grupo de astronautas recebe a missão de verificar possíveis planetas para receberem a população mundial",
            "dataPublicacao": "2024-01-20",
            "quantidadeVisualizacao": 1,
            "categoria": "FICCAO"
        },
        {
            "id": "65a5bac7102f771509eae998",
            "titulo": "Marley e Eu",
            "descricao": "Filme baseado em fatos reais.",
            "dataPublicacao": "2024-01-15",
            "quantidadeVisualizacao": 0,
            "categoria": "ROMANCE"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 5,
        "sort": {
            "sorted": false,
            "empty": true,
            "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": false,
    "totalPages": 2,
    "totalElements": 7,
    "sort": {
        "sorted": false,
        "empty": true,
        "unsorted": true
    },
    "first": true,
    "size": 5,
    "number": 0,
    "numberOfElements": 5,
    "empty": false
}
```
</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``PUT``
`*Para alteração de dados do Video`

```
	/videos/{id}
```

<details>
  <summary>Exemplo Request:</summary>

```
curl --location --request PUT 'http://localhost:8080/videos/65a5b14ba6bf7a1d75bbc83d' \
--header 'Content-Type: application/json' \
--data '{
    "titulo": "Robocop - O Policial do Futuro",
    "descricao": "Policial fatalmente ferido é usado como cobaia por uma empresa de tecnologia robótica",
    "categoria": "ACAO"
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
    "code": "tc.videoNaoEncontrado",
    "message": "Video não encontrado."
}
```
</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``DELETE``
`*Para excluir o cadastro do video`

```
	/videos/{id}
```

<details>
  <summary>Exemplo Request:</summary>

```
curl --location --request DELETE 'http://localhost:8080/videos/65a5b026ecad581794fcb2d3'
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
### Upload de Video

>[ Base URL: http://localhost:8080 ]

Após a etapa bem-sucedida de criar/cadastrar um video, é possível realizar o upload do arquivo de video.
Para essa funcionalidade, implementamos uma solução bem simples, armazenando os videos em nosso sistema de arquivos local.
Os arquivos serão salvos com o id do video já cadastrado anteriormente.

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``POST``
`*Para realizar o upload de arquivo de video`

```
	/videos/{id}/upload
```
<details>
  <summary>Exemplo Request Body:</summary>

```
curl --location 'http://localhost:8080/videos/65ac5d60bc5f92526a7184b6/upload' \
--form 'file=@"/home/tati/Vídeos/20240124_184058.mp4"'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_

```
```

</details>
<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------
### Pesquisa Videos

>[ Base URL: http://localhost:8080 ]

A pesquisa de video por filtros facilita a localização dos videos desejados, permitindo que o usuário refine sua busca por: título, dataPublicacao ou categoria.

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``GET``
`*Para pesquisar por videos utilizando filtros de busca`

```
	/videos/filtros
```
<details>
  <summary>Exemplo Request Body:</summary>

```
curl --location 'http://localhost:8080/videos/filtros?titulo=robocop&dataPublicacao=2024-01-08&categoria=FICCAO'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_
`- Serão retornados os videos desejados`

```
[
    {
        "id": "65a5b14ba6bf7a1d75bbc83d",
        "titulo": "Robocop - O Policial do Futuro",
        "descricao": "Policial fatalmente ferido é usado como cobaia por uma empresa de tecnologia robótica",
        "dataPublicacao": "2024-01-08",
        "quantidadeVisualizacao": 2,
        "categoria": "FICCAO"
    }
]
```
200 - _OK_
`- Caso não tenha encontrado nenhum registro`

```
[]
```
</details>
<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------
### Obter URL

>[ Base URL: http://localhost:8080 ]

Após o usuário selecionar o video desejado, a aplicação disponibiliza uma URL para download de arquivo do video escolhido, permitindo ao usuário assistir ao conteúdo.

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``GET``
`*Para obter url para download`

```
	/videos/{id}/url
```
<details>
  <summary>Exemplo Request Body:</summary>

```
curl --location 'http://127.0.0.1:8080/videos/65ac5d60bc5f92526a7184b6/url'
```

</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_
`- Será retornada a url de download`

```
http://localhost:8081/65ac5d60bc5f92526a7184b6.mp4
```

</details>
<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------
### Favoritar Video

>[ Base URL: http://localhost:8080 ]

Após o usuário selecionar o video desejado, a aplicação disponibiliza uma URL para download de arquivo do video escolhido, permitindo ao usuário assistir ao conteúdo.

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``GET``
`*Para obter url para download`

```
	/videos/{id}/url
```
<details>
  <summary>Exemplo Request Body:</summary>

```
curl --location 'http://127.0.0.1:8080/videos/65ac5d60bc5f92526a7184b6/url'
```

</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_
`- Será retornada a url de download`

```
http://localhost:8081/65ac5d60bc5f92526a7184b6.mp4
```

</details>
<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

