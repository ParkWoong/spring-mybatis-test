package io.github.parkwoong.demo;

import org.apache.ibatis.annotations.*;
import java.util.List;
@Mapper
public interface RequestMapper {
 @Insert("INSERT INTO request_entry (request_id, method, status) VALUES (#{requestId}, #{method}, #{status})")
 int insert(RequestEntry entry);
 @Select("SELECT request_id, method, status FROM request_entry ORDER BY request_id")
 List<RequestEntry> findAll();
}
