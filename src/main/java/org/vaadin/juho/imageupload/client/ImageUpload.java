package org.vaadin.juho.imageupload.client;

import java.util.ArrayList;
import java.util.List;

import org.vectomatic.file.File;
import org.vectomatic.file.FileUploadExt;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * FileUpload with additional features specific to images
 * 
 * @author Juho Nurminen
 */
public class ImageUpload extends FileUploadExt implements ImageDataSource {

    private ImageData imageData = null;
    private final List<ImageManipulator> imageManipulators = new ArrayList<ImageManipulator>();

    public ImageUpload() {
        super(false);
        getElement().removeAttribute("multiple");
        getElement().setAttribute("accept", "image/*");

        addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
                loadImage();
            }
        });
    }

    public ImageData getImageData() {
        return imageData;
    }

    /** Sets the capture attribute. */
    public void setCapture(boolean capture) {
        if (capture) {
            getElement().setAttribute("capture", "camera");
        } else {
            getElement().removeAttribute("capture");
        }
    }

    public HandlerRegistration addImageLoadedHandler(ImageLoadedHandler handler) {
        return addHandler(handler, ImageLoadedEvent.getType());
    }

    public void loadImage() {
        imageData = null;
        if (getFiles().getLength() == 0) {
            fireEvent(new ImageLoadedEvent(null));
        } else {
            loadImageFromFile(getFiles().getItem(0));
        }
    }

    private void loadImageFromFile(File file) {
        ImageDataSource dataSource = new ImageFileDataSource(file);
        for (ImageManipulator manipulator : imageManipulators) {
            manipulator.setImageDataSource(dataSource);
            dataSource = manipulator;
        }
        dataSource.addImageLoadedHandler(new ImageLoadedHandler() {
            public void onImageLoaded(ImageLoadedEvent event) {
                imageData = event.getImageData();
                fireEvent(new ImageLoadedEvent(imageData));
            }
        });
        dataSource.loadImage();
    }

    /**
     * Adds an {@link ImageManipulator} that is applied to all loaded
     * {@link ImageData} objects. The {@link ImageManipulator}s are applied in
     * the order they were added.
     */
    public void addImageManipulator(ImageManipulator manipulator) {
        imageManipulators.add(manipulator);
    }

    /**
     * Adds multiple {@link ImageManipulator}s.
     * 
     * @see #addImageManipulator
     */
    public void addImageManipulators(ImageManipulator... manipulators) {
        for (ImageManipulator manipulator : manipulators) {
            addImageManipulator(manipulator);
        }
    }
}
