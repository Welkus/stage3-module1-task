package com.mjc.school.service;

import com.mjc.school.repository.models.News;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-04T18:41:09+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsDTO newsToNewsDTO(News news) {
        if ( news == null ) {
            return null;
        }

        NewsDTO newsDTO = new NewsDTO();

        newsDTO.setId( news.getId() );
        newsDTO.setTitle( news.getTitle() );
        newsDTO.setContent( news.getContent() );
        newsDTO.setCreateDate( news.getCreateDate() );
        newsDTO.setLastUpdateDate( news.getLastUpdateDate() );
        newsDTO.setAuthorId( news.getAuthorId() );

        return newsDTO;
    }
}
