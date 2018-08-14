package com.grandream.dagt.requestbody.my;

/**
 * Created by chenjing on 2018/5/16.
 */

public class Userlogo {
    private String user_id;
    private String file_content;
    private String file_suffix;

    public Userlogo(String user_id, String file_content, String file_suffix) {
        this.user_id = user_id;
        this.file_content = file_content;
        this.file_suffix = file_suffix;
    }

}
