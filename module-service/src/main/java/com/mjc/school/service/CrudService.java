package com.mjc.school.service;

import com.mjc.school.repository.NewsCrud;
import com.mjc.school.repository.models.News;

import java.util.ArrayList;
import java.util.List;

public class CrudService {


    private final NewsCrud newsCrud;
    NewsMapper newsMapper;

    public CrudService(NewsCrud newsCrud, NewsMapper newsMapper) {
        this.newsCrud = newsCrud;
        this.newsMapper = newsMapper;
    }

    public NewsDTO createNews(NewsDTO newsDTO) {

      News news = newsCrud.create(newsDTO.getTitle(), newsDTO.getContent(), newsDTO.getAuthorId());
      return newsMapper.newsToNewsDTO(news);

    }

    public List<NewsDTO> findAllNews() {
        List<NewsDTO> newsDTOList = new ArrayList<>();

        for(News n : newsCrud.findAllNews()){
            newsDTOList.add(newsMapper.newsToNewsDTO(n));
        }

        return newsDTOList;
    }

    public NewsDTO findNewsById(long id) {
        return newsMapper.newsToNewsDTO(newsCrud.findById(id));
    }

    public NewsDTO updateNews(NewsDTO newsDTO) {

        return newsMapper.newsToNewsDTO(newsCrud.updateNews(newsDTO.getId(), newsDTO.getTitle(), newsDTO.getContent(), newsDTO.getAuthorId()));
    }

    public boolean deleteNews(NewsDTO newsDTO) {
        return newsCrud.deleteNews(newsDTO.getId());
    }

}
