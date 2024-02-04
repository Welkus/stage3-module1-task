package com.mjc.school.repository.models;

import com.mjc.school.repository.AutoID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Author {

    private long id;
    private String name;

    public Author(String name){
        id = AutoID.getInstance().getAuthorId();
        this.name = name;
    }

}
