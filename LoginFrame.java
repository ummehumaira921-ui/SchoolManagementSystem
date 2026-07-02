package SchoolManagementSystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.*;



public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbUserType;
    private RoundedButton btnSubmit;
    private RoundedButton btnClose;

    // Default Login
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";

    public LoginFrame() {

        setTitle("School Management System - Login");
        setSize(1133, 760);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setJMenuBar(createMenuBar());

        GradientPanel background = new GradientPanel(new GridBagLayout(), UITheme.PINK_BG, UITheme.PINK_SOFT);
        background.add(buildCard());

        setContentPane(background);

        // Enter key triggers login from either field
        txtUsername.addActionListener(e -> login());
        txtPassword.addActionListener(e -> login());
    }

    // ============================================
    // Menu Bar - "About Project"
    // ============================================
    private JMenuBar createMenuBar() {

        JMenuBar menuBar = new JMenuBar();
        JMenu aboutMenu = new JMenu("About Project");

        JMenuItem infoItem = new JMenuItem("Project Info");
        infoItem.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "School Management System\n" +
                        "A simple Java Swing application to manage students,\n" +
                        "attendance and academic records.\n\n" +
                        "Developed by Naveenkumar J",
                "About Project",
                JOptionPane.INFORMATION_MESSAGE
        ));

        aboutMenu.add(infoItem);
        menuBar.add(aboutMenu);

        return menuBar;
    }

    // ============================================
    // Centered white login card
    // ============================================
    private JPanel buildCard() {

        RoundedPanel card = new RoundedPanel(new GridBagLayout(), 28, Color.WHITE);
        card.setRoundedBorder(UITheme.CARD_BORDER, 1);
        card.setBorder(new EmptyBorder(34, 48, 34, 48));

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;

        // Vector book icon (in place of the emoji that would not render
        // on machines without an emoji font installed) - centered above
        // the title so the whole card reads symmetrically.
        JLabel icon = new JLabel(IconFactory.book(46, UITheme.PINK_PRIMARY_DARK));
        icon.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel sysTitle = new JLabel("School Management System", SwingConstants.CENTER);
        sysTitle.setFont(UITheme.FONT_APP_TITLE);
        sysTitle.setForeground(UITheme.PINK_PRIMARY);

        JLabel pageTitle = new JLabel("Login Page", SwingConstants.CENTER);
        pageTitle.setFont(UITheme.FONT_TITLE);
        pageTitle.setForeground(UITheme.TEXT_DARK);

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        cmbUserType = new JComboBox<>(new String[]{"Admin", "Teacher", "Staff"});

        styleField(txtUsername);
        styleField(txtPassword);
        styleCombo(cmbUserType);

        btnSubmit = new RoundedButton("SUBMIT", UITheme.PINK_PRIMARY);
        btnSubmit.setIcon(IconFactory.arrowRight(16, Color.WHITE));
        btnSubmit.setIconTextGap(8);
        btnSubmit.setPreferredSize(new Dimension(150, 42));
        btnSubmit.addActionListener(e -> login());

        btnClose = new RoundedButton("CLOSE", Color.WHITE);
        btnClose.setIcon(IconFactory.close(14, UITheme.PINK_PRIMARY_DARK));
        btnClose.setIconTextGap(8);
        btnClose.setPreferredSize(new Dimension(150, 42));
        btnClose.setForeground(UITheme.PINK_PRIMARY_DARK);
        btnClose.addActionListener(e -> System.exit(0));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttons.setOpaque(false);
        buttons.add(btnSubmit);
        buttons.add(btnClose);

        int y = 0;

        gc.gridy = y++;
        gc.insets = new Insets(0, 0, 8, 0);
        card.add(icon, gc);

        gc.gridy = y++;
        gc.insets = new Insets(0, 0, 4, 0);
        card.add(sysTitle, gc);

        gc.gridy = y++;
        gc.insets = new Insets(0, 0, 24, 0);
        card.add(pageTitle, gc);

        gc.gridy = y++;
        gc.insets = new Insets(0, 0, 4, 0);
        card.add(fieldLabel("User name"), gc);

        gc.gridy = y++;
        gc.insets = new Insets(0, 0, 16, 0);
        card.add(txtUsername, gc);

        gc.gridy = y++;
        gc.insets = new Insets(0, 0, 4, 0);
        card.add(fieldLabel("Password"), gc);

        gc.gridy = y++;
        gc.insets = new Insets(0, 0, 16, 0);
        card.add(txtPassword, gc);

        gc.gridy = y++;
        gc.insets = new Insets(0, 0, 4, 0);
        card.add(fieldLabel("User Type"), gc);

        gc.gridy = y++;
        gc.insets = new Insets(0, 0, 28, 0);
        card.add(cmbUserType, gc);

        gc.gridy = y++;
        gc.insets = new Insets(0, 0, 0, 0);
        card.add(buttons, gc);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(false);
        wrapper.add(card);

        return wrapper;
    }

    private JLabel fieldLabel(String text) {
        JLabel lbl = new JLabel(text, SwingConstants.LEFT);
        lbl.setFont(UITheme.FONT_LABEL);
        lbl.setForeground(UITheme.TEXT_MUTED);
        return lbl;
    }

    private void styleField(JTextField field) {
        field.setFont(UITheme.FONT_FIELD);
        field.setPreferredSize(new Dimension(320, 38));
        field.setBackground(Color.WHITE);
        field.setForeground(UITheme.TEXT_DARK);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.CARD_BORDER, 1, true),
                BorderFactory.createEmptyBorder(6, 12, 6, 12)));
    }

    private void styleCombo(JComboBox<String> combo) {
        combo.setFont(UITheme.FONT_FIELD);
        combo.setPreferredSize(new Dimension(320, 38));
        combo.setBackground(Color.WHITE);
        combo.setForeground(UITheme.TEXT_DARK);
    }

    private void login() {

        String username = txtUsername.getText().trim();
        String password = String.valueOf(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Please enter both username and password.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (username.equals(USERNAME) && password.equals(PASSWORD)) {

            JOptionPane.showMessageDialog(this, "Login Successful!");

            dispose();

            SwingUtilities.invokeLater(() -> new SchoolDashboard().setVisible(true));

        } else {

            JOptionPane.showMessageDialog(this,
                    "Invalid Username or Password!",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);

            txtPassword.setText("");
            txtPassword.requestFocus();

        }
    }
}



/**
 * A pill-shaped JButton with its own paint routine. It always keeps its
 * brand color (green / pink / red / etc.) and an automatically computed
 * contrasting text/icon color, so it stays legible on any background.
 */
class RoundedButton extends JButton {

    private Color baseColor;
    private Color hoverColor;
    private int arc = 22;

    public RoundedButton(String text, Color baseColor) {
        super(text);
        this.baseColor = baseColor;
        this.hoverColor = baseColor.darker();
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setForeground(UITheme.contrastColor(baseColor));
        setFont(UITheme.FONT_BUTTON);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void setArc(int arc) {
        this.arc = arc;
    }

    public void setBaseColor(Color c) {
        this.baseColor = c;
        this.hoverColor = c.darker();
        setForeground(UITheme.contrastColor(c));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color c = baseColor;
        if (getModel().isPressed()) {
            c = hoverColor.darker();
        } else if (getModel().isRollover()) {
            c = hoverColor;
        }

        g2.setColor(isEnabled() ? c : new Color(190, 190, 190));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        return new Dimension(Math.max(d.width + 10, 82), Math.max(d.height, 30));
    }
}



/**
 * A JPanel with rounded corners and an optional rounded border.
 */
class RoundedPanel extends JPanel {

    private final int arc;
    private Color bgColor;
    private Color borderColor;
    private int borderThickness;

    public RoundedPanel(LayoutManager layout, int arc, Color bgColor) {
        super(layout);
        this.arc = arc;
        this.bgColor = bgColor;
        setOpaque(false);
    }

    public void setPanelBackground(Color c) {
        this.bgColor = c;
        repaint();
    }

    /** Adds a thin rounded border matching the card's corner radius. */
    public void setRoundedBorder(Color color, int thickness) {
        this.borderColor = color;
        this.borderThickness = thickness;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(bgColor);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
        if (borderColor != null && borderThickness > 0) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderThickness));
            g2.drawRoundRect(
                    borderThickness / 2,
                    borderThickness / 2,
                    getWidth() - borderThickness - 1,
                    getHeight() - borderThickness - 1,
                    arc, arc);
        }
        g2.dispose();
        super.paintComponent(g);
    }
}



/** Simple top-to-bottom gradient panel, used for the header bar and login backdrop. */
class GradientPanel extends JPanel {

    private Color top;
    private Color bottom;

    public GradientPanel(LayoutManager layout, Color top, Color bottom) {
        super(layout);
        this.top = top;
        this.bottom = bottom;
        setOpaque(false);
    }

    public void setColors(Color top, Color bottom) {
        this.top = top;
        this.bottom = bottom;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(new GradientPaint(0, 0, top, 0, getHeight(), bottom));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
        super.paintComponent(g);
    }
}



/**
 * Central place for every color and font used across the app.
 * Keeping this in one file means the palette stays consistent
 * no matter which screen is being drawn.
 */
class UITheme {

    // ---------- Pink palette ----------
    public static final Color PINK_BG        = new Color(255, 240, 246);
    public static final Color PINK_BG_2       = new Color(255, 225, 236);
    public static final Color PINK_PRIMARY    = new Color(233, 30, 99);
    public static final Color PINK_PRIMARY_DARK = new Color(194, 24, 91);
    public static final Color PINK_SOFT       = new Color(255, 214, 229);
    public static final Color CARD_BG         = Color.WHITE;
    public static final Color CARD_BORDER     = new Color(255, 200, 219);
    public static final Color FIELD_BG        = Color.WHITE;
    public static final Color TEXT_DARK       = new Color(55, 40, 48);
    public static final Color TEXT_MUTED      = new Color(150, 110, 130);

    // ---------- Brand-colored buttons ----------
    public static final Color GREEN   = new Color(102, 187, 106);
    public static final Color LAVENDER = new Color(154, 133, 216);
    public static final Color ROSE    = new Color(240, 98, 146);
    public static final Color AMBER   = new Color(255, 183, 51);
    public static final Color RED     = new Color(239, 83, 80);
    public static final Color PURPLE  = new Color(142, 108, 199);
    public static final Color BLUE    = new Color(66, 165, 245);

    // ---------- Fonts ----------
    public static final Font FONT_APP_TITLE = new Font("Segoe UI", Font.BOLD, 21);
    public static final Font FONT_TITLE     = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font FONT_SUBTITLE  = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_LABEL     = new Font("Segoe UI", Font.BOLD, 11);
    public static final Font FONT_FIELD     = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_BUTTON    = new Font("Segoe UI", Font.BOLD, 12);

    private UITheme() {
    }

    public static Color contrastColor(Color c) {
        double lum = (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue());
        return lum > 150 ? Color.BLACK : Color.WHITE;
    }
}



/**
 * Generates small, crisp vector icons drawn directly with Java2D.
 *
 * The previous UI used emoji characters (e.g. "\uD83D\uDC64") for icons.
 * Emoji glyphs depend on fonts that are not guaranteed to be installed on
 * every Windows machine, which is why they were showing up as empty
 * "tofu" boxes. Hand-drawn vector icons render identically everywhere.
 */
final class IconFactory {

    private IconFactory() {
    }

    private interface Drawer {
        void draw(Graphics2D g2, int size);
    }

    private static Icon build(int size, Drawer drawer) {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.translate(x, y);
                drawer.draw(g2, size);
                g2.dispose();
            }

            @Override
            public int getIconWidth() {
                return size;
            }

            @Override
            public int getIconHeight() {
                return size;
            }
        };
    }

    // ---------- Book (app title, login page, Subjects) ----------
    public static Icon book(int size, Color color) {
        return build(size, (g2, s) -> {
            float w = s;
            float h = s;
            float midX = w / 2f;
            float top = h * 0.16f;
            float bottom = h * 0.86f;

            g2.setColor(color);

            GeneralPath left = new GeneralPath();
            left.moveTo(midX, top + h * 0.06f);
            left.curveTo(midX * 0.55f, top, w * 0.08f, top + h * 0.02f, w * 0.06f, top + h * 0.08f);
            left.lineTo(w * 0.06f, bottom - h * 0.06f);
            left.curveTo(w * 0.08f, bottom - h * 0.12f, midX * 0.55f, bottom - h * 0.10f, midX, bottom);
            left.closePath();
            g2.fill(left);

            GeneralPath right = new GeneralPath();
            right.moveTo(midX, top + h * 0.06f);
            right.curveTo(midX * 1.45f, top, w * 0.92f, top + h * 0.02f, w * 0.94f, top + h * 0.08f);
            right.lineTo(w * 0.94f, bottom - h * 0.06f);
            right.curveTo(w * 0.92f, bottom - h * 0.12f, midX * 1.45f, bottom - h * 0.10f, midX, bottom);
            right.closePath();
            g2.fill(right);

            g2.setColor(color.darker());
            g2.setStroke(new BasicStroke(Math.max(1f, s * 0.045f)));
            g2.draw(new Line2D.Float(midX, top + h * 0.10f, midX, bottom - h * 0.02f));
        });
    }

    // ---------- Person (Student Information) ----------
    public static Icon person(int size, Color color) {
        return build(size, (g2, s) -> {
            g2.setColor(color);
            float headD = s * 0.36f;
            float headX = (s - headD) / 2f;
            float headY = s * 0.10f;
            g2.fill(new Ellipse2D.Float(headX, headY, headD, headD));

            float bodyTop = headY + headD - s * 0.02f;
            GeneralPath body = new GeneralPath();
            body.moveTo(s * 0.18f, s * 0.92f);
            body.curveTo(s * 0.18f, s * 0.62f, s * 0.30f, bodyTop, s * 0.5f, bodyTop);
            body.curveTo(s * 0.70f, bodyTop, s * 0.82f, s * 0.62f, s * 0.82f, s * 0.92f);
            body.closePath();
            g2.fill(body);
        });
    }

    // ---------- Group of people (Total Students) ----------
    public static Icon people(int size, Color color) {
        return build(size, (g2, s) -> {
            g2.setColor(color);
            float d1 = s * 0.30f;
            g2.fill(new Ellipse2D.Float(s * 0.08f, s * 0.14f, d1, d1));
            GeneralPath b1 = new GeneralPath();
            b1.moveTo(s * 0.02f, s * 0.86f);
            b1.curveTo(s * 0.02f, s * 0.62f, s * 0.10f, s * 0.48f, s * 0.23f, s * 0.48f);
            b1.curveTo(s * 0.36f, s * 0.48f, s * 0.44f, s * 0.62f, s * 0.44f, s * 0.86f);
            b1.closePath();
            g2.fill(b1);

            float d2 = s * 0.34f;
            g2.fill(new Ellipse2D.Float(s * 0.50f, s * 0.08f, d2, d2));
            GeneralPath b2 = new GeneralPath();
            b2.moveTo(s * 0.40f, s * 0.92f);
            b2.curveTo(s * 0.40f, s * 0.64f, s * 0.50f, s * 0.48f, s * 0.67f, s * 0.48f);
            b2.curveTo(s * 0.84f, s * 0.48f, s * 0.96f, s * 0.64f, s * 0.96f, s * 0.92f);
            b2.closePath();
            g2.fill(b2);
        });
    }

    // ---------- Search magnifying glass ----------
    public static Icon search(int size, Color color) {
        return build(size, (g2, s) -> {
            g2.setColor(color);
            float stroke = Math.max(1.6f, s * 0.11f);
            g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            float d = s * 0.58f;
            g2.draw(new Ellipse2D.Float(s * 0.06f, s * 0.06f, d, d));
            float cx = s * 0.06f + d;
            float cy = s * 0.06f + d;
            g2.draw(new Line2D.Float(cx - d * 0.12f, cy - d * 0.12f, s * 0.94f, s * 0.94f));
        });
    }

    // ---------- Plus (Add New) ----------
    public static Icon plus(int size, Color color) {
        return build(size, (g2, s) -> {
            g2.setColor(color);
            float stroke = Math.max(2f, s * 0.16f);
            g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.draw(new Line2D.Float(s * 0.5f, s * 0.14f, s * 0.5f, s * 0.86f));
            g2.draw(new Line2D.Float(s * 0.14f, s * 0.5f, s * 0.86f, s * 0.5f));
        });
    }

    // ---------- Printer (Print) ----------
    public static Icon printer(int size, Color color) {
        return build(size, (g2, s) -> {
            g2.setColor(color);
            g2.fill(new RoundRectangle2D.Float(s * 0.30f, s * 0.06f, s * 0.40f, s * 0.24f, s * 0.04f, s * 0.04f));
            g2.fill(new RoundRectangle2D.Float(s * 0.08f, s * 0.30f, s * 0.84f, s * 0.36f, s * 0.10f, s * 0.10f));
            g2.setColor(Color.WHITE);
            g2.fill(new Rectangle2D.Float(s * 0.30f, s * 0.40f, s * 0.40f, s * 0.14f));
            g2.setColor(color);
            g2.fill(new RoundRectangle2D.Float(s * 0.26f, s * 0.62f, s * 0.48f, s * 0.32f, s * 0.04f, s * 0.04f));
        });
    }

    // ---------- Pencil (Update / Edit) ----------
    public static Icon pencil(int size, Color color) {
        return build(size, (g2, s) -> {
            g2.setColor(color);
            g2.setStroke(new BasicStroke(Math.max(2f, s * 0.14f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.draw(new Line2D.Float(s * 0.18f, s * 0.86f, s * 0.68f, s * 0.20f));

            GeneralPath tip = new GeneralPath();
            tip.moveTo(s * 0.68f, s * 0.20f);
            tip.lineTo(s * 0.82f, s * 0.06f);
            tip.lineTo(s * 0.94f, s * 0.18f);
            tip.lineTo(s * 0.80f, s * 0.32f);
            tip.closePath();
            g2.fill(tip);

            GeneralPath tail = new GeneralPath();
            tail.moveTo(s * 0.14f, s * 0.90f);
            tail.lineTo(s * 0.18f, s * 0.72f);
            tail.lineTo(s * 0.28f, s * 0.82f);
            tail.closePath();
            g2.fill(tail);
        });
    }

    // ---------- Refresh (Reset) ----------
    public static Icon refresh(int size, Color color) {
        return build(size, (g2, s) -> {
            g2.setColor(color);
            g2.setStroke(new BasicStroke(Math.max(2f, s * 0.13f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            Arc2D.Float arc = new Arc2D.Float(s * 0.12f, s * 0.12f, s * 0.76f, s * 0.76f, 40, 260, Arc2D.OPEN);
            g2.draw(arc);

            GeneralPath head = new GeneralPath();
            head.moveTo(s * 0.72f, s * 0.06f);
            head.lineTo(s * 0.92f, s * 0.16f);
            head.lineTo(s * 0.76f, s * 0.30f);
            head.closePath();
            g2.fill(head);
        });
    }

    // ---------- Trash (Delete) ----------
    public static Icon trash(int size, Color color) {
        return build(size, (g2, s) -> {
            g2.setColor(color);
            g2.fill(new RoundRectangle2D.Float(s * 0.36f, s * 0.02f, s * 0.28f, s * 0.10f, s * 0.03f, s * 0.03f));
            g2.fill(new RoundRectangle2D.Float(s * 0.14f, s * 0.14f, s * 0.72f, s * 0.10f, s * 0.03f, s * 0.03f));

            GeneralPath bodyShape = new GeneralPath();
            bodyShape.moveTo(s * 0.20f, s * 0.26f);
            bodyShape.lineTo(s * 0.80f, s * 0.26f);
            bodyShape.lineTo(s * 0.72f, s * 0.94f);
            bodyShape.lineTo(s * 0.28f, s * 0.94f);
            bodyShape.closePath();
            g2.fill(bodyShape);

            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(Math.max(1.4f, s * 0.05f)));
            g2.draw(new Line2D.Float(s * 0.38f, s * 0.36f, s * 0.40f, s * 0.84f));
            g2.draw(new Line2D.Float(s * 0.50f, s * 0.36f, s * 0.50f, s * 0.84f));
            g2.draw(new Line2D.Float(s * 0.62f, s * 0.36f, s * 0.60f, s * 0.84f));
        });
    }

    // ---------- Power (Exit) ----------
    public static Icon power(int size, Color color) {
        return build(size, (g2, s) -> {
            g2.setColor(color);
            g2.setStroke(new BasicStroke(Math.max(2f, s * 0.13f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            Arc2D.Float arc = new Arc2D.Float(s * 0.10f, s * 0.14f, s * 0.80f, s * 0.80f, 55, 250, Arc2D.OPEN);
            g2.draw(arc);
            g2.draw(new Line2D.Float(s * 0.5f, s * 0.04f, s * 0.5f, s * 0.48f));
        });
    }

    // ---------- Calendar (Attendance) ----------
    public static Icon calendar(int size, Color color) {
        return build(size, (g2, s) -> {
            g2.setColor(color);
            g2.fill(new RoundRectangle2D.Float(s * 0.08f, s * 0.16f, s * 0.84f, s * 0.76f, s * 0.10f, s * 0.10f));

            g2.setColor(Color.WHITE);
            g2.fill(new Rectangle2D.Float(s * 0.08f, s * 0.16f, s * 0.84f, s * 0.18f));

            g2.setColor(color);
            g2.setStroke(new BasicStroke(Math.max(1.6f, s * 0.08f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.draw(new Line2D.Float(s * 0.26f, s * 0.06f, s * 0.26f, s * 0.26f));
            g2.draw(new Line2D.Float(s * 0.74f, s * 0.06f, s * 0.74f, s * 0.26f));

            g2.setColor(Color.WHITE);
            for (int row = 0; row < 2; row++) {
                for (int col = 0; col < 3; col++) {
                    float cx = s * (0.22f + col * 0.28f);
                    float cy = s * (0.50f + row * 0.20f);
                    g2.fill(new Ellipse2D.Float(cx, cy, s * 0.08f, s * 0.08f));
                }
            }
        });
    }

    // ---------- Heart (status / ready) ----------
    public static Icon heart(int size, Color color) {
        return build(size, (g2, s) -> {
            g2.setColor(color);
            GeneralPath heartShape = new GeneralPath();
            heartShape.moveTo(s * 0.5f, s * 0.86f);
            heartShape.curveTo(s * 0.1f, s * 0.58f, s * 0.06f, s * 0.30f, s * 0.28f, s * 0.16f);
            heartShape.curveTo(s * 0.42f, s * 0.07f, s * 0.5f, s * 0.20f, s * 0.5f, s * 0.28f);
            heartShape.curveTo(s * 0.5f, s * 0.20f, s * 0.58f, s * 0.07f, s * 0.72f, s * 0.16f);
            heartShape.curveTo(s * 0.94f, s * 0.30f, s * 0.9f, s * 0.58f, s * 0.5f, s * 0.86f);
            heartShape.closePath();
            g2.fill(heartShape);
        });
    }

    // ---------- Right arrow (Submit) ----------
    public static Icon arrowRight(int size, Color color) {
        return build(size, (g2, s) -> {
            g2.setColor(color);
            g2.setStroke(new BasicStroke(Math.max(2f, s * 0.16f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.draw(new Line2D.Float(s * 0.12f, s * 0.5f, s * 0.72f, s * 0.5f));

            GeneralPath head = new GeneralPath();
            head.moveTo(s * 0.56f, s * 0.24f);
            head.lineTo(s * 0.92f, s * 0.5f);
            head.lineTo(s * 0.56f, s * 0.76f);
            g2.draw(head);
        });
    }

    // ---------- Close X (Close / Cancel) ----------
    public static Icon close(int size, Color color) {
        return build(size, (g2, s) -> {
            g2.setColor(color);
            g2.setStroke(new BasicStroke(Math.max(2f, s * 0.15f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.draw(new Line2D.Float(s * 0.2f, s * 0.2f, s * 0.8f, s * 0.8f));
            g2.draw(new Line2D.Float(s * 0.8f, s * 0.2f, s * 0.2f, s * 0.8f));
        });
    }
}