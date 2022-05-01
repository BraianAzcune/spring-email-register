# Tutorial del canal AmigosCode
Registrar usuarios y logear usando email con seguridad.

https://www.youtube.com/watch?v=QwQuro7ekvc

## crear base de datos
crearemos un docker-compose.yml con la configuracion para levantar facilmente en contenedor postgres configurado.
tambien creamos un .env de las propiedades que tendra el postgres. y seteamos usuario y password en el application.properties.

Creamos construimos el contenedor con:

```bash
docker-compose -f docker-compose.yml up --build
```

para ejecutarlo:

```bash
docker-compose -f docker-compose.yml up
```

para entrar a ver la db:
  
```bash
docker exec -it amigoscode-email-register-postgresDB psql -U braian amigoscodeEmailRegister
```

mostrar tablas de la db

```bash
\d
select * from app_user;
\x
select * from app_user;
```


## aprendizaje sobre los Beans, concurrencia, multihilo, thread-safe

por defecto todos los @bean (@Component, @Service, @Repository) son singleton, es decir, una sola instancia, que es creada en cuanto inicia la aplicacion a menos que use use @Lazy que los carga ante la primera request.

El hecho de que sean singleton significa que son compartido entre hilos y request, por lo que si tienen algun tipo de estado tendremos problemas.

ademas definir los beans como prototype no nos garatiza ser thread safe, ya que si un prototype es usado por un @bean singleton, estamos en la misma. (aunque seria diferente entre otros @bean que lo requieran).

para definir a un @Bean que no sea singleton existe la anotacion @Scope, que nos permite definir el bean en 5 modalidades.

mas info:

https://blog.marcnuri.com/spring-bean-scopes-guia-rapida

## que hay hasta ahora 1:03:23
esta la estructura basica hecha, se puede registrar un usuario enviando un POST con los datos
email y password a la url /api/v1/registration
y crea al usuario.
si entramos desde el navegador a cualquier url nos redirigira a logearnos, y si ponemos
algo que no existe nos lo dira, y si ponemos el de un usuario que hayamos registrado nos dira que esta desabilitado.

```bash
curl --request POST \
  --url http://localhost:8080/api/v1/registration \
  --header 'Content-Type: application/json' \
  --data '{
	"email":"braian@gmail.com",
	"password": "ggeasy"
}'
```

## fin del tutorial que quedo:

usamos un simulador SMTP para enviar emails, y una vez que se registra el usuario, se le envia un email con un link de activacion.

Para simular en envio de email, usaremos un servidor de correo en localhost, en este docker, que nos proveera tambien de una pagina para ver los correos que llegan.
https://github.com/maildev/maildev

```bash
$ docker run -p 1080:1080 -p 1025:1025 maildev/maildev
```

ir a navegador a la url http://localhost:1080/


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