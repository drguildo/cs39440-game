package uk.ac.aber.cs39440.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SimpleStars {

    private Image img;
    private int tilesize;
    private float x, y;
    private int width, height;
    private float[] depths;

    public SimpleStars(int width, int height, float[] depths) {
        try {
            img = new Image("data/ss122.gif");
            tilesize = img.getWidth();
        } catch (SlickException e) {
            e.printStackTrace();
        }
        this.width = width;
        this.height = height;
        this.depths = depths;
    }

    public void render(Graphics g) {
        for (float scale : depths) {
            float cornerX = x / scale - width / 2 / scale;
            float cornerY = y / scale - height / 2 / scale;
            float startX = (float) (Math.floor(cornerX / tilesize) * tilesize);
            float startY = (float) (Math.floor(cornerY / tilesize) * tilesize);
            float offsetX = -cornerX;
            float offsetY = -cornerY;

            for (int row = (int) startY; row < startY + 2 * height; row += tilesize) {
                for (int col = (int) startX; col < startX + 2 * width; col += tilesize) {
                    g.setDrawMode(Graphics.MODE_ADD);
                    g.drawImage(img, col + offsetX, row + offsetY);
                    g.setDrawMode(Graphics.MODE_NORMAL);
                }

            }
        }
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

}