package com.filehandling;
import com.filehandling.exception.InvalidPathException;
import com.filehandling.model.FileData;
import java.io.*;
import java.util.Scanner;
public class Application{
    static FileRecord fileRecord = new FileRecord();
    public static void main(String[] args){
        BufferedReader bufferedReader = null;
        try(Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter Folder Path :");
            File file = new File(sc.nextLine());
            if (file.exists()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        if (f.isFile()) {
                            bufferedReader = fileRecord.getBufferedReader(f);
                            if (bufferedReader != null){
                                    Application.printFileInfo(bufferedReader, f);
                            } else {
                                throw new NullPointerException("Null Pointer Exception");
                            }
                        } else {
                            try {
                                fileRecord.listFilesInDirectory(f);
                                System.out.println("--------------------------------------------------------------");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            } else {
                throw new InvalidPathException("The provided path is invalid");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
   static public void printFileInfo(BufferedReader bufferedReader, File file) throws IOException {
        FileData fileData = fileRecord.readRecord(bufferedReader, file);
        System.out.println("File Name : " + fileData.getFileName());
        System.out.println("Line count : " + fileData.getLineCount());
        System.out.println("Unique Words : " + fileData.getUniqueWordCount());
        System.out.println("Distinct Words : " + fileData.getDistinctWordCount());
        System.out.println("--------------------------------------------------------------");
    }
}

