package io.github.parkwoong.demo.infra.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClientBackendApiMapper {

    @Select("select * from client_backend_api")
    List<Map<String, String>> selectAllClientBackendApi();
}
