package ru.areaOfPolygon.services;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImagesService {

    /** –асположение изображений */
    private static final String LOCATION_IMAGES = "./AreaOfaPolygon/images";


    /**
     * @return картинку.
     */
    public static Image getImage(String imageName) {
        try {
            return ImageIO.read(new File(LOCATION_IMAGES + "/" + imageName));
        } catch (IOException e) {
            return null;
        }
    }
}
