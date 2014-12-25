package com.tweetkorean.core.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tweetkorean.core.model.Token;
import com.twitter.penguin.korean.TwitterKoreanProcessor;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;
import org.apache.log4j.Logger;
import scala.collection.JavaConversions;
import scala.collection.Seq;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/v1")
public class API {
    private static Logger log = Logger.getLogger(API.class);
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String args[]) throws Exception {
        // Testing
    }

    @POST
    @Path("/tokenize/{normalize}/{stem}")
    @Consumes("text/plain")
    public String tokenize(String data, @PathParam("normalize") String normalize, @PathParam("stem") String stem) throws Exception {
        if (null == data || data.trim().length() == 0) {
            throw new IllegalArgumentException("Input data should not be empty");
        }
        // Logic embedded in Controller for now
        try {
            log.info("Request: " + data);
            boolean isNormalized = true; // default
            boolean isStemmed = false; // default
            boolean keepSpaces = false; // default

            if (null != normalize) {
                try {
                    isNormalized = Boolean.parseBoolean(normalize);
                } catch (Exception ex) {
                    // ignore and use default
                }
            }
            if (null != stem) {
                try {
                    isStemmed = Boolean.parseBoolean(stem);
                } catch (Exception ex) {
                    // ignore and use default
                }
            }

            // Tokenize
            Seq<String> parsed = TwitterKoreanProcessor.tokenizeToStrings(data, isNormalized, isStemmed, keepSpaces);
            //Seq<String> parsed = TwitterKoreanProcessor.tokenizeToNormalizedStrings(data); // Old API
            List<String> javaParsed = JavaConversions.seqAsJavaList(parsed);

            // Json-ize
            String json = gson.toJson(javaParsed);
            log.info("Response: " + json);

            return json;
        } catch (Exception ex) {
            // Issue
            String rId = UUID.randomUUID().toString();
            log.error("Issue in tokenizing. Request-Id: " + rId, ex);
            throw new Exception("Issue in tokenizing. Please contact us with Request-Id: " + rId);
        }
    }

    @POST
    @Path("/tokenizeSpeechParts/{normalize}/{stem}")
    @Consumes("text/plain")
    public String tokenizePartOfSpeech(String data, @PathParam("normalize") String normalize, @PathParam("stem") String stem)
            throws Exception {
        if (null == data || data.trim().length() == 0) {
            throw new IllegalArgumentException("Input data should not be empty");
        }
        // Logic embedded in Controller for now
        try {
            log.debug("Request: " + data);
            boolean isNormalized = true; // default
            boolean isStemmed = false; // default
            boolean keepSpaces = false; // default

            if (null != normalize) {
                try {
                    isNormalized = Boolean.parseBoolean(normalize);
                } catch (Exception ex) {
                    // ignore and use default
                }
            }
            if (null != stem) {
                try {
                    isStemmed = Boolean.parseBoolean(stem);
                } catch (Exception ex) {
                    // ignore and use default
                }
            }

            // Tokenize
            Seq<KoreanTokenizer.KoreanToken> parsedPos = TwitterKoreanProcessor
                .tokenize(data, isNormalized, isStemmed, keepSpaces);
            List<KoreanTokenizer.KoreanToken> javaParsedPos = JavaConversions.seqAsJavaList(parsedPos);
            List<Token> tokenList = new ArrayList<Token>();

            // Adapt
            for (KoreanTokenizer.KoreanToken k : javaParsedPos) {
                Token token = new Token(k.text(), k.pos().toString());
                tokenList.add(token);
            }

            // Json-ize
            String json = gson.toJson(tokenList);
            log.info("Response: " + json);

            return json;
        } catch (Exception ex) {
            String rId = UUID.randomUUID().toString();
            log.error("Issue in tokenizing. Request-Id: " + rId, ex);
            throw new Exception("Issue in tokenizing. Please contact us with Request-Id: " + rId);
        }
    }
}
