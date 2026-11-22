# MyBatis Repository Extraction / MyBatis Repository 추출

[ENG](#eng) | [KR](#kr)

## ENG

Extracts the original request-log insert and client-backend select paths, including entities, mappers, repositories, and LogUtils.

### Source and minimal changes

Only generic package/class/table names and formatting changed in the extracted Java classes. Added H2 schema/sample rows and a test. Removed the generated RequestEntry record and its replacement validation/repository code.

| Original source | Extracted file |
|---|---|
| `ProxyApplication.java` | [DemoApplication.java](src/main/java/io/github/parkwoong/demo/DemoApplication.java) |
| `infra/entity/RequestLog.java` | [infra/entity/RequestLog.java](src/main/java/io/github/parkwoong/demo/infra/entity/RequestLog.java) |
| `infra/mapper/GfepApiRequestLogMapper.java` | [infra/mapper/ApiRequestLogMapper.java](src/main/java/io/github/parkwoong/demo/infra/mapper/ApiRequestLogMapper.java) |
| `infra/repository/GfepApiRequestLogRepository.java` | [infra/repository/ApiRequestLogRepository.java](src/main/java/io/github/parkwoong/demo/infra/repository/ApiRequestLogRepository.java) |
| `spec/util/LogUtils.java` | [spec/util/LogUtils.java](src/main/java/io/github/parkwoong/demo/spec/util/LogUtils.java) |
| `infra/mapper/GfepClientBackendApiMapper.java` | [infra/mapper/ClientBackendApiMapper.java](src/main/java/io/github/parkwoong/demo/infra/mapper/ClientBackendApiMapper.java) |
| `infra/repository/GfepClientBackendApiRepository.java` | [infra/repository/ClientBackendApiRepository.java](src/main/java/io/github/parkwoong/demo/infra/repository/ClientBackendApiRepository.java) |

### Structure

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

### How it works

ApiRequestLogRepository.saveRequestHistory sets timestamp/random ID and calls the annotation INSERT. ClientBackendApiRepository.getClientBackendApis calls the annotation SELECT. RepositoryTest checks both paths with H2 and sample data.

### Run and test

Use Java 17 and Maven 3.6.3+. Run from the repository root:

```sh
mvn clean verify
mvn spring-boot:run
```

The app starts without an HTTP server or an added console workflow. Use the tests under src/test for actual calls and results. Database modules initialize H2 at startup.

### Formatting

All Java files use google-java-format 1.24.0 with the AOSP style: four-space indentation, consistent line breaks, and organized imports.

### Original limits

Original INSERT relies on table column order. The original null input returns 0. LogUtils keeps its original timestamp/random behavior; no uniqueness guarantee is added. H2 data disappears when the JVM exits; production MySQL compatibility is not verified.

The build keeps Spring Boot 2.6.6 and the original MyBatis/JJWT APIs for source fidelity. Lombok 1.18.36 supports the local Java 17 build. These are legacy dependencies; this extraction is for learning, not a production-ready upgrade. No production integration or load test is included.

## KR

원본의 요청 로그 INSERT와 클라이언트별 백엔드 SELECT 경로를 엔티티·매퍼·Repository·LogUtils와 함께 추출했습니다.

### 원본과 최소 변경 사항

추출한 Java 클래스는 범용 패키지·클래스·테이블 이름과 포맷만 변경했습니다. H2 스키마·샘플 행 및 테스트를 추가했습니다. 새로 작성했던 RequestEntry record와 대체 검증·Repository 코드는 제거했습니다.

| 원본 파일 | 추출 파일 |
|---|---|
| `ProxyApplication.java` | [DemoApplication.java](src/main/java/io/github/parkwoong/demo/DemoApplication.java) |
| `infra/entity/RequestLog.java` | [infra/entity/RequestLog.java](src/main/java/io/github/parkwoong/demo/infra/entity/RequestLog.java) |
| `infra/mapper/GfepApiRequestLogMapper.java` | [infra/mapper/ApiRequestLogMapper.java](src/main/java/io/github/parkwoong/demo/infra/mapper/ApiRequestLogMapper.java) |
| `infra/repository/GfepApiRequestLogRepository.java` | [infra/repository/ApiRequestLogRepository.java](src/main/java/io/github/parkwoong/demo/infra/repository/ApiRequestLogRepository.java) |
| `spec/util/LogUtils.java` | [spec/util/LogUtils.java](src/main/java/io/github/parkwoong/demo/spec/util/LogUtils.java) |
| `infra/mapper/GfepClientBackendApiMapper.java` | [infra/mapper/ClientBackendApiMapper.java](src/main/java/io/github/parkwoong/demo/infra/mapper/ClientBackendApiMapper.java) |
| `infra/repository/GfepClientBackendApiRepository.java` | [infra/repository/ClientBackendApiRepository.java](src/main/java/io/github/parkwoong/demo/infra/repository/ClientBackendApiRepository.java) |

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

ApiRequestLogRepository.saveRequestHistory가 시각·랜덤 ID를 설정하고 애너테이션 INSERT를 호출합니다. ClientBackendApiRepository.getClientBackendApis는 애너테이션 SELECT를 호출합니다. RepositoryTest에서 H2와 샘플 데이터로 두 경로를 확인합니다.

### 실행과 테스트

Java 17, Maven 3.6.3 이상을 사용합니다. 저장소 루트에서 실행하세요:

```sh
mvn clean verify
mvn spring-boot:run
```

앱은 HTTP 서버나 별도 콘솔 시나리오 없이 시작합니다. 실제 호출과 결과 확인은 src/test의 테스트를 이용하세요. DB 모듈은 시작 시 H2를 초기화합니다.

### 코드 포맷

전체 Java 파일에 google-java-format 1.24.0의 AOSP 스타일을 적용했습니다. 들여쓰기 4칸, 일관된 줄바꿈 및 import 정리를 사용합니다.

### 유지한 원본 동작과 제한

원본 INSERT는 테이블 컬럼 순서에 의존합니다. null 입력에는 원본처럼 0을 반환합니다. LogUtils의 시각·랜덤 처리도 유지하며 고유성을 보장하지 않습니다. JVM 종료 시 H2 데이터가 사라지고 운영 MySQL 호환성은 검증하지 않습니다.

원본 코드 보존을 위해 Spring Boot 2.6.6과 원본 MyBatis/JJWT API를 사용합니다. 로컬 Java 17 빌드를 위해 Lombok 1.18.36을 적용했습니다. 레거시 의존성을 사용하는 학습용 추출본이며 운영용 업그레이드가 아닙니다. 운영 연동 및 부하 테스트는 포함하지 않습니다.
