Для обфускации
java -jar ./target/lab1-1.0-SNAPSHOT-jar-with-dependencies.jar -f true -i /Users/darya.sukhova/IdeaProjects/sabd/lab1/src/main/resources/normalConfig.xml -o /Users/darya.sukhova/IdeaProjects/sabd/lab1/src/main/resources/obfuscated.xml

Для де-обфускации
java -jar ./target/lab1-1.0-SNAPSHOT-jar-with-dependencies.jar -f false -i /Users/darya.sukhova/IdeaProjects/sabd/lab1/src/main/resources/obfuscated.xml -o /Users/darya.sukhova/IdeaProjects/sabd/lab1/src/main/resources/normalConfig2.xml
