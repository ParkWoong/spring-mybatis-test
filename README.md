# MyBatis Database Access / MyBatis DB 접근

[ENG](#eng) | [KR](#kr)

## ENG

### Project structure

```text
pom.xml
src/main/java/io/github/parkwoong/demo/DemoApplication.java
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

### Run and test

Use JDK 8 and Maven 3.6.3+. Set JAVA_HOME to your JDK 8 directory, then run from the repository root:

```sh
java -version
mvn -version
mvn clean verify
mvn spring-boot:run
```

`RepositoryTest` checks insert/select, sample data, parameter binding, and null handling.

The app starts without an HTTP server and exits. The test class shows the actual method calls and results. Database examples use H2 and need no external server.

## KR

### 프로젝트 구조

```text
pom.xml
src/main/java/io/github/parkwoong/demo/DemoApplication.java
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

### 실행과 테스트

JDK 8과 Maven 3.6.3 이상을 사용합니다. JAVA_HOME을 JDK 8 경로로 설정하고 저장소 루트에서 실행하세요:

```sh
java -version
mvn -version
mvn clean verify
mvn spring-boot:run
```

`RepositoryTest`는 저장·조회, 샘플 데이터, 파라미터 바인딩 및 null 처리를 확인합니다.

앱은 HTTP 서버 없이 시작 후 종료합니다. 실제 메서드 호출과 결과는 테스트 클래스에서 확인합니다. DB 예제는 H2를 사용하며 외부 서버는 필요하지 않습니다.
