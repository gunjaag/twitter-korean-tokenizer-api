package com.tweetkorean.core;

import com.tweetkorean.core.api.API;

public class App {
    public static void main(String[] args) throws Exception {
        // Test the APIs
        API api = new API();

        String output = api.tokenize("한국어를 처리하는 예시입니닼ㅋㅋㅋㅋㅋ", "true", "false");
        System.out.println(output);

        output = api.tokenizePartOfSpeech("한국어를 처리하는 예시입니닼ㅋㅋㅋㅋㅋ", "true", "false");
        System.out.println(output);

    }
}
