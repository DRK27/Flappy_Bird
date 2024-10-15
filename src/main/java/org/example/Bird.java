package org.example;

import java.awt.*;

public class Bird {
    public int horizontal, vertical;
    public int width, height;
    public Image img;

    public Bird(int startX, int startY, int width, int height, Image img) {
        this.horizontal = startX;
        this.vertical = startY;
        this.width = width;
        this.height = height;
        this.img = img;
    }

    public boolean checkCollision(Pipe pipe) {
        return this.horizontal < pipe.horizontal + pipe.width &&
                this.horizontal + this.width > pipe.horizontal &&
                this.vertical < pipe.vertical + pipe.height &&
                this.vertical + this.height > pipe.vertical;
    }

    public void resetPosition(int startY) {
        this.vertical = startY;
    }
}
