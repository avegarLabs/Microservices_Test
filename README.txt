La solución propuesta se estructura de 5 microservicios
- config-microservice: este proporciona un punto centralizado para la gestión de la configuración de otros microservicios
- naming-microservice: este permite que los microservicios se registren automáticamente cuando se inician y facilitar la localización dinámica de otros servicios en la red. Esta solución utiliza Spring Cloud Eureka
- microservice-gateway: actua como punto de entrada único para todas las solicitudes de clientes hacia los servicios subyacentes. 
- wastemanager-microservice: este microservicio se encarga de la implementación de las requesitos funcionales definidos para los gestores de residuos
- wastemanageraddress-microservice: este microservicio se encarga de la implementación de la gestión de la direción de los gestores de residuos

wastemanager-microservice

Sobre la base de la entidad WasteManagerEntity se desarrollaron las relaciones siguientes

WasteManagerEntity tiene una relacion de 1 a muchos con WasteCenterAuthorizationEntity, asumiendo que varios centros emiten una autorización para un gestor de residuos

Se desarrolló un microservicio para la gestión de WasteManagerAddressEntity. Esta entidad utilizará el ID del gestor de residuos para la gestión de dichos datos 

Los microservicios contienen la siguiente estructura de paquetes
- dto
- entities
- repositories
- services
- controllers
- config
- client(en el caso de wastemanager-microservice) para interactuar utilizando Spring Cloud OpenFeign con el microservicio wastemanageraddress-microservice

