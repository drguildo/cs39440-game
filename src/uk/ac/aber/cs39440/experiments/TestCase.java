package uk.ac.aber.cs39440.experiments;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class TestCase extends BasicGame {
    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new TestCase());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    Input input;

    public TestCase() {
        super("TestCase");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        input = container.getInput();
    }

    @Override
    public void render(GameContainer container, Graphics g)
            throws SlickException {
        float centrex = container.getWidth() / 2.0f;
        float centrey = container.getHeight() / 2.0f;

        g.drawLine(centrex, centrey, centrex, centrey - 50);
    }

    @Override
    public void update(GameContainer container, int delta)
            throws SlickException {
    }
}
