package SimonJiayan;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gui.components.Action;
import gui.components.TextLabel;
import gui.components.Visible;
import gui.screens.ClickableScreen;
import partnerCodeInHerePlease.ButtonHans;
import partnerCodeInHerePlease.MoveHans;
import partnerCodeInHerePlease.ProgressHans;

public class SimonScreenJiayan extends ClickableScreen implements Runnable{
	private ProgressInterfaceJiayan progress;
	private ButtonInterfaceJiayan[] buttons;
	private ArrayList<MoveInterfaceJiayan> sequence;
	private int roundNum;
	private boolean acceptingInput;
	private int lastSelected;
	private int sequenceIndex;
	private TextLabel label;
	public SimonScreenJiayan(int width, int height) {
		super(width, height);
		Thread play = new Thread(this);
		play.start();
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		// TODO Auto-generated method stub
		roundNum = 0;
		addButtons();
		for(ButtonInterfaceJiayan b:buttons){
			viewObjects.add(b);
		}
		progress = getProgress();
		label = new TextLabel(350,220,200,40, "Let's Play Simon!");
		sequence = new ArrayList<MoveInterfaceJiayan>();
		lastSelected = -1;
		sequence.add(randomMove());
		sequence.add(randomMove());
		viewObjects.add(label);
		viewObjects.add(progress);
	}

	private MoveInterfaceJiayan randomMove() {
		int random = (int) (Math.random()*buttons.length);
		while(random==lastSelected)
		{
			random = (int) (Math.random()*buttons.length);
		}
		return getMove(buttons[random]);
	}

	private MoveInterfaceJiayan getMove(ButtonInterfaceJiayan b) {
		return new MoveHans(b);
	}

	private ProgressInterfaceJiayan getProgress() {
		return new ProgressHans();
	}

	private void addButtons() {
		int numButtons = 6;
		buttons = new ButtonInterfaceJiayan[numButtons];
		Color[] colors = {Color.blue, Color.pink, Color.yellow, Color.green, Color.black, Color.red};
		for(int i=0;i<buttons.length;i++){
			buttons[i] = getAButton(10, 50+(60*i));
			final ButtonInterfaceJiayan b = buttons[i];
			b.setColor(colors[i]);
			b.setAction(new Action(){
				@Override
				public void act() {
					if(acceptingInput){
						Thread buttonPress = new Thread(new Runnable(){
							@Override
							public void run() {
								b.highlight();
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								b.dim();
								
							}
							
						});
					
						buttonPress.start();
						
						if(acceptingInput && sequence.get(sequenceIndex).getButton() == b){
							sequenceIndex++;
						}else if(acceptingInput){
							progress.gameOver();
							acceptingInput=false;
							return;
						}
						if(sequenceIndex == sequence.size() && acceptingInput){
							Thread nextRound = new Thread(SimonScreenJiayan.this);
							nextRound.start();
						}
					}
				}
			});
		}
		   
		    		
	}


	private ButtonInterfaceJiayan getAButton(int i, int j) {
		return new ButtonHans(i,j);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		  label.setText("");
		    nextRound();
		    roundNum++;
	}
	private void nextRound() {
		// TODO Auto-generated method stub
		acceptingInput = false;                
		roundNum ++;
		progress.setRound(roundNum);
		sequence.add(randomMove());
		progress.setSequenceLength(sequence.size());
		changeText("Simon's Turn");
		label.setText(" ");
		playSequence();
		changeText("Your Turn");
		acceptingInput = true;
		label.setText(" ");
		sequenceIndex = 0;

	}

	private void playSequence() {
		// TODO Auto-generated method stub
		ButtonInterfaceJiayan b = null;
        for(MoveInterfaceJiayan i: sequence){
            if(b!=null)
            {
            	b.dim();
			}
			b = i.getButton();
			b.highlight();
			try {                        
			Thread.sleep((int)(1000*(1.0/roundNum)));
			}        
			catch (InterruptedException e) {
			e.printStackTrace();
			}
			b.dim();
		}
			
	}
	
	private void changeText(String text) {
		label.setText(text);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
