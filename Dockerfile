# 基于 Ubuntu 22.04 基础镜像
FROM ubuntu:22.04

# 设置环境变量，避免安装过程中出现交互提示
ENV DEBIAN_FRONTEND=noninteractive

# 更新系统并安装必要的依赖和 OpenJDK 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# 2. 设置工作目录
WORKDIR /app

# 3. 复制 JAR 文件到容器中
# 假设 JAR 文件位于 target/ 目录下
COPY target/web-1.0-SNAPSHOT.jar /app/web-1.0-SNAPSHOT.jar

# 4. 暴露应用的端口
EXPOSE 8080

# 5. 设置容器启动时的命令
CMD ["java", "-jar", "/app/web-1.0-SNAPSHOT.jar"]