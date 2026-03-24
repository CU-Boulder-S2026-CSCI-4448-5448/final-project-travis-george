# Artificial Weather System (AWS)
Team: Travis Uhrig, George Fisher

## Design Patterns
### 1. Observer
The Observer pattern defines a one-to-many dependency so that when one object changes state, all dependent objects can be notified and updated automatically. In this project, `SimulationObserver` is the observer interface and `EventBus` is the publisher. `TemperatureField` posts a `temperatureUpdated` event after each update, so other simulation components can update without being tightly coupled.

### 2. Singleton
The Singleton pattern ensures that a class has exactly one instance and provides a global access point to it. In this project, `EventBus` is a Singleton and the design pattern ensures only one instance of `EventBus` exists. It uses a private constructor. That gives the simulation one shared event channel.

### 3. Facade
The Facade pattern provides a unified, higher-level interface to a subsystem and shields clients from unnecessary internal details. In this project, `SimulationFacade` will be the Facade. Client code can call `update()`, `getTemperatureGrid()`, and `getMoistureGrid()` without needing to manage the individual fields.


