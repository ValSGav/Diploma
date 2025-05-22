package ru.gb.api;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime sentAt;

    public Message(User sender, Chat chat, String content) {
        this.sender = sender;
        this.chat = chat;
        this.content = content;
    }

    @PrePersist
    public void prePersist() {
        sentAt = LocalDateTime.now();
    }
}
