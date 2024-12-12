package org.example.logic

import org.example.ui.Bird
import org.example.ui.Pipe
import java.awt.Image
import java.util.ArrayList

class GameLogic @JvmOverloads constructor(
    @JvmField val boardWidth: Int,
    @JvmField val boardHeight: Int,
    birdWidth: Int,
    birdHeight: Int,
    birdImg: Image,
    @JvmField val pipeWidth: Int,
    @JvmField val pipeHeight: Int,
    @JvmField val openingSpace: Int,
    @JvmField val topPipeImg: Image,
    @JvmField val bottomPipeImg: Image
) {

    @JvmField
    var speedHorizontal = -4
    @JvmField
    var speedVertical = 0
    @JvmField
    var gameOver = false
    @JvmField
    var score = 0.0
    @JvmField
    val pipes: ArrayList<Pipe> = ArrayList()
    @JvmField
    val bird = Bird(boardWidth / 8, boardHeight / 2, birdWidth, birdHeight, birdImg)

    fun moveBird() {
        speedVertical++
        bird.vertical += speedVertical
        bird.vertical = bird.vertical.coerceAtLeast(0)
    }

    fun movePipes() {
        for (pipe in pipes) {
            pipe.horizontal += speedHorizontal

            if (!pipe.passed && bird.horizontal > pipe.horizontal + pipe.width) {
                score += 0.5
                pipe.passed = true
            }
            if (bird.checkCollision(pipe)) gameOver = true
        }
        if (bird.vertical > boardHeight) gameOver = true
    }

    fun resetGame() {
        bird.resetPosition(boardHeight / 2)
        speedVertical = 0
        pipes.clear()
        gameOver = false
        score = 0.0
    }

    fun addPipe() {
        val randomPipeY = (speedVertical - boardHeight / 4 - Math.random() * (pipeHeight / 2)).toInt()

        pipes.add(Pipe(boardWidth, randomPipeY, pipeWidth, pipeHeight, topPipeImg))
        pipes.add(
            Pipe(
                boardWidth,
                randomPipeY + pipeHeight + openingSpace,
                pipeWidth,
                pipeHeight,
                bottomPipeImg
            )
        )
    }
}
