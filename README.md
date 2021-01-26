# Challenge Frávega
Api REST que expone 3 endpoints para:
- creación de una entidad Sucursal 
- consulta de sucursal por id
- consulta de sucursal más cercana por geoposicionamiento

Persiste los datos en una base de datos NoSQL (MongoDB)

En la ruta ```http://localhost:8080/swagger-ui.html#``` se encuentra el Swagger
del proyecto

En el repo: [challenge-ui](https://github.com/saterdoe/challenge-ui) 
se encuentra una pequeña aplicación para mostrar en un navegador el resultado de  
consulta de sucursal más cercana
`GET  /inicio`
***
#### Dependencias ####

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```
```xml
<dependency>
<groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
```
```xml
<dependency>
<groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
***
## Endpoint habilitados

Metodo | Ruta | Funcion |
 ------ | ------ | ------| 
GET |  /inicio | Recibe latitud y longitud como parametros de busqueda y devuelve la sucursal más cercana a las posiciones recibidas |
GET |  /sucursal/{id} | Listar  segun id |
POST |  /sucursal | Agregar una entidad en la base de datos |
***
### Request ejemplo para recibir una sucursal cercana:
`http://localhost:8080/inicio?latitud=-34.69244482541838&longitud=-58.72990968866752`

### Response:
```json
{
  "id": "600f486ff877442930221298",
  "direccion": "Avenida siempre viva 742",
  "latitud": "-34.6940751",
  "longitud": "-58.7305333"
}
```
***
### Request ejemplo para listar una sucursal por id:
`http://localhost:8080/sucursal/600f67ef59ce483cd660e280`

### Response:
```json
{
  "id": "600f67ef59ce483cd660e280",
  "direccion": "Avenida siempre viva 742",
  "latitud": "-34.6940751",
  "longitud": "-58.7305333"
}
```
***
### Request ejemplo para crear una Sucursal:
```json
{
  "direccion": "Avenida siempre viva 742",
  "latitud": -34.6940751,
  "longitud": -58.7305333
}
```

### Response:
```json
{
"id": "600f67ef59ce483cd660e280",
"direccion": "Avenida siempre viva 742",
"latitud": "-34.6940751",
"longitud": "-58.7305333"
}
```
***
### Iniciar aplicación:
```mvn clean install spring-boot:run```