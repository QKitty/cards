/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * This class represents a rectangle with a Z ordering value for rendering
 *
 * @author rtucker
 */
public class RectangleZ extends Rectangle implements Comparable<RectangleZ> {

    private int zDepth;

    public RectangleZ() {
        super();
        zDepth = 0;
    }

    public RectangleZ(Rectangle rctngl) {
        super(rctngl);
        zDepth = 0;
    }

    public RectangleZ(Rectangle rctngl, int z1) {
        super(rctngl);
        zDepth = z1;
    }

    public RectangleZ(int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
        zDepth = 0;
    }

    public RectangleZ(int i, int i1, int z1, int i2, int i3) {
        super(i, i1, i2, i3);
        zDepth = z1;
    }

    public RectangleZ(int i, int i1) {
        super(i, i1);
        zDepth = 0;
    }

    public RectangleZ(int i, int i1, int z1) {
        super(i, i1);
        zDepth = z1;
    }

    public RectangleZ(Point point, Dimension dmnsn) {
        super(point, dmnsn);
        zDepth = 0;
    }

    public RectangleZ(Point point, int z1, Dimension dmnsn) {
        super(point, dmnsn);
        zDepth = z1;
    }

    public RectangleZ(Point point) {
        super(point);
        zDepth = 0;
    }

    public RectangleZ(Point point, int z1) {
        super(point);
        zDepth = z1;
    }

    public RectangleZ(Dimension dmnsn) {
        super(dmnsn);
        zDepth = 0;
    }

    public int getZDepth() {
        return zDepth;
    }

    public void setZDepth(int depth) {
        zDepth = depth;
    }

    @Override
    public int compareTo(RectangleZ t) {
        int depth = 0;
        if (null != t) {
            if (this.zDepth != t.zDepth) {
                if (this.zDepth < t.zDepth) {
                    depth = -1;
                } else {
                    depth = 1;
                }
            }
        }
        return depth;
    }
}
