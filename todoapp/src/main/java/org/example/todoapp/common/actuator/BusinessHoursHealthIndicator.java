package org.example.todoapp.common.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class BusinessHoursHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        LocalTime now = LocalTime.now();
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(18, 0);

        if (now.isAfter(start) && now.isBefore(end)) {
            return Health.up().build();
        } else {
            return Health.down()
                    .withDetail("reason", "영업 종료 (09:00 ~ 18:00)")
                    .withDetail("currentTime", now)
                    .build();
        }
    }
}