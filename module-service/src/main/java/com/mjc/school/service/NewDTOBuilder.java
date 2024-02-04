package com.mjc.school.service;

import com.mjc.school.repository.models.News;

import java.time.LocalDateTime;

public class NewDTOBuilder {

    NewsDTO dto = new NewsDTO();

    public NewDTOBuilder setId(long id){
        dto.setId(id);
        return this;
    }

    public NewDTOBuilder setContent(String content){
        dto.setContent(content);
        return this;
    }

    public NewDTOBuilder setTitle(String title){
        dto.setTitle(title);
        return this;
    }

    public NewDTOBuilder setAuthorId(long id){
        dto.setAuthorId(id);
        return this;
    }

    public NewDTOBuilder setCreationDate(LocalDateTime localDateTime){
        dto.setCreateDate(localDateTime);
        return this;
    }

    public NewDTOBuilder setUpdateDate(LocalDateTime localDateTime){
        dto.setLastUpdateDate(localDateTime);
        return this;
    }


    public NewsDTO build(){
        return dto;
    }
}
