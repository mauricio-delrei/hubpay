# hubpay
Arquitetura do Projeto
- Gateway com Netflix Zull (Ribbin - Load Balance)
- Microservice de autenticação com Spring Boot Security
- Microservice CRUD com Spring Boot
- MIcroservice de Pagamento com Spring Boot
- Comunicação Assíncrona com RabbitMQ
- Service Regisrty com NEtflix Eureka
- Banco de Dados MySQL
- Docker

Para subir o RabbitMQ, acessar o diretório /compose e digitar o comando: docker-compose up -d
Acessar a URL : http://localhost:15672/
usuario: admin
senha: admin


Para acessar o sistema e gerar o token:
- Abra o Postman 
- http://localhost:8080/api/auth/login (POST)
{
    "userName":"Hubpay",
    "password":"2021@"
}

Com o token gerado:
- Para cadastrar um produto:
http://localhost:8080/api/crud/product (POST)
{
    "name": "String",
    "inventory": Integer,
    "price": Double
}
- Consultar um Produto por id 
http://localhost:8080/api/crud/product/{id} (GET)
- Consultar todos os produtos
http://localhost:8080/api/crud/product (GET)
- Alterar um Produto
http://localhost:8080/api/crud/product (PUT)
- Apagar um Produto
http://localhost:8080/api/crud/product/{id} (DELETE)

Para realizar uma Venda:

http://localhost:8080/api/payment/sale (POST)
{
    "dateOfTheSale":"Date",
    "products":[
        {
            "idProduct":Integer,
            "quantity":Integer
        },
        {
            "idProduct":Integer,
            "quantity":Intger
        }
    ],
    "amount":Double
}
Consultar por id
http://localhost:8080/api/crud/product/sale{id} (GET)
Consultar todas:
http://localhost:8080/api/crud/product/sale