# API de produtos - Vaga BeckEnd - Júnior

Para iniciar o banco de dados MongoDB utilize o comando: 
```
docker compose up
```

Para iniciar a aplicação utilze o comando: 
```
./mvnw spring-boot:run
```

Para rodar o teste do Controller utilize o comando: 
```
./mvnw -Dtest=ProductControllerTest test
```

Para rodar o teste do ServiceImpl utilize o comando: 
```
./mvnw -Dtest=ProductServiceImplTest test
```

## Endpoits da API

### Buscar todos os produtos
- http://localhost:8080/produtos
- HTTP Method GET
![Retorno da chamada HTTP para buscar todos os produtos]("docAssets\GETALL.png")

### Criar um novo produto
- http://localhost:8080/produtos
- HTTP Method PUT
![Retorno da chamada HTTP para criar novo produto]("docAssets\CREATE.png")

### Procurar um produto por ID
- http://localhost:8080/produtos/{idProduto}
- HTTP Method GET
![Retorno da chamada HTTP para buscar um produto]("docAssets\GETONE.png")

### Editar um produto por ID
- http://localhost:8080/produtos/{idProduto}
- HTTP Method PUT
![Retorno da chamada HTTP para editar um produto]("docAssets\EDIT.png")

### Remover um produto por ID
- http://localhost:8080/produtos/{idProduto}
- HTTP Method DELETE
![Retorno da chamada HTTP para remover um produto]("docAssets\DELETE.png")


- Preparado por: **José Mourão Costa Pinto Teles**