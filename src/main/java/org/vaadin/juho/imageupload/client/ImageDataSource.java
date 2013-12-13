package org.vaadin.juho.imageupload.client;

/**
 * Objects implementing this interface can act as a source for new
 * {@link ImageData} objects.
 * 
 * @author Juho Nurminen
 */
public interface ImageDataSource extends HasImageLoadedHandlers {

    /** Starts loading a new image. */
    void loadImage();

    /**
     * @returns The loaded {@link ImageData} object, or null if no data has been
     *          loaded yet
     */
    ImageData getImageData();
}
