# ==============================================================
# CONFIGURACIÓN GENERAL
# ==============================================================
spring.application.name=grocery-backend
server.port=8081

# ==============================================================
# CONEXIÓN A AZURE MYSQL (PRODUCCIÓN)
# ==============================================================
# Cambiamos localhost por tu servidor engrocer y la DB ecomerce en minúsculas
spring.datasource.url=jdbc:mysql://engrocer.mysql.database.azure.com:3306/ecomerce?useSSL=true&requireSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true

# Credenciales de Azure (Asegúrate de poner tu contraseña real abajo)
spring.datasource.username=admin_marygca
spring.datasource.password=Je#190523

# Driver oficial de MySQL para Azure Flexible Server
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# ==============================================================
# CONFIGURACIÓN DE JPA / HIBERNATE
# ==============================================================
# 'none' porque ya creaste las tablas manualmente con el archivo SQL
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

# ==============================================================
# CONFIGURACIÓN DE ENCODING Y PERFILES
# ==============================================================
spring.profiles.active=prod
server.servlet.encoding.charset=UTF-8
server.servlet.encoding.enabled=true
server.servlet.encoding.force=true

# ==============================================================
# API EXTERNA
# ==============================================================
spoonacular.api.key=c2627e8cfc5e4a96a340accdac387cd6
