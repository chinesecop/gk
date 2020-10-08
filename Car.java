public class Car implements Runnable {

    private static int CARS_COUNT;

    private Race race;
    private int speed;
    private String name;
    private static CountDownLatch carsReady;
    private static CountDownLatch carsFinish;
    private static CyclicBarrier cyclicBarrier;

    static {
        CARS_COUNT = 0;
        carsReady = MainClass.carsReady;
        carsFinish = MainClass.carsFinish;
        cyclicBarrier = MainClass.cyclicBarrier;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            carsReady.countDown();
            System.out.println(this.name + " готов");
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        final ArrayList<Stage> stages = race.getStages();
        for (int i = 0; i < stages.size(); i++) {
            stages.get(i).go(this);
        }
        carsFinish.countDown();
    }
}