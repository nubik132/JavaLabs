package Lab17;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DBDisplayer extends JDialog {
    private JPanel contentPane;
    private JTextField requestField;
    private JButton requestButton;
    private JList selectList;
    private JButton selectButton;
    private JPanel lowerPanel;
    private JComboBox requestComboBox;
    private JPanel requestPanel;
    private JButton histogramButton;
    private JScrollPane scrollPane;
    private JList idList;
    private JTextArea rowTextArea;
    private JButton buttonOK;
    private JButton buttonCancel;

    private GridBagConstraints gbc;
    private JTextField[] requestJTextArea;

    private Connection conn;
    private Statement statement;
    private ResultSet resultSet;

    final static String[] tables = {"bank", "charterer", "members", "race", "ships"};
    final String[] requests = {"INSERT", "UPDATE", "DELETE", "SELECT"};

    public DBDisplayer() {
        setContentPane(contentPane);
        setModal(true);



        prepareDB();

        for (var request : requests){
            requestComboBox.addItem(request);
        }
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets.right = 5;
        gbc.insets.top = 10;
        gbc.insets.bottom = 10;
        gbc.insets.left = 5;



        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        DefaultListModel<String> model = (DefaultListModel<String>) selectList.getModel();
        for (var table:
                tables) {
            model.addElement(table);
        }

        selectList.repaint();
        selectList.setLayoutOrientation(JList.VERTICAL);


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        requestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String request;
                    int commandIndex = requestComboBox.getSelectedIndex();
                    if (commandIndex == 0){
                        request = generateInsert((String) selectList.getSelectedValue());
                    }
                    else if (commandIndex == 1) {
                        request = generateUpdate((String) selectList.getSelectedValue());
                    }
                    else if (commandIndex == 2){
                        request = generateDelete((String) selectList.getSelectedValue());
                    } else {
                        request = generateSelect((String) selectList.getSelectedValue());
                        select((String) selectList.getSelectedValue(), request);
                        return;
                    }
                    execute(request);
                    select((String) selectList.getSelectedValue(), null);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        selectList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                showRequestTextFields();
            }
        });
        requestComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRequestTextFields();
            }
        });
        histogramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DrawHistogram();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        idList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String selectRowRequest = getSelectRowString((String)selectList.getSelectedValue(), idList.getSelectedIndex() + 1);
                String rowString = getResultFromRowSelection(selectRowRequest);
                rowTextArea.setText(rowString);
            }
        });
    }

    private String getSelectRowString(String table, int row){
        String id = "";
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + table);
            id = resultSet.getMetaData().getColumnName(1);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return "SELECT * FROM " + table + " WHERE " + id + " = " + row;
    }

    private String getResultFromRowSelection(String request){
        try {
           resultSet = statement.executeQuery(request);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        String result = "";
        int columnCount = getColumnCount();
        try {
            var metaData = resultSet.getMetaData();
            resultSet.next();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                result += columnName + ": " + resultSet.getObject(i).toString() + "\n";
            }
        }
        catch (SQLException ex){
            System.out.println("getResultFromRowSelection " + ex.getMessage());
        }

        return result;
    }

    private int getColumnCount(){
        int count = 0;
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            count = metaData.getColumnCount();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return count;
    }


    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        DBDisplayer dialog = new DBDisplayer();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void prepareDB(){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shippingcompany", "vald", "DBpass");
            statement = conn.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            dispose();
        }
    }

    void showRequestTextFields(){
        select((String) selectList.getSelectedValue(), null);
        deleteTextFields(requestJTextArea);
        try {
            addTextFields((String) selectList.getSelectedValue(), requestComboBox.getSelectedIndex());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void addTextFields(String table, int index) throws SQLException {
        int fieldsCount;
        if (index == 0) {
            fieldsCount = getRequestColumnCount(table);
        } else if (index == 1){
            fieldsCount = getRequestColumnCount(table) + 1;
        } else if (index == 2){
            fieldsCount = 1;
        } else {
            fieldsCount = 2;
        }
        CreateTextFields(fieldsCount);
    }

    void CreateTextFields(int count){
        requestJTextArea = new JTextField[count];
        for (int i = 0; i < count; i++)
        {
            requestJTextArea[i] = new JTextField("", 10);
            gbc.gridx = i; // столбец (0, 1 или 2)
            gbc.gridy = 0; // строка (0, 1 или 2)
            gbc.gridwidth = 1;
            requestPanel.add(requestJTextArea[i], gbc);
        }
    }

    void deleteTextFields(JTextField[] fields){
        if (fields != null) {
            for (var field : fields) {
                requestPanel.remove(field);
            }
            requestPanel.revalidate();
            requestPanel.repaint();
        }
    }

    void select(String table, String request){
        try {
            resultSet = statement.executeQuery(request == null ? "SELECT * FROM " + table + ";" : request);
            ResultSetMetaData metaData = resultSet.getMetaData();
            DefaultListModel<String> model = new DefaultListModel<>();
            while (resultSet.next()) {
                model.addElement(resultSet.getObject(metaData.getColumnName(1)).toString());
            }
            idList.setModel(model);
        }
        catch (SQLException ex){
            System.out.println("Ошибка в select\n" + ex.getMessage());
        }
    }

    String generateInsert(String table) throws SQLException {
        String request = "INSERT INTO " + table + " (";
        resultSet = statement.executeQuery("SELECT * FROM " + table + ";");
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 0; i < columnCount; i++){
            request += requestJTextArea[i].getText() != "" ? metaData.getColumnName(i + 1) + ", " : "";
        }
        request = request.substring(0, request.length() - 2) + ") VALUES (";
        for (int i = 0; i < columnCount; i++){
            request += requestJTextArea[i].getText() + ", ";
        }
        return request.substring(0, request.length() - 2) + ");";
    }

    String generateUpdate(String table) throws SQLException {
        String request = "UPDATE " + table + " SET ";
        resultSet = statement.executeQuery("SELECT * FROM " + table + ";");
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        System.out.println(requestJTextArea[0].getText());
        for (int i = 0; i < columnCount; i++){
            request += !requestJTextArea[i + 1].getText().isEmpty()
                    ? metaData.getColumnName(i + 1) + " = "
                    +  requestJTextArea[i + 1].getText() + ", "
                    : "";
        }
        return request.substring(0, request.length() - 2) + " WHERE " + metaData.getColumnName(1) + " = " + requestJTextArea[0].getText() + ";";
    }

    String generateDelete(String table) throws SQLException{
        resultSet = statement.executeQuery("SELECT * FROM " + table + ";");
        ResultSetMetaData metaData = resultSet.getMetaData();
        return "DELETE FROM " + table +  " WHERE " + metaData.getColumnName(1) + " = " + requestJTextArea[0].getText() + ";";
    }

    String generateSelect(String table) throws SQLException{
        String request = "SELECT " + requestJTextArea[0].getText() + " FROM " + table;
        request += requestJTextArea[1].getText().isEmpty() ? "" : " WHERE " + requestJTextArea[1].getText();
        return request + ";";
    }

    void execute(String request){
        try {
            statement.executeUpdate(request);

        } catch (SQLException ex){
            System.out.println("Ошибка в execute: " + ex.getMessage());

        }
    }

    int getRequestColumnCount(String table) throws SQLException {
        return statement.executeQuery("SELECT * FROM " + table + ";").getMetaData().getColumnCount();
    }

    void DrawHistogram() throws SQLException {
        int[] recordCounts = new int[5];

        // Запросы для получения количества записей в каждой таблице
        for (int i = 0; i < tables.length; i++) {
            String query = "SELECT COUNT(*) FROM " + tables[i];
            resultSet = statement.executeQuery(query);
            resultSet.next();
            recordCounts[i] = resultSet.getInt(1);
        }
        Histogram a = new Histogram(recordCounts);
        //a.setBackground(Color.BLACK);
    }
}
