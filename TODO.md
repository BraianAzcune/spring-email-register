# cosas para agregar o modificar
### mejor manejo de errores de api rest
Mensajes descriptivos de error en la api, actualmente lanzamos excepciones, debe haber una forma de enviar mensajes en formato json, con errores comprensibles, para aquel que usa la api.
 
no es recomendable lanzar excepciones que supuestamente son errores internos y tener en
application.properties
```bash
server.error.include-message=always
```
### cambio en la estructura de confirmationToken

el hecho de que sea muchos a uno el confirmToken con el usuario es molesto.



### integracion front end
se podria integrar todo esto con Reactjs.