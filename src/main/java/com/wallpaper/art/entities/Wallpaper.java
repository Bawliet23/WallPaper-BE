package com.wallpaper.art.entities;



import com.wallpaper.art.utils.Env;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Wallpaper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private int view;
    private int download;
    @Embedded
    private Resolution resolution;
    private String image;
    private Long fileSize;
    @ManyToMany(fetch = FetchType.EAGER,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "wallpaper_categories",
            joinColumns = @JoinColumn(name = "wallpaper_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories=new ArrayList<Category>();


}
