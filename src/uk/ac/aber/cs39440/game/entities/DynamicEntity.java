package uk.ac.aber.cs39440.game.entities;

import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Polygon;
import uk.ac.aber.cs39440.game.Util;

/**
 * @author Simon Morgan
 *
 */
public class DynamicEntity extends Entity {
    public DynamicEntity(float[] vertices, float x, float y, float mass,
                         String name) {
        poly = new Polygon(Util.floatsToVectors(vertices));
        body = new Body(name, poly, mass);
        body.setUserData(this);
        body.setRotDamping(1.0f);
        move(x, y);
    }
}
