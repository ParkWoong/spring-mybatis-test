package io.github.parkwoong.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RequestRepositoryTest {
 @Autowired RequestRepository repository;
 @Test void parameterizedInsertAndValidation() {
  var entry = new RequestEntry("test' --", "POST", 201);
  repository.save(entry);
  assertTrue(repository.findAll().contains(entry));
  assertThrows(IllegalArgumentException.class, () -> repository.save(new RequestEntry("bad", "GET", 999)));
  assertThrows(org.springframework.dao.DuplicateKeyException.class, () -> repository.save(entry));
 }
}
