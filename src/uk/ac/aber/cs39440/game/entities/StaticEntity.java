package uk.ac.aber.cs39440.game.entities;

import net.phys2d.raw.StaticBody;
import net.phys2d.raw.shapes.Polygon;
import uk.ac.aber.cs39440.game.Util;

/**
 * @author Simon Morgan
 *
 */
public class StaticEntity extends Entity {
    public StaticEntity(float[] vertices, float x, float y, String name) {
        poly = new Polygon(Util.floatsToVectors(vertices));
        body = new StaticBody(name, poly);
        body.setUserData(this);
        move(x, y);
    }
}
