package com.mjc.school.repository;

import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AutoID {
    @Setter
    private static long authorId;
    private static final String authorIdPath = "authorID.txt";
    @Setter
    private static long newsId;
    private static final String newsIdPath = "newsID.txt";

    private static AutoID instance = null;

    private AutoID() {
        authorId = readIdFromFile("authors.txt");
        newsId = readIdFromFile("newsID.txt");
    }

    public  static synchronized AutoID getInstance() {
       if(instance == null){
           instance = new AutoID();
       }
        return instance;
    }

    public long getAuthorId(){
        authorId++;
        writeIdToFile(authorIdPath,authorId);
        return authorId;
    }
    public long getNewsId(){
        newsId++;
        writeIdToFile(newsIdPath,newsId);
        return newsId;
    }

    private long readIdFromFile(String path){
        try{
            String idStr = Files.readString(Paths.get(path));
            return Long.parseLong(idStr);
        } catch (IOException | NumberFormatException e){
            return 0;
        }
    }

    private void writeIdToFile(String path, long id){
        try{
            Files.writeString(Paths.get(path), String.valueOf(id));
        } catch (IOException e){
            e.printStackTrace();
        }

    }


}
