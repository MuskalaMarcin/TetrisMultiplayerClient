package main.java.com.tetrismultiplayer.client.gui.panel;

import main.java.com.tetrismultiplayer.client.engine.tetromino.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

/**
 * Created by Marcin on 2016-02-16.
 */
public class GamePanel extends JPanel
{
    public GamePanel()
    {
        setBackground(Color.WHITE);
        setFocusable(false);
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));


        addMouseListener(new MouseListener()
        {
            LinkedList<Tetromino> tetrisList = new LinkedList<Tetromino>();

            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (tetrisList.isEmpty())
                {
                    tetrisList.add(new TetrominoI(GamePanel.this, Color.RED, 50, 50));
                    tetrisList.add(new TetrominoJ(GamePanel.this, Color.BLUE, 150, 50));
                    tetrisList.add(new TetrominoL(GamePanel.this, Color.YELLOW, 50, 150));
                    tetrisList.add(new TetrominoO(GamePanel.this, Color.GREEN, 150, 150));
                    tetrisList.add(new TetrominoS(GamePanel.this, Color.GRAY, 50, 250));
                    tetrisList.add(new TetrominoT(GamePanel.this, Color.CYAN, 150, 250));
                    tetrisList.add(new TetrominoZ(GamePanel.this, Color.PINK, 50, 350));

                    tetrisList.forEach(t -> t.draw());

                }
                else
                {
                    tetrisList.forEach(t -> t.rotate());
                }


            }

            @Override
            public void mousePressed(MouseEvent e)
            {

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            @Override
            public void mouseExited(MouseEvent e)
            {

            }
        });
    }


}
