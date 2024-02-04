package com.mjc.school.repository;

import com.mjc.school.repository.models.News;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter@Setter
public class NewsCrudImpl implements NewsCrud {

    private RepositoryAccess repositoryAccess;

    public NewsCrudImpl(RepositoryAccess repositoryAccess){
        this.repositoryAccess = repositoryAccess;
    }

    @Override
    public News create(String title, String content, long authorId) {
        News news = new News(title,content,authorId);
        repositoryAccess.getNewsList().add(news);
        return news;
    }

    @Override
    public List<News> findAllNews() {
        return repositoryAccess.getNewsList();
    }

    @Override
    public News findById(long newsId) {
        return repositoryAccess.getNewsList().stream().filter(n -> n.getId() == newsId).findFirst().orElse(null);
    }

    @Override
    public News updateNews(long newsId, String title, String content, long authorId) {
        News news = repositoryAccess.getNewsList().stream().findFirst().filter(n ->n.getId() == newsId).orElse(null);
        if(title != null && !title.isEmpty()) news.setTitle(title);
        if(content != null && !content.isEmpty()) news.setContent(content);
        if(authorId > 0) news.setAuthorId(authorId);
        news.setLastUpdateDate(LocalDateTime.now());
        return news;
    }

    @Override
    public boolean deleteNews(long newsId) {

        News news = repositoryAccess.getNewsList().stream().filter(n -> n.getId() == newsId).findFirst().orElse(null);

        return repositoryAccess.getNewsList().remove(news);

    }
}
