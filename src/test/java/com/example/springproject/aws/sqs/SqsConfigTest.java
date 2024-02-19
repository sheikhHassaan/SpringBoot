package com.example.springproject.aws.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class SqsConfigTest {

    String queue = UUID.randomUUID().toString();
    String queueUrl;
    @Autowired
    AmazonSQS amazonSQS;
    String messageReceiptHandle;


    @Test
    @Order(1)
    void testCreateQueue() {
        CreateQueueResult createQueueResult = amazonSQS.createQueue(queue);
        queueUrl = createQueueResult.getQueueUrl();
        assertEquals(200, createQueueResult.getSdkHttpMetadata().getHttpStatusCode());
    }

    @Test
    @Order(1)
    void testCreateFifoQueue() {
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queue+".fifo");
        createQueueRequest.addAttributesEntry("FifoQueue", "true");
        CreateQueueResult createFifoQueueResult = amazonSQS.createQueue(createQueueRequest);
        assertEquals(200, createFifoQueueResult.getSdkHttpMetadata().getHttpStatusCode());
    }

    @Test
    @Order(2)
    void testListQueues() {
        ListQueuesResult listQueuesResult = amazonSQS.listQueues();
        assertEquals(200, listQueuesResult.getSdkHttpMetadata().getHttpStatusCode());
        assertTrue(listQueuesResult.getQueueUrls().contains(queue));
    }

    @Test
    @Order(3)
    void testSendMessage() {
        SendMessageRequest sendMessageRequest = new SendMessageRequest(queueUrl, "This message is pushed to the queue "+queue+".");
        SendMessageResult sendMessageResult = amazonSQS.sendMessage(sendMessageRequest);
        assertEquals(200, sendMessageResult.getSdkHttpMetadata().getHttpStatusCode());
        assertNotNull(sendMessageResult.getMessageId());
    }

    @Test
    @Order(4)
    void testReceiveMessage(){
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
        ReceiveMessageResult receiveMessageResult = amazonSQS.receiveMessage(receiveMessageRequest);
        Message message = receiveMessageResult.getMessages().isEmpty() ? null : receiveMessageResult.getMessages().getFirst();
        if (message != null) messageReceiptHandle = message.getReceiptHandle();
        assertNotNull(message);
    }

    @Test
    @Order(5)
    void testDeleteMessage() {
        DeleteMessageRequest deleteMessageRequest = new DeleteMessageRequest(queueUrl, messageReceiptHandle);
        DeleteMessageResult deleteMessageResult = amazonSQS.deleteMessage(deleteMessageRequest);
        assertEquals(200, deleteMessageResult.getSdkHttpMetadata().getHttpStatusCode());
    }

    @Test
    @Order(6)
    void testDeleteQueue() {
        DeleteQueueRequest deleteQueueRequest = new DeleteQueueRequest(queueUrl);
        DeleteQueueResult deleteQueueResult = amazonSQS.deleteQueue(deleteQueueRequest);
        assertEquals(200, deleteQueueResult.getSdkHttpMetadata().getHttpStatusCode());
    }

}