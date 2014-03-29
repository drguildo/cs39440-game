package uk.ac.aber.cs39440.experiments;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import uk.ac.aber.cs39440.game.entities.DynamicEntity;
import uk.ac.aber.cs39440.game.entities.Entity;

public class Ship extends BasicGame {
    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new Ship());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private World world;
    private StaticBody staticBox;

    private Entity test;

    private Input input;

    public Ship() {
        super("Blah");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        world = new World(new Vector2f(0.0f, 2.5f), 10, new QuadSpaceStrategy(
                20, 5));

        // Top
        staticBox = new StaticBody(new Box(container.getWidth(), 10));
        staticBox.setPosition(container.getWidth() / 2, -5);
        world.add(staticBox);

        // Bottom
        staticBox = new StaticBody(new Box(container.getWidth(), 10));
        staticBox.setPosition(container.getWidth() / 2,
                              container.getHeight() + 4);
        world.add(staticBox);

        // Left
        staticBox = new StaticBody(new Box(10, container.getHeight()));
        staticBox.setPosition(-5, container.getHeight() / 2);
        world.add(staticBox);

        // Right
        staticBox = new StaticBody(new Box(10, container.getHeight()));
        staticBox.setPosition(container.getWidth() + 5,
                              container.getHeight() / 2);
        world.add(staticBox);

        test = new DynamicEntity(new float[] { 0, -10, 10, 10, -10, 10 }, 320,
                240, 3.0f, "Ship");

        world.add(test.getBody());

        input = container.getInput();
    }

    @Override
    public void render(GameContainer container, Graphics graphics)
            throws SlickException {
        test.draw(graphics);
    }

    @Override
    public void update(GameContainer container, int delta)
            throws SlickException {
        double vx = 20.0 * Math.sin(test.getBody().getRotation());
        double vy = 20.0 * Math.cos(test.getBody().getRotation());

        if (input.isKeyDown(Input.KEY_UP)) {
            test.getBody().setForce((float) vx, (float) -vy);
        }
        if (input.isKeyDown(Input.KEY_LEFT)) {
            test.getBody().setRotation(
                                       test.getBody().getRotation()
                                               + (-0.001f * delta));
            System.out.println(test.getBody().getRotation());
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            test.getBody().setRotation(
                                       test.getBody().getRotation()
                                               + (0.001f * delta));
            System.out.println(test.getBody().getRotation());
        }

        world.step();
    }
}
