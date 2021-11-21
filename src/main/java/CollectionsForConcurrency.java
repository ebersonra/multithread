import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ramos
 *
 * **/
public class CollectionsForConcurrency {

    // Coleções que são Thread-safe
    // Não utilizar CopyOnWriteArrayList com um numero alto de read/write
    // pois a lista inteira é clonada e colocada em memória
    // a cada add e remove de um item da lista
    private static List<String> list = new CopyOnWriteArrayList<>();

    // Thread-safe
    // Pode ser usado por N Threads
    // Usa o synchronized no ponto correto
    private static Map<Integer, String> map = new ConcurrentHashMap<>();

    // Thread-safe
    // N Threads add item na lista
    // Ex: batchs add item na lista e outros batchs removendo item da lista
    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {

        MyRunnable runnable = new MyRunnableWithQueue();

        Thread thread0 = new Thread(runnable);
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread0.start();
        thread1.start();
        thread2.start();

        Thread.sleep(500);
        if(!list.isEmpty()) {
            System.out.println(list);
        }
        if (!map.isEmpty()) {
            System.out.println(map);
        }
        if (!queue.isEmpty()) {
            System.out.println(queue);
        }
    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            list.add("Add Item");
            String name = Thread.currentThread().getName();
            System.out.println(name + ": Item adicionado");
        }
    }

    public static class MyRunnableWithHashMap extends MyRunnable {

        @Override
        public void run() {
            map.put(new Random().nextInt(),"HashMap Add Item");
            String name = Thread.currentThread().getName();
            System.out.println(name + ": HashMap Item adicionado");
        }
    }

    public static class MyRunnableWithQueue extends MyRunnable {

        @Override
        public void run() {
            queue.add("Queue Add Item");
            String name = Thread.currentThread().getName();
            System.out.println(name + ": Queue Item adicionado");
        }
    }
}
