package com.example.tomtimmy.project0904;

import java.io.Serializable;

/**
 * Created by Soomin CHOI on 2020-08-29(029).
 */

public class ChatData implements Serializable {

    private String msg;
    private String nickname;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
