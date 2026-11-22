package io.github.parkwoong.demo.infra.repository;

import io.github.parkwoong.demo.infra.mapper.ClientBackendApiMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ClientBackendApiRepository {

    @Autowired private ClientBackendApiMapper mapper;

    public List<Map<String, String>> getClientBackendApis() {

        return mapper.selectAllClientBackendApi();
    }
}
