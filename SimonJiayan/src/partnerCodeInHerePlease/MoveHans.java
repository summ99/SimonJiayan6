package partnerCodeInHerePlease;

import SimonJiayan.ButtonInterfaceJiayan;
import SimonJiayan.MoveInterfaceJiayan;

public class MoveHans implements MoveInterfaceJiayan {
	private ButtonInterfaceJiayan b;
	
	public MoveHans(ButtonInterfaceJiayan b) {
		this.b = b;
	}

	@Override
	public ButtonInterfaceJiayan getButton() {
		return this.b;
	}

}
