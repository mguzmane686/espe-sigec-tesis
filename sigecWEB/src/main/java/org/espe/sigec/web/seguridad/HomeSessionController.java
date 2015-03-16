package org.espe.sigec.web.seguridad;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.espe.sigec.model.entities.UsuarioPerfil;
import org.espe.sigec.web.utils.CommonController;
import org.espe.sigec.web.utils.FacesUtils;
import org.richfaces.component.Mode;
import org.richfaces.component.UIDropDownMenu;
import org.richfaces.component.UIMenuItem;
import org.richfaces.component.UIPanelMenuGroup;
import org.richfaces.component.UIToolbar;
import org.richfaces.event.ItemChangeEvent;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="homeSessionController")
@SessionScoped
public class HomeSessionController extends CommonController{
	
	private String idPerfiles;
	public void setSessionUser(UsuarioPerfil usuarioPerfil){
		setUsuarioPerfil(usuarioPerfil);
	}
	
	public HomeSessionController() {
		if(getLstModulos()!=null){
			FacesUtils.buildUserMenu(getLstModulos());
			setAllowNavigate(Boolean.TRUE);
		}
	}
	
	public void btnLogOut(){
		try {
			FacesUtils.removeManagedBean("homeSessionController");
			FacesContext.getCurrentInstance().getExternalContext().redirect("../index.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void menuAction (String url){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}	  
	}
	
	public void cambioItemMenu(ItemChangeEvent itemChangeEvent){
		System.out.println(itemChangeEvent);
		
	}
	
	public void pmiSeleccionPanel(ActionEvent e){
		UIPanelMenuGroup uiPanelMenuGroup = (UIPanelMenuGroup) e.getSource();
		String itemActivo = String.valueOf(uiPanelMenuGroup.getAttributes().get("idMenuPanelGroup"));
		System.out.println("Se mantendra activo el item ..........." +itemActivo);
		setActiveItem(itemActivo);
	}
	private UIToolbar menuBar;
	  
    public void setMenuBar(UIToolbar menuBar) {
        this.menuBar = menuBar;
    }

    public UIToolbar getMenuBar() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        menuBar = (UIToolbar) ctx.getApplication()
            .createComponent(ctx, UIToolbar.COMPONENT_TYPE,
            "org.richfaces.ToolbarRenderer");
        UIDropDownMenu dropDownMenu = (UIDropDownMenu) ctx.getApplication()
            .createComponent(ctx, UIDropDownMenu.COMPONENT_TYPE,
            "org.richfaces.DropDownMenuRenderer");
        HtmlOutputText label = (HtmlOutputText) ctx.getApplication()
            .createComponent(HtmlOutputText.COMPONENT_TYPE);
        label.setValue("File");
        dropDownMenu.getFacets().put(UIDropDownMenu.Facets.label.name(), label);
        dropDownMenu.setMode(Mode.ajax);
        dropDownMenu.setHideDelay(0);
      
        UIMenuItem menItm = (UIMenuItem) ctx.getApplication().createComponent(ctx,
                UIMenuItem.COMPONENT_TYPE,
                "org.richfaces.MenuItemRenderer");
        menItm.setLabel("New");
        menItm.setIcon("/images/icons/create_doc.gif");
        menItm.setMode(Mode.ajax);
        menItm.setOnclick("alert('hello');");
        dropDownMenu.getChildren().add(menItm);

        UIMenuItem menItm2 = (UIMenuItem) ctx.getApplication().createComponent(ctx,
                UIMenuItem.COMPONENT_TYPE,
                "org.richfaces.MenuItemRenderer");
        menItm2.setLabel("Open");
        menItm2.setIcon("/images/icons/open_doc.gif");
        menItm2.setMode(Mode.ajax);
        menItm2.setOnclick("alert('hello2');");
        dropDownMenu.getChildren().add(menItm2);

        menuBar.getChildren().add(dropDownMenu);
      
        return menuBar;
    }

	public String getIdPerfiles() {
		return idPerfiles;
	}

	public void setIdPerfiles(String idPerfiles) {
		this.idPerfiles = idPerfiles;
	}
    
}
