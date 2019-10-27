package hashmapStudy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HashMap<K,V> {

    int defaultCapacity = 1<<4;
    public MapEntry[] list = new MapEntry[defaultCapacity];
    transient int size = 0;

    /**
     * 内部类存在的意义是什么？？
     */
    class MapEntry<K,V>{ //为什么加<K,V>,才能用数组声明
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public V put(K key,V value){
        // 1、构建一个MapEntry
        // 2、for循环数组有没有该key，有的话替换
        // 3、没有的话新增
        int i = key.hashCode() % defaultCapacity;
        return null;//返回的是原来的value
    }

    public K get(K key){
        return null;
    }

}
