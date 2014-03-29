package uk.ac.aber.cs39440.experiments;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Physics2 extends BasicGame {
    private World world;
    private StaticBody staticBox, staticCircle;
    private Body dynamicBody;

    private org.newdawn.slick.geom.Circle mindalin;
    private Rectangle rectangle;
    private org.newdawn.slick.geom.Circle circle;
    private Input input;

    public Physics2() {
        super("Physics2");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        world = new World(new Vector2f(0.0f, 5.0f), 10, new QuadSpaceStrategy(
                20, 5));

        staticBox = new StaticBody(new Box(200, 10));
        staticBox.setPosition(320, 240);
        world.add(staticBox);
        rectangle = new Rectangle(0, 0, 200, 10);
        rectangle.setCenterX(320);
        rectangle.setCenterY(240);

        staticCircle = new StaticBody(new Circle(100));
        staticCircle.setPosition(500, 440);
        world.add(staticCircle);
        circle = new org.newdawn.slick.geom.Circle(500, 440, 100);

        dynamicBody = new Body(new Circle(10), 10);
        dynamicBody.setPosition(320, 0);
        world.add(dynamicBody);
        mindalin = new org.newdawn.slick.geom.Circle(250, 20, 10);

        input = container.getInput();
    }

    @Override
    public void update(GameContainer container, int delta)
            throws SlickException {
        if (input.isKeyDown(Input.KEY_UP)) {
            dynamicBody.setForce(0, -100);
        }
        if (input.isKeyDown(Input.KEY_LEFT)) {
            /*
             * dynamicBody.setPosition(dynamicBody.getPosition().getX() - 0.1f,
             * dynamicBody.getPosition().getY());
             */
            dynamicBody.setForce(-100, 0);
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            /*
             * dynamicBody.setPosition(dynamicBody.getPosition().getX() + 0.1f,
             * dynamicBody.getPosition().getY());
             */
            dynamicBody.setForce(100, 0);
        }

        mindalin.setCenterX(dynamicBody.getPosition().getX());
        mindalin.setCenterY(dynamicBody.getPosition().getY());

        world.step();
    }

    @Override
    public void render(GameContainer container, Graphics graphics)
            throws SlickException {
        graphics.draw(mindalin);
        graphics.draw(rectangle);
        graphics.draw(circle);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new Physics2());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
