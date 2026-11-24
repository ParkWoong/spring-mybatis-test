# MyBatis Database Access / MyBatis DB 접근

[ENG](#eng) | [KR](#kr)

## ENG

### Project structure

```text
pom.xml
src/main/java/io/github/parkwoong/demo/DemoApplication.java
src/main/java/io/github/parkwoong/demo/MyBatisController.java
src/main/java/io/github/parkwoong/demo/infra/entity/RequestLog.java
src/main/java/io/github/parkwoong/demo/infra/mapper/ApiRequestLogMapper.java
src/main/java/io/github/parkwoong/demo/infra/mapper/ClientBackendApiMapper.java
src/main/java/io/github/parkwoong/demo/infra/repository/ApiRequestLogRepository.java
src/main/java/io/github/parkwoong/demo/infra/repository/ClientBackendApiRepository.java
src/main/java/io/github/parkwoong/demo/spec/util/LogUtils.java
src/main/resources/application.properties
src/main/resources/schema.sql
src/test/java/io/github/parkwoong/demo/RepositoryTest.java
```

### Code explanation

`ApiRequestLogRepository.saveRequestHistory` adds a timestamp/random ID to `RequestLog` and calls the annotation-based INSERT in `ApiRequestLogMapper`. `ClientBackendApiRepository.getClientBackendApis` calls the SELECT in `ClientBackendApiMapper`. `schema.sql` creates H2 tables and a sample route.

The INSERT depends on table column order. A null log returns 0. The timestamp/random suffix is not guaranteed to be unique or thread-safe. H2 data is lost when the JVM exits.

MyBatisController exposes the existing SELECT and INSERT repository methods.

### Run and test

Use JDK 8 and Maven 3.6.3+. Set JAVA_HOME to your JDK 8 directory, then run from the repository root:

```sh
java -version
mvn -version
mvn clean verify
mvn spring-boot:run
```

`RepositoryTest` checks insert/select, sample data, parameter binding, and null handling.

The app listens on localhost:8080. The original unit tests still check the underlying methods.

#### Postman API

Call GET http://localhost:8080/api/routes with no body. Expected: HTTP 200, [{"client_id":"sample-client","backend_api":"http://localhost:9090"}]. To test INSERT, send POST http://localhost:8080/api/request-logs with Content-Type: application/json and body {"userId":"sample","clientId":"sample-client","requestUri":"/sample","responseCode":"200"}. Expected: HTTP 200, {"inserted":1}.

## KR

### 프로젝트 구조

```text
pom.xml
src/main/java/io/github/parkwoong/demo/DemoApplication.java
src/main/java/io/github/parkwoong/demo/MyBatisController.java
src/main/java/io/github/parkwoong/demo/infra/entity/RequestLog.java
src/main/java/io/github/parkwoong/demo/infra/mapper/ApiRequestLogMapper.java
src/main/java/io/github/parkwoong/demo/infra/mapper/ClientBackendApiMapper.java
src/main/java/io/github/parkwoong/demo/infra/repository/ApiRequestLogRepository.java
src/main/java/io/github/parkwoong/demo/infra/repository/ClientBackendApiRepository.java
src/main/java/io/github/parkwoong/demo/spec/util/LogUtils.java
src/main/resources/application.properties
src/main/resources/schema.sql
src/test/java/io/github/parkwoong/demo/RepositoryTest.java
```

### 코드 설명

`ApiRequestLogRepository.saveRequestHistory`는 `RequestLog`에 시각·랜덤 ID를 넣고 `ApiRequestLogMapper`의 애너테이션 INSERT를 호출합니다. `ClientBackendApiRepository.getClientBackendApis`는 `ClientBackendApiMapper`의 SELECT를 호출합니다. `schema.sql`이 H2 테이블과 샘플 경로를 만듭니다.

INSERT는 테이블 컬럼 순서에 의존합니다. null 로그에는 0을 반환합니다. 시각·랜덤 접미사는 고유성과 스레드 안전성을 보장하지 않습니다. JVM 종료 시 H2 데이터는 사라집니다.

MyBatisController는 기존 저장소의 SELECT와 INSERT 메서드를 HTTP로 호출합니다.

### 실행과 테스트

JDK 8과 Maven 3.6.3 이상을 사용합니다. JAVA_HOME을 JDK 8 경로로 설정하고 저장소 루트에서 실행하세요:

```sh
java -version
mvn -version
mvn clean verify
mvn spring-boot:run
```

`RepositoryTest`는 저장·조회, 샘플 데이터, 파라미터 바인딩 및 null 처리를 확인합니다.

앱은 localhost:8080에서 실행됩니다. 기존 단위 테스트는 내부 메서드를 계속 확인합니다.

#### Postman API

본문 없이 GET http://localhost:8080/api/routes를 호출하세요. 예상 결과: HTTP 200, [{"client_id":"sample-client","backend_api":"http://localhost:9090"}]. INSERT를 확인하려면 Content-Type: application/json으로 POST http://localhost:8080/api/request-logs에 {"userId":"sample","clientId":"sample-client","requestUri":"/sample","responseCode":"200"}을 보내세요. 예상 결과: HTTP 200, {"inserted":1}입니다.
