//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package xyz.comfyz.learning.swagger;

import java.util.List;

public class Slice<T> {
    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;
    private boolean isFirst;
    private boolean isLast;
    private int number;
    private int size;
    private int numberOfElements;
    private List<T> content;

    public Slice() {
    }

    public long getTotalElements() {
        return this.totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHasNext() {
        return this.hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return this.hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public boolean isFirst() {
        return this.isFirst;
    }

    public void setFirst(boolean first) {
        this.isFirst = first;
    }

    public boolean isLast() {
        return this.isLast;
    }

    public void setLast(boolean last) {
        this.isLast = last;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return this.numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<T> getContent() {
        return this.content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    /*public <S> Slice<S> map(Converter<T, S> converter) {
        Slice<S> result = new Slice();
        result.setContent(this.getConvertedContent(converter));
        result.setFirst(this.isFirst());
        result.setLast(this.isLast());
        result.setHasPrevious(this.isHasPrevious());
        result.setHasNext(this.isHasNext());
        result.setNumber(this.getNumber());
        result.setSize(this.getSize());
        result.setNumberOfElements(this.getNumberOfElements());
        result.setTotalElements(this.getTotalElements());
        result.setTotalPages(this.getTotalPages());
        return result;
    }

    private <S> List<S> getConvertedContent(Converter<S, T> converter) {
        Assert.notNull(converter, "Converter must not be null!");
        List<S> result = new ArrayList(this.getContent().size());
        Stream var10001 = this.content.stream();
        converter.getClass();
        result.addAll((Collection) var10001.map(converter::convert).collect(Collectors.toList()));
        return result;
    }*/
}
