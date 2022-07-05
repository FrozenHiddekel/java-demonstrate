package Geon4.company.util;


import lombok.AllArgsConstructor;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.List;

@AllArgsConstructor
public class CustomTable<T> extends AbstractTableModel {

    private String[] columnNames;
    private Class<T> cls;
    private List<T> rows;

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public List getRows() {
        return rows;
    }
    //тут вообще ничего нет и всё норм

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return cls.getDeclaredFields().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            T t = rows.get(rowIndex);
            Field field = cls.getDeclaredFields()[columnIndex];
            field.setAccessible(true);
            return field.get(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return "Каво что";
        }
    }
}
