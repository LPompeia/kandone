package br.com.kandone.controller.form;

import br.com.kandone.model.statusCard;

public class CardStatusForm {
	
	private statusCard status;
	
	public CardStatusForm() {
	
	}


	public CardStatusForm(statusCard status) {
		this.status = status;
	}
	
	
	public statusCard getStatus() {
		return status;
	}

	public void setStatus(statusCard status) {
		this.status = status;
	}

}
