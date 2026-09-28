# Sistema de Gestion de Tickets

Practica individual de implementacion de estructuras de datos - SOFT-10 Estructuras de Datos, Universidad CENFOTEC (C3-2026).
Autor: Pablo Daniel Hernandez Melendez.

Aplicacion de linea de comandos (CLI) que simula el ciclo de vida de un ticket. Las dos estructuras de datos estan implementadas a mano con nodos (no se usa `LinkedList`, `PriorityQueue` ni `ArrayList`).

## Clases

| Clase | Responsabilidad |
|---|---|
| `Ticket` | Modelo: id unico (contador estatico `cantidad`), descripcion, nombreCompleto, fechaCreacion y fechaResolucion (inicia en `null`). |
| `NodoTicket` | Nodo autorreferenciado: un `Ticket` y una referencia al `siguiente`. |
| `ListaEnlazadaSimple` | Tickets **resueltos**. Atributo unico: `primero`. Metodos: `insertarFinal`, `buscarPorId`. |
| `ColaPrioridad` | Tickets **pendientes**. Nodos ordenados por prioridad; el frente es el ticket mas antiguo (menor id). Metodos: `insertar`, `verFrente`, `remover`, `contiene`. |
| `SistemaTickets` | Logica de negocio: coordina cola y lista. |
| `MenuConsola` | Menus de usuario y administrador, lectura y validacion de entrada. |
| `Main` | Crea el sistema y el menu y los arranca. |

## Flujo de un ticket

1. Usuario crea ticket -> `Ticket` (id = ++cantidad, fechaResolucion = null) -> `ColaPrioridad.insertar`.
2. Administrador ve el frente -> `ColaPrioridad.verFrente` (no lo saca).
3. Administrador resuelve -> `ColaPrioridad.remover` -> `ticket.resolver()` (fechaResolucion = ahora) -> `ListaEnlazadaSimple.insertarFinal`.
4. Usuario busca por id -> `ListaEnlazadaSimple.buscarPorId`. Si no esta pero sigue en la cola, se informa que esta pendiente; si no existe en ninguna estructura, se informa que no existe.

## Estructuras

- **Cola de prioridad:** lista enlazada ordenada. `insertar` recorre hasta la posicion que corresponde por `compareTo` (O(n)); `verFrente` y `remover` son O(1).
- **Lista enlazada simple:** `insertarFinal` recorre hasta el ultimo nodo; `buscarPorId` recorre desde `primero` hasta hallar el id o llegar a `null`.

## Compilar y ejecutar (JDK 11+)

```bash
javac -d bin $(find src -name "*.java")
java -cp bin com.cenfotec.tickets.Main
```

## Abrir en IntelliJ IDEA

File -> Open y elegir la carpeta del proyecto. Si `Main` no se puede ejecutar, clic derecho en `src/main/java` -> Mark Directory as -> Sources Root. Luego ejecutar `Main`.
