package org.vaadin.juho.imageupload.client;

import org.vectomatic.file.Blob;
import org.vectomatic.file.FileUtils;

import com.google.gwt.typedarrays.client.Uint8ArrayNative;
import com.google.gwt.typedarrays.shared.ArrayBuffer;
import com.google.gwt.typedarrays.shared.Uint8Array;

/**
 * A class representing image data.
 * 
 * @author Juho Nurminen
 */
public class ImageData {

    final String type; // the mime type of the data
    final String data; // the data as a binary string

    /** Constructs a new {@link ImageData} object from a data URL. */
    public ImageData(String dataURI) {
        DataURI uri = new DataURI(dataURI);
        data = uri.getBinaryData();
        type = uri.getType();
    }

    /** Constructs a new {@link ImageData} object from an array buffer. */
    public ImageData(ArrayBuffer arrayBuffer, String type) {
        data = toBinaryString(arrayBuffer);
        this.type = type;
    }

    /** Constructs a new {@link ImageData} object from a binary string. */
    public ImageData(String data, String type) {
        this.data = data;
        this.type = type;
    }

    /** @return The image as a binary string */
    public String getBinaryString() {
        return data;
    }

    /** @return The image as a data URL */
    public String getDataURL() {
        return FileUtils.createDataUrl(type, getBinaryString());
    }

    /** @return The MIME type of the image */
    public String getType() {
        return type;
    }

    /** @return The image as a Blob */
    public Blob getBlob() {
        return toBlob(toArrayBuffer(data), type);
    }

    private static String toBinaryString(ArrayBuffer arrayBuffer) {
        StringBuilder sb = new StringBuilder();
        Uint8Array array = Uint8ArrayNative.create(arrayBuffer);
        for (int i = 0; i < array.length(); i++) {
            sb.append((char) array.get(i));
        }
        return sb.toString();
    }

    private static ArrayBuffer toArrayBuffer(String binaryString) {
        Uint8Array array = Uint8ArrayNative.create(binaryString.length());
        for (int i = 0; i < binaryString.length(); i++) {
            array.set(i, binaryString.charAt(i));
        }
        return array.buffer();
    }

    private static native Blob toBlob(ArrayBuffer data, String type)
    /*-{
        return new $wnd.Blob([ data ], { type: type });
    }-*/;
}
