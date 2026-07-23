package org.example.todoapp.common.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // 외부 서버 상태 체크 로직
        boolean serverIsUp = checkExternalServer();

        if (serverIsUp) {
            return Health.up()
                    .withDetail("externalServer", "OK")
                    .withDetail("responseTime", "150ms")
                    .build();
        } else {
            return Health.down()
                    .withDetail("externalServer", "Not Responding")
                    .withDetail("error", "Connection timeout")
                    .build();
        }
    }

    private boolean checkExternalServer() {
        // 실제 서버 체크 로직 (HTTP 요청, Ping 등)
        try {
            // 외부 API 호출 예시
            // RestTemplate, WebClient 등 사용
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}