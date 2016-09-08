package ru.kmorozov.librarian.layouts.metadata;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import ru.kmorozov.librarian.interfaces.BookMetadata;
import ru.kmorozov.librarian.interfaces.DocumentMetadata;

/**
 * Created by sbt-morozov-kv on 08.09.2016.
 */
public class MetadataViewerFactory {

    private static final MetadataViewerFactory INSTANCE = new MetadataViewerFactory();

    private MetadataViewerFactory() {

    }

    public static MetadataViewerFactory getFactory() {
        return INSTANCE;
    }

    public Component getMetadataLayout(DocumentMetadata metadata) {
        if (metadata == null)
            return new VerticalLayout();
        else if (metadata instanceof BookMetadata)
            return getBookMetadata(metadata);
        else
            return getGenericDocumentMetadata(metadata);
    }

    private Component getGenericDocumentMetadata(DocumentMetadata metadata) {
        HorizontalLayout nameLayout = new HorizontalLayout(new Label("Имя файла"), new Label(metadata.getName()));
        HorizontalLayout keyLayout = new HorizontalLayout(new Label("Ключ файла"), new Label(metadata.getKey()));

        return new VerticalLayout(nameLayout, keyLayout);
    }

    private Component getBookMetadata(DocumentMetadata metadata) {
        return getGenericDocumentMetadata(metadata);
    }
}
