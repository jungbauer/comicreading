# Comic Reading

### Initial data settings
[reference link](https://www.baeldung.com/spring-boot-data-sql-and-schema-sql)

Database can be initialised from the `schema.sql` and `data.sql` files. To trigger initialisation at startup change `spring.sql.init.mode` in the `application.properties` file from `never` to `always`.

```
spring.sql.init.mode=always
```