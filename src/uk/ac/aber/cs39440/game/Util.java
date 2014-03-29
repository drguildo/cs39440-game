package uk.ac.aber.cs39440.game;

import net.phys2d.math.Vector2f;

public class Util {
    public static Vector2f[] floatsToVectors(float[] f) {
        Vector2f[] vectors = new Vector2f[f.length / 2];

        for (int i = 0; i < vectors.length; i++) {
            vectors[i] = new Vector2f(f[i * 2], f[(i * 2) + 1]);
        }

        return vectors;
    }

    public static float[] vectorsToFloats(Vector2f[] v) {
        float[] f = new float[v.length * 2];

        int i = 0;
        for (Vector2f vec : v) {
            f[i] = vec.getX();
            f[i+1] = vec.getY();
            i += 2;
        }

        return f;
    }
}
