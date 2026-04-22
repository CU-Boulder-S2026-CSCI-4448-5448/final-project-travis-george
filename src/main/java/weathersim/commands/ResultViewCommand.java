package weathersim.commands;

import weathersim.SimulationUI;
import weathersim.views.ResultViewStrategy;

public class ResultViewCommand implements ICommand {
    private final SimulationUI ui;

    public ResultViewCommand(SimulationUI ui) {
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.setViewStrategy(new ResultViewStrategy());
    }
}