/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.svg;

import java.awt.image.BufferedImage;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

/**
 * SVG Image transcoder to convert to a Java buffered image
 * @author Cameron McCormack - http://mcc.id.au/2005/04/SVGIcon.java
 */
public class BufferedImageTranscoder extends ImageTranscoder {

    /**
     * The BufferedImage generated from the SVG document.
     */
    protected BufferedImage bufferedImage;

    // <editor-fold defaultstate="collapsed" desc="ImageTranscoder interface implementation">
    @Override
    public BufferedImage createImage(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void writeImage(BufferedImage img, TranscoderOutput output) throws TranscoderException {
        bufferedImage = img;
    }
    // </editor-fold>

    /**
     * Returns the BufferedImage generated from the SVG document.
     * @return the BufferedImage generated from the SVG document
     */
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    /**
     * Set the dimensions to be used for the image.
     * @param w - int being the image width
     * @param h - int being the image height
     */
    public void setDimensions(int w, int h) {
        hints.put(KEY_WIDTH, new Float(w));
        hints.put(KEY_HEIGHT, new Float(h));
    }

}
