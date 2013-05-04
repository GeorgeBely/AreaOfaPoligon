package ru.example.common;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: krld
 * Date: 04.05.13
 * Time: 17:50
 * To change this template use File | Settings | File Templates.
 */
public class WaterTile extends Tile {
    public static final int SAND_SPREAD_RADIUS = 1;
    private final boolean nearBeachLine;
    private final boolean drawSand;

    public WaterTile(int x, int y, boolean nearBeachLine, boolean drawSand) {
        super(x, y);
        this.nearBeachLine = nearBeachLine;
        this.drawSand = drawSand;
    }

    @Override
    public void draw(Graphics g) {
        if (nearBeachLine) {
        g.drawImage(water, x - 8, y - 8, null);  } else {
        g.drawImage(waterDeep, x - 8, y - 8, null);          }
    }

    public void drawSand(Graphics g) {
        if (!drawSand) {
            return;
        }
        for (int i = -SAND_SPREAD_RADIUS; i <= SAND_SPREAD_RADIUS; i++) {
            for (int j = -SAND_SPREAD_RADIUS; j <= SAND_SPREAD_RADIUS; j++) {
                /*if (Math.random() > 0.0d) {
                g.drawImage(sand, x - 8 + i * 4, y - 8 + j * 4, null);
                }
                */
                g.drawImage(sand, x - 8 + i * 4, y - 8 + j * 4, null);
            }
        }
    }
}
