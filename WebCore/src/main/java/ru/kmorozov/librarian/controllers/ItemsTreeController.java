package ru.kmorozov.librarian.controllers;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kmorozov.librarian.discovery.ItemsProvider;
import ru.kmorozov.librarian.layouts.ItemsTree;

/**
 * Created by sbt-morozov-kv on 24.06.2016.
 */

@SpringUI(path = "/items")
@Theme("valo")
public class ItemsTreeController extends UI {

    private ItemsProvider itemsProvider;
    private ItemsTree itemsTree;

    @Autowired
    public ItemsTreeController(ItemsProvider itemsProvider) {
        this.itemsProvider = itemsProvider;
    }

    @Override
    protected void init(VaadinRequest request) {
        itemsTree = new ItemsTree();

        setContent(itemsTree);

        itemsTree.populateTree(itemsProvider);
    }
}
