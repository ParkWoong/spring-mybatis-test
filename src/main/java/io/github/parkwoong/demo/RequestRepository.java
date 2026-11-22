package io.github.parkwoong.demo;

import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class RequestRepository {
 private final RequestMapper mapper;
 public RequestRepository(RequestMapper mapper) { this.mapper = mapper; }
 public void save(RequestEntry entry) {
  if (entry == null || entry.requestId() == null || entry.requestId().isBlank() || entry.method() == null || !entry.method().matches("[A-Z]{3,10}") || entry.status() < 100 || entry.status() > 599)
   throw new IllegalArgumentException("Invalid request entry");
  mapper.insert(entry);
 }
 public List<RequestEntry> findAll() { return mapper.findAll(); }
}
