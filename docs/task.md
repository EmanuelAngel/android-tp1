# Trabajo Práctico: Conversor de Moneda con MVVM en Android

## Consigna
Desarrollar una aplicación móvil en Android (Java) que permita convertir valores entre Dólares (USD) y Euros (EUR), implementando el patrón de arquitectura MVVM (Model - View - ViewModel). La aplicación deberá presentar una interfaz similar a la mostrada, permitiendo al usuario ingresar valores, seleccionar el tipo de conversión y visualizar el resultado.

---

## Objetivos de aprendizaje
* Aplicar el patrón de arquitectura MVVM en una aplicación real.
* Separar correctamente la lógica de negocio de la interfaz de usuario.
* Utilizar LiveData para la comunicación entre capas.
* Manejar eventos de usuario y estados de la UI.

---

## Requisitos funcionales
La aplicación debe:
1. Permitir ingresar un valor en una moneda (Dólares o Euros).
2. Permitir seleccionar el tipo de conversión mediante opciones (RadioButtons):
    * Convertir a Dólares
    * Convertir a Euros
3. Mostrar el resultado de la conversión en el campo correspondiente.
4. Incluir un botón "Convertir" que ejecute la operación.
5. Mostrar el valor de conversión actual (por ejemplo: 1 USD = X EUR).
6. Incluir un botón "Cambiar valor" que permita modificar el tipo de cambio.

---

## Requisitos técnicos (MVVM)
La aplicación debe estar estructurada en tres capas:

* **Model**
    * Clase que represente la lógica de conversión.
    * Puede incluir el valor del tipo de cambio.
* **ViewModel**
    * Debe contener:
        * Los datos observables (por ejemplo: valor ingresado, resultado, tipo de cambio).
        * La lógica de conversión.
    * Utilizar LiveData para comunicar cambios a la vista.
* **View (Activity o Fragment)**
    * Maneja la interfaz gráfica.
    * Observa los datos del ViewModel.
    * No debe contener lógica de negocio.

---

## Requisitos adicionales
* Validar que el usuario ingrese valores numéricos válidos.
* Manejar estados de error (por ejemplo: campos vacíos).
* Código ordenado y comentado.

---

## Entrega
1. Subir el proyecto a un repositorio público en GitHub.
2. Incluir un archivo README con:
    * Descripción de la app.
    * Explicación breve de cómo se implementó MVVM.
3. Entregar el enlace al repositorio en el aula virtual.

---

## Criterios de evaluación
* Correcta implementación del patrón MVVM.
* Separación de responsabilidades.
* Funcionamiento de la aplicación.
* Calidad del código.
* Manejo de errores y validaciones.
* Uso adecuado de componentes Android.