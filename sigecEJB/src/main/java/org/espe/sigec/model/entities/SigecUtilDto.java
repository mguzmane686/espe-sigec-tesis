package org.espe.sigec.model.entities;

import java.io.Serializable;

import javax.persistence.Transient;

@SuppressWarnings("serial")
public class SigecUtilDto implements Serializable{
	@Transient
	protected boolean selected;
	@Transient
	protected boolean editMode;
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public boolean isEditMode() {
		return editMode;
	}
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	
}
