twitter-korean-tokenizer-api
============================

API and UI Interface for Twitter's Korean tokenizer https://github.com/twitter/twitter-korean-text

**Note:**
* This was created as a quick demo and is not intended for production use (misses thread safety, edge case handling, throttling, etc.) 
* Please mature it or contact me if you need help in getting it production ready, and I will be happy to suggest changes required.

**Installation:**
* Run (where you see pom.xml): mvn clean package
* It will generate target/korean-jar-with-dependencies.jar
* Run it using: java -jar target/korean-jar-with-dependencies.jar
* (Currently UIServer is hardcoded to use src/main/webapp as the webapp that needs to be made relative / or app converted to a war from jar)

