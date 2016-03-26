/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import java.util.Collection;

/**
 * An interface used to represent a known list of cards.
 * @author qkitt
 */
public interface IKnownFiniteDeckAlgorithm extends IDeckAlgorithm {

    boolean add (ICard aCard);

    void add (int index, ICard element);

    boolean addAll (Collection<? extends ICard> c);

    boolean addAll (int index, Collection<? extends ICard> c);

    void clear ();

    ICard remove (int index);

    boolean remove (Object o);

}
