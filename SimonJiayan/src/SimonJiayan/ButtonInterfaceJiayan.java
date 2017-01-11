package SimonJiayan;

import java.awt.Color;

import gui.components.Action;
import gui.components.Clickable;

public interface ButtonInterfaceJiayan extends Clickable {

	void setColor(Color colors);

	public void setAction(Action a);

	void highlight();

	void dim();
}
