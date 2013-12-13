package org.vaadin.juho.imageupload.client;

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * A convenience class combining an {@link ImageDataSource} and an
 * {@link ImageLoadedHandler}.
 * 
 * @author Juho Nurminen
 */
public abstract class ImageManipulator extends AbstractImageDataSource
        implements ImageLoadedHandler {

    private ImageDataSource source;
    private HandlerRegistration handlerRegistration;

    /** Sets the {@link ImageDataSource} to use. */
    public void setImageDataSource(ImageDataSource source) {
        this.source = source;
        if (handlerRegistration != null) {
            handlerRegistration.removeHandler();
        }
        handlerRegistration = source.addImageLoadedHandler(this);
    }

    public void loadImage() {
        source.loadImage();
    }

    /** Called when an {@link ImageData} object has been loaded from the source. */
    public abstract void onImageLoaded(ImageLoadedEvent event);

}
