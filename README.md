# Llistat endpoints

## Client ( /api/clients )

### POST "/create" - crea un nou client

    RequestBody: {
     "name":"String",
     "createdDate":"Date"
    }

### PUT "/update/{id}" - modifica un client ja existent

    RequestBody: {
      "id":0,
      "name":"String",
      "createdDate":"Date"
    }

### GET "{id}" - cerca un client ja existent
### GET "/all" - retorna tots els clients

### DELETE "{id}" - elimina client per ID

## Product ( /api/products )

### POST "/create" - crea un nou producte

    RequestBody: {
     "name":"String",
     "price":"Float"
    } 

### GET "{id}" - cerca un producte ja existent
### GET "/all" - retorna tots els productes

### DELETE "{id}" - elimina producte per ID

## Sales ( /api/sales )

## POST "/create" - crea una nova venta

    RequestBody: {
      "saleTime": timestamp,
      "units": Float,
      "clientDetails": {
        "id": 0,
        "name": "String",
        "createdDate": Date
       },
       "product":{
         "id":0,
        "name": "String",
        "price": Float
       }
    }

> **Nota:** si no s'indica id tant a "clientDetails" com a "product", l'endpoint crearà les entitats

### GET "{id}" - cerca una venta ja existent
### GET "/all" - cerca totes les ventes

### DELETE "{id}" - elimina venta per ID

### QUERIES

##### GET " /searchBetweenDates " - cerca les ventes entre 2 dates

    Params: - fromDate: Date
            - toDate: Date


##### GET " /searchByClientId " - cerca les ventes a través de la id del Client

    Params: - clientId: Integer

##### GET " /searchByOlderClients " - cerca les ventes dels clients creats a una data indicada

    Params: - createdDate: date


