package io.github.parkwoong.demo.spec.util;

import org.springframework.http.HttpHeaders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class LogUtils {

    private static final SimpleDateFormat T_FORMAT = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    private static final Random RANDOM = new Random();

    public static String getTimeNow() {

        return T_FORMAT.format(new Date());
    }

    // 0 ~ 99 random num string
    public static String getRandom() {

        return getRandom(100);
    }

    public static String getRandom(int bound) {

        return String.valueOf(RANDOM.nextInt(bound));
    }

    public static String getHeaderStringWithoutAuthorization(HttpHeaders headers) {

        if (headers == null) return "[]";

        final StringBuilder sb = new StringBuilder("[");

        headers.forEach(
                (k, v) -> {
                    if (!"Authorization".equals(k) && !"authorization".equals(k))
                        sb.append(k).append(":\"").append(String.join(";", v)).append("\" ");
                });

        return sb.append("]").toString();
    }
}
