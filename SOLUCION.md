## 📝 Problema #1: El Videoclub de Don 

### Prompt

Claude, la cuestión es la siguiente, cuento con un problema de codificación en donde es necesario seguir con cada una de las indicaciones al pie de la letra.

Como primera instancia, se recibirá el contexto del problema, el cual debes tener presente en todo momento al realizar la solución.

Consecuente a este, se obtendrán las indicaciones de qué se debe crear en el código, este codigo deber ser lo mas claro posible, tanto en temas de nombres de variables como en las metodologias implementadas para desarrollarlo, no debes olvidar la documentación necesaria para cada metodo implementado

Despues de ello, tendras la sección de un caso de ejemplo, donde se visualiza como debe de salir la respuesta para este problema, necesito que mantengas este formato en todo momento.

Por ultimo, se tiene los objetivos del ejercicio, en esta parte en específico, necesito una respuesta no en código sino teorica, organizada y explicada segun lo que se solicita en esta parte.

El codigo generado será en java y no necesito ninguna otra respuesta externas a este problema, así que, apenas estes listo para comenzar, envía el mensaje "comencemos"

### Objetivos del Ejercicio — Respuesta Teórica
## 1. Patrones de Diseño
Strategy Pattern
Se aplica para el cálculo del precio según el tipo de membresía. La interfaz MembershipStrategy define el contrato, y cada membresía (BasicMembership, PremiumMembership) implementa su propia lógica de descuento. El cliente delega el cálculo a la estrategia que tenga asignada.
Factory Method Pattern
Se aplica para la creación de películas. La clase MovieFactory centraliza la construcción de objetos PhysicalMovie o DigitalMovie según el tipo recibido, evitando que el código cliente dependa de las clases concretas.

## 2. Principios SOLID aplicados
S — Single Responsibility
Cada clase tiene una única razón de cambiar: Movie gestiona datos de la película, MembershipStrategy maneja el descuento, RentalService orquesta el alquiler, y ReceiptPrinter se encarga exclusivamente de imprimir el recibo.
O — Open/Closed
El sistema está abierto para extender nuevos tipos de membresía (ej. VIPMembership) o nuevos tipos de película sin modificar las clases existentes, solo agregando nuevas implementaciones.
L — Liskov Substitution
PhysicalMovie y DigitalMovie son intercambiables en cualquier lugar donde se use Movie, sin alterar el comportamiento esperado del programa.
I — Interface Segregation
MembershipStrategy expone únicamente el método necesario (calculatePrice), sin forzar a las implementaciones a depender de métodos que no usan.
D — Dependency Inversion
RentalService depende de la abstracción MembershipStrategy y no de implementaciones concretas como PremiumMembership o BasicMembership.

## 3. Polimorfismo y Encapsulamiento

Polimorfismo: el método getType() es sobreescrito por cada subclase de Movie, permitiendo que el recibo muestre (Fisica) o (Digital) sin preguntar el tipo explícitamente con condicionales.
Encapsulamiento: los atributos de Movie son privados y accesibles solo a través de getters, protegiendo el estado interno del objeto de modificaciones externas no controladas.

### Evidencia de ejecución

```Peliculas Disponibles:
 1. [Fisica] Interestellar - $8.000 - Disponible
 2. [Fisica] El Padrino - $7.000 - No disponible
 3. [Digital] Inception - $5.000 - Disponible
 4. [Digital] Matrix - $6.000 - Disponible

Membresia del cliente (Basica / Premium): Premium
Seleccione peliculas (numeros separados por coma): 1,3

--- RECIBO DE ALQUILER ---
Cliente: Premium
Peliculas:
 - Interestellar (Fisica) - $8.000
 - Inception (Digital) - $5.000
Subtotal: $13.000
Descuento (20%): $2.600
Total a pagar: $10.400
--------------------------
¡Disfrute su pelicula! ```

(subido en el minuto 21)