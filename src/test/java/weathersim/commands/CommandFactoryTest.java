package weathersim.commands;

import org.junit.jupiter.api.Test;
import weathersim.SimulationUI;
import weathersim.views.MoistureViewStrategy;
import weathersim.views.ResultViewStrategy;
import weathersim.views.TempViewStrategy;

import static org.junit.jupiter.api.Assertions.*;

public class CommandFactoryTest {
    @Test
    public void testTempViewStrategy() {
        CommandFactory commandFactory = new CommandFactory();
        SimulationUI ui = new SimulationUI();
        ICommand command = commandFactory.newTempViewCommand(ui);
        command.execute();
        assertInstanceOf(TempViewStrategy.class, ui.getViewStrategy());
    }

    @Test
    public void testMoistureViewStrategy() {
        CommandFactory commandFactory = new CommandFactory();
        SimulationUI ui = new SimulationUI();
        ICommand command = commandFactory.newMoistureViewCommand(ui);
        command.execute();
        assertInstanceOf(MoistureViewStrategy.class, ui.getViewStrategy());
    }

    @Test
    public void testResultViewStrategy() {
        CommandFactory commandFactory = new CommandFactory();
        SimulationUI ui = new SimulationUI();
        ICommand command = commandFactory.newResultViewCommand(ui);
        command.execute();
        assertInstanceOf(ResultViewStrategy.class, ui.getViewStrategy());
    }
}
