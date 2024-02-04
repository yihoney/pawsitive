package com.pawsitive.config;

import com.pawsitive.chatgroup.handler.StompHandshakeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //클라이언트에서 보낸 메세지를 받을 prefix
        registry.setApplicationDestinationPrefixes("/pub"); // 보내기
        //해당 주소를 구독하고 있는 클라이언트들에게 메세지 전달
        registry.enableSimpleBroker("/sub"); //받기
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat")   //SockJS 연결 주소
            .addInterceptors(new StompHandshakeInterceptor())
            .setAllowedOriginPatterns("http://localhost:3000", "https://localhost:3000",
                "https://i10c111.p.ssafy.io")
//            .setAllowedOriginPatterns("*")
            .withSockJS()
            .setDisconnectDelay(30 * 1000)
            .setClientLibraryUrl(
                "https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.4/sockjs.min.js");
    }

}
