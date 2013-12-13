package org.vaadin.juho.imageupload.client;

import org.vectomatic.file.File;
import org.vectomatic.file.FileReader;
import org.vectomatic.file.events.LoadEndEvent;
import org.vectomatic.file.events.LoadEndHandler;

/**
 * A {@link File}-based {@link ImageDataSource}.
 * 
 * @author Juho Nurminen
 */
public class ImageFileDataSource extends AbstractImageDataSource {

    private final File file;

    public ImageFileDataSource(File file) {
        this.file = file;
    }

    public void loadImage() {
        final FileReader fileReader = new FileReader();
        fileReader.readAsBinaryString(file);
        fileReader.addLoadEndHandler(new LoadEndHandler() {
            public void onLoadEnd(LoadEndEvent event) {
                String data = fileReader.getStringResult();
                setImageData(new ImageData(data, file.getType()));
            }
        });
    }
}
