## 📝 Problema #1: El Videoclub de Don 

### Prompt

Claude, la cuestión es la siguiente, cuento con un problema de codificación en donde es necesario seguir con cada una de las indicaciones al pie de la letra.

Como primera instancia, se recibirá el contexto del problema, el cual debes tener presente en todo momento al realizar la solución.

Consecuente a este, se obtendrán las indicaciones de qué se debe crear en el código, este codigo deber ser lo mas claro posible, tanto en temas de nombres de variables como en las metodologias implementadas para desarrollarlo, no debes olvidar la documentación necesaria para cada metodo implementado

Despues de ello, tendras la sección de un caso de ejemplo, donde se visualiza como debe de salir la respuesta para este problema, necesito que mantengas este formato en todo momento.

Por ultimo, se tiene los objetivos del ejercicio, en esta parte en específico, necesito una respuesta no en código sino teorica, organizada y explicada segun lo que se solicita en esta parte.

El codigo generado será en java y no necesito ninguna otra respuesta externas a este problema, así que, apenas estes listo para comenzar, envía el mensaje "comencemos"

### Objetivos del Ejercicio — Respuesta Teórica
### 1. Patrones de Diseño
Strategy Pattern
Se aplica para el cálculo del precio según el tipo de membresía. La interfaz MembershipStrategy define el contrato, y cada membresía (BasicMembership, PremiumMembership) implementa su propia lógica de descuento. El cliente delega el cálculo a la estrategia que tenga asignada.
Factory Method Pattern
Se aplica para la creación de películas. La clase MovieFactory centraliza la construcción de objetos PhysicalMovie o DigitalMovie según el tipo recibido, evitando que el código cliente dependa de las clases concretas.

### 2. Principios SOLID aplicados
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

### 3. Polimorfismo y Encapsulamiento

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

(subido en el minuto 21 - ClaudeAI)

---

## Problema 2

### Prompt

perfecto, ahora, como siguiente paso vamos a ver la resolución de otro problema.
En este caso, obtendremos una forma diferente de solucionar esto.

Ya no nos centraremos en la creacion de codigo desde 0 sino que vamos a realizar correcciones, el caso es el siguiente:

Junto a este prompt, en la parte final, te mandaré las indicaciones de este nuevo ejercicio con elsiguiente fin: los proximos mensajes hasta terminar el problema, serán solamente del codigo que necesita ser corregido, por mientras necesito que analices y leas atentamente las indicaciones de este problema (iniciarán despues de una separación con este simbolo '=')

centrate en la parte de arreglar el código para que compile de manera correcta, que sea totalmente funcional y que no hay incongruencias en este mismo

por ultimo, tambien poseemos unas preguntas teóricas, que se responderan unica y exclusivamente cuando se termine todo el código.

para empezar, me diras "comencemos"
y cuando ya no haya mas código y tu puedas responder las preguntas teoricas, te diré "listo"

## Respuestas Teóricas

### 1. Patrones de diseño utilizados
Observer Pattern — ECIPayment actúa como sujeto observable, mantiene una lista de PaymentObserver y los notifica cuando un pago tiene éxito o falla. PaymentEventObserver es el observador concreto que reacciona coordinando inventario, facturación y notificación. Este patrón es adecuado porque desacopla completamente el procesamiento del pago de sus efectos secundarios.
Factory Method Pattern — PaymentFactory define el contrato createPaymentMethod(), y cada clase concreta (CreditCardFactory, PaypalFactory, CryptoFactory) implementa la creación de su propio método de pago. Esto es adecuado porque permite que ECIPayment.processPayment() opere sobre la abstracción sin conocer el tipo concreto de pago.

### 2. Clases e interfaces faltantes
La única pieza estructural que faltaba era la interfaz PaymentFactory. Sin ella el código no compilaba porque ECIPayment la referenciaba como tipo del parámetro factory pero no existía en el proyecto. Las factories concretas tampoco declaraban implements PaymentFactory, rompiendo el contrato del patrón.

### 3. Validación del diagrama de contexto
El diagrama proporciona información suficiente para entender los actores y el flujo general, pero debería documentar explícitamente que CreditCardFactory, PaypalFactory y CryptoFactory implementan dos contratos simultáneamente: PaymentFactory y PaymentMethod. Esta dualidad no es inmediatamente obvia en un diagrama de contexto básico y es el núcleo de la arquitectura del sistema.

### 4. Errores identificados que impedían la compilación
#ArchivoError1PaymentMethod.javaParámetro transactionID mal nombrado; this.customerID = customerID asignaba una variable nula en lugar del parámetro recibido2PaymentMethod.javasetAmount declarado como double en lugar de void, siendo un setter3PaymentEventObserver.javaimport javax.management.Notification en conflicto con la clase Notification del propio paquete4CryptoFactory.javathis.token = token donde token no existe como parámetro del constructor5ECIPayment.javaReferencia a PaymentFactory que no existía en ningún archivo del proyecto6Todas las factoriesNinguna implementaba PaymentFactory, haciendo imposible pasarlas como argumento en ECIPayment.processPayment()

### 5. Principios SOLID aplicados
O — Open/Closed: agregar un nuevo método de pago (ej. TransferFactory) solo requiere crear una nueva clase que extienda PaymentMethod e implemente PaymentFactory, sin tocar ECIPayment ni ninguna clase existente.
D — Dependency Inversion: ECIPayment depende de la abstracción PaymentFactory y de la abstracción PaymentObserver, nunca de CreditCardFactory o PaymentEventObserver directamente.
S — Single Responsibility: cada clase tiene una responsabilidad clara y única: Facturation factura, Notification notifica, Inventory gestiona stock, ECIPayment orquesta, y PaymentEventObserver coordina los efectos post-pago.

(Subido minuto 25 - ClaudeAI)