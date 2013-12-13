package org.vaadin.juho.imageupload.client.resources;

import com.google.gwt.core.client.GWT;

public class ImageUploadResourceInjector {

    protected static ImageUploadClientBundle bundle;

    public static void ensureInjected() {
        if (bundle == null) {
            bundle = GWT.create(ImageUploadClientBundle.class);
            ImageUploadResourceInjector injector = GWT
                    .create(ImageUploadResourceInjector.class);
            injector.injectResources();
        }
    }

    protected void injectResources() {
        injectScript(bundle.binaryAjax().getText());
        injectScript(bundle.exif().getText());
        injectScript(bundle.megapixImage().getText());
    }

    private static native void injectScript(String script)
    /*-{
        $wnd.eval(script);
    }-*/;

}
