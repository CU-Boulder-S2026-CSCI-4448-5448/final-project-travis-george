package weathersim.commands;

import weathersim.SimulationUI;
import weathersim.views.MoistureViewStrategy;

public class MoistureViewCommand implements ICommand {
    private final SimulationUI ui;

    public MoistureViewCommand(SimulationUI ui) {
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.setViewStrategy(new MoistureViewStrategy());
    }
}