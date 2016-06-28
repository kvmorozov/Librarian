package ru.kmorozov.librarian.layouts;

import com.vaadin.server.StreamResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import ru.kmorozov.librarian.discovery.Item;

/**
 * Created by sbt-morozov-kv on 27.06.2016.
 */
public class DocumentViewer extends VerticalLayout {

    private Item documentItem;

    public void setDocument(Item documentItem) {
        this.documentItem = documentItem;

        displayDocument();
    }

    private void displayDocument() {
        removeAllComponents();

        StreamResource.StreamSource source = (StreamResource.StreamSource) () -> documentItem.getStream();

        Embedded embedded = new Embedded(documentItem.getName(), new StreamResource(source, documentItem.getName()) {
            @Override
            public String getMIMEType() {
                return documentItem.getMIMEType();
            }
        });

        addComponent(embedded);

        markAsDirtyRecursive();
    }
}
