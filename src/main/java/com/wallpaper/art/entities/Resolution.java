package com.wallpaper.art.entities;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Resolution {
    private int height;
    private int width;

}
