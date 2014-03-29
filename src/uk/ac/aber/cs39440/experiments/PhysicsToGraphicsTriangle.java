package uk.ac.aber.cs39440.experiments;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.ConvexPolygon;
import net.phys2d.raw.shapes.Polygon;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class PhysicsToGraphicsTriangle extends BasicGame {
    private World world;
    private StaticBody staticBox;
    private Body dynamicBody;

    private Rectangle rectangle;

    private Polygon testPoly;
    private Input input;

    public PhysicsToGraphicsTriangle() {
        super("PhysicsToGraphicsTriangle");
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

        testPoly = new ConvexPolygon(new Vector2f[] { new Vector2f(0, -10),
                new Vector2f(10, 10), new Vector2f(-10, 10) });
        dynamicBody = new Body(testPoly, 3);
        dynamicBody.setPosition(320, 30);
        world.add(dynamicBody);

        input = container.getInput();
    }

    @Override
    public void update(GameContainer container, int delta)
            throws SlickException {
        if (input.isKeyDown(Input.KEY_UP)) {
            dynamicBody.setForce(0, -20);
        }
        if (input.isKeyDown(Input.KEY_LEFT)) {
            dynamicBody.setRotation(dynamicBody.getRotation()
                    + (-0.01f * delta));
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            dynamicBody
                    .setRotation(dynamicBody.getRotation() + (0.01f * delta));
        }

        world.step();
    }

    @Override
    public void render(GameContainer container, Graphics g)
            throws SlickException {
        ROVector2f[] verts = testPoly.getVertices(dynamicBody.getPosition(),
                                                  dynamicBody.getRotation());

        for (int i = 0, j = verts.length - 1; i < verts.length; j = i, i++) {
            g.drawLine(0.5f + verts[i].getX(), 0.5f + verts[i].getY(),
                       0.5f + verts[j].getX(), 0.5f + verts[j].getY());
        }

        g.draw(rectangle);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(
                    new PhysicsToGraphicsTriangle());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
