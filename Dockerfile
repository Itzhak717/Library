From openjdk:11
copy ./target/library-server-1.0.0.jar library-server-1.0.0.jar
ENV LIBRARY_MYSQL_URL=jdbc:mysql://mysql:3306
ENV LIBRARY_MONGODB_URL=mongodb+srv://00123:00123password@cluster0.ysp8h.mongodb.net/library?retryWrites=true&w=majority
ENV LIBRARY_MYSQL_PASSWORD=1234
ENV LIBRARY_MYSQL_USERNAME=root
CMD ["java","-jar","library-server-1.0.0.jar"]

EXPOSE 8080