```shell
docker run \
    -e MYSQL_ROOT_PASSWORD=root_super_password \
    -e MYSQL_USER=briannqc \
    -e MYSQL_PASSWORD=P@ssw0rd \
    -e MYSQL_DATABASE=spring_quickly \
    -p 33061:3306 \
    -d --rm \
    mysql --default-authentication-plugin=mysql_native_password
```