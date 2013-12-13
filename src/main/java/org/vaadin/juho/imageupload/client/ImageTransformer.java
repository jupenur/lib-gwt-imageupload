package org.vaadin.juho.imageupload.client;

import org.vaadin.juho.imageupload.client.resources.ImageUploadResourceInjector;
import org.vectomatic.file.Blob;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * An ImageManipulator for changing the dimensions and orientation of an image.
 * 
 * @author Juho Nurminen
 */
public class ImageTransformer extends ImageManipulator {

    private int width = -1;
    private int height = -1;
    private int maxWidth = -1;
    private int maxHeight = -1;
    private int orientation = 1;

    public ImageTransformer() {
        ImageUploadResourceInjector.ensureInjected();
    }

    /**
     * @see #setMaxWidth(int)
     * @see #setMaxHeigth(int)
     * @see #setOrientation(int)
     */
    public ImageTransformer(int maxWidth, int maxHeight, int orientation) {
        this();
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.orientation = orientation;
    }

    /** Sets the width of the transformed image. */
    public void setWidth(int width) {
        this.width = width;
    }

    /** Sets the height of the transformed image. */
    public void setHeigth(int height) {
        this.height = height;
    }

    /** Sets the maximum width of the transformed image. */
    public void setMaxWidth(int width) {
        maxWidth = width;
    }

    /** Sets the maximum height of the transformed image. */
    public void setMaxHeigth(int height) {
        maxHeight = height;
    }

    /**
     * Sets the orientation of the transformed image.
     * 
     * @param orientation
     *            The orientation of the image as a value corresponding to a
     *            value of an EXIF orientation tag.
     */
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public void onImageLoaded(ImageLoadedEvent event) {
        final RootPanel rootPanel = RootPanel.get();
        final Image image = new Image(event.getImageData().getDataURL());
        Style style = image.getElement().getStyle();
        style.setPosition(Position.ABSOLUTE);
        style.setVisibility(Visibility.HIDDEN);

        image.addLoadHandler(new LoadHandler() {
            public void onLoad(final LoadEvent event) {
                setImageData(new ImageData(image.getUrl()));
                rootPanel.remove(image);
            }
        });

        scaleAndRotateImage(event.getImageData().getBlob(), image.getElement(),
                width, height, maxWidth, maxHeight, orientation);
        rootPanel.add(image);
    }

    private static native void scaleAndRotateImage(Blob blob, Element img,
            int width, int height, int maxWidth, int maxHeight, int orientation)
    /*-{
        var mpImg = new $wnd.MegaPixImage(blob);
        var opts = { orientation: orientation };
        if (width > 0) {
            opts.width = width;
        }
        if (height > 0) {
            opts.height = height;
        }
        if (maxWidth > 0) {
            opts.maxWidth = maxWidth;
        }
        if (maxHeight > 0) {
            opts.maxHeight = maxHeight;
        }
        mpImg.render(img, opts);
    }-*/;

}
