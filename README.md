# stchallenge
Sooft Technology - Challenge Backend Java
## Enunciado:
El challenge que se trata de generar los siguientes 3 endpoints:
- Uno que traiga las empresas que hicieron transferencias el último mes
- Otro que traiga las empresas que se adhirieron el último mes.
- El último que haga la adhesión de una empresa.
  
Deseable: usar arquitectura hexagonal

Base: puede usarse relacional o no relacional

Datos de la empresa: CUIT, Razón Social, Fecha Adhesión

Datos de la transferencia: Importe, Id Empresa, Cuenta Débito, Cuenta Crédito.


## Tecnologias utilizadas:
- Java 17
- Spring Boot 3.5.3
- Spring Data JPA
- Base de datos en memoria **H2**
- Arquitectura Hexagonal
- Maven 3.9.6

## Como ejecutar la aplicacion:
./mvn spring-boot:run

La app quedara levantada en: http://localhost:8080

## Acceso a la consola H2:
http://localhost:8080/h2-console

- JDBC URL: jdbc:h2:mem:testdb
- Usuario: sa
- Contraseña: (vacio)

## Endpoints disponibles:
1. Obtener empresas con transferencias el ultimo mes

   GET /empresas/transferencias-ultimo-mes

2. Obtener empresas adheridas el ultimo mes

   GET /empresas/adheridas-ultimo-mes

3. Adherir una nueva empresa

   POST /empresas/adherir
   Content-Type: application/json
  
 {
    "cuit": "20-11111111-1",
    "razonSocial": "Empresa Nueva S.A."
  }


