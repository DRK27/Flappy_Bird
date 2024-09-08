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

    public boolean checkCollision(Pipe pipe) {
        return this.x < pipe.x + pipe.width &&
                this.x + this.width > pipe.x &&
                this.y < pipe.y + pipe.height &&
                this.y + this.height > pipe.y;
    }

    public void resetPosition(int startY) {
        this.y = startY;
    }
}
