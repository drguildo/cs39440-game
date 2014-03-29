package uk.ac.aber.cs39440.experiments;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Timing extends BasicGame {
    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new Timing(
                    "Timing"));
            container.setMinimumLogicUpdateInterval(20);
            // container.setVSync(true);
            // container.setTargetFrameRate(60);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private long time = System.currentTimeMillis();

    public Timing(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
    }

    @Override
    public void render(GameContainer container, Graphics g)
            throws SlickException {
    }

    @Override
    public void update(GameContainer container, int delta)
            throws SlickException {
        System.out.println("delta: " + delta);
        System.out.println("time: " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
    }

}
