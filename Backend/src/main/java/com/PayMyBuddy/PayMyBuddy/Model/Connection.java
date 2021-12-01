package com.PayMyBuddy.PayMyBuddy.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="connections")
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="userid")
    private int userid;

    @Column(name="friendid")
    private int friendid;

    public Connection(int userid, int friendid) {
        this.userid = userid;
        this.friendid = friendid;
    }

    public Connection() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getFriendid() {
        return friendid;
    }

    public void setFriendid(int friendid) {
        this.friendid = friendid;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "id=" + id +
                ", userid=" + userid +
                ", friendid=" + friendid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return id == that.id && userid == that.userid && friendid == that.friendid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userid, friendid);
    }
}

