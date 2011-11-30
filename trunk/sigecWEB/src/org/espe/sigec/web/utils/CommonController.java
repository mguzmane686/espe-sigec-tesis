package org.espe.sigec.web.utils;

import java.io.Serializable;
import java.util.Collection;

import org.espe.sigec.model.entities.Modulo;
import org.espe.sigec.model.entities.UsuarioPerfil;
import org.richfaces.component.UIPanelMenu;

@SuppressWarnings("serial")
public class CommonController implements Serializable{
	protected boolean allowNavigate;
	protected UIPanelMenu uiPanelMenu;
	protected Collection<Modulo> lstModulos;
	private UsuarioPerfil usuarioPerfil;
	
	public UIPanelMenu getUiPanelMenu() {
		return uiPanelMenu;
	}

	public void setUiPanelMenu(UIPanelMenu uiPanelMenu) {
		this.uiPanelMenu = uiPanelMenu;
	}

	public boolean isAllowNavigate() {
		return allowNavigate;
	}

	public void setAllowNavigate(boolean allowNavigate) {
		this.allowNavigate = allowNavigate;
	}
	
	public Collection<Modulo> getLstModulos() {
		return lstModulos;
	}

	public void setLstModulos(Collection<Modulo> lstModulos) {
		this.lstModulos = lstModulos;
	}

	public UsuarioPerfil getUsuarioPerfil() {
		return usuarioPerfil;
	}

	public void setUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
		this.usuarioPerfil = usuarioPerfil;
	}
	
}
