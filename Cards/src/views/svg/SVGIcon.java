/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.svg;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Dimension2D;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.w3c.dom.Document;

/**
 * Class that allows an SVG 'image' to be used as a Swing Icon
 * @author Cameron McCormack - http://mcc.id.au/2005/04/SVGIcon.java
 */
public class SVGIcon extends UserAgentAdapter implements Icon {
    
    // <editor-fold defaultstate="collapsed" desc="Class Attributes">
    /**
     * The BufferedImage generated from the SVG document.
     */
    protected BufferedImage bufferedImage;

    /**
     * The width of the rendered image.
     */
    protected int width;
    
    /**
     * The height of the rendered image.
     */
    protected int height;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Create a new SVGIcon object.
     * @param uri The URI to read the SVG document from.
     * @throws org.apache.batik.transcoder.TranscoderException
     */
    public SVGIcon(String uri) throws TranscoderException {
        this(uri, 0, 0);
    }

    /**
     * Create a new SVGIcon object.
     * @param uri The URI to read the SVG document from.
     * @param w The width of the icon.
     * @param h The height of the icon.
     * @throws org.apache.batik.transcoder.TranscoderException
     */
    public SVGIcon(String uri, int w, int h) throws TranscoderException {
        generateBufferedImage(new TranscoderInput(uri), w, h);
    }

    /**
     * Create a new SVGIcon object.
     * @param doc The SVG document.
     * @throws org.apache.batik.transcoder.TranscoderException
     */
    public SVGIcon(Document doc) throws TranscoderException {
        this(doc, 0, 0);
    }

    /**
     * Create a new SVGIcon object.
     * @param doc The SVG document.
     * @param w The width of the icon.
     * @param h The height of the icon.
     * @throws org.apache.batik.transcoder.TranscoderException
     */
    public SVGIcon(Document doc, int w, int h) throws TranscoderException {
        generateBufferedImage(new TranscoderInput(doc), w, h);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Icon interface methods implementation">
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.drawImage(bufferedImage, x, y, null);
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="UserAgentAdapter methods implementation">
    @Override
    public Dimension2D getViewportSize() {
        return new Dimension(width, height);
    }
    // </editor-fold>

    protected final void generateBufferedImage(TranscoderInput in, int w, int h) throws TranscoderException {
        BufferedImageTranscoder t = new BufferedImageTranscoder();
        if (w != 0 && h != 0) {
            t.setDimensions(w, h);
        }
        t.transcode(in, null);
        bufferedImage = t.getBufferedImage();
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
    }
}
