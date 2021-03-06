package fun.enhui.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 代码实现自旋锁
 * @Author: HuEnhui
 * @Date: 2019/10/17 17:37
 */
public class SpinLockDemo {
    // 原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t尝试加锁。。。");
        // 主内存为null，则修改，否则循环等待
        while(!atomicReference.compareAndSet(null,thread)){

        }
    }
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t解锁。。。");
        atomicReference.compareAndSet(thread,null);
    }
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.myLock();
            System.out.println(Thread.currentThread().getName() + "\t获得锁，正在执行。。。");
            // 暂停一会线程
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.myUnLock();
        },"A").start();

        new Thread(()->{
            spinLockDemo.myLock();
            System.out.println(Thread.currentThread().getName() + "\t获得锁，正在执行。。。");
            // 暂停一会线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.myUnLock();
        },"B").start();
    }
}
