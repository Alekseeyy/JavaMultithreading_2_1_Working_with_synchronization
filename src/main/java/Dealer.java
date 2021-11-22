import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private static final int SELL_TIME = 1000;
    private static final int RECEIVE_TIME = 3000;
    private static final int CARS = 10;
    private final List<Car> cars = new ArrayList<>();

    public synchronized void sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (cars.size() == 0) {
                System.out.println("Машин нет");
                wait();
            }
            Thread.sleep(SELL_TIME);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
            cars.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void receiveCar() {
        for (int i = 0; i < CARS; i++) {
            try {
                synchronized (this) {
                    Thread.sleep(RECEIVE_TIME);
                    cars.add(new Car());
                    System.out.println("Производитель " + Thread.currentThread().getName() + " выпустил 1 авто");
                    notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
