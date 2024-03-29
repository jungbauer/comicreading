# Comic Reading
This is a project to track the manhwa comics I read. [Current website link](https://dark-shadow-5732.fly.dev/)

* Spring Boot framework
* Thymeleaf templating
* Postgresql database
* Docker image hosing on [Fly.io](https://fly.io/)

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
The database, using the default profile, is initialised via JPA/Hibernate and `import.sql` is used to populate initial data. Running with the dev profile doesn't trigger the initialisation. There is a Gradle task for this: `bootRunDev`. [Reference link](https://www.baeldung.com/spring-boot-data-sql-and-schema-sql)

Run `./gradlew bootRun` to setup or clear the database. Run `./gradle bootRunDev` if you want to keep data.

## Local Mobile Testing
ngrok can be used to create a link if browser simulation is not enough. Make sure the ports match the local ports. Current port is set to 8080.
```
ngrok http 8080
```

## Useful hosting commands
Build Docker image with: `./gradlew bootBuildImage`

Initialise Fly.io app with: `flyctl launch --no-deploy --local-only --image reik/comicreading`

Deploy with: `flyctl deploy --local-only --image reik/comicreading`

Connect to Fly Postgres with: `fly postgres connect -a <postgres-app-name>`

Fly.io generates a database url the JDBC doesn't like. Reformat the url and set the secrets so the correct url can be formed from environment variables.
```
flyctl secrets set DB_USERNAME=<put stuff here>
flyctl secrets set DB_PASSWORD=<put stuff here>
flyctl secrets set JDBC_DATASOURCE_URL=<put stuff here>
```