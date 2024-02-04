package com.mjc.school.repository;

import com.mjc.school.repository.models.Author;
import com.mjc.school.repository.models.News;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.text.DateFormat;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@Data
public class RepositoryAccess {

    private List<Author> authorsList;
    private List<News> newsList;
    private String authorsPath = "authors.txt";
    private String newsPath = "news.txt";
    private Map<Long, Author> authorsMap;

    public RepositoryAccess() {
        authorsList = selectDataSource(authorsPath, Author.class);
        authorsMap = authorsList.stream().collect(Collectors.toMap(Author::getId, Function.identity()));
        newsList = selectDataSource(newsPath, News.class);
    }

    private <T> List<T> selectDataSource(String path, Class<T> type) {
        try (Reader reader = new InputStreamReader
                (RepositoryAccess.class.getClassLoader().getResourceAsStream(path), "UTF-8");
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)
        ) {

            if (type == Author.class) {
                return (List<T>) readAuthor(csvParser);
            } else if (type == News.class) {
                return (List<T>) readNews(csvParser);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private List<Author> readAuthor(CSVParser csvParser) throws IOException {
        List<Author> dataSourceList = new ArrayList<>();
        String nextLine;

        for (CSVRecord csvRecord : csvParser) {
            dataSourceList.add(new Author(Long.parseLong(csvRecord.get(0)), csvRecord.get(1)));
        }
        return dataSourceList;
    }

    private List<News> readNews(CSVParser csvParser) throws IOException {
        List<News> dataSourceList = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;



        for (CSVRecord csvRecord : csvParser) {
            try {
                long newsId = Long.parseLong(csvRecord.get(0));
                AutoID.setNewsId(newsId);

                LocalDateTime createdDateTime = LocalDateTime.parse(csvRecord.get(3).trim(), formatter);
                LocalDateTime updatedDateTime = LocalDateTime.parse(csvRecord.get(4).trim(), formatter);



                dataSourceList.add(new News(Long.parseLong(csvRecord.get(0)),
                        csvRecord.get(1),
                        csvRecord.get(2),
                        createdDateTime,
                        updatedDateTime,
                        Long.parseLong(csvRecord.get(5))));
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing date in record: " + csvRecord.getRecordNumber() + " and index 0: " + csvRecord.get(0) + " index 1: "+ csvRecord.get(1)+ " index 2: " + csvRecord.get(2) + " index 3: "+ csvRecord.get(3)+ " index 4: " + csvRecord.get(4) + " index5: " + csvRecord.get(5)+"'");
            } catch (NumberFormatException e) {
                System.err.println("Error parsing number in record: " + csvRecord.getRecordNumber());
            }

        }
        return dataSourceList;

    }
}