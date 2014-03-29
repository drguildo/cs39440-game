package uk.ac.aber.cs39440.game.entities;

import net.phys2d.math.ROVector2f;
import uk.ac.aber.cs39440.game.Map;
import uk.ac.aber.cs39440.game.Physics;

public class Ship extends DynamicEntity {
    private int shields = 100;

    private long lastTime = System.currentTimeMillis();

    private float curRot = 0.0f;

    public Ship(float x, float y) {
        super(new float[] { 10, 5, -10, 5, 0, -15 }, x, y, 3.0f, "Ship");
    }

    public void decreaseShields(int amount) {
        setShields(shields - amount);
    }

    public void fire(Map map) {
        long currTime = System.currentTimeMillis();
        if ((currTime - lastTime) > Physics.BULLETTIME) {
            float vx, vy;

            vx = (float)Math.sin(getBody().getRotation());
            vy = (float)Math.cos(getBody().getRotation());

            Bullet bullet = new Bullet(getTip().getX() + vx,
                                       getTip().getY() + -vy * 10);
            bullet.getBody().setForce(vx * Physics.BULLETSPEED,
                                      -vy * Physics.BULLETSPEED);
            map.add(bullet);

            lastTime = System.currentTimeMillis();
        }
    }

    public int getShields() {
        return shields;
    }

    public ROVector2f getTip() {
        return poly.getVertices(body.getPosition(), body.getRotation())[2];
    }

    public boolean isDestroyed() {
        if (shields <= 0) {
            return true;
        }
        return false;
    }

    public void rotateLeft() {
        //body.setTorque(-ROTATION);
        curRot -= Physics.ROTATION;
        body.setRotation(curRot);
    }

    public void rotateRight() {
        //body.setTorque(ROTATION);
        curRot += Physics.ROTATION;
        body.setRotation(curRot);
    }

    public void setShields(int shields) {
        if (shields > 0) {
            this.shields = shields;
        } else {
            this.shields = 0;
        }
    }

    public void thrust() {
        double vx, vy;

        vx = Physics.THRUST * Math.sin(getBody().getRotation());
        vy = Physics.THRUST * Math.cos(getBody().getRotation());

        body.setForce((float) vx, (float) -vy);
    }
}
