package org.example;

import java.awt.*;

public class Bird {
    public int x, y;
    public int width, height;
    public Image img;

    public Bird(int startX, int startY, int width, int height, Image img) {
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.height = height;
        this.img = img;
    }

    public void resetPosition(int startY) {
        this.y = startY;
    }
}
