package org.espe.sigec.web.utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.faces.FacesException;
import javax.faces.FactoryFinder;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.espe.sigec.model.entities.UsuarioPerfil;
import org.espe.sigec.web.seguridad.HomeSessionController;

@SuppressWarnings("serial")
public class SigecLifeCycle implements PhaseListener{
	/** a name to save messages in the session under */
	private static final String sessionToken = "MULTI_PAGE_MESSAGES_SUPPORT";

	/**
	 * Return the identifier of the request processing phase during which this
	 * listener is interested in processing PhaseEvent events.
	 */
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

	/**
	 * Handle a notification that the processing for a particular phase of the
	 * request processing lifecycle is about to begin.
	 */
	public void beforePhase(PhaseEvent event) {
		event.getFacesContext().getExternalContext().log("BEFORE " + event.getPhaseId());
//		validarLogin();
		
		if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
			FacesContext facesContext = event.getFacesContext();
			restoreMessages(facesContext);
		}
		//Manda al timeout si se necesita


	}
	public void validarLogin(){
		HomeSessionController homeSessionController = null;
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest servletRequest = (HttpServletRequest) ctx.getExternalContext().getRequest();
		String fullURI = servletRequest.getRequestURI();
		System.out.println(fullURI);
		if(fullURI.contains("index")){
			
		}else{
			try {
				homeSessionController = (HomeSessionController) FacesUtils.getManagedBean("homeSessionController");
				if(homeSessionController == null){
					FacesUtils.redirectPage("../index.jsf");
				}else{
					UsuarioPerfil usuarioPerfil = homeSessionController.getUsuarioPerfil();
					if(usuarioPerfil ==null){
						FacesUtils.redirectPage("../index.jsf");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Handle a notification that the processing for a particular phase has just
	 * been completed.
	 */
	public void afterPhase(PhaseEvent event) {
		event.getFacesContext().getExternalContext().log("AFTER " + event.getPhaseId());
//		validarLogin();
		if (event.getPhaseId() == PhaseId.APPLY_REQUEST_VALUES
				|| event.getPhaseId() == PhaseId.PROCESS_VALIDATIONS
				|| event.getPhaseId() == PhaseId.INVOKE_APPLICATION) {
			FacesContext facesContext = event.getFacesContext();
			saveMessages(facesContext);
		}
		
//		FacesContext facesContext = event.getFacesContext();
		HttpSession session = (HttpSession) event.getFacesContext().getExternalContext().getSession(false);

		if (session == null) {
//			NavigationHandler nh = facesContext.getApplication()
//					.getNavigationHandler();
//			nh.handleNavigation(facesContext, null, "loginPage");
			doRedirect(event.getFacesContext(), "/salir.html");
		}else if(session.isNew()){
			System.out.println("Session creada con la peticion");
		}
	}

	private void doRedirect(FacesContext fc, String redirectPage) throws FacesException {
		ExternalContext ec = fc.getExternalContext();
		try {
			if (ec.isResponseCommitted()) {
				// redirect is not possible
				return;
			}

			// fix for renderer kit (Mojarra's and PrimeFaces's ajax redirect)
			if ((fc.getPartialViewContext().isPartialRequest()) && fc.getResponseWriter() == null && fc.getRenderKit() == null) {
				ServletResponse response = (ServletResponse) ec.getResponse();
				ServletRequest request = (ServletRequest) ec.getRequest();
				response.setCharacterEncoding(request.getCharacterEncoding());

				RenderKitFactory factory = (RenderKitFactory) FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);

				RenderKit renderKit = factory.getRenderKit(fc, fc.getApplication().getViewHandler().calculateRenderKitId(fc));

				ResponseWriter responseWriter = renderKit.createResponseWriter(response.getWriter(), null, request.getCharacterEncoding());
				fc.setResponseWriter(responseWriter);
			}

			ec.redirect(ec.getRequestContextPath() + (redirectPage != null ? redirectPage : ""));
		} catch (IOException e) {
			e.printStackTrace();
			throw new FacesException(e);
		}
	}
	/**
	 * Remove the messages that are not associated with any particular component
	 * from the faces context and store them to the user's session.
	 *
	 * @return the number of removed messages.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int saveMessages(FacesContext facesContext) {
		// remove messages from the context
		Set<FacesMessage> messages = new HashSet<FacesMessage>();
		for (Iterator i = facesContext.getMessages(null); i.hasNext();) {
			FacesMessage msg = (FacesMessage) i.next();
			messages.add(msg);
			i.remove();
		}
		// store them in the session
		if (messages.size() == 0){
			return 0;
		}
		Map sessionMap = facesContext.getExternalContext().getSessionMap();
		// if there already are messages
		Set<FacesMessage> existingMessages = (Set<FacesMessage>) sessionMap.get(sessionToken);
		if (existingMessages != null) {
			existingMessages.addAll(messages);
			// if these are the first messages
		} else {
			sessionMap.put(sessionToken, messages);
		}
		return messages.size();
	}

	/**
	 * Remove the messages that are not associated with any particular component
	 * from the user's session and add them to the faces context.
	 *
	 * @return the number of removed messages.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int restoreMessages(FacesContext facesContext) {
		// remove messages from the session

		Map sessionMap = facesContext.getExternalContext().getSessionMap();
		Set<FacesMessage> messages = (Set<FacesMessage>) sessionMap.remove(sessionToken);
		// store them in the context
		if (messages == null){
			return 0;
		}
		int restoredCount = messages.size();

		// set that contains wich messages already exists in the FacesContext
		Set<FacesMessage> facesContextMessages = new HashSet<FacesMessage>();
		for (Iterator i = facesContext.getMessages(null); i.hasNext();) {
			FacesMessage msg = (FacesMessage) i.next();
			facesContextMessages.add(msg);
			i.remove();
		}

		// add the messages that aren't in the FacesContext
		for (FacesMessage facesMessage : messages) {
			if (!facesContextMessages.contains(facesMessage)){
				facesContext.addMessage(null, facesMessage);
			}
		}
		return restoredCount;
	}
}