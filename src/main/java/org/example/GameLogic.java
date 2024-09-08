package org.example;

import java.awt.*;
import java.util.ArrayList;

public class GameLogic {
    int velocityX = -4;
    int velocityY = 0;
    boolean gameOver = false;
    double score = 0;
    ArrayList<Pipe> pipes = new ArrayList<>();
    Bird bird;
    int boardWidth, boardHeight, pipeWidth, pipeHeight, openingSpace;
    Image topPipeImg, bottomPipeImg;

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
        velocityY += 1;
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
            if (bird.checkCollision(pipe))  gameOver = true;
        }
        if (bird.y > boardHeight) gameOver = true;
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
}
