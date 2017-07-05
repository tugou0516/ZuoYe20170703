package org.forten.si.dto;

/**
 * Created by student1 on 2017/7/3.
 */
public class Message {
    private String msg;

    public Message(String msg) {
        this.msg = msg;
    }

    public Message() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
