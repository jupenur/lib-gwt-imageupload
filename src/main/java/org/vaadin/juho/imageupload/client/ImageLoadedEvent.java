package org.vaadin.juho.imageupload.client;

import com.google.gwt.event.shared.GwtEvent;

/**
 * An event triggered when an image file has been fully loaded.
 * 
 * @author Juho Nurminen
 */
public class ImageLoadedEvent extends GwtEvent<ImageLoadedHandler> {

    private static final Type<ImageLoadedHandler> TYPE = new Type<ImageLoadedHandler>();

    private final ImageData image;

    public ImageLoadedEvent(ImageData image) {
        this.image = image;
    }

    @Override
    public Type<ImageLoadedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ImageLoadedHandler handler) {
        handler.onImageLoaded(this);
    }

    /** @return The image that was loaded */
    public ImageData getImageData() {
        return image;
    }

    /** @return The event type associated with {@link ImageLoadedEvent} events */
    public static Type<ImageLoadedHandler> getType() {
        return TYPE;
    }
}