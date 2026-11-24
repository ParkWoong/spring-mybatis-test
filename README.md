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

`ApiRequestLogRepository.saveRequestHistory` adds a timestamp/random ID to `RequestLog` and
calls the annotation-based INSERT in `ApiRequestLogMapper`.
`ClientBackendApiRepository.getClientBackendApis` calls the SELECT in `ClientBackendApiMapper`.
`schema.sql` creates H2 tables and a sample route.

The INSERT depends on table column order. A null log returns 0. The timestamp/random suffix is
not guaranteed to be unique or thread-safe. H2 data is lost when the JVM exits.

MyBatisController exposes the existing SELECT and INSERT repository methods.

### Run and test

Use JDK 8 and Maven 3.6.3+. Set JAVA_HOME to your JDK 8 directory, then run from the repository
root:

```sh
java -version
mvn -version
mvn clean verify
mvn spring-boot:run
```

`RepositoryTest` checks insert/select, sample data, parameter binding, and null handling.

The app listens on localhost:8080. Unit tests cover the helper methods.

#### Postman API

##### 1. Select routes

**Request**

- Method: GET
- URL: http://localhost:8080/api/routes
- Body: none

**Response**

- Status: 200 OK

~~~json
[
  {
    "client_id": "sample-client",
    "backend_api": "http://localhost:9090"
  }
]
~~~

##### 2. Insert a request log

**Request**

- Method: POST
- URL: http://localhost:8080/api/request-logs
- Content-Type: application/json

~~~json
{
  "userId": "sample",
  "clientId": "sample-client",
  "requestUri": "/sample",
  "responseCode": "200"
}
~~~

**Response**

- Status: 200 OK

~~~json
{
  "inserted": 1
}
~~~

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

`ApiRequestLogRepository.saveRequestHistory`는 `RequestLog`에 시각·랜덤
ID를 넣고 `ApiRequestLogMapper`의 애너테이션 INSERT를 호출한다.
`ClientBackendApiRepository.getClientBackendApis`는
`ClientBackendApiMapper`의 SELECT를 호출한다. `schema.sql`이 H2 테이블과 샘플 경로를
만든다.

INSERT는 테이블 컬럼 순서에 의존한다. null 로그에는 0을 반환한다. 시각·랜덤 접미사는 고유성과 스레드 안전성을
보장하지 않는다. JVM 종료 시 H2 데이터는 사라집니다.

MyBatisController는 기존 저장소의 SELECT와 INSERT 메서드를 HTTP로 호출한다.

### 실행과 테스트

JDK 8, Maven 3.6.3 이상 사용. 저장소 루트에서 실행.

```sh
java -version
mvn -version
mvn clean verify
mvn spring-boot:run
```

`RepositoryTest`는 저장·조회, 샘플 데이터, 파라미터 바인딩 및 null 처리를 확인한다.

앱은 localhost:8080에서 실행된다. 단위 테스트는 내부 메서드를 확인한다.

#### Postman API

##### 1. 라우트 조회

**요청**

- 메서드: GET
- URL: http://localhost:8080/api/routes
- 본문: 없음

**응답**

- 상태: 200 OK

~~~json
[
  {
    "client_id": "sample-client",
    "backend_api": "http://localhost:9090"
  }
]
~~~

##### 2. 요청 로그 저장

**요청**

- 메서드: POST
- URL: http://localhost:8080/api/request-logs
- Content-Type: application/json

~~~json
{
  "userId": "sample",
  "clientId": "sample-client",
  "requestUri": "/sample",
  "responseCode": "200"
}
~~~

**응답**

- 상태: 200 OK

~~~json
{
  "inserted": 1
}
~~~
