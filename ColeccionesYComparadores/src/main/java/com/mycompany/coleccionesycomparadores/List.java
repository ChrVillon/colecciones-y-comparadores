/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coleccionesycomparadores;

import java.util.Comparator;
import java.util.Iterator;
/**
 *
 * @author Usuario
 */
public interface List<E> extends Iterable<E> {
    
    //metodos de la tarea
    public void removeDuplicates(Comparator<E> comparator);
    
    public boolean binarySearch(E element, Comparator<E> comparator);
    
    public E removeElement(E element, Comparator<E> comparator);
    
    public int getIndexOf(E element, Comparator<E> comparator);
    
    public List<Integer> getAllIndicesOf(E element, Comparator<E> comparator);
    
    public void sort(Comparator<E> comparator);
    
    public void insertSorted(E element, Comparator<E> comparator);
    
    public LinkedList<E> mergeSorted(LinkedList<E> otherList, Comparator<E> comparator);
    
    public LinkedList<E> findUnion(LinkedList<E> otherList, Comparator<E> comparator);
    
    public boolean containsAll(LinkedList<E> elements, Comparator<E> comparator);
    
    //metodos para poder implementar los metodos de la tarea
    public E remove(int index);
    
    public boolean addFirst(E element);
    
    public boolean addLast(E element);
    
    public boolean add(int index, E element);
    
    public boolean removeLast();
    
    public boolean removeFirst();
    
    public boolean contains(E element);
    
    public boolean isEmpty();
    
    public boolean isFull();
    
    public E get(int index);
    
    public E set(int index, E element);
    
    public int indexOf(E element);
    
    public int size();
    
    public List<E> subList(int from, int to);
    
}
