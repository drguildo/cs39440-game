package uk.ac.aber.cs39440.experiments;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Zooming2 extends BasicGame {
    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new Zooming2());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private Input input;
    private float scale = 1.0f;

    public Zooming2() {
        super("Zooming2");
    }

    @Override
    public void init(GameContainer c) throws SlickException {
        input = c.getInput();
    }

    @Override
    public void render(GameContainer c, Graphics g) throws SlickException {
        g.scale(scale, scale);

        /*
        g.drawLine(0, 0, 200, 0);
        g.drawLine(200, 0, 200, 200);
        g.drawLine(200, 200, 0, 200);
        g.drawLine(0, 200, 0, 0);
        */

        g.drawRect(0, 0, 200, 200);
    }

    @Override
    public void update(GameContainer c, int delta) throws SlickException {
        if (input.isKeyDown(Input.KEY_Z)) {
            scale += 0.001;
        }

        if (input.isKeyDown(Input.KEY_X)) {
            scale -= 0.001;
        }
    }
}
