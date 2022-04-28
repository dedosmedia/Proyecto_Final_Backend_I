CREATE USER 'superuser'@'%' IDENTIFIED BY 'password';
CREATE USER 'movies'@'%' IDENTIFIED BY 'password';

/*
    Se podría crear la BD manualmente, pero en el docker-compose, para la imagen de bitnami/mysql
    es posible pasarle la variable de entorno MYSQL_DATABASE y éste la crea automáticamente
    CREATE DATABASE IF NOT EXISTS movies_database;
 */

GRANT ALL ON movies_database.* TO 'superuser'@'%';
GRANT SELECT, UPDATE, INSERT , DELETE, CREATE, DROP ON movies_database.* TO 'movies'@'%';

FLUSH PRIVILEGES;
