package com.grocerystore.swing.table;

import com.grocerystore.model.ModelStudent;
import javax.swing.JTable;

public class ModelAction {

    public ModelStudent getStudent() {
        return student;
    }

    public void setStudent(ModelStudent student) {
        this.student = student;
    }

    public EventAction getEvent() {
        return event;
    }

    public void setEvent(EventAction event) {
        this.event = event;
    }

    public ModelAction(ModelStudent student, EventAction event) {
        this.student = student;
        this.event = event;
    }

    public ModelAction() {
    }

    private ModelStudent student;
    private EventAction event;
    private JTable table;
    private int rowIndex;

    public void setTable(JTable table) {
        this.table = table;
    }

    public JTable getTable() {
        return this.table;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getRowIndex() {
        return this.rowIndex;
    }
}
