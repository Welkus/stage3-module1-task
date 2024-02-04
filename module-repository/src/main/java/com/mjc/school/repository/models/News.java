package com.mjc.school.repository.models;

import com.mjc.school.repository.AutoID;
import com.mjc.school.repository.models.Author;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
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
        createDate = LocalDateTime.from(Instant.now());
        lastUpdateDate = LocalDateTime.from(Instant.now());
    }


}
