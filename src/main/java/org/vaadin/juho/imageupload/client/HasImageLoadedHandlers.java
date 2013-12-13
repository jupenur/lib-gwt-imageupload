package org.vaadin.juho.imageupload.client;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * Objects implementing this interface have a collection of
 * {@link ImageLoadedHandler}s associated with them.
 * 
 * @author Juho Nurminen
 */
public interface HasImageLoadedHandlers extends HasHandlers {

    /** Adds an {@link ImageLoadedHandler}. */
    HandlerRegistration addImageLoadedHandler(ImageLoadedHandler handler);
}
