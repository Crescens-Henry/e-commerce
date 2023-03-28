package com.escuelita.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.escuelita.demo.services.interfaces.ISNSService;

@Service
public class SNSServiceImpl implements ISNSService {

    @Value(value = "${aws.topic-arn-sns}")
    private String snsTopicArn;

    @Autowired
    private AmazonSNSClient snsClient;

    @Override
    public String sendNotificationOfSuscribe() {
        String message = buildMessageBody();
        PublishRequest publishRequest = new PublishRequest(snsTopicArn, message);
        PublishResult result = snsClient.publish(publishRequest);
        return result.getMessageId();
    }

    @Override
    public String subscribeAnUserWithEmail(String email) {
        SubscribeRequest subscribeRequest = new SubscribeRequest(snsTopicArn, "email", email);
        SubscribeResult subscribeResult = snsClient.subscribe(subscribeRequest);
        return subscribeResult.getSubscriptionArn();
    }

    private String buildMessageBody() {
        return "Hola, te has suscrito al mejor e-commerce" +
                "\nrecibiras promociones y descuentos y mas!";

    }
}
