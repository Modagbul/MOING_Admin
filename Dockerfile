FROM openjdk:17-jdk
ARG JAR_FILE=./backend/build/libs/backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# 문서를 이미지의 /static/docs 디렉토리에 복사
COPY ./backend/build/docs/asciidoc/*.html /static/docs/

# 애플리케이션 실행 시 -cp 옵션을 사용하여 /static/docs 디렉토리를 클래스패스에 추가
ENTRYPOINT ["java","-cp",".:/static/docs","-jar","/app.jar"]


