# Ratings

## Setting up the MySQL Database localy.

```console
$ docker run -d --rm --name ratings-db \
    -e MYSQL_ROOT_PASSWORD=password \
    -v $(pwd)/hack/mysql:/docker-entrypoint-initdb.d:ro \
    -p 3307:3306 \
    mysql:8.0
```

## Build ratings container.

```conosle
$ ./mvnw clean package -Dmaven.test.skip=true \
    -Dquarkus.container-image.build=true \
    -Dquarkus.container-image.push=true \
    -Dquarkus.container-image.group=registry.gitlab.com/cloudnative_impress/bookinfo-microservices \
    -Dquarkus.container-image.tag=latest
```