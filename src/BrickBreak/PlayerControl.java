/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BrickBreak;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 *
 * @author anthony-pc
 */
public class PlayerControl implements KeyListener {

    private  Player player;
    private final int right;
    private final int left;

    public PlayerControl(Player player, int left, int right) {
        this.player = player;
        this.right = right;
        this.left = left;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == left) {
            this.player.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.player.toggleRightPressed();
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == left) {
            this.player.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.player.unToggleRightPressed();
        }
    }
}