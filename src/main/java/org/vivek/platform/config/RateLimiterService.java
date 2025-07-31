package org.vivek.platform.config;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {
    private final Map<String , Bucket> Bucketcache  = new ConcurrentHashMap<>();
    public Bucket resolveBucket(String clientId){
        return Bucketcache.computeIfAbsent(clientId, this::createBucket);
    }


    private Bucket createBucket(String key){
        Bandwidth limit = Bandwidth.classic(15, Refill.greedy(15, Duration.ofMinutes(1)));

        return Bucket.builder().addLimit(limit).build();
    }
}
