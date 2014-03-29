package uk.ac.aber.cs39440.game.entities;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Polygon;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import uk.ac.aber.cs39440.game.Util;

/**
 * @author Simon Morgan
 *
 * XXX: Why doesn't calling move() here, instead of in each subclass, work?
 *
 */
public abstract class Entity {
    protected Body body;
    protected Polygon poly;

    public void draw(Graphics g) {
        ROVector2f[] verts = poly.getVertices(body.getPosition(),
                                              body.getRotation());

        for (int i = 0, j = verts.length - 1; i < verts.length; j = i, i++) {
            g.drawLine(verts[i].getX(), verts[i].getY(),
                       verts[j].getX(), verts[j].getY());
        }
    }

    public Body getBody() {
        return body;
    }

    public Polygon getPoly() {
        return poly;
    }

    public Shape getShape() {
        Vector2f[] v = poly.getVertices(body.getPosition(), body.getRotation());

        return new org.newdawn.slick.geom.Polygon(Util.vectorsToFloats(v));
    }

    public void move(float x, float y) {
        body.setPosition(x, y);
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
