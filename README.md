# Library Database In Spanish

Esta aplicación hecha con Java, Maven, Spring Boot y Swing sirve como cliente para la applicación [Library Database In Spanish Server](https://github.com/dangarcar/library-database-in-spanish-server)

![Imagen de la pantalla de inicio del programa](src/main/resources/files/images/captura.png)

## Requisitos previos
- Tener [Java](https://www.java.com/en/) instalado (Como mínimo la versión JRE 17)

## Tecnologías usadas
- [Java](https://www.java.com/en/) 17.0
- [Spring Boot](https://spring.io/projects/spring-boot/) 2.7
- [Maven](https://maven.apache.org/) 3.8
- [Swing](https://es.wikipedia.org/wiki/Swing_(biblioteca_gr%C3%A1fica))
- [MigLayout](https://www.miglayout.com/) 11.0

## Cómo ejecutarlo
1. Asegúrate de tener corriendo la última versión del servidor, si no sabes cómo, mira el README de [Library Database In Spanish Server](https://github.com/dangarcar/library-database-in-spanish-server)

2. En `src/main/resources/application.properties`, cambie el valor de `server.url` por la URL del servidor y cambie `crypto.password` por un string de caracteres alfanuméricos.

3. Abre una terminal en el directorio del proyecto.

4. Ejecuta el siguiente comando para ejecutar la aplicación:
>```console 
>./mvnw spring-boot:run -q
>```
