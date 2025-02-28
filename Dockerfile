# 1. 选择一个基础镜像
FROM dragonwell-registry.cn-hangzhou.cr.aliyuncs.com/dragonwell/dragonwell:17

# 2. 设置工作目录
WORKDIR /app

# 3. 复制 JAR 文件到容器中
# 假设 JAR 文件位于 target/ 目录下
COPY target/web-1.0-SNAPSHOT.jar /app/web-1.0-SNAPSHOT.jar

# 4. 暴露应用的端口
EXPOSE 8080

# 5. 设置容器启动时的命令
CMD ["java", "-jar", "/app/web-1.0-SNAPSHOT.jar"]