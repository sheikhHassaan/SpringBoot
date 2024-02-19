package com.example.springproject.aws;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SqsSnsIntegrationTest {

    @Test
    @Order(1)
    void testCreateTopic() {

    }

    @Test
    @Order(2)
    void testCreateQueues() {

    }

    @Test
    @Order(3)
    void testSubscriptions() {

    }

    @Test
    @Order(4)
    void testPublish() {

    }
}