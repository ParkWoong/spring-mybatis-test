package io.github.parkwoong.demo;

import static org.junit.jupiter.api.Assertions.*;

import io.github.parkwoong.demo.infra.entity.RequestLog;
import io.github.parkwoong.demo.infra.repository.ApiRequestLogRepository;
import io.github.parkwoong.demo.infra.repository.ClientBackendApiRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class RepositoryTest {
    @Autowired ApiRequestLogRepository logs;
    @Autowired ClientBackendApiRepository routes;
    @Autowired JdbcTemplate jdbc;

    @Test
    void originalInsertAndSelect() {
        assertEquals(0, logs.saveRequestHistory(null));
        var entry =
                RequestLog.builder()
                        .userId("sample' --")
                        .requestUri("/sample")
                        .responseCode("200")
                        .build();
        assertEquals(1, logs.saveRequestHistory(entry));
        assertEquals(
                "sample' --",
                jdbc.queryForObject("select user_id from api_request_log", String.class));
        assertEquals(17, entry.getTYmdhms().length());
        assertEquals(
                "http://localhost:9090", routes.getClientBackendApis().get(0).get("backend_api"));
    }
}
