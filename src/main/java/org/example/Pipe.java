package org.example;

import java.awt.*;

public class Pipe {
    public int horizontal, vertical;
    public int width, height;
    public Image img;
    public boolean passed;

    public Pipe(int horizontal, int vertical, int width, int height, Image img) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.width = width;
        this.height = height;
        this.img = img;
        this.passed = false;
    }
}
