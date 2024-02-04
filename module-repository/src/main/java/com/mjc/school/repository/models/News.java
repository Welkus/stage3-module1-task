package com.mjc.school.repository.models;

import com.mjc.school.repository.AutoID;
import com.mjc.school.repository.models.Author;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@AllArgsConstructor
public class News {
    private long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private long authorId;

    public News(String title, String content, long authorId) {
        id = AutoID.getInstance().getNewsId();
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        createDate = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        lastUpdateDate = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
    }


}
