package weathersim.commands;

import weathersim.SimulationUI;

public class CommandFactory {
    public ICommand newMoistureViewCommand(SimulationUI sim) {
        return new MoistureViewCommand(sim);
    }

    public ICommand newResultViewCommand(SimulationUI sim) {
        return new ResultViewCommand(sim);
    }

    public ICommand newTempViewCommand(SimulationUI sim) {
        return new TempViewCommand(sim);
    }
}
