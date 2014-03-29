package uk.ac.aber.cs39440.experiments;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Geometry extends BasicGame {
    private Rectangle testRectangle;

    public Geometry() {
        super("Geometry");
    }

    @Override
    public void init(GameContainer arg0) throws SlickException {
        testRectangle = new Rectangle(0, 0, 10, 10);
        for (int i = 0; i < testRectangle.getPointCount(); i++) {
            System.out.print(testRectangle.getPoint(i)[0]);
            System.out.print(", ");
            System.out.println(testRectangle.getPoint(i)[1]);
        }
    }

    @Override
    public void update(GameContainer arg0, int arg1) throws SlickException {
        testRectangle.grow(0.1f, 0.1f);
        testRectangle.setX(testRectangle.getX() + 1);
        testRectangle.setY(testRectangle.getY() + 1);
    }

    @Override
    public void render(GameContainer arg0, Graphics arg1) throws SlickException {
        arg1.draw(testRectangle);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new Geometry());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
