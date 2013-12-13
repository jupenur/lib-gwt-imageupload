package org.vaadin.juho.imageupload.client;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler for {@link ImageLoadedEvent} events.
 * 
 * @author Juho Nurminen
 */
public interface ImageLoadedHandler extends EventHandler {

    /** Called when an image has been fully loaded. */
    void onImageLoaded(ImageLoadedEvent event);
}