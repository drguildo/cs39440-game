package uk.ac.aber.cs39440.experiments;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MoveImage extends BasicGame {
    private Image ship;
    private Input input;
    private float x, y;

    public MoveImage() {
        super("MovingImage");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        input = container.getInput();
        ship = new Image("data/ship.png");
        x = 0;
        y = 0;
    }

    @Override
    public void update(GameContainer container, int delta)
            throws SlickException {
        if (input.isKeyDown(Input.KEY_UP)) {
            y = y - 1;
        }
        if (input.isKeyDown(Input.KEY_DOWN)) {
            y = y + 1;
        }
        if (input.isKeyDown(Input.KEY_LEFT)) {
            x = x - 1;
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            x = x + 1;
        }
    }

    @Override
    public void render(GameContainer arg0, Graphics arg1) throws SlickException {
        ship.draw(x, y);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new MoveImage());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
