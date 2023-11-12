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
public class LinkedList<E> implements List<E>{
    
    private Node<E> first;
    private Node<E> last;

    public LinkedList() {
        first = null;
        last = null;
    }

    @Override
    public void removeDuplicates(Comparator comparator) {
        for (Node<E> i = first; i != null; i = i.getNext()){
            LinkedList<Integer> indices = new LinkedList<>();
            int contador = 0;
            for (Node<E> j = i.getNext(); j != null; j = j.getNext()){
                contador++;
                if(comparator.compare(i.getContent(), j.getContent()) == 0){
                    indices.addLast(contador);
                }
            }
            int contador2 = 0;
            for (int indice: indices){
                this.remove(indice - contador2);
                contador2++;
            }
        }
        
    }

    @Override
    public boolean binarySearch(E element, Comparator<E> comparator) {
        int mitad = this.size() / 2;
        System.out.println(this);
        if (comparator.compare(this.get(mitad), element) == 0)
            return true;
        if (this.size() == 1)
            return false;
        if (comparator.compare(this.get(mitad), element) < 0){
            return this.subList(mitad, this.size()).binarySearch(element, comparator);
        }else
            mitad = (this.size() - 1) / 2;
            return this.subList(0,mitad).binarySearch(element, comparator);            
    }

    @Override
    public E removeElement(E element, Comparator<E> comparator) {
        int contador = 0;
        for(E e: this){
            if (comparator.compare(e, element) == 0)
                return this.remove(contador);
            contador++;
        }
        return null;
    }

    @Override
    public int getIndexOf(E element, Comparator<E> comparator) {
        int indice = 0;
        for (E e: this){
            if (comparator.compare(e, element) == 0)
                return indice;
            indice++;
        }
        return -1;
    }

    @Override
    public List<Integer> getAllIndicesOf(E element, Comparator<E> comparator) {
        List<Integer> indices = new LinkedList<>();
        int indice = 0;
        for (E e: this){
            if (comparator.compare(e, element) == 0){
                indices.addLast(indice);
            }
            indice++;
        }
        return indices;
    }

    @Override
    public void sort(Comparator comparator) {
        for (int i = 0; i < this.size(); i++){
            for(int j = i + 1; j < this.size(); j++){
                if(comparator.compare(this.get(i), this.get(j)) < 0){
                    E e = this.get(i);
                    this.set(i, this.get(j));
                    this.set(j, e);
                }
            }
        }
    }

    @Override
    public void insertSorted(E element, Comparator<E> comparator) {
        int contador = 0;
        for(Node<E> i = first; i != null; i = i.getNext()){
            if(comparator.compare(element, i.getContent()) < 0){
                this.addFirst(element);
                break;
            }
            if(contador == this.size() - 1 && comparator.compare(element, i.getContent()) > 0){
                this.addLast(element);
            }
            if(comparator.compare(element, i.getContent()) > 0 && (i.getNext().getContent() != null && comparator.compare(element, i.getNext().getContent()) < 0)){
                System.out.println(contador);
                this.add(contador + 1, element);
              
            }
            contador++;
        }
    }

    @Override
    public LinkedList<E> mergeSorted(LinkedList<E> otherList, Comparator<E> comparator) {
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
        for(E e: elements){
            if(!this.contains(e))
                return false;
        }
        return true;
    }

    
    //METODOS NECESARIOS
    @Override
    public E remove(int index) {
        if (index < 0 || index >= this.size()) {
            return null;
        } else if (index == 0) {
            E tmp = getFirst();
            removeFirst();
            return tmp;
        } else if (index == this.size() - 1) {
            E tmp = getLast();
            removeLast();
            return tmp;
        }

        Node<E> j = first;
        for (int i = 0; i != index - 1; i++) {
            j = j.getNext();
        }

        Node<E> tmp = j.getNext();
        j.setNext(tmp.getNext());
        tmp.setNext(null);
        return tmp.getContent();

    }

    @Override
    public boolean addFirst(E element) {
        Node<E> nodo = new Node<>(element);
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            first = last = nodo;
        } else {
            nodo.setNext(first);
            first = nodo;
        }
        return true;
    }

    @Override
    public boolean addLast(E element) {
        Node<E> nodo = new Node<>(element);
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            first = last = nodo;
        } else {
            last.setNext(nodo);
            last = nodo;
        }
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if(index == 0){
            this.addFirst(element);
            return true;
        }
        if(index == this.size()){
            this.addLast(element);
            return true;
        }
        Node<E> j = first;
        for (int i = 0; i != index - 1; i++) {
            j = j.getNext();
        }

        Node<E> nuevo = new Node<>(element);
        nuevo.setNext(j.getNext());
        j.setNext(nuevo);
        
        return true;
            
    }

    @Override
    public boolean removeLast() {
        if (this.isEmpty()){
            return false;
        } else if (first == last) {
            first = last = null; 
        } else {
            Node<E> nodo = first;
            while (nodo.getNext() != last) {
                nodo = nodo.getNext();
            }
            last = nodo;
            last.setNext(null);
        }
        return true;
    }
    
    @Override
    public boolean removeFirst() {
        if (isEmpty()) {
            return false;
        } else if (first == last) {
            first = last = null;
        } else {
            Node<E> tmp = first;
            first = first.getNext();
            tmp.setNext(null);
        }
        return true;
    }    

    @Override
    public boolean contains(E element) {
        if (element == null || isEmpty()) {
            return false;
        }
        for (Node<E> i = first; i != null; i = i.getNext()) {
            if (i.getContent().equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return (first == null && last == null);
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= this.size()) {
            return null;
        }
        int j = 0;
        for (Node<E> i = first; i != null; i = i.getNext()) {
            if (j == index) {
                return i.getContent();
            }
            j++;
        }
        return null;
    }
    
    @Override
    public E set(int index, E element) {
        if (element == null || index < 0 || index >= this.size()) {
            return null;
        }

        int j = 0;
        for (Node<E> i = first; i != null; i = i.getNext()) {
            if (j == index) {
                E tmp = i.getContent();
                i.setContent(element);
                return tmp;
            }
            j++;
        }
        return null;
    }

    @Override
    public int indexOf(Object element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        int cont = 0;
        Node n;
        for (n = this.first; n != null; n = n.getNext()) {
            cont++;
        }
        return cont;
    }

    @Override
    public List<E> subList(int from, int to) {
        List<E> lista = new LinkedList<>();
        for(int i = from; i <= to; i++){
            lista.addLast(this.get(i));
        }
        return lista;
    }

    public E getFirst() {
        return first.getContent();
    }

    public E getLast() {
        return last.getContent();
    }
    
        @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (isEmpty()) {
            return "[]";
        }
        s.append("[");

        for (Node<E> p = this.first; p != null; p = p.getNext()) {
            if (p != this.last) {
                s.append(p.getContent() + ",");
            } else {
                s.append(p.getContent() + "]");
            }
        }
        return s.toString();
    }
    
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private Node<E> cursor = first;

            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public E next() {
                E tmp = cursor.getContent();
                cursor = cursor.getNext();
                return tmp;
            }
        };

        return it;
    }    

    
}
