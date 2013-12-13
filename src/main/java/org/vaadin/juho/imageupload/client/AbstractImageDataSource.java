package org.vaadin.juho.imageupload.client;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * An abstract {@link ImageDataSource}.
 * 
 * @author Juho Nurminen
 */
public abstract class AbstractImageDataSource implements ImageDataSource {

    private final HandlerManager handlerManager = new HandlerManager(this);
    private ImageData imageData = null;

    public ImageData getImageData() {
        return imageData;
    }

    /**
     * Sets the current {@link ImageData} object associated with this
     * {@link ImageDataSource} and notifies the attached
     * {@link ImageLoadedHandler}s.
     */
    protected void setImageData(ImageData imageData) {
        setImageData(imageData, true);
    }

    /**
     * Sets the current {@link ImageData} object associated with this
     * {@link ImageDataSource}.
     * 
     * @param fireEvents
     *            Specifies, whether the {@link ImageLoadedHandler}s should be
     *            notified about this change
     */
    protected void setImageData(ImageData imageData, boolean fireEvents) {
        this.imageData = imageData;
        if (fireEvents) {
            fireEvent(new ImageLoadedEvent(imageData));
        }
    }

    public HandlerRegistration addImageLoadedHandler(ImageLoadedHandler handler) {
        return handlerManager.addHandler(ImageLoadedEvent.getType(), handler);
    }

    public void fireEvent(GwtEvent<?> event) {
        handlerManager.fireEvent(event);
    }

}
