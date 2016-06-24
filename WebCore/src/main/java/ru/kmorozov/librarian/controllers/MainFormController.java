package ru.kmorozov.librarian.controllers;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ru.kmorozov.librarian.entities.Entry;
import ru.kmorozov.librarian.repositories.EntryRepository;

/**
 * Created by sbt-morozov-kv on 23.06.2016.
 */

@SpringUI
@Theme("valo")
public class MainFormController extends UI {

    private final EntryRepository repo;

    private final EntryEditor editor;

    private final Grid grid;

    private final TextField filter;

    private final Button addNewBtn;

    @Autowired
    public MainFormController(EntryRepository repo, EntryEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid();
        this.filter = new TextField();
        this.addNewBtn = new Button("New entry", FontAwesome.PLUS);
    }

    @Override
    protected void init(VaadinRequest request) {
        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);
        setContent(mainLayout);

        // Configure layouts and components
        actions.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        grid.setHeight(300, Unit.PIXELS);
        grid.setColumns("id", "name", "description");

        filter.setInputPrompt("Filter by name");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.addTextChangeListener(e -> listEntries(e.getText()));

        // Connect selected Entry to editor or hide if none is selected
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                editor.setVisible(false);
            } else {
                editor.editEntry((Entry) grid.getSelectedRow());
            }
        });

        // Instantiate and edit new Entry the new button is clicked
        addNewBtn.addClickListener(e -> editor.editEntry(new Entry("", "")));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listEntries(filter.getValue());
        });

        // Initialize listing
        listEntries(null);
    }

    // tag::listEntries[]
    private void listEntries(String text) {
        if (StringUtils.isEmpty(text)) {
            grid.setContainerDataSource(
                    new BeanItemContainer(Entry.class, repo.findAll()));
        } else {
            grid.setContainerDataSource(new BeanItemContainer(Entry.class,
                    repo.findByDescriptionStartsWithIgnoreCase(text)));
        }
    }
    // end::listEntries[]
}