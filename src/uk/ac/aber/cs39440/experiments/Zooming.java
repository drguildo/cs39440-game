package uk.ac.aber.cs39440.experiments;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Zooming extends BasicGame {
    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new Zooming());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private Input input;
    private float height, width;
    private float scale = 1.0f;
    private Rectangle rect;

    public Zooming() {
        super("Zooming");
    }

    private float foo(float x) {
        return x / scale;
    }

    @Override
    public void init(GameContainer arg0) throws SlickException {
        input = arg0.getInput();

        height = arg0.getHeight();
        width = arg0.getWidth();

        rect = new Rectangle((width / 2)-50, (height / 2)-50, 100, 100);
    }

    @Override
    public void render(GameContainer arg0, Graphics arg1) throws SlickException {
        float diffx, diffy;

        diffx = width - foo(width);
        diffy = height - foo(height);

        arg1.scale(scale, scale);

        arg1.translate(-(diffx / 2), -(diffy / 2));
        arg1.draw(rect);
        arg1.drawLine(0, 0, 640, 480);

    }

    @Override
    public void update(GameContainer arg0, int arg1) throws SlickException {
        if (input.isKeyDown(Input.KEY_Z)) {
            scale += 0.001;
        }

        if (input.isKeyDown(Input.KEY_X)) {
            scale -= 0.001;
        }
    }

}
