package uk.ac.aber.cs39440.game;

import java.io.File;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilderFactory;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.World;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

import org.newdawn.slick.svg.Diagram;
import org.newdawn.slick.svg.Figure;
import org.newdawn.slick.svg.InkscapeLoader;
import org.w3c.dom.Document;

import uk.ac.aber.cs39440.game.entities.Entity;
import uk.ac.aber.cs39440.game.entities.StaticEntity;

/**
 * @author Simon Morgan
 *
 */
public class Map {
    private final World world;
    private final StaticEntity topWall, bottomWall, leftWall, rightWall;

    private Vector2f p1start, p2start;

    private final Vector<Entity> entities;

    private float height, width;

    public Map(String filename) {
        Document doc;

        /*
         * Parse the raw SVG file to get the height and width of the map file as
         * Slick doesn't get it for us.
         */
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new File(filename));
            doc.getDocumentElement().normalize();

            height = Float.valueOf(doc.getDocumentElement().getAttribute(
                    "height"));
            width = Float.valueOf(doc.getDocumentElement()
                    .getAttribute("width"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        world = new World(new Vector2f(0.0f, Physics.GRAVITY), 10,
                new QuadSpaceStrategy(20, 5));
        entities = new Vector<Entity>();

        try {
            Diagram d = InkscapeLoader.load(filename);
            Figure f;

            // Fetch the ship starting points and then remove them from the
            // Diagram as we don't want them appearing as game objects.
            f = d.getFigureByID("p1start");
            p1start = new Vector2f(f.getShape().getCenterX(), f.getShape()
                    .getCenterY());
            d.removeFigure(f);

            f = d.getFigureByID("p2start");
            p2start = new Vector2f(f.getShape().getCenterX(), f.getShape()
                    .getCenterY());
            d.removeFigure(f);

            for (int i = 0; i < d.getFigureCount(); i++) {
                float[] points = d.getFigure(i).getShape().getPoints();
                StaticEntity e = new StaticEntity(points, 0, 0, d.getFigure(i)
                        .getData().getMetaData());
                add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Map boundaries.

        float[] horizontalWall = { 0, 0, width, 0, width, 5, 0, 5 };

        topWall = new StaticEntity(horizontalWall, 0, -6, "Top Wall");
        bottomWall = new StaticEntity(horizontalWall, 0, height, "Bottom Wall");

        float[] verticalWall = { 0, 0, 5, 0, 5, height, 0, height };

        leftWall = new StaticEntity(verticalWall, -6, 0, "Left Wall");
        rightWall = new StaticEntity(verticalWall, width, 0, "Right Wall");

        // add(topWall);
        // add(bottomWall);
        // add(leftWall);
        // add(rightWall);

        world.add(topWall.getBody());
        world.add(bottomWall.getBody());
        world.add(leftWall.getBody());
        world.add(rightWall.getBody());
    }

    public void add(Entity entity) {
	entities.add(entity);
	world.add(entity.getBody());
    }

    public Vector<Entity> getEntities() {
	return entities;
    }

    public float getHeight() {
	return height;
    }

    public Vector2f getP1start() {
        return p1start;
    }

    public Vector2f getP2start() {
        return p2start;
    }

    public float getWidth() {
	return width;
    }

    public World getWorld() {
	return world;
    }

    public void remove(Entity entity) {
	entities.remove(entity);
	world.remove(entity.getBody());
    }
}
