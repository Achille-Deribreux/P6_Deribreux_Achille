package com.PayMyBuddy.PayMyBuddy.Model.Specific;

public class AddConnection {
    String friendemail;
    int userid;

    public AddConnection(String friendemail, int userid) {
        this.friendemail = friendemail;
        this.userid = userid;
    }

    public AddConnection() {
    }

    public String getFriendemail() {
        return friendemail;
    }

    public void setFriendemail(String friendemail) {
        this.friendemail = friendemail;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
