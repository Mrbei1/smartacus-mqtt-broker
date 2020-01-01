package com.message;

import com.handler.MqttHandler;
import com.handler.RetransmissionHandler;
import io.netty.channel.EventLoop;
import io.netty.handler.codec.mqtt.MqttSubscribeMessage;
import io.netty.util.concurrent.Promise;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @Author: chihaojie
 * @Date: 2020/1/1 13:43
 * @Version 1.0
 * @Note
 */
public class MqttPendingSubscription {

    private final Promise<Void> future;
    private final String topic;
    private final Set<MqttPendingHandler> handlers = new HashSet<>();
    private final MqttSubscribeMessage subscribeMessage;

    private final RetransmissionHandler<MqttSubscribeMessage> retransmissionHandler = new RetransmissionHandler<>();

    private boolean sent = false;

    public MqttPendingSubscription(Promise<Void> future, String topic, MqttSubscribeMessage message) {
        this.future = future;
        this.topic = topic;
        this.subscribeMessage = message;

        this.retransmissionHandler.setOriginalMessage(message);
    }

    public Promise<Void> getFuture() {
        return future;
    }

    public     String getTopic() {
        return topic;
    }

   public boolean isSent() {
        return sent;
    }

    public  void setSent(boolean sent) {
        this.sent = sent;
    }

    public   MqttSubscribeMessage getSubscribeMessage() {
        return subscribeMessage;
    }

    public    void addHandler(MqttHandler handler, boolean once){
        this.handlers.add(new MqttPendingHandler(handler, once));
    }

    public  Set<MqttPendingHandler> getHandlers() {
        return handlers;
    }

    public  void startRetransmitTimer(EventLoop eventLoop, Consumer<Object> sendPacket) {
        if(this.sent){ //If the packet is sent, we can start the retransmit timer
            this.retransmissionHandler.setHandle((fixedHeader, originalMessage) ->
                    sendPacket.accept(new MqttSubscribeMessage(fixedHeader, originalMessage.variableHeader(), originalMessage.payload())));
            this.retransmissionHandler.start(eventLoop);
        }
    }

    public   void onSubackReceived(){
        this.retransmissionHandler.stop();
    }

    public final class MqttPendingHandler {
        private final MqttHandler handler;
        private final boolean once;

        MqttPendingHandler(MqttHandler handler, boolean once) {
            this.handler = handler;
            this.once = once;
        }

        public      MqttHandler getHandler() {
            return handler;
        }

        boolean isOnce() {
            return once;
        }
    }
}
