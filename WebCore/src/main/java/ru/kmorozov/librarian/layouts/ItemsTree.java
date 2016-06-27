package ru.kmorozov.librarian.layouts;

import com.vaadin.server.StreamResource;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kmorozov.librarian.discovery.Item;
import ru.kmorozov.librarian.discovery.ItemsProvider;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sbt-morozov-kv on 24.06.2016.
 */
public class ItemsTree extends VerticalLayout {

    private Tree itemsTree;

    public ItemsTree() {
        setMargin(true);
        setSpacing(true);

        addComponent(itemsTree = new Tree());
    }

    private class IconSource implements StreamResource.StreamSource {

        ImageIcon icon;

        IconSource(ImageIcon icon) {
            this.icon = icon;
        }

        @Override
        public InputStream getStream() {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                ImageIO.write((RenderedImage) icon.getImage(), "png", os);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ByteArrayInputStream(os.toByteArray());
        }
    }

    private void addChildren(final Item root) {
        for (Item child : root.getChildren()) {
            itemsTree.addItem(child);
            itemsTree.setParent(child, root);
            itemsTree.setItemIcon(child, new StreamResource(new IconSource((ImageIcon) child.getIcon()), "tmp"));
            itemsTree.setChildrenAllowed(child, child.hasChildren());
        }

        root.setLoaded(true);

        itemsTree.containerItemSetChange(null);
    }

    @Autowired
    public void populateTree(ItemsProvider itemsProvider) {
        Item root = itemsProvider.getRoot();

        itemsTree.addItem(root);
        addChildren(root);

        itemsTree.addExpandListener(event -> {
            Item item = (Item) event.getItemId();
            if (item.isLoaded())
                return;
            else
                addChildren(item);
        });
    }
}
