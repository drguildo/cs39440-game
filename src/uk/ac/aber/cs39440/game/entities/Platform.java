package uk.ac.aber.cs39440.game.entities;


/**
 * @author Simon Morgan
 *
 */
public class Platform extends StaticEntity {
    public Platform(float height, float width, float x, float y) {
        super(new float[] { 0, 0, width, 0, width, height, 0, height }, x, y, "Platform");
    }
}
