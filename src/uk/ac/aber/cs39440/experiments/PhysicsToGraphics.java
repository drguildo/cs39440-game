package uk.ac.aber.cs39440.experiments;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

public class PhysicsToGraphics extends BasicGame {
    private Body testBody;
    private Box testBox;
    private Vector2f[] points;

    private Polygon testPoly;

    public PhysicsToGraphics() {
        super("PhysicsToGraphics");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        testBody = new Body(new Box(100, 100), 0);
        testPoly = new Polygon();
        testBox = (Box) testBody.getShape();
        points = testBox.getPoints(testBody.getPosition(), 0);
        for (int i = 0; i < points.length; i++) {
            testPoly.addPoint(points[i].getX(), points[i].getY());
        }

        testPoly.setLocation(320, 240);
    }

    @Override
    public void update(GameContainer container, int delta)
            throws SlickException {
    }

    @Override
    public void render(GameContainer container, Graphics g)
            throws SlickException {
        g.draw(testPoly);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(
                    new PhysicsToGraphics());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
