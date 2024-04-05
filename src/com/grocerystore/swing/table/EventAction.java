package com.grocerystore.swing.table;

import com.grocerystore.model.ModelStudent;

public interface EventAction {

    public void delete(ModelStudent student);

    public void update(ModelStudent student);
}
