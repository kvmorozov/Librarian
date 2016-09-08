package ru.kmorozov.librarian.layouts.preview;

import com.vaadin.server.StreamResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.VerticalLayout;
import ru.kmorozov.librarian.interfaces.Book;
import ru.kmorozov.librarian.interfaces.Document;

/**
 * Created by sbt-morozov-kv on 08.09.2016.
 */
public class PreviewFactory {

    private static final PreviewFactory INSTANCE = new PreviewFactory();

    private PreviewFactory() {

    }

    public static PreviewFactory getFactory() {
        return INSTANCE;
    }

    public Component getPreview(Document document) {
        if (document == null)
            return new VerticalLayout();
        else if (document instanceof Book)
            return getBookPreview(document);
        else
            return getGenericDocumentPreview(document);
    }

    private Component getGenericDocumentPreview(Document document) {
        StreamResource.StreamSource source = (StreamResource.StreamSource) () -> document.getStream();

        Embedded embedded = new Embedded(document.getName(), new StreamResource(source, document.getName()) {
            @Override
            public String getMIMEType() {
                return document.getMIMEType();
            }
        });

        return embedded;
    }

    private Component getBookPreview(Document document) {
        return getGenericDocumentPreview(document);
    }
}
