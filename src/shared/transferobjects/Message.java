package shared.transferobjects;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    private String content;
    private User sender, receiver;
    LocalDateTime dateTime;

    public Message(String content, User sender, User receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.dateTime = LocalDateTime.now();
    }

    public Message(String content, User sender) {
        this.content = content;
        this.sender = sender;
        this.dateTime = LocalDateTime.now();
        this.receiver = null;
    }

    public String getContent() {
        return content;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
