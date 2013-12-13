package org.vaadin.juho.imageupload.client;


/**
 * An {@link ImageTransformer} that reads the EXIF orientation tag from an
 * image, removes it, and performs the associated transformation.
 * 
 * @author Juho Nurminen
 */
public class EXIFOrientationNormalizer extends ImageTransformer {

    @Override
    public void onImageLoaded(ImageLoadedEvent event) {
        ImageData imageData = event.getImageData();
        int orientation = getImageOrientation(imageData.getBinaryString());
        super.setOrientation(orientation);
        super.onImageLoaded(event);
    }

    private static native int getImageOrientation(final String binary)
    /*-{
        var binaryFile = new $wnd.BinaryFile(binary);
        var exif = $wnd.EXIF.readFromBinaryFile(binaryFile);
        var orientation = exif.Orientation;
        if (!orientation){
            orientation = 1;
        }
        return orientation;
    }-*/;

}
