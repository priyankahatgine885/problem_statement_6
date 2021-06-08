package com.filehandling;
import com.filehandling.model.FileData;
import java.io.*;
import java.util.*;
public class FileRecord implements FileOperation {
    private static Set<String> setWord = new HashSet<>();
    private static List<String> wordList = new ArrayList<>();
    private static Map<String, Integer> wordsWithCount = new HashMap<>();
    @Override
    public FileData readRecord(BufferedReader bufferedReader, File file) throws IOException {
        FileData fileData = new FileData();
        String line = null;
        int count = 0;
        while ((line = bufferedReader.readLine()) != null) {
            fileData.setLineCount(++count);
            int uniqueWord = getUniqueWords(line);
            fileData.setUniqueWordCount(uniqueWord);
            int distinctWordCount = getDistinctWord(line);
            fileData.setDistinctWordCount(distinctWordCount);
            Map<String, Integer> map = getWordsWithCount(line);
            fileData.setWordsWithCount(map);
        }
        String fileName = file.getName();
        fileData.setFileName(fileName);
        return fileData;
    }
    @Override
    public int getUniqueWords(String line) {
        int length = 0;
        String[] words = line.split("[-{}()\t=+.,*:;?!~\\s]+");
        for (int i = 0; i < words.length; ++i) {
            if (wordList.contains(words[i])) {
                wordList.remove(words[i]);
            } else {
                wordList.add(words[i]);
            }
        }
        length = wordList.size();
        return length;
    }
    @Override
    public BufferedReader getBufferedReader(File file) throws FileNotFoundException {
        BufferedReader bufferedReader = null;
        try {
            if (file.isFile()) {
                bufferedReader = new BufferedReader(new FileReader(file));
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
        return bufferedReader;
    }
    @Override
    public int getDistinctWord(String line) {
        int length = 0;
        String[] words = line.split("[-{}()\t=+.,*:;?!~\\s]+");
        for (int i = 0; i < words.length; ++i) {
            setWord.add(words[i].toLowerCase());
        }
        length = setWord.size();
        return length;
    }
    @Override
    public Map<String, Integer> getWordsWithCount(String line) {
        String[] words = line.split("[-{}//<>()\t=+.,*:;?!~\\s]+");
        for (int i = 0; i < words.length; ++i) {
            if (wordsWithCount.containsKey(words[i])) {
                wordsWithCount.put(words[i], wordsWithCount.get(words[i]) + 1);
            } else {
                wordsWithCount.put(words[i], 1);
            }
        }
        return wordsWithCount;
    }
    public void printWordsWithCount(Map<String, Integer> map) {
        for (String key : map.keySet()) {
            Integer value = map.get(key);
            System.out.println("Word Name : " + key + "   " + "Count : " + value);
        }
    }
    public void listFilesInDirectory(File file) throws IOException {
        if (file != null) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        listFilesInDirectory(f.getAbsoluteFile());
                    }
                }
            } else {
                System.out.println("File Path :" + file.getAbsolutePath());
                BufferedReader bufferedReader = getBufferedReader(file);
                FileData fileData = readRecord(bufferedReader, file);
                System.out.println("File Name : " + fileData.getFileName());
                System.out.println("Line count : " + fileData.getLineCount());
                System.out.println("Unique Words : " + fileData.getUniqueWordCount());
                System.out.println("Distinct Words : " + fileData.getDistinctWordCount());
                printWordsWithCount(fileData.getWordsWithCount());
            }
        }
    }
}
