package io.github.parkwoong.demo.infra.repository;

import io.github.parkwoong.demo.infra.entity.RequestLog;
import io.github.parkwoong.demo.infra.mapper.ApiRequestLogMapper;
import io.github.parkwoong.demo.spec.util.LogUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApiRequestLogRepository {

    @Autowired private ApiRequestLogMapper mapper;

    public int saveRequestHistory(RequestLog history) {

        if (history == null) return 0;

        history.setTYmdhms(LogUtils.getTimeNow());
        history.setRandomId(LogUtils.getRandom()); // 0 ~ 99

        return mapper.insertRequestHistory(history);
    }
}
