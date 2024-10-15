package org.example;

import java.awt.*;
import java.util.ArrayList;

public class GameLogic {
    int speedHorizontal = -4;
    int speedVertical = 0;
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
        speedVertical += 1;
        bird.vertical += speedVertical;
        bird.vertical = Math.max(bird.vertical, 0);
    }

    public void movePipes() {
        for (Pipe pipe : pipes) {
            pipe.horizontal += speedHorizontal;

            if (!pipe.passed && bird.horizontal > pipe.horizontal + pipe.width) {
                score += 0.5;
                pipe.passed = true;
            }
            if (bird.checkCollision(pipe))  gameOver = true;
        }
        if (bird.vertical > boardHeight) gameOver = true;
    }

    public void resetGame() {
        bird.resetPosition(boardHeight / 2);
        speedVertical = 0;
        pipes.clear();
        gameOver = false;
        score = 0;
    }

    public void addPipe() {
        int randomPipeY = (int) (speedVertical - boardHeight / 4 - Math.random() * (pipeHeight / 2));

        pipes.add(new Pipe(boardWidth, randomPipeY, pipeWidth, pipeHeight, topPipeImg));
        pipes.add(new Pipe(boardWidth, randomPipeY + pipeHeight + openingSpace, pipeWidth, pipeHeight, bottomPipeImg));
    }
}
