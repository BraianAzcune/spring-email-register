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

lo que falta hacer es que cuando se registre envie un email de registro y que se active el usuario.