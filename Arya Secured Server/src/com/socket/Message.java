package com.socket;

import java.io.Serializable;

public class Message implements Serializable{
    
    private static final long serialVersionUID = 1L;
    public String type, sender, content, recipient, digitalId;
    
    public Message(String type, String sender, String content, String recipient){
        this.type = type; this.sender = sender; this.content = content; this.recipient = recipient; this.digitalId = null;
    }
    
    public Message(String type, String sender, String content, String recipient, String digitalId){
        this.type = type; this.sender = sender; this.content = content; this.recipient = recipient; this.digitalId = digitalId;
    }
    
    @Override
    public String toString(){
        if (digitalId == null) {
            return "{type='"+type+"', sender='"+sender+"', content='"+content+"', recipient='"+recipient+"'}";
        } else {
            return "{type='"+type+"', sender='"+sender+"', content='"+content+"', recipient='"+recipient+"', digitalId='"+digitalId+"'}";
        }
    }
}
