package org.example;

import org.example.Bird;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    private static final int GRAVITY = 1;
    private int velocityX = -4;
    int velocityY = 0;
    private int gravity = GRAVITY;

    public boolean gameOver = false;
    public double score = 0;

    public Bird bird;
    public ArrayList<Pipe> pipes = new ArrayList<>();
    private int boardWidth;
    private int boardHeight;
    private int pipeWidth;
    private int pipeHeight;
    private int openingSpace;
    private Image topPipeImg, bottomPipeImg;
    private Random random = new Random();

    public GameLogic(int boardWidth, int boardHeight, int birdWidth, int birdHeight, Image birdImg, int pipeWidth, int pipeHeight, int openingSpace, Image topPipeImg, Image bottomPipeImg) {
        this.bird = new Bird(boardWidth / 8, boardHeight / 2, birdWidth, birdHeight, birdImg);
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.pipeWidth = pipeWidth;
        this.pipeHeight = pipeHeight;
        this.openingSpace = openingSpace;
        this.topPipeImg = topPipeImg;
        this.bottomPipeImg = bottomPipeImg;
    }

    public void moveBird() {
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);
    }

    public void movePipes() {
        for (Pipe pipe : pipes) {
            pipe.x += velocityX;

            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                score += 0.5;
                pipe.passed = true;
            }

            if (collision(bird, pipe)) {
                gameOver = true;
            }
        }

        if (bird.y > boardHeight) {
            gameOver = true;
        }
    }

    public void resetGame() {
        bird.resetPosition(boardHeight / 2);
        velocityY = 0;
        pipes.clear();
        gameOver = false;
        score = 0;
    }

    public void addPipe() {
        int randomPipeY = (int) (velocityY - boardHeight / 4 - Math.random() * (pipeHeight / 2));

        pipes.add(new Pipe(boardWidth, randomPipeY, pipeWidth, pipeHeight, topPipeImg));
        pipes.add(new Pipe(boardWidth, randomPipeY + pipeHeight + openingSpace, pipeWidth, pipeHeight, bottomPipeImg));
    }

    private boolean collision(Bird bird, Pipe pipe) {
        return bird.x < pipe.x + pipe.width &&
                bird.x + bird.width > pipe.x &&
                bird.y < pipe.y + pipe.height &&
                bird.y + bird.height > pipe.y;
    }
}
