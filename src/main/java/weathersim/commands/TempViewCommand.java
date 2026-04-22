package weathersim.commands;

import weathersim.SimulationUI;
import weathersim.views.TempViewStrategy;

public class TempViewCommand implements ICommand {
    private final SimulationUI ui;

    public TempViewCommand(SimulationUI ui) {
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.setViewStrategy(new TempViewStrategy());
    }
}