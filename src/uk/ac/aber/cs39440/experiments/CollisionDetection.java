package uk.ac.aber.cs39440.experiments;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;

public class CollisionDetection extends BasicGame {
    private Input input;
    private Ellipse testEllipse;
    private Rectangle testRectangle;

    public CollisionDetection() {
        super("CollisionDetection");
    }

    @Override
    public void init(GameContainer arg0) throws SlickException {
        testEllipse = new Ellipse(0, 0, 40, 40);
        testRectangle = new Rectangle(arg0.getWidth() / 2,
                arg0.getHeight() / 2, 20, 20);
        input = arg0.getInput();
    }

    @Override
    public void update(GameContainer arg0, int arg1) throws SlickException {
        if (input.isKeyDown(Input.KEY_UP)) {
            testEllipse.setY(testEllipse.getY() - 1);
        }
        if (input.isKeyDown(Input.KEY_DOWN)) {
            testEllipse.setY(testEllipse.getY() + 1);
        }
        if (input.isKeyDown(Input.KEY_LEFT)) {
            testEllipse.setX(testEllipse.getX() - 1);
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            testEllipse.setX(testEllipse.getX() + 1);
        }

        if (testEllipse.intersects(testRectangle)
                || testEllipse.contains(testRectangle.getCenterX(),
                                        testRectangle.getCenterY())) {
            System.out.println("BANG!");
        }
    }

    @Override
    public void render(GameContainer arg0, Graphics arg1) throws SlickException {
        arg1.draw(testEllipse);
        arg1.draw(testRectangle);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(
                    new CollisionDetection());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
