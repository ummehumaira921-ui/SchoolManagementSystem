package SchoolManagementSystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.util.ArrayList;



public class SchoolDashboard extends JFrame {

    // ==============================
    // Managers
    // ==============================

    private StudentManager manager;
    private TableRowSorter<DefaultTableModel> sorter;

    // ==============================
    // Components
    // ==============================

    private JTable table;
    private DefaultTableModel model;

    private JTextField txtId;
    private JTextField txtFirstName;
    private JTextField txtSurname;
    private JTextField txtAddress;
    private JTextField txtMobile;
    private JTextField txtSearch;

    private JComboBox<String> cmbGender;

    private JComboBox<String> cmbMaths;
    private JComboBox<String> cmbScience;
    private JComboBox<String> cmbPhysics;
    private JComboBox<String> cmbChemistry;
    private JComboBox<String> cmbBiology;
    private JComboBox<String> cmbBangla;
    private JComboBox<String> cmbBusiness;
    private JComboBox<String> cmbEnglish;

    private RoundedButton btnAdd;
    private RoundedButton btnPrint;
    private RoundedButton btnUpdate;
    private RoundedButton btnReset;
    private RoundedButton btnDelete;
    private RoundedButton btnExit;
    private RoundedButton btnAttendance;

    private JLabel lblStatus;
    private JLabel lblTotal;

    private static final String SEARCH_PLACEHOLDER = "Search student by ID, name, address...";

    private static final String[] COLUMNS = {
            "ID", "Student ID", "Firstname", "Surname", "Address", "Gender", "Mobile",
            "Maths", "Science", "Physics", "Chemistry", "Biology", "Bangla", "Business", "English"
    };

    private int selectedRow = -1;

    public SchoolDashboard() {

        manager = new StudentManager();

        setTitle("Student Management Systems Using Java");
        setSize(1500, 860);
        setMinimumSize(new Dimension(1250, 820));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(UITheme.PINK_BG);

        createMenuBar();

        //---------------- HEADER ----------------

        GradientPanel header = new GradientPanel(new BorderLayout(), UITheme.PINK_PRIMARY, UITheme.PINK_PRIMARY_DARK);
        header.setBorder(new EmptyBorder(10, 22, 10, 22));

        JLabel title = new JLabel("Student Management Systems Using Java", SwingConstants.LEFT);
        title.setIcon(IconFactory.book(26, Color.WHITE));
        title.setIconTextGap(10);
        title.setFont(UITheme.FONT_APP_TITLE);
        title.setForeground(Color.WHITE);

        header.add(title, BorderLayout.WEST);

        add(header, BorderLayout.NORTH);

        //---------------- MAIN PANEL ----------------

        JPanel mainPanel = new JPanel(new BorderLayout(12, 8));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(new EmptyBorder(10, 12, 10, 12));

        //---------------- LEFT PANEL (form) ----------------

        JPanel leftContent = new JPanel();
        leftContent.setOpaque(false);
        leftContent.setLayout(new BoxLayout(leftContent, BoxLayout.Y_AXIS));

        // -- Student Information card --
        RoundedPanel basicInfo = new RoundedPanel(new GridBagLayout(), 16, UITheme.CARD_BG);
        basicInfo.setRoundedBorder(UITheme.CARD_BORDER, 1);
        basicInfo.setBorder(new EmptyBorder(8, 12, 8, 12));

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 5, 0);
        basicInfo.add(sectionHeader("Student Information", IconFactory.person(16, UITheme.PINK_PRIMARY_DARK)), gc);

        txtId = new JTextField();
        txtFirstName = new JTextField();
        txtSurname = new JTextField();
        txtAddress = new JTextField();
        txtMobile = new JTextField();
        cmbGender = new JComboBox<>(new String[]{"Male", "Female", "Other"});

        styleField(txtId);
        styleField(txtFirstName);
        styleField(txtSurname);
        styleField(txtAddress);
        styleField(txtMobile);
        styleCombo(cmbGender);

        int row = 1;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1;
        gc.insets = new Insets(0, 0, 5, 0);

        addStackedField(basicInfo, gc, row++, "Student ID", txtId);
        addStackedField(basicInfo, gc, row++, "Firstname", txtFirstName);
        addStackedField(basicInfo, gc, row++, "Surname", txtSurname);
        addStackedField(basicInfo, gc, row++, "Address", txtAddress);
        addStackedField(basicInfo, gc, row++, "Gender", cmbGender);
        addStackedField(basicInfo, gc, row++, "Mobile", txtMobile);

        // -- Subjects card --
        RoundedPanel subjectsCard = new RoundedPanel(new BorderLayout(), 16, UITheme.CARD_BG);
        subjectsCard.setRoundedBorder(UITheme.CARD_BORDER, 1);
        subjectsCard.setBorder(new EmptyBorder(8, 12, 8, 12));

        JLabel subjectsHeader = sectionHeader("Subjects", IconFactory.book(16, UITheme.PINK_PRIMARY_DARK));
        subjectsHeader.setBorder(new EmptyBorder(0, 0, 5, 0));

        JPanel subjectsGrid = new JPanel(new GridLayout(4, 4, 6, 5));
        subjectsGrid.setOpaque(false);

        String[] yesNo = {"No", "Yes"};

        cmbMaths = new JComboBox<>(yesNo);
        cmbScience = new JComboBox<>(yesNo);
        cmbPhysics = new JComboBox<>(yesNo);
        cmbChemistry = new JComboBox<>(yesNo);
        cmbBiology = new JComboBox<>(yesNo);
        cmbBangla = new JComboBox<>(yesNo);
        cmbBusiness = new JComboBox<>(yesNo);
        cmbEnglish = new JComboBox<>(yesNo);

        styleCombo(cmbMaths);
        styleCombo(cmbScience);
        styleCombo(cmbPhysics);
        styleCombo(cmbChemistry);
        styleCombo(cmbBiology);
        styleCombo(cmbBangla);
        styleCombo(cmbBusiness);
        styleCombo(cmbEnglish);

        subjectsGrid.add(subjectLabel("Maths"));
        subjectsGrid.add(cmbMaths);
        subjectsGrid.add(subjectLabel("Science"));
        subjectsGrid.add(cmbScience);

        subjectsGrid.add(subjectLabel("Physics"));
        subjectsGrid.add(cmbPhysics);
        subjectsGrid.add(subjectLabel("Chemistry"));
        subjectsGrid.add(cmbChemistry);

        subjectsGrid.add(subjectLabel("Biology"));
        subjectsGrid.add(cmbBiology);
        subjectsGrid.add(subjectLabel("Bangla"));
        subjectsGrid.add(cmbBangla);

        subjectsGrid.add(subjectLabel("Business"));
        subjectsGrid.add(cmbBusiness);
        subjectsGrid.add(subjectLabel("English"));
        subjectsGrid.add(cmbEnglish);

        subjectsCard.add(subjectsHeader, BorderLayout.NORTH);
        subjectsCard.add(subjectsGrid, BorderLayout.CENTER);

        // -- Buttons --
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 6, 6));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(3, 0, 0, 0));

        btnAdd = new RoundedButton("Add New", UITheme.GREEN);
        btnPrint = new RoundedButton("Print", UITheme.LAVENDER);
        btnUpdate = new RoundedButton("Update", UITheme.ROSE);
        btnReset = new RoundedButton("Reset", UITheme.AMBER);
        btnDelete = new RoundedButton("Delete", UITheme.RED);
        btnExit = new RoundedButton("Exit", UITheme.PURPLE);

        btnAdd.setIcon(IconFactory.plus(14, UITheme.contrastColor(UITheme.GREEN)));
        btnPrint.setIcon(IconFactory.printer(14, UITheme.contrastColor(UITheme.LAVENDER)));
        btnUpdate.setIcon(IconFactory.pencil(14, UITheme.contrastColor(UITheme.ROSE)));
        btnReset.setIcon(IconFactory.refresh(14, UITheme.contrastColor(UITheme.AMBER)));
        btnDelete.setIcon(IconFactory.trash(14, UITheme.contrastColor(UITheme.RED)));
        btnExit.setIcon(IconFactory.power(14, UITheme.contrastColor(UITheme.PURPLE)));

        btnAdd.setIconTextGap(5);
        btnPrint.setIconTextGap(5);
        btnUpdate.setIconTextGap(5);
        btnReset.setIconTextGap(5);
        btnDelete.setIconTextGap(5);
        btnExit.setIconTextGap(5);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnPrint);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnReset);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnExit);

        btnAttendance = new RoundedButton("Attendance", UITheme.PINK_PRIMARY);
        btnAttendance.setIcon(IconFactory.calendar(18, UITheme.contrastColor(UITheme.PINK_PRIMARY)));
        btnAttendance.setIconTextGap(8);
        btnAttendance.setArc(22);
        btnAttendance.setPreferredSize(new Dimension(100, 38));
        btnAttendance.setFont(UITheme.FONT_TITLE.deriveFont(Font.BOLD, 14f));

        JPanel southButtons = new JPanel(new BorderLayout(6, 6));
        southButtons.setOpaque(false);
        southButtons.setBorder(new EmptyBorder(6, 0, 0, 0));
        southButtons.add(buttonPanel, BorderLayout.CENTER);
        southButtons.add(btnAttendance, BorderLayout.SOUTH);

        leftContent.add(basicInfo);
        leftContent.add(Box.createVerticalStrut(8));
        leftContent.add(subjectsCard);
        leftContent.add(Box.createVerticalStrut(3));
        leftContent.add(southButtons);

        // Plain (non-scrolling) wrapper: content sits at its natural
        // preferred size at the top of the column, so every field and
        // button is visible at once without needing to drag/scroll.
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(380, 10));
        leftPanel.add(leftContent, BorderLayout.NORTH);

        //---------------- SEARCH ----------------

        RoundedPanel searchCard = new RoundedPanel(new BorderLayout(8, 0), 14, UITheme.CARD_BG);
        searchCard.setRoundedBorder(UITheme.CARD_BORDER, 1);
        searchCard.setBorder(new EmptyBorder(5, 12, 5, 6));

        JLabel lblSearchIcon = new JLabel(IconFactory.search(16, UITheme.PINK_PRIMARY));

        txtSearch = new JTextField();
        txtSearch.setFont(UITheme.FONT_FIELD);
        txtSearch.setBorder(BorderFactory.createEmptyBorder(5, 6, 5, 6));
        txtSearch.setText(SEARCH_PLACEHOLDER);
        txtSearch.setForeground(UITheme.TEXT_MUTED);

        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSearch.getText().equals(SEARCH_PLACEHOLDER)) {
                    txtSearch.setText("");
                    txtSearch.setForeground(UITheme.TEXT_DARK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().trim().isEmpty()) {
                    txtSearch.setForeground(UITheme.TEXT_MUTED);
                    txtSearch.setText(SEARCH_PLACEHOLDER);
                }
            }
        });

        RoundedButton btnSearch = new RoundedButton("Search", UITheme.PINK_PRIMARY);
        btnSearch.setIcon(IconFactory.search(13, UITheme.contrastColor(UITheme.PINK_PRIMARY)));
        btnSearch.setIconTextGap(5);
        btnSearch.setPreferredSize(new Dimension(100, 30));

        searchCard.add(lblSearchIcon, BorderLayout.WEST);
        searchCard.add(txtSearch, BorderLayout.CENTER);
        searchCard.add(btnSearch, BorderLayout.EAST);

        mainPanel.add(searchCard, BorderLayout.NORTH);

        //---------------- TABLE ----------------

        model = new DefaultTableModel(COLUMNS, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        table = new JTable(model);
        table.setRowHeight(26);
        table.setFont(UITheme.FONT_FIELD);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);
        table.setShowGrid(true);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                                                             boolean isSelected, boolean hasFocus,
                                                             int r, int col) {
                Component comp = super.getTableCellRendererComponent(t, value, isSelected, hasFocus, r, col);

                if (isSelected) {
                    comp.setBackground(UITheme.PINK_PRIMARY);
                    comp.setForeground(Color.WHITE);
                } else {
                    comp.setBackground(r % 2 == 0 ? Color.WHITE : UITheme.PINK_BG);
                    comp.setForeground(UITheme.TEXT_DARK);
                }

                return comp;
            }
        });

        JTableHeader headerTable = table.getTableHeader();
        headerTable.setFont(UITheme.FONT_LABEL);
        headerTable.setBackground(UITheme.PINK_BG_2);
        headerTable.setForeground(UITheme.PINK_PRIMARY_DARK);
        headerTable.setReorderingAllowed(false);

        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);

        RoundedPanel tableCard = new RoundedPanel(new BorderLayout(), 16, UITheme.CARD_BG);
        tableCard.setRoundedBorder(UITheme.CARD_BORDER, 1);
        tableCard.setBorder(new EmptyBorder(8, 8, 8, 8));
        tableCard.add(scrollPane, BorderLayout.CENTER);

        JPanel centerRow = new JPanel(new BorderLayout(12, 0));
        centerRow.setOpaque(false);
        centerRow.add(leftPanel, BorderLayout.WEST);
        centerRow.add(tableCard, BorderLayout.CENTER);

        mainPanel.add(centerRow, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        //---------------- STATUS BAR ----------------

        RoundedPanel statusPanel = new RoundedPanel(new BorderLayout(), 0, UITheme.CARD_BG);
        statusPanel.setBorder(new EmptyBorder(6, 16, 6, 16));

        lblStatus = new JLabel("Ready");
        lblStatus.setIcon(IconFactory.heart(13, UITheme.PINK_PRIMARY));
        lblStatus.setIconTextGap(6);

        lblTotal = new JLabel("Total Students : 0");
        lblTotal.setIcon(IconFactory.people(14, UITheme.PINK_PRIMARY_DARK));
        lblTotal.setIconTextGap(6);

        lblStatus.setFont(UITheme.FONT_LABEL);
        lblTotal.setFont(UITheme.FONT_LABEL);

        statusPanel.add(lblTotal, BorderLayout.WEST);
        statusPanel.add(lblStatus, BorderLayout.EAST);

        add(statusPanel, BorderLayout.SOUTH);

        // ==============================
        // Load Saved Students
        // ==============================

        loadData();

        // ==============================
        // Table Row Selection
        // ==============================

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                selectedRow = table.getSelectedRow();

                if (selectedRow != -1) {

                    int r = table.convertRowIndexToModel(selectedRow);

                    txtId.setText(String.valueOf(model.getValueAt(r, 1)));
                    txtFirstName.setText(String.valueOf(model.getValueAt(r, 2)));
                    txtSurname.setText(String.valueOf(model.getValueAt(r, 3)));
                    txtAddress.setText(String.valueOf(model.getValueAt(r, 4)));
                    cmbGender.setSelectedItem(String.valueOf(model.getValueAt(r, 5)));
                    txtMobile.setText(String.valueOf(model.getValueAt(r, 6)));

                    cmbMaths.setSelectedItem(String.valueOf(model.getValueAt(r, 7)));
                    cmbScience.setSelectedItem(String.valueOf(model.getValueAt(r, 8)));
                    cmbPhysics.setSelectedItem(String.valueOf(model.getValueAt(r, 9)));
                    cmbChemistry.setSelectedItem(String.valueOf(model.getValueAt(r, 10)));
                    cmbBiology.setSelectedItem(String.valueOf(model.getValueAt(r, 11)));
                    cmbBangla.setSelectedItem(String.valueOf(model.getValueAt(r, 12)));
                    cmbBusiness.setSelectedItem(String.valueOf(model.getValueAt(r, 13)));
                    cmbEnglish.setSelectedItem(String.valueOf(model.getValueAt(r, 14)));
                }

            }

        });

        // ==============================
        // Live Search
        // ==============================

        txtSearch.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) { search(); }
            public void removeUpdate(DocumentEvent e) { search(); }
            public void changedUpdate(DocumentEvent e) { search(); }

        });

        // ==============================
        // Button Actions
        // ==============================

        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnReset.addActionListener(e -> clearFields());
        btnPrint.addActionListener(e -> printTable());
        btnExit.addActionListener(e -> exitApplication());
        btnSearch.addActionListener(e -> search());

        btnAttendance.addActionListener(e -> {

            AttendancePanel attendance = new AttendancePanel();
            attendance.setVisible(true);

        });

        // ==============================
        // Auto Save On Exit
        // ==============================

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                FileManager.saveStudents(manager.getStudents());
            }

        });

    }

    // ============================================
    // Styling helpers
    // ============================================

    private JLabel sectionHeader(String text, Icon icon) {
        JLabel lbl = new JLabel(text);
        lbl.setIcon(icon);
        lbl.setIconTextGap(7);
        lbl.setFont(UITheme.FONT_SUBTITLE);
        lbl.setForeground(UITheme.PINK_PRIMARY_DARK);
        return lbl;
    }

    private JLabel subjectLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(UITheme.FONT_LABEL);
        return lbl;
    }

    private void styleField(JTextField field) {
        field.setFont(UITheme.FONT_FIELD);
        field.setBackground(UITheme.FIELD_BG);
        field.setForeground(UITheme.TEXT_DARK);
        field.setPreferredSize(new Dimension(100, 25));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.CARD_BORDER, 1, true),
                BorderFactory.createEmptyBorder(3, 7, 3, 7)));
    }

    private void styleCombo(JComboBox<String> combo) {
        combo.setFont(UITheme.FONT_FIELD);
        combo.setBackground(UITheme.FIELD_BG);
        combo.setForeground(UITheme.TEXT_DARK);
        combo.setPreferredSize(new Dimension(100, 25));
    }

    // ============================================
    // Helper to lay out a label above a field
    // ============================================
    private void addStackedField(JPanel panel, GridBagConstraints gc, int gridRow, String label, JComponent field) {

        JLabel lbl = new JLabel(label);
        lbl.setFont(UITheme.FONT_LABEL);

        JPanel block = new JPanel();
        block.setOpaque(false);
        block.setLayout(new BorderLayout(0, 2));
        block.add(lbl, BorderLayout.NORTH);
        block.add(field, BorderLayout.CENTER);

        gc.gridy = gridRow;
        panel.add(block, gc);

    }

    // ============================================
    // Create Menu Bar
    // ============================================

    private void createMenuBar() {

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu studentMenu = new JMenu("Student");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        JMenuItem addItem = new JMenuItem("Add Student");
        JMenuItem updateItem = new JMenuItem("Update Student");
        JMenuItem deleteItem = new JMenuItem("Delete Student");

        JMenuItem aboutItem = new JMenuItem("About");

        saveItem.addActionListener(e -> saveData());

        exitItem.addActionListener(e -> exitApplication());

        addItem.addActionListener(e -> addStudent());
        updateItem.addActionListener(e -> updateStudent());
        deleteItem.addActionListener(e -> deleteStudent());

        aboutItem.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "School Management System\nVersion 2.0",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE
                )
        );

        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        studentMenu.add(addItem);
        studentMenu.add(updateItem);
        studentMenu.add(deleteItem);

        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(studentMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

    }

    // ============================================
    // Add Student
    // ============================================

    private void addStudent() {

        if (!validateInput()) {
            return;
        }

        Student student = buildStudentFromForm();

        if (!manager.addStudent(student)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student ID already exists."
            );

            return;
        }

        refreshTable();

        lblStatus.setText("Student Added Successfully");

        FileManager.saveStudents(manager.getStudents());

        clearFields();

    }

    // ============================================
    // Update Student
    // ============================================

    private void updateStudent() {

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a student first."
            );

            return;
        }

        if (!validateInput()) {
            return;
        }

        int row = table.convertRowIndexToModel(selectedRow);

        Student student = buildStudentFromForm();

        manager.updateStudent(row, student);

        refreshTable();

        lblStatus.setText("Student Updated Successfully");

        FileManager.saveStudents(manager.getStudents());

        clearFields();

    }

    // ============================================
    // Delete Student
    // ============================================

    private void deleteStudent() {

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student."
            );

            return;
        }

        int option = JOptionPane.showConfirmDialog(

                this,
                "Delete selected student?",
                "Confirm",
                JOptionPane.YES_NO_OPTION

        );

        if (option != JOptionPane.YES_OPTION)
            return;

        int row = table.convertRowIndexToModel(selectedRow);

        manager.deleteStudent(row);

        refreshTable();

        lblStatus.setText("Student Deleted Successfully");

        FileManager.saveStudents(manager.getStudents());

        clearFields();

    }

    // ============================================
    // Print Table
    // ============================================

    private void printTable() {

        try {

            boolean printed = table.print();

            lblStatus.setText(printed ? "Print job sent" : "Print cancelled");

        } catch (PrinterException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to print: " + ex.getMessage(),
                    "Print Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }

    // ============================================
    // Exit Application
    // ============================================

    private void exitApplication() {

        FileManager.saveStudents(manager.getStudents());
        System.exit(0);

    }

    // ============================================
    // Search
    // ============================================

    private void search() {

        String text = txtSearch.getText();

        if (text.trim().isEmpty() || text.equals(SEARCH_PLACEHOLDER)) {

            sorter.setRowFilter(null);

        } else {

            sorter.setRowFilter(
                    RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(text))
            );

        }

    }

    // ============================================
    // Clear Form
    // ============================================

    private void clearFields() {

        txtId.setText("");
        txtFirstName.setText("");
        txtSurname.setText("");
        txtAddress.setText("");
        txtMobile.setText("");

        cmbGender.setSelectedIndex(0);

        cmbMaths.setSelectedIndex(0);
        cmbScience.setSelectedIndex(0);
        cmbPhysics.setSelectedIndex(0);
        cmbChemistry.setSelectedIndex(0);
        cmbBiology.setSelectedIndex(0);
        cmbBangla.setSelectedIndex(0);
        cmbBusiness.setSelectedIndex(0);
        cmbEnglish.setSelectedIndex(0);

        table.clearSelection();

        selectedRow = -1;

        lblStatus.setText("Ready");

    }

    // ============================================
    // Update Student Count
    // ============================================

    private void updateStudentCount() {
        lblTotal.setText("Total Students : " + manager.getTotalStudents());
    }

    // ============================================
    // Refresh Table
    // ============================================

    private void refreshTable() {

        model.setRowCount(0);

        int no = 1;

        for (Student s : manager.getStudents()) {

            model.addRow(new Object[]{
                    no++,
                    s.getId(),
                    s.getFirstName(),
                    s.getSurname(),
                    s.getAddress(),
                    s.getGender(),
                    s.getMobile(),
                    s.getMaths(),
                    s.getScience(),
                    s.getPhysics(),
                    s.getChemistry(),
                    s.getBiology(),
                    s.getBangla(),
                    s.getBusiness(),
                    s.getEnglish()
            });

        }

        updateStudentCount();

    }

    // ============================================
    // Build a Student object from the form fields
    // ============================================

    private Student buildStudentFromForm() {

        return new Student(
                txtId.getText().trim(),
                txtFirstName.getText().trim(),
                txtSurname.getText().trim(),
                txtAddress.getText().trim(),
                String.valueOf(cmbGender.getSelectedItem()),
                txtMobile.getText().trim(),
                String.valueOf(cmbMaths.getSelectedItem()),
                String.valueOf(cmbScience.getSelectedItem()),
                String.valueOf(cmbPhysics.getSelectedItem()),
                String.valueOf(cmbChemistry.getSelectedItem()),
                String.valueOf(cmbBiology.getSelectedItem()),
                String.valueOf(cmbBangla.getSelectedItem()),
                String.valueOf(cmbBusiness.getSelectedItem()),
                String.valueOf(cmbEnglish.getSelectedItem())
        );

    }

    // ============================================
    // Validate Input
    // ============================================

    private boolean validateInput() {

        if (txtId.getText().trim().isEmpty()
                || txtFirstName.getText().trim().isEmpty()
                || txtSurname.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in Student ID, Firstname and Surname."
            );

            return false;

        }

        String mobile = txtMobile.getText().trim();

        if (!mobile.isEmpty() && !mobile.matches("[0-9+\\-() ]{6,}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid mobile number."
            );

            return false;

        }

        return true;

    }

    // ============================================
    // Save Data
    // ============================================

    private void saveData() {

        FileManager.saveStudents(manager.getStudents());

        lblStatus.setText("Data Saved Successfully");

    }

    // ============================================
    // Load Data
    // ============================================

    private void loadData() {

        ArrayList<Student> students = FileManager.loadStudents();

        manager.getStudents().clear();

        manager.getStudents().addAll(students);

        refreshTable();

    }

}



class StudentManager {

    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    // Add Student
    public boolean addStudent(Student student) {

        if (isDuplicateId(student.getId())) {
            return false;
        }

        students.add(student);
        return true;
    }

    // Delete Student
    public void deleteStudent(int index) {

        if (index >= 0 && index < students.size()) {
            students.remove(index);
        }
    }

    // Update Student
    public void updateStudent(int index, Student student) {

        if (index >= 0 && index < students.size()) {
            students.set(index, student);
        }
    }

    // Get Student List
    public ArrayList<Student> getStudents() {
        return students;
    }

    // Total Students
    public int getTotalStudents() {
        return students.size();
    }

    // Duplicate ID Check
    public boolean isDuplicateId(String id) {

        for (Student s : students) {

            if (s.getId().equalsIgnoreCase(id)) {
                return true;
            }

        }

        return false;
    }

    // Search by keyword across the main identifying fields
    public ArrayList<Student> searchStudent(String keyword) {

        ArrayList<Student> result = new ArrayList<>();

        keyword = keyword.toLowerCase();

        for (Student s : students) {

            if (s.getId().toLowerCase().contains(keyword)
                    || s.getFirstName().toLowerCase().contains(keyword)
                    || s.getSurname().toLowerCase().contains(keyword)
                    || s.getAddress().toLowerCase().contains(keyword)
                    || s.getMobile().toLowerCase().contains(keyword)) {

                result.add(s);

            }

        }

        return result;
    }

}



class FileManager {

    private static final String FILE_NAME = "students.dat";

    // Save Student List
    public static void saveStudents(ArrayList<Student> students) {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(FILE_NAME));

            out.writeObject(students);

            out.close();

        } catch (Exception e) {

            System.out.println("Error Saving Data!");

        }

    }

    // Load Student List
    @SuppressWarnings("unchecked")
    public static ArrayList<Student> loadStudents() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {

            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(FILE_NAME));

            ArrayList<Student> students =
                    (ArrayList<Student>) in.readObject();

            in.close();

            return students;

        } catch (Exception e) {

            System.out.println("Error Loading Data!");

            return new ArrayList<>();

        }

    }

}



class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    private String studentId;
    private String studentName;
    private String status;

    public Attendance(String studentId, String studentName, String status) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return studentId + " - " + studentName + " - " + status;
    }
}



class AttendancePanel extends JFrame {

    private JTextField txtId;
    private JTextField txtName;

    private JComboBox<String> cmbStatus;

    private RoundedButton btnMark;
    private RoundedButton btnClear;

    private JTable table;
    private DefaultTableModel model;

    public AttendancePanel() {

        setTitle("Attendance Management");
        setSize(760, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(UITheme.PINK_BG);

        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setOpaque(false);
        root.setBorder(new EmptyBorder(16, 16, 16, 16));

        RoundedPanel formPanel = new RoundedPanel(new GridBagLayout(), 18, UITheme.CARD_BG);
        formPanel.setRoundedBorder(UITheme.CARD_BORDER, 1);
        formPanel.setBorder(new EmptyBorder(14, 18, 14, 18));

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6, 6, 6, 6);
        gc.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("Mark Attendance");
        header.setIcon(IconFactory.calendar(18, UITheme.PINK_PRIMARY_DARK));
        header.setIconTextGap(8);
        header.setFont(UITheme.FONT_SUBTITLE);
        header.setForeground(UITheme.PINK_PRIMARY_DARK);

        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.WEST;
        formPanel.add(header, gc);
        gc.gridwidth = 1;

        txtId = new JTextField();
        txtName = new JTextField();

        cmbStatus = new JComboBox<>();
        cmbStatus.addItem("Present");
        cmbStatus.addItem("Absent");
        cmbStatus.addItem("Leave");

        styleField(txtId);
        styleField(txtName);
        styleCombo(cmbStatus);

        addRow(formPanel, gc, 1, "Student ID", txtId);
        addRow(formPanel, gc, 2, "Student Name", txtName);
        addRow(formPanel, gc, 3, "Status", cmbStatus);

        btnMark = new RoundedButton("Mark Attendance", UITheme.GREEN);
        btnMark.setIcon(IconFactory.plus(16, UITheme.contrastColor(UITheme.GREEN)));
        btnMark.setIconTextGap(6);

        btnClear = new RoundedButton("Clear", UITheme.AMBER);
        btnClear.setIcon(IconFactory.close(14, UITheme.contrastColor(UITheme.AMBER)));
        btnClear.setIconTextGap(6);

        JPanel buttonRow = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonRow.setOpaque(false);
        buttonRow.add(btnMark);
        buttonRow.add(btnClear);

        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 2;
        gc.insets = new Insets(14, 6, 6, 6);
        formPanel.add(buttonRow, gc);

        root.add(formPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new Object[]{
                        "Student ID",
                        "Student Name",
                        "Status"
                }, 0);

        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(UITheme.FONT_FIELD);
        table.getTableHeader().setFont(UITheme.FONT_LABEL);
        table.getTableHeader().setBackground(UITheme.PINK_BG_2);
        table.getTableHeader().setForeground(UITheme.PINK_PRIMARY_DARK);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);

        RoundedPanel tableCard = new RoundedPanel(new BorderLayout(), 18, UITheme.CARD_BG);
        tableCard.setRoundedBorder(UITheme.CARD_BORDER, 1);
        tableCard.setBorder(new EmptyBorder(10, 10, 10, 10));
        tableCard.add(scrollPane, BorderLayout.CENTER);

        root.add(tableCard, BorderLayout.CENTER);

        add(root, BorderLayout.CENTER);

        btnMark.addActionListener(e -> {

            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            String status = cmbStatus.getSelectedItem().toString();

            if (id.isEmpty() || name.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all fields."
                );

                return;

            }

            model.addRow(new Object[]{
                    id,
                    name,
                    status
            });

            clearFields();

        });

        btnClear.addActionListener(e -> clearFields());

    }

    private void styleField(JTextField field) {
        field.setFont(UITheme.FONT_FIELD);
        field.setPreferredSize(new Dimension(100, 32));
        field.setBackground(UITheme.FIELD_BG);
        field.setForeground(UITheme.TEXT_DARK);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.CARD_BORDER, 1, true),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)));
    }

    private void styleCombo(JComboBox<String> combo) {
        combo.setFont(UITheme.FONT_FIELD);
        combo.setPreferredSize(new Dimension(100, 32));
        combo.setBackground(UITheme.FIELD_BG);
        combo.setForeground(UITheme.TEXT_DARK);
    }

    private void addRow(JPanel panel, GridBagConstraints gc, int row, String label, JComponent field) {

        JLabel lbl = new JLabel(label);
        lbl.setFont(UITheme.FONT_LABEL);

        gc.gridx = 0;
        gc.gridy = row;
        gc.weightx = 0;
        panel.add(lbl, gc);

        gc.gridx = 1;
        gc.weightx = 1;
        panel.add(field, gc);

    }

    private void clearFields() {

        txtId.setText("");
        txtName.setText("");
        cmbStatus.setSelectedIndex(0);

    }

}