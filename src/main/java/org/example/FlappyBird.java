package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    private final GameLogic gameLogic;
    private final Timer gameLoop;
    private final Timer placePipeTimer;
    private final Image backgroundImg;

    public FlappyBird() {
        setPreferredSize(new Dimension(360, 640));
        setFocusable(true);
        addKeyListener(this);

        // Инициализация ресурсов
        backgroundImg = loadImage("/flappybirdbg.png");
        Image birdImg = loadImage("/flappybird.png");
        Image topPipeImg = loadImage("/toppipe.png");
        Image bottomPipeImg = loadImage("/bottompipe.png");

        // Логика игры
        gameLogic = new GameLogic(360, 640, 34, 24, birdImg, 64, 512, 640 / 4, topPipeImg, bottomPipeImg);

        // Таймеры
        placePipeTimer = createTimer(1500, e -> gameLogic.addPipe());
        gameLoop = createTimer(1000 / 60, this);

        // Старт игры
        startGame();
    }

    private void startGame() {
        placePipeTimer.start();
        gameLoop.start();
    }

    private Timer createTimer(int delay, ActionListener listener) {
        return new Timer(delay, listener);
    }

    private Image loadImage(String path) {
        return new ImageIcon(getClass().getResource(path)).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(gameLogic.bird.img, gameLogic.bird.x, gameLogic.bird.y, gameLogic.bird.width, gameLogic.bird.height, this);

        for (Pipe pipe : gameLogic.pipes) {
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, this);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        g.drawString(gameLogic.gameOver ? "Game Over: " + (int) gameLogic.score : String.valueOf((int) gameLogic.score), 10, 35);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameLogic.gameOver) {
            stopGame();
        } else {
            gameLogic.moveBird();
            gameLogic.movePipes();
            repaint();
        }
    }

    private void stopGame() {
        placePipeTimer.stop();
        gameLoop.stop();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameLogic.gameOver) {
                gameLogic.resetGame();
                startGame();
            } else {
                gameLogic.velocityY = -9;
            }
        }
    }

    // Неиспользуемые методы KeyListener
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
