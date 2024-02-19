package com.example.springproject.aws.sqs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.example.springproject.common.EnvironmentVariables;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqsConfig {

    @Bean(destroyMethod = "shutdown")
    public AmazonSQS AmazonSQS(){
        return AmazonSQSAsyncClient.asyncBuilder()
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        EnvironmentVariables.AWS_USER, EnvironmentVariables.AWS_PASSPHRASE
                                )
                        )
                )
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                EnvironmentVariables.AWS_ENDPOINT_URL, EnvironmentVariables.AWS_REGION
                        )
                )
                .build();

                /*AmazonSQSClientBuilder.standard()
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
                .build();*/ // non-Async client configuration
    }
}
