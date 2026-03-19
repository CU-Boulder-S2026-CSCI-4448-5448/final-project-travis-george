package weathersim;

import java.util.ArrayList;
import java.util.List;

public class EventBus {
    private static final EventBus instance = new EventBus();
    private final List<SimulationObserver> observers = new ArrayList<>();

    private EventBus() {
    }

    public static EventBus getInstance() {
        return instance;
    }

    public void attach(SimulationObserver observer) {
        observers.add(observer);
    }

    public void detach(SimulationObserver observer) {
        observers.remove(observer);
    }

    public void post(SimulationEvent event) {
        notifyObservers(event);
    }

    private void notifyObservers(SimulationEvent event) {
        for (SimulationObserver observer : observers) {
            observer.onEvent(event);
        }
    }

    public void reset() {
        observers.clear();
    }
}
