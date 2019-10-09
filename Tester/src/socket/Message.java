package socket;

import java.io.Serializable;

public class Message implements Serializable {
  
	private static final long serialVersionUID = 8334869471954688076L;
	private String type, sender, content, recipient, digitalId;
    
    public Message(String type, String sender, String content, String recipient) {
        this.type = type; this.sender = sender; this.content = content; this.recipient = recipient;
    }
    
    public Message(String type, String sender, String content, String recipient, String digitalId) {
    	
        this.type = type; this.sender = sender; this.content = content; this.recipient = recipient; this.digitalId = digitalId;
    }
    
    @Override
    public String toString() {
    	
        if (digitalId == null) {
        	
            return "{type='"+type+"', sender='"+sender+"', content='"+content+"', recipient='"+recipient+"'}";
        } else {
        	
            return "{type='"+type+"', sender='"+sender+"', content='"+content+"', recipient='"+recipient+"', digitalId='"+digitalId+"'}";
        }
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getDigitalId() {
		return digitalId;
	}

	public void setDigitalId(String digitalId) {
		this.digitalId = digitalId;
	}
}
