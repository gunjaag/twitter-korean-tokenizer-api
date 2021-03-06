package com.tweetkorean.core.model;

public class Token {
    private String token;
    private String type;
    private int offset;
    private int length;

    public Token(String token, String type) {
        new Token(token, type, -1, -1);
    }

    public Token(String token, String type, int offset, int length) {
        this.token = token;
        this.type = type;
        this.offset = offset;
        this.length = length;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public int getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }
}
