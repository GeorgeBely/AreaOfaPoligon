package ru.example.common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: krld
 * Date: 04.05.13
 * Time: 17:50
 * To change this template use File | Settings | File Templates.
 */
public abstract class Tile {
    protected static BufferedImage water;
    protected static BufferedImage waterDeep;

    protected static BufferedImage grass;

    protected static BufferedImage sand;

    static {
        try {
            final String dir = System.getProperty("user.dir");
            grass = ImageIO.read(new File("AreaOfaPoligon/res/grass.png"));
            sand = ImageIO.read(new File("AreaOfaPoligon/res/sand.png"));
            water = ImageIO.read(new File("AreaOfaPoligon/res/water.png"));
            waterDeep = ImageIO.read(new File("AreaOfaPoligon/res/waterDeep.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected final int x;
    protected final int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void draw(Graphics g);
}
