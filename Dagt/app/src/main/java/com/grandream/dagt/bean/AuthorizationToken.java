package com.grandream.dagt.bean;

/**
 * Created by chenjing on 2018/4/10.
 */

public class AuthorizationToken {
    /**
     * access_token : 449f1f9a519288b5fce3592256d6d20c6e636236
     * expires_in : 3600
     * token_type : Bearer
     * scope : null
     */

    private String access_token;
    private int expires_in;
    private String token_type;
    private String scope;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
