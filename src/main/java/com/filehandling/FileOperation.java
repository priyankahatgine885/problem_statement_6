package com.filehandling;
import com.filehandling.model.FileData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
public interface FileOperation {
    FileData readRecord(BufferedReader bufferedReader, File file) throws IOException;
    int getUniqueWords(String line);
    BufferedReader getBufferedReader(File file) throws FileNotFoundException;
    int getDistinctWord(String line);
    Map<String, Integer> getWordsWithCount(String line);
}

