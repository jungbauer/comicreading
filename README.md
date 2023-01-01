# Comic Reading
This is a project to track the manhwa comics I read.

# Development

## Postgres user and db
Create the Postgres user with the command below. Initially `sillybillie` was the dev password. Use whatever you want locally.
```
sudo -u postgres createuser --interactive --password comicadmin
```

Create the db with the following command. This also makes `comicadmin` the db owner.
```
sudo -u postgres createdb comicdb -O comicadmin
```

## Initial data settings
The dev database is initialised from the `schema.sql` and `data.sql` files. When using the default profile the initialisation is triggered at startup. See `spring.sql.init.mode` setting in the `application.properties` file. Running with the dev profile doesn't trigger the initialisation. There is a Gradle task for this: `bootRunDev`. [Reference link](https://www.baeldung.com/spring-boot-data-sql-and-schema-sql)

## Mobile Testing
ngrok can be used to create a link if browser simulation is not enough. Make sure the ports match the local ports. Current port is set to 8080.
```
ngrok http 8080
```