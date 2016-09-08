package ru.kmorozov.librarian.layouts;

import com.vaadin.ui.VerticalLayout;
import ru.kmorozov.librarian.interfaces.Document;
import ru.kmorozov.librarian.layouts.metadata.MetadataViewerFactory;
import ru.kmorozov.librarian.layouts.preview.PreviewFactory;

/**
 * Created by sbt-morozov-kv on 27.06.2016.
 */
public class DocumentViewer extends VerticalLayout {

    private Document documentItem;

    public void setDocument(Document documentItem) {
        this.documentItem = documentItem;

        displayDocument();
    }

    private void displayDocument() {
        removeAllComponents();

        addComponent(MetadataViewerFactory.getFactory().getMetadataLayout(documentItem.getMetadata()));
        addComponent(PreviewFactory.getFactory().getPreview(documentItem));

        markAsDirtyRecursive();
    }
}
