public class Array<E> {
    private E[] data;
    private int size;

    // 构造函数
    public Array(int Capacity){
        data = (E[])new Object[Capacity];
        size = 0;
    }
    // 默认构造函数
    public Array(){
        this(10);
    }

    // 判断数组是否已满
    public boolean isFull(){
        return size == data.length;
    }
    // 判断数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 找出元素 e 的下标，没有返回 -1
    public int find(E e){
        for(int i = 0; i < size; i++){
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    // 判断数组是否包含元素 e
    public boolean contain(E e){
        return find(e) == -1 ? false : true ;
    }

    // 获取数组的大小 size
    public int getSize(){
        return size;
    }

    // 获取数组的容量 Capacity
    public int getCapacity(){
        return data.length;
    }

    // 获取下标为 index 位置的元素 e
    public E get(int index){
        // 判断传入下标是否合法
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get Failed! Index is illegal!");
        }
        return data[index];
    }

    // 获取数组的第一个元素
    public E getFirst(){
        return get(0);
    }

    // 获取数组的最后一个元素
    public E getLast(){
        return get(size - 1);
    }

    // 设置下标 index 位置处的元素为 e
    public void set(int index, E e){
        // 判断传入下标是否合法
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Set Failed! Index is illegal!");
        }
        data[index] = e;
    }

    // 在下标为 index 位置插入元素 e
    public void add(int index, E e){
        // 判断传入下标是否合法
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add Failed! Index is illegal!");
        }

        // 如果数组已满，则重新分配空间
        if (isFull()){
            resize(data.length * 2);
        }

        // 在 index 位置插入元素 e
        for (int i = size - 1; i >= index; i--){
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    // 在数组头插入元素 e
    public void addFirst(E e){
        add(0, e);
    }

    // 在数组尾插入元素 e
    public void addLast(E e){
        add(size, e);
    }

    // 删除下标为 index 位置处的元素
    public E remove(int index){
        // 判断下标是否合法
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Remove Failed! Index is illegal!");
        }

        // 判断是否需要重新分配空间
        if (size == data.length / 4 && data.length / 2 != 0){
            resize(data.length / 2);
        }
        E tmp = data[index];
        for(int i = index; i < size - 1; i++){
            data[i] = data[i + 1];
        }
        size--;
        return tmp;
    }

    // 删除数组头部元素
    public E removeFirst(){
        return remove(0);
    }

    // 删除数组尾部元素
    public E removeLast(){
        return remove(size - 1);
    }

    // 删除元素 e
    public void removeElement(E e){
        int index = find(e);
        if (index != -1){
            remove(index);
        }
    }

    // 重新分配空间大小
    public void resize(int newCapacity){
        if (newCapacity <= 0){
            throw new IllegalArgumentException("Resize Failed! NewCapacity is illegal!");
        }

        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++){
            newData[i] = data[i];
        }
        data = newData;
    }

    // 重载打印函数
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array size = %d, Capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++){
            res.append(data[i]);
            if (i != size - 1)
                res.append(", ");
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args){
        Array<Integer> arr = new Array<>();

        for(int i = 0 ; i < 10 ; i ++)
            arr.addLast(i);
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

        for(int i = 0 ; i < 4 ; i ++){
            arr.removeFirst();
            System.out.println(arr);
        }
    }
}
