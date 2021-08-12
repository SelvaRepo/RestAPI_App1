package com.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public interface Constants {
    String issuer = "fms";
    String secretKey = "DB4AEF4719809709E560ED8DE2F9C77B886B963B28BA20E9A8A621BBD4ABA400";
    Date expiry = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
    Date issueDate = Date.from(Instant.now());
    String[] trustedAud = {"mlsc-po","mlsc-batch"};
}
