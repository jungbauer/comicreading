# Comic Reading
This is a project to track the manhwa comics I read.

# Development

## Initial data settings
Database can be initialised from the `schema.sql` and `data.sql` files. To trigger initialisation at startup change `spring.sql.init.mode` in the `application.properties` file from `never` to `always`. [Reference link](https://www.baeldung.com/spring-boot-data-sql-and-schema-sql)

```
spring.sql.init.mode=always
```

## Mobile Testing
ngrok can be used to create a link if browser simulation is not enough. Make sure the ports match the local ports. Current port is set to 8080.
```
ngrok http 8080
```