package uk.ac.aber.cs39440.experiments;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.svg.InkscapeLoader;

public class Shapes extends BasicGame {
    private Image testImage;
    private Shape testShape;

    private Input input;

    public Shapes() {
        super("Shapes");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        testImage = new Image("data/test.png");
        testShape = InkscapeLoader.load("data/triangle.svg").getFigure(0)
                .getShape();
        System.out.println(testShape.getCenter()[0]);
        System.out.println(testShape.getCenter()[1]);

        input = container.getInput();
    }

    @Override
    public void update(GameContainer container, int delta)
            throws SlickException {
        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            testShape = testShape.transform(Transform
                    .createRotateTransform(0.1f));
        }
    }

    @Override
    public void render(GameContainer container, Graphics g)
            throws SlickException {
        g.draw(testShape);
        testImage.draw(10, 10);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new Shapes());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

}
