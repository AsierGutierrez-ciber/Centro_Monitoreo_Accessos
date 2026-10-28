# Centro_Monitoreo_Accessos
Centro de monitoreo de accesos: Enfoque en intentos de autenticación y clasificación del riesgo de dos servidores.

# Centro de monitoreo de accesos

Programa en Java que analiza los intentos de autenticación de dos servidores, clasifica su estado de seguridad y determina cuál requiere atención prioritaria.

Proyecto desarrollado como actividad de la asignatura de Programación Orientada a Objetos del grado en Ciberseguridad.

## Funcionalidades

- Solicita por consola los datos de dos servidores.
- Comprueba que el nombre, el origen, la criticidad y los intentos sean válidos.
- Clasifica cada servidor como **NORMAL**, **ALERTA**, **BLOQUEADO** o **DATOS INVALIDOS**.
- Calcula el porcentaje de intentos fallidos cuando los datos son válidos.
- Compara los estados e indica qué servidor necesita atención prioritaria.
- Muestra un mensaje cuando los servidores tienen la misma prioridad o no se pueden comparar.

## Tecnologías y conceptos

- Java
- Programación orientada a objetos: clases, objetos y atributos.
- Métodos, condicionales y operadores lógicos.
- Entrada por consola con `Scanner`.
- Validación y procesamiento de cadenas.

## Cómo ejecutarlo

Necesitas tener instalado un **JDK**.

1. Descarga el repositorio o clónalo:

   ```bash
   git clone https://github.com/AsierGutierrez-ciber/Centro_Monitoreo_Accessos
   ```

2. Entra en la carpeta del proyecto:

   ```bash
   cd actividad-1-poo-servidores
   ```

3. Compila y ejecuta el programa:

   ```bash
   javac Main.java
   java Main
   ```

4. Introduce los datos que solicita el programa para cada servidor.

## Ejemplo de resultado

```text
========== REPORTE DE SEGURIDAD ==========
VPN | 3/10 | 30.0% | BLOQUEADO
CORREO | 3/12 | 25.0% | ALERTA
ATENCION PRIORITARIA: VPN
```

## Documentación

El repositorio incluye un reporte con la explicación del código, las reglas de clasificación y los casos de prueba.

## Autor

Asier — estudiante de Ciberseguridad.
