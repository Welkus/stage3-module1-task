package com.mjc.school.repository;

import com.mjc.school.repository.models.News;

import java.util.List;

public interface NewsCrud {

    News create(String title, String content, long id);
    List<News> findAllNews();
    News findById(long newsId);
    News updateNews(long newsId, String title, String content, long authorId);
    boolean deleteNews(long newsId);

}
