package org.espe.sigec.web.utils;

import java.io.IOException;
import java.io.OutputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="sessionBean")
@SessionScoped
public class SessionBean {
	private byte[] data;
	private boolean renderimage;
	private OutputStream stream;
	private Object object;
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public boolean isRenderimage() {
		return renderimage;
	}

	public void setRenderimage(boolean renderimage) {
		this.renderimage = renderimage;
	}
	
	public void paint(OutputStream stream, Object object) throws IOException {
		setStream(stream);
		setObject(object);
        try {
        	stream.write(getData());
            stream.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public OutputStream getStream() {
		return stream;
	}

	public void setStream(OutputStream stream) {
		this.stream = stream;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
}
