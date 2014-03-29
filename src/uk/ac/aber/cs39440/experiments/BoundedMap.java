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

public class BoundedMap extends BasicGame {
    private World world;
    private StaticBody staticBox;
    private Body dynamicBody;

    private org.newdawn.slick.geom.Circle mindalin;
    private Input input;

    public BoundedMap() {
        super("BoundedMap");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        world = new World(new Vector2f(0.0f, 5.0f), 10, new QuadSpaceStrategy(
                20, 5));

        // Top
        staticBox = new StaticBody(new Box(container.getWidth(), 0));
        staticBox.setPosition(container.getWidth() / 2, 0);
        world.add(staticBox);

        // Bottom
        staticBox = new StaticBody(new Box(container.getWidth(), 0));
        staticBox.setPosition(container.getWidth() / 2, container.getHeight());
        world.add(staticBox);

        // Left
        staticBox = new StaticBody(new Box(0, container.getHeight()));
        staticBox.setPosition(0, container.getHeight() / 2);
        world.add(staticBox);

        // Right
        staticBox = new StaticBody(new Box(0, container.getHeight()));
        staticBox.setPosition(container.getWidth(), container.getHeight() / 2);
        world.add(staticBox);

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
    }

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new BoundedMap());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
