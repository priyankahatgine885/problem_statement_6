package com.filehandling.model;
import java.util.Map;
public class FileData {
    private String fileName;
    private int lineCount;
    private int uniqueWordCount;
    private int distinctWordCount;
    private Map<String, Integer> wordsWithCount;
    public FileData() {
    }
    public FileData(String fileName, int lineCount, int uniqueWordCount, int distinctWordCount, Map<String, Integer> wordsWithCount) {
        this.fileName = fileName;
        this.lineCount = lineCount;
        this.uniqueWordCount = uniqueWordCount;
        this.distinctWordCount = distinctWordCount;
        this.wordsWithCount = wordsWithCount;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }
    public void setUniqueWordCount(int uniqueWordCount) {
        this.uniqueWordCount = uniqueWordCount;
    }
    public void setWordsWithCount(Map<String, Integer> wordsWithCount) {
        this.wordsWithCount = wordsWithCount;
    }
    public String getFileName() {
        return fileName;
    }
    public int getLineCount() {
        return lineCount;
    }
    public int getUniqueWordCount() {
        return uniqueWordCount;
    }
    public void setDistinctWordCount(int distinctWordCount) {
        this.distinctWordCount = distinctWordCount;
    }
    public int getDistinctWordCount() {
        return distinctWordCount;
    }
    public Map<String, Integer> getWordsWithCount() {
        return wordsWithCount;
    }
    @Override
    public String toString() {
        return String.format("%-10s%-5d%-5d%-5d", this.fileName, this.lineCount, this.uniqueWordCount, this.distinctWordCount);
    }
}
