package io.github.parkwoong.demo;

import io.github.parkwoong.demo.infra.entity.RequestLog;
import io.github.parkwoong.demo.infra.repository.ApiRequestLogRepository;
import io.github.parkwoong.demo.infra.repository.ClientBackendApiRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class MyBatisController {
    private final ClientBackendApiRepository routes;
    private final ApiRequestLogRepository logs;

    public MyBatisController(ClientBackendApiRepository routes, ApiRequestLogRepository logs) {
        this.routes = routes;
        this.logs = logs;
    }

    @GetMapping("/api/routes")
    public List<Map<String, String>> routes() {
        return routes.getClientBackendApis();
    }

    @PostMapping("/api/request-logs")
    public Map<String, Integer> log(@RequestBody Map<String, String> input) {
        RequestLog requestLog =
                RequestLog.builder()
                        .userId(input.get("userId"))
                        .clientId(input.get("clientId"))
                        .tokenKeyId("sample-key")
                        .requestUri(input.get("requestUri"))
                        .requestHeader("{}")
                        .requestBody("{}")
                        .responseCode(input.get("responseCode"))
                        .build();
        return Collections.singletonMap("inserted", logs.saveRequestHistory(requestLog));
    }
}
