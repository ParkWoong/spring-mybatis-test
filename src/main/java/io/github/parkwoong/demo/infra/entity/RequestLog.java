package io.github.parkwoong.demo.infra.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RequestLog {

    private String tYmdhms;
    private String randomId;
    private String userId;
    private String clientId;
    private String tokenKeyId;
    private String requestUri;
    private String requestHeader;
    private String requestBody;
    private String responseCode;
}
