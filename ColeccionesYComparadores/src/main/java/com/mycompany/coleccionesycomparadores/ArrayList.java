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
public class ArrayList<E> implements List<E> {

    private E[] elements;
    private int MAX_SIZE = 100;
    private int effectiveSize;

    public ArrayList() {
        elements = (E[]) new Object[MAX_SIZE];
        effectiveSize = 0;
    }
    
    @Override
    public void removeDuplicates(Comparator<E> comparator) {   
        for(int i = 0; i < effectiveSize; i++){
            ArrayList<Integer> indices = new ArrayList<>();
            int contador = 0;
            for(int j = i + 1; j < effectiveSize; j++){                
                if(comparator.compare(elements[i], elements[j]) == 0){
                    if(!indices.contains(j))
                        indices.addLast(j);                  
                }
            }
            for(int indice : indices){
                this.remove(indice - contador++);
            }
        }        
    }

    @Override
    public boolean binarySearch(E element, Comparator<E> comparator) {
        int indice = effectiveSize / 2;     
        E medio = elements[indice];
        List<E> lista;
        System.out.println(this);
        if (comparator.compare(medio, element) == 0)
            return true;
        if (this.size() == 1)
            return false;               
        if (comparator.compare(medio, element) < 0){
            lista = this.subList(indice, effectiveSize);           
            return lista.binarySearch(element, comparator);
        }            
        else{
            indice = (effectiveSize - 1) / 2;
            lista = this.subList(0, indice);
            return lista.binarySearch(element, comparator);
        }
    }

    @Override
    public E removeElement(E element, Comparator<E> comparator) {
        for(int i = 0; i < effectiveSize; i++){
            if(comparator.compare(elements[i], element) == 0)
                return this.remove(i);
        }
        return null;
    }

    @Override
    public int getIndexOf(E element, Comparator<E> comparator) {
        for(int i = 0; i < effectiveSize; i++){
            if(comparator.compare(elements[i], element) == 0)
                return i;
        }
        return -1;
    }

    @Override
    public List<Integer> getAllIndicesOf(E element, Comparator<E> comparator) {
        List<Integer> indices = new ArrayList<>();
        for(int i = 0; i < effectiveSize; i++){
            if(comparator.compare(elements[i], element) == 0)
                indices.addLast(i);
        }        
        return indices;
    }

    @Override
    public void sort(Comparator<E> comparator) {
       for(int i = 0; i < effectiveSize; i++){
          for(int j = i + 1; j < effectiveSize; j++){
                if(comparator.compare(elements[i], elements[j]) > 0){
                    E aux = elements[i];
                    elements[i] = elements[j];
                    elements[j] = aux;
                }
          }
       }
    }

    @Override
    public void insertSorted(E element, Comparator<E> comparator) {
        for(int i = 0; i < effectiveSize; i++){
            if( i == 0 && comparator.compare(element, elements[i]) < 0)
                this.addFirst(element);
            if(i == effectiveSize - 1 && comparator.compare(element, elements[i]) > 0)
                this.addLast(element);
            if(comparator.compare(element, elements[i]) < 0 && comparator.compare(element, elements[i - 1]) > 0)
                this.add(i, element);
        }
    }

    @Override
    public LinkedList<E> mergeSorted(LinkedList<E> otherList, Comparator<E> comparator){
        for(E e: this){
            otherList.addLast(e);
        }
        otherList.sort(comparator);
        return otherList;
    }   
    
    @Override
    public LinkedList<E> findUnion(LinkedList<E> otherList, Comparator<E> comparator){
        for(E e: this){
            otherList.addLast(e);
        }
        otherList.removeDuplicates(comparator);
        return otherList;
    }
    
    @Override
    public boolean containsAll(LinkedList<E> elements, Comparator<E> comparator){
        for (E e: elements){
            if(!this.contains(e))
                return false;
        }
        return true;
    }


    
    //METODOS NECESARIOS
    @Override
    public E remove(int index) {
        if(this.isEmpty()){
            return null;
        } else if(index > effectiveSize){
            return null;
        }else if(index == effectiveSize){
            this.removeLast();
        }
        E e = elements[index];
        for(int i = index; i < effectiveSize; i++){
            elements[i] = elements[i + 1];
        }
        effectiveSize--;
        return e;
    }
    
    @Override
    public boolean removeLast() {
        if(isEmpty())
            return false;
        E e = elements[effectiveSize - 1];
        elements[effectiveSize - 1] = null;
        effectiveSize--;
        return true;
    }
    
    @Override
    public boolean removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    
    private void addCapacity() {
        MAX_SIZE = MAX_SIZE * 2;
        E[] newElements = (E[]) new Object[MAX_SIZE];
        for (int i= 0; i<effectiveSize; i++) {
            newElements[i] = elements[i];
        }
        this.elements = newElements;
    }    

    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize] = element;
            effectiveSize++;

            return true;
        }
        if (isFull()) {
            addCapacity();
        }
        for (int i = effectiveSize; i > 0; i--) {
            elements[i] = elements[i - 1];
        }
        elements[0] = element;
        effectiveSize++;
        return true;        
    }
    
    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        }
        if (isFull()) {
            addCapacity();
        }
        elements[effectiveSize++] = element;
        return true;
    }    

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }
    
    @Override
    public boolean add(int index, E element) {
        if (index < 0 || index > effectiveSize) {
            return false;
        }
        if (isFull()) {
            addCapacity();
        }
        for (int i = effectiveSize; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        effectiveSize++;
        return true;
    }
    
    @Override
    public boolean contains(E element) {
        if (element == null || isEmpty()) {
            return false;
        }

        for (int i = 0; i < effectiveSize; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }
    
    
    @Override
    public String toString(){
        if(this.isEmpty()){
            return "";
        }
        String s = "" + this.get(0);
        for (int i = 1; i < effectiveSize;i++){
            s += "," + this.get(i);
        }
        return s;
    }    
    
    @Override
    public E get(int index) {
        if (index < 0 || index >= effectiveSize) {
            return null;
        }
        return elements[index];
    }    

    @Override
    public boolean isFull() {
        return effectiveSize == MAX_SIZE;
    }
    
    @Override
    public int indexOf(E element) {
        if (element == null) {
            return -1;
        }

        for (int i = 0; i < effectiveSize; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    } 

    @Override
    public List<E> subList(int from, int to) {
        ArrayList<E> lista = new ArrayList<>();
            for (int i = from; i <= to; i++){
                lista.addLast(elements[i]);
            }
        return lista;
    }
    
    @Override
    public int size() {
        return effectiveSize;
    }    
    
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor < effectiveSize;
            }

            @Override
            public E next() {
                E element = elements[cursor];
                cursor++;
                return element;
            }
        };
        return it;
    }    

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


  
}
