/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.animations;

import datamodel.interfaces.ICard;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.Icon;
import views.components.CardListPanel;

/**
 *
 * @author rtucker
 */
public class CardDrawingAnimation implements Callable<Void> {

    private final LocalTime start;
    private final LocalTime end;
    private final ICard drawnCard;
    private final Point startPoint;
    private final Point renderPoint;
    private final Integer targetY;
    private final CardListPanel dest;
    private final Component component;
    private final ReentrantLock lock;
    private final BufferedImage cardImage;
    private boolean complete;

    public CardDrawingAnimation(ICard theCard, Point startLoc, int desiredY, long animationLength, CardListPanel destList, Component toRepaint) {
        start = LocalTime.now();
        end = start.plusSeconds(animationLength);
        drawnCard = theCard;
        startPoint = startLoc;
        renderPoint = new Point(startLoc);
        targetY = desiredY;
        dest = destList;
        component = toRepaint;
        lock = new ReentrantLock();
        complete = false;
        int width, height;
        width = 100;
        height = 200;
        Icon cardIcon = drawnCard.getCardIcon(width, height);
        cardImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = cardImage.createGraphics();
        Composite originalComposite = g.getComposite();
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f);
        g.setComposite(composite);
        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(0, 0, width, height);
        g.setComposite(originalComposite);
        g.setColor(Color.BLACK);
        cardIcon.paintIcon(null, g, 0, 0);
        g.dispose();
    }

    @Override
    public Void call() throws Exception {
        //if (!complete && !Thread.interrupted()) {
        java.awt.EventQueue.invokeLater(() -> {
            System.out.println("Animation loop ran....");
        });
        LocalTime currTime = LocalTime.now();
        if (currTime.isBefore(end)) {
            Long elapsed = ChronoUnit.NANOS.between(start, currTime);
            Long totalDuration = ChronoUnit.NANOS.between(start, end);
            Double percComplete = elapsed.doubleValue() / totalDuration.doubleValue();
            Double deltaY = targetY.doubleValue() - startPoint.y;
            Double currY = (deltaY * percComplete) + startPoint.y;
            lock.lock();
            try {
                renderPoint.x = startPoint.x;
                renderPoint.y = currY.intValue();
            } finally {
                lock.unlock();
            }
            component.repaint();
        } else {
            complete = true;
            dest.addCard(drawnCard);
            component.repaint();
            throw new InterruptedException("Animation Completed");
        }
//        } else {
//            throw new InterruptedException("Animation Completed");
//        }
        return null;
    }

    public Point getRenderPoint() {
        Point result = new Point();
        lock.lock();
        try {
            result = new Point(renderPoint);
        } finally {
            lock.unlock();
        }
        return result;
    }

    public BufferedImage getRenderImage() {
        return cardImage;
    }

    public boolean isComplete() {
        return complete;
    }

}
