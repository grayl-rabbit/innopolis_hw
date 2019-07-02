package main.java.part01.lesson05.task02.service;

public interface RandomUtilService {
    String getRandomWord(int length);
    String[] generateWords(int count);
    String getRandomSentence(int countWord, int probability,  String[] words);
    String getRandomParagraph(int sentenceCount,int probability,  String[] words);

    boolean getProbabilityResult(int probability);

    void getFiles( String path, int n, int size,  String[] words, int probability);

    int getRandomNumberInRange(int min, int max);
}
