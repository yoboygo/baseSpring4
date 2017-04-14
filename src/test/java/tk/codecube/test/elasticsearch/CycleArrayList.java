package tk.codecube.test.elasticsearch;

/**
 * @dec 循环列表
 * @author songjl 2017年4月7日 上午11:18:46
 *
 */
public class CycleArrayList<T> {
    
    private Object[] datas;
    private int size;
    
    private T header;
    private int offset;
    
    public CycleArrayList(int size) {
        this.offset = -1;
        this.size = size;
        this.datas = new Object[size];
    }
    
    /**
     * @Dec 添加
     * @param entrty
     * 2017年4月7日 上午11:26:09 songjl
     */
    public void add(T entrty){
        ++offset;
        
        int index = offset % this.size;
        datas[index] = entrty;
        
        offset = index;
        header = entrty;
    }
    
    /**
     * @Dec 获取最新一条
     * @return
     * 2017年4月7日 上午11:26:16 songjl
     */
    public T getHeader(){
        return this.header;
    }
    
    public T get(int index){
        return (T) datas[index];
    }
}
