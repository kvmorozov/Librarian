package ru.kmorozov.librarian.controllers;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kmorozov.librarian.entities.Entry;
import ru.kmorozov.librarian.repositories.EntryRepository;

/**
 * Created by sbt-morozov-kv on 23.06.2016.
 */

@SpringComponent
@UIScope
public class EntryEditor extends VerticalLayout {

    private final EntryRepository repository;

    /**
     * The currently edited customer
     */
    private Entry entry;

    /* Fields to edit properties in Customer entity */
    TextField name = new TextField("Name");
    TextField description = new TextField("Description");

    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    @Autowired
    public EntryEditor(EntryRepository repository) {
        this.repository = repository;

        addComponents(name, description, actions);

        // Configure and style components
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> repository.save(entry));
        delete.addClickListener(e -> repository.delete(entry));
        cancel.addClickListener(e -> editEntry(entry));
        setVisible(false);
    }

    public interface ChangeHandler {

        void onChange();
    }

    public final void editEntry(Entry _entry) {
        final boolean persisted = _entry.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            entry = repository.findOne(_entry.getId());
        } else {
            entry = _entry;
        }
        cancel.setVisible(persisted);

        // Bind entry properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        BeanFieldGroup.bindFieldsUnbuffered(entry, this);

        setVisible(true);

        // A hack to ensure the whole form is visible
        save.focus();
        // Select all text in name field automatically
        name.selectAll();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }

}
