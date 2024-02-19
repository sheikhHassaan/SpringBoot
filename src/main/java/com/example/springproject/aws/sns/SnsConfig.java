package com.example.springproject.aws.sns;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.example.springproject.common.EnvironmentVariables;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnsConfig {

    @Bean(destroyMethod = "shutdown")
    public AmazonSNS amazonSNS() {
        return AmazonSNSClientBuilder.standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                EnvironmentVariables.AWS_ENDPOINT_URL, EnvironmentVariables.AWS_REGION
                        )
                )
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        EnvironmentVariables.AWS_USER, EnvironmentVariables.AWS_PASSPHRASE
                                )
                        )
                )
                .build();
    }
}

    /*

note:
 create SNS topic
 list SNS topics
 publish SNS message
 subscribe to SNS topic
 list subscriptions
 unsubscribe from SNS topic
 delete SNS topic



    public String createTopic(String topicName){        // returns created topic's ARN

        return amazonSNS().createTopic(topicName).getTopicArn();
    }

    public List<Topic> listSnsTopics(){
        return amazonSNS().listTopics().getTopics();
    }

    /*public PublishResult publishSnsMessage(String topicArn, String message, String subject){
        return amazonSNS().publish(topicArn, message, subject);
    }
    public PublishResult publishSnsMessage(PublishRequest publishRequest){
        return amazonSNS().publish(publishRequest);
    }

//    public SubscribeResult subscribeToSnsTopic(String topicArn, String protocol, String targetEndpoint){   // subscribe(String (SNS topic ARN)topicArn, String ("sqs") protocol, String (SQS ARN) endpoint)
//        return amazonSNS().subscribe(topicArn, protocol, targetEndpoint);
//    }

    public SubscribeResult subscribeToSnsTopic(SubscribeRequest subscribeRequest){
        return amazonSNS().subscribe(subscribeRequest);
    }

    public List<Subscription> listSubscriptions(){
        return amazonSNS().listSubscriptions().getSubscriptions();
    }

    public UnsubscribeResult unsubscribeFromSnsTopic(String subscriptionArn){
        return amazonSNS().unsubscribe(subscriptionArn);
    }

    public String deleteSnsTopic(String topicArn){
        return amazonSNS().deleteTopic(topicArn).toString();
    }

    */ //note: no need for all of this