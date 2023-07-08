package sk.matusturjak.price_sender.model.db;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Document("users")
public class User {
    @Id
    private ObjectId id;
    private String name;
    private String surname;
    private String email;
    private Date lastEmailSend;
    private List<ObjectId> items;

    public User(ObjectId id, String name, String surname, String email, Date lastEmailSend, List<ObjectId> items) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.lastEmailSend = lastEmailSend;
        this.items = items;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastEmailSend() {
        return lastEmailSend;
    }

    public void setLastEmailSend(Date lastEmailSend) {
        this.lastEmailSend = lastEmailSend;
    }

    public List<ObjectId> getItems() {
        return items;
    }

    public void setItems(List<ObjectId> items) {
        this.items = items;
    }
}
