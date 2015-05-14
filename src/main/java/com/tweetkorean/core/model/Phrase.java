package com.tweetkorean.core.model;

public class Phrase {
    private String phrase;
    private String type;
    private int offset;
    private int length;

    public Phrase(String phrase, String type) {
        new Phrase(phrase, type, -1, -1);
    }

    public Phrase(String phrase, String type, int offset, int length) {
        this.phrase = phrase;
        this.type = type;
        this.offset = offset;
        this.length = length;
    }

    public String getPhrase() {
        return phrase;
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
