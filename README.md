This is an example API project written with Java using Spring Boot Framework. Test codes have been written for all Controller and Service functions.
Two environment variables are used `prod` and `dev` which are taken by docker-compose environment. Swagger used to see all exposed endpoints, you can see via the link below.

```
http://localhost:8080/swagger-ui/index.html
```

## Running the app on localhost with Docker

```shell
$ ./mvnw package
$ docker-compose up -d
```

## Running the app in Vagrant

```shell
$ vagrant up && vagrant ssh
$ cd /vagrant
$ ./mvnw package
$ docker-compose up -d
```