## Consignas Challenge Java
### Agenda Telefónica
***
1. Construir una API Rest que permita Agendar números de teléfono fijo de Argentina.
Solo se necesita recibir el nombre de la persona o razón social y su número.
2. Debe listar los números.
3. Debe permitir obtener un número consultando por la razón social o nombre de la
persona.
4. Debe decodificar aquellos que el cliente envíe utilizando letras, debiendo guardarlos
como números. Ejemplo: 4411-JAVA -> 4411-5282
5. Debe almacenar el número con guiones o espacios según corresponda, de modo que
sea más fácil su lectura. Ejemplo 1145456565 -> 011 4545-6565
6. Implementar la identificación de código de área para permitir números
de otras localidades diferentes a CABA y GBA.
7. Paginación del Listado de números.


### Nota:
- Los códigos de área pueden tener 2, 3 o 4 dígitos, siendo los 6, 7 u 8 dígitos restantes
  el número de teléfono local.
  Por ejemplo, el número +54 11 1234-5678 de Buenos Aires está compuesto de un
  código de área de 2 dígitos seguido de un número de teléfono de ocho dígitos;
  mientras que +54 341 123-4567 sería un ejemplo de un número de Rosario.

[//]: # (### Screenshot)

[//]: # (![Image text]&#40;https://www.united-internet.de/fileadmin/user_upload/Brands/Downloads/Logo_IONOS_by.jpg&#41;)

[//]: # (## Tecnologías)
***
Tecnologías utilizadas en el proyecto
* [Java](https://example.com): Version 8
* [Spring Framework](https://docs.spring.io/spring-boot/docs/2.5.8/reference/htmlsingle/): Version 2.5.8
* [ModelMapper](https://javadoc.io/doc/org.modelmapper/modelmapper/latest/index.html): Version 3.1.0
* [SpringFox](https://springfox.github.io/springfox/docs/2.9.2/): Version 2.9.2
* [H2 DataBase](https://www.baeldung.com/spring-boot-h2-database)
## Instalación

***
![Badge en Desarollo](https://img.shields.io/badge/STATUS-EN%20DESAROLLO-green)

[<img src="https://avatars.githubusercontent.com/u/41403395?v=4" width=115><br><sub>Franco Demetrio</sub>](https://github.com/Caco74)
***
A little intro about the installation.
```
$ git clone https://github.com/Caco74/agenda-api-rest.git
$ cd ../path/to/the/file
$ npm install
$ npm start
```
Side information: To use the application in a special environment use ```lorem ipsum``` to start

[//]: # (## Collaboration)

[//]: # (***)

[//]: # (Give instructions on how to collaborate with your project.)

[//]: # (> Maybe you want to write a quote in this part.)

[//]: # (> It should go over several rows?)

[//]: # (> This is how you do it.)
## FAQs
***
A list of frequently asked questions
1. **This is a question in bold**
   Answer of the first question with _italic words_.
2. __Second question in bold__
   To answer this question we use an unordered list:
* First point
* Second Point
* Third point
3. **Third question in bold**
   Answer of the third question with *italic words*.
4. **Fourth question in bold**
   | Headline 1 in the tablehead | Headline 2 in the tablehead | Headline 3 in the tablehead |
   |:--------------|:-------------:|--------------:|
   | text-align left | text-align center | text-align right |