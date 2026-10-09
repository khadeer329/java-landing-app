FROM tomcat:10.1-jdk21-temurin

# Remove default Tomcat applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Deploy the application
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Tomcat listens on port 8080
EXPOSE 8080

CMD ["catalina.sh", "run"]
