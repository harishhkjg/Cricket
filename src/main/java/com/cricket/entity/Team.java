package com.cricket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Document(collection = "Teams ")
public class Team {
    @Id
    private String id;

    private String teamname;



}
