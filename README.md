# Mutant Detection API
Este proyecto incluye un servicio en la API para detectar si una secuencia de ADN pertenece a un mutante.

Requisitos
Java 11 o superior
Gradle
Spring Boot
Dependencias: spring-boot-starter-web, spring-boot-starter-data-jpa, H2 Database
## Ejecución
Descargar el repositorio
Compilar y ejecutar la API:


./gradlew bootRun
Uso del servicio /mutant/
Endpoint:
POST /mutant/

Descripción:
Detecta si una secuencia de ADN corresponde a un mutante.

Ejemplo de Request

Enviar una solicitud POST al uno de los siguientes Endpoint: 
- Render: https://prueba-render-qnwa.onrender.com/api/v1/adn/mutant/ 
- Local: http://localhost:8080/api/v1/adn/mutant/ (El proyecto debe estar en Ejecución)

Con un cuerpo JSON como el siguiente:


{
    "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA",
        "TCACTG"
    ]
}
Respuestas

200 OK: Si la secuencia corresponde a un mutante.
{
  "message": "Es un mutante"
}


403 Forbidden: Si la secuencia no corresponde a un mutante.
{
  "message": "No es un mutante"
}


400 Bad Request: Si ocurre un error en el formato de los datos.