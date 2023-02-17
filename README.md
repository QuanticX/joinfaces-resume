JoinFaces Resume Example
=====

[//]: # ([![Build Status]&#40;https://github.com/joinfaces/joinfaces-maven-jar-example/actions/workflows/maven.yml/badge.svg&#41;]&#40;https://github.com/joinfaces/joinfaces-maven-jar-example/actions&#41;)

[//]: # ([![Codecov]&#40;https://codecov.io/gh/joinfaces/joinfaces-maven-jar-example/branch/4.7.x/graph/badge.svg&#41;]&#40;https://codecov.io/gh/joinfaces/joinfaces-maven-jar-example&#41;)

[//]: # ([![Bugs]&#40;https://sonarcloud.io/api/project_badges/measure?project=joinfaces_joinfaces-maven-jar-example&metric=bugs&#41;]&#40;https://sonarcloud.io/dashboard?id=joinfaces_joinfaces-maven-jar-example&#41;)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This SAP (Single Page Application) illustrates JSF usage inside JAR packaged Spring Boot Application.

[JoinFaces](http://joinfaces.org) autoconfigures [PrimeFaces](http://primefaces.org/), [BootsFaces](http://bootsfaces.net/), [ButterFaces](http://butterfaces.org), [OmniFaces](http://omnifaces.org/), [AngularFaces](http://angularfaces.com/), [Mojarra](https://eclipse-ee4j.github.io/mojarra/) and [MyFaces](http://myfaces.apache.org) libraries to run at embedded [Tomcat](http://tomcat.apache.org/), [Jetty](http://www.eclipse.org/jetty) or [Undertow](http://undertow.io/). It autoconfigures [Weld](http://weld.cdi-spec.org) and [Rewrite](https://www.ocpsoft.org/rewrite/) too.

## Run Example Application locally with maven and jdk 17

1- Clone this project
```Shell
git clone https://github.com/QuanticX/joinfaces-resume.git
```

2- Build
```Shell
mvn clean install
```

3- Run
```Shell
java -jar target/joinfaces-resume-5.0.x.jar
```

4- Access starter page at **http://localhost:8080/** This page can help you to choose the JoinFaces Starter that fits your needs. You may log in with credentials

| User       | Password | Roles      |
|------------|----------|------------|
| persapiens | 123      | ROLE_ADMIN |
| nyilmaz    | qwe      | ROLE_USER  |

Optional: If your IDE is showing build errors install [Lombok](https://projectlombok.org/setup/overview)

## Running in Docker environment


1- Clone this project
```Shell
git clone https://github.com/QuanticX/joinfaces-resume.git
```

2- Build and run
```Shell
docker-compose up
```

