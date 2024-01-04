package com.reporting.healthCheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthMonitor implements HealthIndicator {

    @Override
    public Health health() {

        if(isHealthIsGood()){
            return Health.up().withDetail("Service " , " Health is good").build();
        }
        else {
            return Health.down().withDetail("Service" , " Health is not Up").build();
        }
    }

    private boolean isHealthIsGood() {
        return true;
    }
}
