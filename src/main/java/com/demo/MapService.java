package com.demo;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Just a service to wire in Hazelcast so that Spring will do the Hazelcast configuration
 * and start an instance.
 *
 * Spring will only start Hazelcast if it is used.
 *
 * __NOTE:__ Spring will configure and start a Hazelcast instance when caching is enabled through
 * `@EnableCaching` even if it is not explicitly wired in anywhere.
 */
@Service
public class MapService {

    @Autowired
    private HazelcastInstance instance;


    public void test() {
        System.out.println(instance.getConfig());
    }

}
