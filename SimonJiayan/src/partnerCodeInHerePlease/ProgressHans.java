package partnerCodeInHerePlease;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import SimonJiayan.ButtonInterfaceJiayan;
import SimonJiayan.MoveInterfaceJiayan;
import SimonJiayan.ProgressInterfaceJiayan;
import gui.components.Component;

public class ProgressHans extends Component implements ProgressInterfaceJiayan {
	private int round;
	private int length;
	private boolean gameOver;
	public ProgressHans() {
		super(400,40,200,100);
		gameOver = false;
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
		if(gameOver){
			g.setColor(Color.red);
			g.fillRoundRect(0, 0, 198, 98, 20, 20);
			g.setColor(Color.black);
			g.drawRoundRect(0,0,198,98,20,20);	
			g.drawString("Game Over!",5,35);
			
		}
		else{
			g.setColor(Color.gray);
			g.fillRoundRect(0, 0, 198, 98, 20, 20);
			g.setColor(Color.black);
			g.drawRoundRect(0,0,198,98,20,20);	
		}
		g.drawString("Round: "+round,5,55);
		g.drawString("Sequence Length: "+length,5,75);

	}

	@Override
	public void gameOver() {
		gameOver = true;
		update();
		
	}

	@Override
	public void setSequenceLength(int size) {
		this.length = size;
		update();
		
	}

	@Override
	public void setRound(int roundNum) {
		this.round = roundNum;
		update();
	}

}
