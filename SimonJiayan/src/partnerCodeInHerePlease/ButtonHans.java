package partnerCodeInHerePlease;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import SimonJiayan.ButtonInterfaceJiayan;
import gui.components.Action;
import gui.components.Component;

public class ButtonHans extends Component implements ButtonInterfaceJiayan {
	private Action action;
	private Color c;
	private boolean isOn;
	
	public ButtonHans(int x,int y) {
		super(x,y,60,60);
		isOn = false;
	}

	@Override
	public void act() {
		this.action.act();
	}	

	@Override
	public boolean isHovered(int x, int y) {
		return ((x>this.getX() && x<(this.getX()+this.getWidth())) && (y>this.getY() && y<(this.getY()+this.getHeight())));
	}

	@Override
	public void setColor(Color c) {
		this.c = c;

	}

	@Override
	public void setAction(Action a) {
		this.action = a;

	}

	@Override
	public void highlight() {
		isOn = true;
		update();

	}

	@Override
	public void dim() {
		isOn = false;
		update();

	}

	@Override
	public void update(Graphics2D g) {
		g = clear();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(isOn)
			g.setColor(this.c);
		else
			g.setColor(Color.gray);
		g.fillOval(0,0, 55, 55);
		g.setColor(Color.black);
		g.drawOval(0,0, 55, 55);

	}

}
