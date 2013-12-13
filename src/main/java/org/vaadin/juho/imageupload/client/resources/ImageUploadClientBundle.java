package org.vaadin.juho.imageupload.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource.DoNotEmbed;
import com.google.gwt.resources.client.TextResource;

public interface ImageUploadClientBundle extends ClientBundle {

    @Source("binaryajax.js")
    @DoNotEmbed
    TextResource binaryAjax();

    @Source("exif.js")
    @DoNotEmbed
    TextResource exif();

    @Source("megapix-image.js")
    @DoNotEmbed
    TextResource megapixImage();

}
