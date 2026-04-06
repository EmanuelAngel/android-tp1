# Conversor de Moneda - Patrón MVVM

## Descripción de la aplicación

Esta es una aplicación móvil desarrollada en Android (Java) que permite realizar conversiones numéricas entre Dólares (USD) y Euros (EUR). La aplicación está diseñada bajo el patrón de arquitectura MVVM y cuenta con las siguientes características:

1. **Gestión del Tipo de Cambio:** Permite al usuario establecer y actualizar el valor de conversión actual de la moneda de forma dinámica.
2. **Conversión Bidireccional:** A través de opciones (RadioButtons), el usuario puede elegir si desea calcular el equivalente de Dólares a Euros, o de Euros a Dólares.
3. **Validación de Entradas:** El sistema verifica que los campos no estén vacíos y valida que los valores ingresados tengan un formato numérico correcto, notificando al usuario mediante alertas visuales (setError y Toasts) en caso de error.

## Integrantes

Esta actividad se realizó de manera individual.

- **Apellido**: Angel.
- **Nombre**: Emanuel.
- **Número de Documento**: 44600506.

## Detalles Técnicos y Arquitectura

La aplicación fue desarrollada implementando estrictamente el patrón de arquitectura **MVVM (Model - View - ViewModel)**, separando las responsabilidades en tres capas fundamentales:

- **View (`MainActivity`):** Se encarga única y exclusivamente de gestionar la interfaz gráfica y capturar las interacciones del usuario. Se utilizó `ViewBinding` para enlazar los componentes de la vista. Esta capa no contiene lógica de negocio; simplemente observa los datos expuestos por el ViewModel para actualizar la pantalla (por ejemplo, mostrando el resultado de la conversión o los mensajes de error).
- **ViewModel (`MainActivityViewModel`):** Actúa como puente entre la Vista y el Modelo. Recibe los datos crudos de la vista, realiza las validaciones lógicas necesarias y utiliza `MutableLiveData` (para `rate`, `conversionResult` y `errorMessage`) para exponer el estado actual a la vista de manera reactiva. Aquí se decide qué método del Modelo invocar según la opción seleccionada por el usuario.
- **Model (`ConversionModel`):** Es una clase Java pura que representa la lógica de negocio central. Contiene los algoritmos matemáticos encargados de realizar el cálculo de conversión entre divisas utilizando la tasa de cambio provista. Es completamente independiente y no tiene referencias al framework de Android.

*Proyecto académico desarrollado para la Universidad de La Punta (ULP).*