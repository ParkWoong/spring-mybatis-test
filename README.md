# MyBatis Database Access / MyBatis 조회·저장

[ENG](#eng) | [KR](#kr)

## ENG

Shows annotation-based SQL, bound parameters, and a repository with MyBatis. An H2 in-memory database makes the example easy to run without a separate database server.

### Project structure

```text
pom.xml
src/
├── main/
│   ├── java/io/github/parkwoong/demo/
│   │   ├── DemoApplication.java
│   │   ├── RequestEntry.java
│   │   ├── RequestMapper.java
│   │   └── RequestRepository.java
│   └── resources/
│       ├── application.properties
│       └── schema.sql
└── test/java/io/github/parkwoong/demo/
    └── RequestRepositoryTest.java
```

Java package: `io.github.parkwoong.demo`.

| File | Role |
|---|---|
| [DemoApplication.java](src/main/java/io/github/parkwoong/demo/DemoApplication.java) | Starts Spring Boot and runs the example. |
| [RequestEntry.java](src/main/java/io/github/parkwoong/demo/RequestEntry.java) | A record holding requestId, method, and status. |
| [RequestMapper.java](src/main/java/io/github/parkwoong/demo/RequestMapper.java) | Defines INSERT and SELECT queries with MyBatis annotations and bound values. |
| [RequestRepository.java](src/main/java/io/github/parkwoong/demo/RequestRepository.java) | Validates input before saving and exposes a list query. |
| [application.properties](src/main/resources/application.properties) | Holds the demo settings. |
| [schema.sql](src/main/resources/schema.sql) | Creates the sample table. |
| [pom.xml](pom.xml) | Dependencies and build settings. |

### How it works

Spring initializes H2 with schema.sql. DemoApplication saves a sample RequestEntry through RequestRepository and prints findAll(). RequestMapper uses #{...} parameters instead of building SQL from input text. Snake-case columns map to Java record fields.

### Run

Requirements: Java 17+ and Maven 3.6.3+. Run these commands from the repository root.

```sh
mvn clean verify
mvn spring-boot:run
```

The console prints an entry with requestId=sample, method=GET, and status=200. The database URL is jdbc:h2:mem:demo;DB_CLOSE_DELAY=-1. No external database credentials are needed.

### Tests

RequestRepositoryTest saves and reads an ID containing SQL-like text, rejects an invalid status, and checks that a duplicate primary key fails.

[RequestRepositoryTest.java](src/test/java/io/github/parkwoong/demo/RequestRepositoryTest.java)

### Scope and limits

Data is lost when the JVM stops. This example has insert and list operations only, with no HTTP API or migration tool. It does not test compatibility with a production MySQL database.

This learning example uses Spring Boot 3.5.16. Before production use, move to a supported version; see the [3.5 OSS support notice](https://spring.io/blog/2026/06/25/spring-boot-3-5-16-available-now/). Production connections and load tests are not included.

Adapted from a Java proxy project using generic names and sample data. Original Git history, production settings, and business identifiers are not included.

## KR

MyBatis의 애너테이션 SQL, 파라미터 바인딩 및 Repository 패턴을 다룹니다. H2 메모리 DB를 사용하므로 별도 DB 서버 없이 실행할 수 있습니다.

### 프로젝트 구조

```text
pom.xml
src/
├── main/
│   ├── java/io/github/parkwoong/demo/
│   │   ├── DemoApplication.java
│   │   ├── RequestEntry.java
│   │   ├── RequestMapper.java
│   │   └── RequestRepository.java
│   └── resources/
│       ├── application.properties
│       └── schema.sql
└── test/java/io/github/parkwoong/demo/
    └── RequestRepositoryTest.java
```

Java 패키지: `io.github.parkwoong.demo`.

| 파일 | 역할 |
|---|---|
| [DemoApplication.java](src/main/java/io/github/parkwoong/demo/DemoApplication.java) | Spring Boot를 시작하고 예제를 실행합니다. |
| [RequestEntry.java](src/main/java/io/github/parkwoong/demo/RequestEntry.java) | requestId, method, status를 담는 record입니다. |
| [RequestMapper.java](src/main/java/io/github/parkwoong/demo/RequestMapper.java) | MyBatis 애너테이션과 바인딩 값으로 INSERT·SELECT 쿼리를 정의합니다. |
| [RequestRepository.java](src/main/java/io/github/parkwoong/demo/RequestRepository.java) | 저장 전 입력을 검증하고 전체 조회 기능을 제공합니다. |
| [application.properties](src/main/resources/application.properties) | 예제 실행 설정을 정의합니다. |
| [schema.sql](src/main/resources/schema.sql) | 샘플 테이블을 생성합니다. |
| [pom.xml](pom.xml) | 의존성과 빌드 설정입니다. |

### 동작 방식

Spring이 schema.sql로 H2를 초기화합니다. DemoApplication이 RequestRepository를 통해 샘플 RequestEntry를 저장하고 findAll() 결과를 출력합니다. RequestMapper는 입력 문자열로 SQL을 조합하지 않고 #{...} 파라미터를 사용합니다. snake_case 컬럼은 Java record 필드에 매핑됩니다.

### 실행 방법

Java 17 이상, Maven 3.6.3 이상이 필요합니다. 저장소 루트에서 실행하세요.

```sh
mvn clean verify
mvn spring-boot:run
```

콘솔에는 requestId=sample, method=GET, status=200인 항목이 출력됩니다. DB 주소는 jdbc:h2:mem:demo;DB_CLOSE_DELAY=-1이며 외부 DB 계정 정보가 필요하지 않습니다.

### 테스트

RequestRepositoryTest는 SQL처럼 보이는 문자가 포함된 ID의 저장·조회, 잘못된 상태 코드 거부 및 기본 키 중복 오류를 확인합니다.

[RequestRepositoryTest.java](src/test/java/io/github/parkwoong/demo/RequestRepositoryTest.java)

### 범위와 제한

JVM이 종료되면 데이터가 사라집니다. 저장·전체 조회만 제공하며 HTTP API와 마이그레이션 도구는 포함하지 않습니다. 운영 MySQL DB와의 호환성은 검증하지 않습니다.

Spring Boot 3.5.16 기반 학습용 예제입니다. 운영 도입 전 지원 버전으로 전환하세요. [3.5 OSS 지원 종료 안내](https://spring.io/blog/2026/06/25/spring-boot-3-5-16-available-now/)를 참고할 수 있습니다. 운영 시스템 연결 및 부하 테스트는 포함하지 않습니다.

기존 Java 프록시 프로젝트에서 기술을 추출해 범용 이름과 샘플 데이터로 재작성했습니다. 기존 Git 이력, 운영 설정 및 업무 식별자는 포함하지 않습니다.
