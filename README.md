This is an example API project written with Java using Spring Boot Framework. Test codes have been written for all Controller and Service functions.
Two environment variables are used `prod` and `dev` which are taken by docker-compose environment. Swagger used to see all exposed endpoints, you can see via the link below.

```
http://localhost:8080/swagger-ui/index.html
```

There are available two endpoint as `/getPrice/{from}-{to}` and `/save`. Endpoint of `/getPrice/{from}-{to}` gives you the current price of selected crypto currency. Endpoint of `/save` allows you to buy or sell crypto currencies.

## Requirements
1. Only USD or EUR can be selected.
2. Crypto currrency price is only available for only 10 seconds.


## Profiles
Two profiles are defined as Dev and Prod. You may change the profile in the docker-compose.yml with the following line.

`SPRING_PROFILES_ACTIVE=prod`

You can see `dev` profile listening on 8080 port and `prod` profile listening on 8090 port.


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
