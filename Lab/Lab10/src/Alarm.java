public class Alarm extends Thread {

    private long timer;
    private String name;
    private int id;
    private static int nextId = 1;

    public Alarm(String name, int timer) {
        id = nextId++;
        this.name = ((name.isEmpty()) ? ("Alarm " + id) : (name));
        this.timer = timer * 1000L;
    }

    @Override
    public void run() {
        long endTime = System.currentTimeMillis() + timer;
        do {
            try {
                timer = endTime - System.currentTimeMillis();

                if(timer <= 0) break;

                Thread.sleep(Math.min(1000, timer));

                int alert = 10000;
                if(timer <= alert && timer > (alert - 1000)) {
                    System.out.println(name + " will go off in " + (alert / 1000) + " seconds.");
                }
            } catch (InterruptedException e) {
                System.out.println(name + " has been interrupted at " + (timer / 1000) + " seconds.");
                return;
            }
        } while(timer > 0);

        System.out.println(name + " has gone off!");
    }

    @Override
    public String toString() {
        return name + " is currently at " + (timer/1000) + " seconds";
    }

}
