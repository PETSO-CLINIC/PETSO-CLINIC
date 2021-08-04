package com.example.demo.infrastructure;

import com.example.demo.messagingstompwebsocket.GeneralChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralChatRepository extends JpaRepository<GeneralChat,Long> {

}
