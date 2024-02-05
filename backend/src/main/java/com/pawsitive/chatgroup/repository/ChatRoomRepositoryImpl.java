package com.pawsitive.chatgroup.repository;

import com.pawsitive.chatgroup.dto.response.ChatRes;
import com.pawsitive.chatgroup.entity.Chat;
import com.pawsitive.chatgroup.entity.ChatRoom;
import com.pawsitive.chatgroup.entity.QChat;
import com.pawsitive.chatgroup.entity.QChatRoom;
import com.pawsitive.usergroup.entity.QUser;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ChatRoomRepositoryImpl extends QuerydslRepositorySupport
    implements ChatRoomRepositoryCustom {

    private static final QChatRoom qChatRoom = QChatRoom.chatRoom;
    private static final QChat qChat = QChat.chat;
    private static final QUser qUser = QUser.user;

    public ChatRoomRepositoryImpl() {
        super(Chat.class);
    }

    @Override
    public List<ChatRoom> getChatRoomsByOrderByCreatedAtDesc(int userNo) {
        return from(qChat)
            .innerJoin(qChat.room, qChatRoom)
            .innerJoin(qChat.user, qUser)
            .select(qChat.room)
            .where(qChat.user.userNo.eq(userNo))
            .groupBy(qChat.room)
            .orderBy(qChatRoom.createdAt.desc())
            .fetch();
    }

    @Override
    public List<ChatRes> getChatHistoryByChatRoomNo(String roomNo) {
        return from(qChat)
            .innerJoin(qChat.room, qChatRoom)
            .innerJoin(qChat.user, qUser)
            .select(Projections.constructor(ChatRes.class, qChat.chatNo, qUser.name,
                qUser.image, qChat.message, qChat.createdAt))
            .where(qChatRoom.chatRoomNo.eq(roomNo))
            .orderBy(qChat.createdAt.desc())
            .fetch();
    }
}
