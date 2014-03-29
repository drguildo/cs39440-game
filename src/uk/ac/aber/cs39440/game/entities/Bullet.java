package uk.ac.aber.cs39440.game.entities;

public class Bullet extends DynamicEntity {

    public Bullet(float x, float y) {
        //super(new float[] {2, 2, -2, 2, -2, -2, 2, -2}, x, y, 1.0f, "Bullet");
        super(new float[] {2, 0, 0, 2, -2, 0, 0, -2}, x, y, 1.0f, "Bullet");
    }

}
