package main.java.part01.lesson05.task02.service;


import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static java.nio.file.StandardOpenOption.CREATE;

public class RandomUtilServiceImpl implements RandomUtilService{
    /**
     * generate random word
     * @param length
     * @return string
     */
    @Override
    public String getRandomWord(int length) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < length; i++) {
            str.append((char) (Math.random() * 26 + 97));
        }
        return str.toString();
    }

    /**
     * generate random words
     * @param count
     * @return String list
     */
    @Override
    public String[] generateWords(int count){
        String[] list = new String[count];
        for (int i = 0; i < count; i++) {
            list[i] = "Array_"+getRandomWord(getRandomNumberInRange(1, 15));
        }
        return list;
    }

    /**
     * generate random sentence
     * @param countWord
     * @return string
     */
    @Override
    public String getRandomSentence(int countWord, int probability, String[] words){
        StringBuilder sentence = new StringBuilder();
        String firstWord;
        boolean probabilityResult = getProbabilityResult(probability);
        if(probabilityResult){
            int randomIndex = getRandomNumberInRange(1, words.length-1);
            firstWord = words[randomIndex];
        }else {
            firstWord = getRandomWord(getRandomNumberInRange(1, 15));
        }
        sentence.append(firstWord.substring(0, 1).toUpperCase()).append(firstWord.substring(1)).append("|");

        for(int i = 0; i < countWord-1; i++) {
            sentence.append(getRandomWord(getRandomNumberInRange(1, 15))).append("|");
        }

        sentence = new StringBuilder(sentence.substring(0, sentence.length() - 1) + LastSymbol.getRandom().toString() + " ");
        return sentence.toString().replace("|", SymbolComma.getRandom().toString());
    }

    /**
     * generate random paragraph
     * @param sentenceCount
     * @return string
     */
    @Override
    public String getRandomParagraph(int sentenceCount,int probability, String[] words){
        StringBuilder paragraph = new StringBuilder();
        for(int i = 0; i < sentenceCount; i++) {
            paragraph.append(getRandomSentence(getRandomNumberInRange(1, 15), probability, words));
        }
        return paragraph +"\n\r";
    }

    /**
     * generate random number
     * @param min
     * @param max
     * @return
     */
    @Override
    public int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * get result of probability
     * @param probability
     * @return true/false
     */
    @Override
    public boolean getProbabilityResult(int probability) {
        double prob =(double) probability / 100;
        return Math.random() < prob;
    }

    /**
     * create n files of size in the path directory.
     * words is an array of words.
     * @param path directory for result file
     * @param n - count files
     * @param size - size file
     * @param words
     * @param probability
     */
    @Override
    public void getFiles(String path, int n, int size, String[] words, int probability)  {

        int i = 1;
        while (i <= n){
            Path fileWrite = Paths.get(path+i);
            StringBuilder text = new StringBuilder();
            for(int j = 0; j < 10; j++)
                text.append(getRandomParagraph(getRandomNumberInRange(1, 5), probability, words));

            String cutString = cut(text.toString(), size);

            try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(fileWrite, CREATE))) {
                out.write(cutString.getBytes(), 0, size);
            } catch (IOException x) {
                System.err.println(x.getMessage());
            }

            i++;
        }
    }


    /**
     * cut string by value(n)
     * @param s - string to cut
     * @param n - end int
     * @return
     */
    private  String cut(String s, int n) {
        byte[] str = s.getBytes();
        if (str.length < n) n = str.length;
        int endSymbol = 0;
        int advance;
        int i = 0;
        while (i < n) {
            advance = 1;
            if ((str[i] & 0x80) == 0) i += 1;
            else if ((str[i] & 0xE0) == 0xC0) i += 2;
            else if ((str[i] & 0xF0) == 0xE0) i += 3;
            else { i += 4; advance = 2; }
            if (i <= n) endSymbol += advance;
        }
        return s.substring(0,endSymbol);
    }

    private enum LastSymbol {
        DOT("."),
        EMPTY(" "),
        QUESTION_MARK("?"),
        EXCLAMATION_MARK("!");

        private String value;
        LastSymbol(String value) {
            this.value = value;
        }

        public String toString()
        {
            return this.value;
        }
        public static LastSymbol getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }


    private enum SymbolComma {
        COMMA(", "),
        EMPTY(" ");

        private String value;
        SymbolComma(String value) {
            this.value = value;
        }

        public String toString()
        {
            return this.value;
        }
        public static SymbolComma getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }
}
