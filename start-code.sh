#!/bin/bash
cd `dirname $0`

img_mvn="maven:3.3.3-jdk-8"                 # docker image of maven
m2_cache=~/.m2                              # the local maven cache dir
proj_home=$PWD                              # the project root dir
img_output="deepexi/springcloud-demo"       # output image tag

git pull  # should use git clone https://name:pwd@xxx.git

echo "use docker maven"
docker run --rm \
   -v $m2_cache:/root/.m2 \
   -v $proj_home:/usr/src/mymaven \
   -w /usr/src/mymaven $img_mvn mvn clean package -U

docker build -t $img_output .

mkdir -p $PWD/logs
chmod 777 $PWD/logs

# 删除容器
docker rm -f springcloud-demo &> /dev/null

version=`date "+%Y%m%d%H"`

# 启动镜像
docker run -d --restart=on-failure:5 --privileged=true \
    -w /home \
    -v $PWD/logs:/home/logs \
    --net=host \
    --name springcloud-demo deepexi/springcloud-demo \
    java \
        -Djava.security.egd=file:/dev/./urandom \
        -Duser.timezone=Asia/Shanghai \
        -Denv=DEV \
        -Dapollo.configService=http://118.31.184.251:8080 \
        -XX:+PrintGCDateStamps \
        -XX:+PrintGCTimeStamps \
        -XX:+PrintGCDetails \
        -XX:+HeapDumpOnOutOfMemoryError \
        -Xloggc:logs/gc_$version.log \
        -jar /home/springcloud-demo.jar \
          --spring.profiles.active=prod \
          --eureka.client.serviceUrl.defaultZone=http://118.31.184.251:8080/eureka/ \
          --app.id=demo \
          --apollo.meta=http://118.31.184.251:8080 \
          --spring.cloud.sentinel.transport.dashboard=118.31.184.251:8089