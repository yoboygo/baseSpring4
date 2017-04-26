package tk.codecube.test.queue;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个阻塞的循环数组
 * @author songjl
 *
 * @param <E>
 */
public class CycleArray<E> {
   
    private Object[] datas;
    private int offset = -1;
    private boolean full = false;
    
    private final ReentrantLock lock = new ReentrantLock();
    
    public CycleArray(int size) {
        this.datas = new Object[size];
    }
    
    public void add(E e){
        lock.lock();
        try{
            int index = ( this.offset + 1 ) % this.datas.length;
            this.offset = index;
            
            //判断是否是第一圈
            if(this.offset == (this.datas.length - 1)) 
            {
                setFull(true);
            }
            
            datas[index] = e;
        }finally{
            lock.unlock();
        }
    }
    
    @SuppressWarnings("unchecked")
    public E get(int i){
        rangeCheck(i);
        lock.lock();
        try{
            //第一圈
            if(!isFull()){
                return (E) datas[i];
            }
            //第N圈
            int index = (this.offset + i + 1) % this.datas.length;
            E element = (E) datas[index];
            return element;
        }finally{
            lock.unlock();
        }
    }
    
    private void rangeCheck(int index) {
        if (index >= this.datas.length)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+ this.datas.length;
    }

    
    public boolean isFull() {
        return full;
    }

    
    public void setFull(boolean full) {
        this.full = full;
    }

    public int getSize(){
        return this.datas.length;
    }
    public int getCapacity(){
        if(isFull()){
            return getSize();
        }else{
            return this.offset + 1;
        }
    }

}