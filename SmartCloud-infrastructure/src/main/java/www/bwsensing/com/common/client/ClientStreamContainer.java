package www.bwsensing.com.common.client;

import www.bwsensing.com.domainevent.ClientMessagePushEvent;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author macos-zyj
 */
public class ClientStreamContainer {

    private static ConcurrentLinkedDeque<ClientMessagePushEvent> linkedDeque = new ConcurrentLinkedDeque<>();

    public static ClientMessagePushEvent poll(){
        return linkedDeque.poll();
    }

    public static ClientMessagePushEvent peek(){
        return linkedDeque.peek();
    }

    public static Integer getSize(){
        return linkedDeque.size();
    }

    public static void offer(ClientMessagePushEvent event){
        linkedDeque.offer(event);
    }
}

