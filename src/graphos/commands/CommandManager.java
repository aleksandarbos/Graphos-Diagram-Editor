package graphos.commands;

import graphos.gui.MainFrame;

import java.util.ArrayList;

public class CommandManager {
	private ArrayList<AbstractCommand> commands = new ArrayList<AbstractCommand>();
	private int currentCommand = 0;

	public void addCommand(AbstractCommand command) {
		while (currentCommand < commands.size())
			commands.remove(currentCommand);
		commands.add(command);
		doCommand();
	}

	public void doCommand() {
		if (currentCommand < commands.size()) {
			commands.get(currentCommand++).doCommand();
			MainFrame.getInstance().getToolbar().getBtnUndo().setEnabled(true);
		}
		if (currentCommand == commands.size())
			MainFrame.getInstance().getToolbar().getBtnRedo().setEnabled(false);
	}

	public void undoCommand() {
		if (currentCommand > 0) {
			MainFrame.getInstance().getToolbar().getBtnRedo().setEnabled(true);
			commands.get(--currentCommand).undoCommand();
		}
		if (currentCommand == 0)
			MainFrame.getInstance().getToolbar().getBtnUndo().setEnabled(false);
	}

	public boolean commandsFull() {
		if (currentCommand==commands.size())
			return true;
		else	
			return false;
	}
	public boolean commandsEmpty() {
		if (currentCommand==0)	
			return true;
		else
			return false;
	}
}
