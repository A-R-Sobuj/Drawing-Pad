import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;

public class Drawing_Pad {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DrawingFrame());
    }
}

// ── Frame ─────────────────────────────────────────────────────────────────────
class DrawingFrame extends JFrame {

    DrawingPanel panel;

    private File lastSaveDirectory =
            new File(System.getProperty("user.home"), "Desktop");

    DrawingFrame() {
        setTitle("Sobuj's Drawing Pad");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new DrawingPanel();
        add(panel, BorderLayout.CENTER);

        JPanel tools = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));
        tools.setBorder(BorderFactory.createEtchedBorder());

        JButton       colorBtn  = new JButton("Color");
        JButton       eraserBtn = new JButton("Eraser");
        JButton       undoBtn   = new JButton("Undo");
        JButton       redoBtn   = new JButton("Redo");
        JButton       clearBtn  = new JButton("Clear");
        JButton       saveBtn   = new JButton("Save PNG");
        JToggleButton fillBtn   = new JToggleButton("Fill");
        JToggleButton gridBtn   = new JToggleButton("Grid");

        JSlider strokeSlider = new JSlider(1, 50, 3);
        strokeSlider.setPreferredSize(new Dimension(90, 28));

        JSlider alphaSlider = new JSlider(0, 255, 255);
        alphaSlider.setPreferredSize(new Dimension(90, 28));

        String[] shapeNames = {"Free Draw", "Line", "Rectangle", "Oval"};
        JComboBox<String> shapeBox = new JComboBox<>(shapeNames);

        // Live preview — draws a horizontal line in current color/width
        JPanel preview = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);

                int maxSize = Math.min(getWidth(), getHeight()) - 2;
                int size = Math.min(Math.max(panel.strokeWidth, 4), maxSize);

                int x = (getWidth() - size) / 2;
                int y = (getHeight() - size) / 2;

                if (panel.usingEraser) {
                    g2.setColor(Color.WHITE);
                    g2.fillOval(x, y, size, size);

                    g2.setColor(Color.BLACK);
                    g2.setStroke(new BasicStroke(1f));
                    g2.drawOval(x, y, size, size);
                } else {
                    g2.setColor(panel.currentColor);
                    g2.fillOval(x, y, size, size);

                    g2.setColor(new Color(0, 0, 0, 100));
                    g2.drawOval(x, y, size, size);
                }

                g2.dispose();
            }
        };
        preview.setPreferredSize(new Dimension(55, 55));
        preview.setOpaque(false);
        preview.setBackground(Color.WHITE);
        preview.setBorder(null);

        tools.add(colorBtn);   tools.add(eraserBtn);
        tools.add(undoBtn);    tools.add(redoBtn);
        tools.add(clearBtn);   tools.add(saveBtn);
        tools.add(new JLabel("Stroke:")); tools.add(strokeSlider);
        tools.add(new JLabel("Alpha:"));  tools.add(alphaSlider);
        tools.add(preview);
        tools.add(shapeBox);   tools.add(fillBtn);
        tools.add(gridBtn);
        add(tools, BorderLayout.NORTH);

        JLabel status = new JLabel("  Ready");
        status.setBorder(BorderFactory.createEtchedBorder());
        add(status, BorderLayout.SOUTH);
        panel.statusBar = status;

        // ── Listeners ──────────────────────────────────────────────────────────

        colorBtn.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Choose Color", panel.savedColor);
            if (c != null) {
                panel.savedColor = c;
                panel.currentColor = new Color(
                        c.getRed(), c.getGreen(), c.getBlue(), panel.currentAlpha);
                panel.usingEraser = false;
                preview.repaint();
            }
        });

        eraserBtn.addActionListener(e -> {
            panel.usingEraser = true;
            panel.currentShape = "Free Draw";

            if (!"Free Draw".equals(shapeBox.getSelectedItem())) {
                shapeBox.setSelectedItem("Free Draw");
            }

            preview.repaint();
        });

        undoBtn.addActionListener(e -> { panel.undo(); preview.repaint(); });
        redoBtn.addActionListener(e -> { panel.redo(); preview.repaint(); });
        clearBtn.addActionListener(e -> panel.clear());

        saveBtn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser(lastSaveDirectory);
            fc.setFileFilter(new FileNameExtensionFilter("PNG Image", "png"));
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {

    lastSaveDirectory = fc.getCurrentDirectory();

    File f = fc.getSelectedFile();
                if (!f.getName().endsWith(".png")) f = new File(f.getPath() + ".png");
                try {
                    ImageIO.write(panel.canvas, "PNG", f);
                    JOptionPane.showMessageDialog(this, "Saved: " + f.getAbsolutePath());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Save failed: " + ex.getMessage());
                }
            }
        });

        strokeSlider.addChangeListener(e -> {
            panel.strokeWidth = strokeSlider.getValue();
            preview.repaint();
        });

        alphaSlider.addChangeListener(e -> {
            panel.currentAlpha = alphaSlider.getValue();
            if (!panel.usingEraser) {
                Color b = panel.savedColor;
                panel.currentColor = new Color(b.getRed(), b.getGreen(),
                                               b.getBlue(), panel.currentAlpha);
            }
            preview.repaint();
        });

        shapeBox.addActionListener(e -> {
            panel.currentShape = (String) shapeBox.getSelectedItem();
        });

        fillBtn.addActionListener(e -> panel.filledShapes = fillBtn.isSelected());
        gridBtn.addActionListener(e -> { panel.showGrid = gridBtn.isSelected(); panel.repaint(); });

        setVisible(true);
    }
}

// ── Shape hierarchy (rubber-band preview only; never stored long-term) ────────
abstract class DrawableShape {
    Color color;
    int   stroke;
    DrawableShape(Color c, int s) { color = c; stroke = s; }
    abstract void draw(Graphics2D g);
    abstract void resize(Point p);
}

class Freehand extends DrawableShape {
    ArrayList<Point> pts = new ArrayList<>();
    Freehand(Color c, int s) { super(c, s); }
    void addPoint(Point p)   { pts.add(p); }
    void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 1; i < pts.size(); i++) {
            Point a = pts.get(i - 1), b = pts.get(i);
            g.drawLine(a.x, a.y, b.x, b.y);
        }
    }
    void resize(Point p) {}
}

class LineShape extends DrawableShape {
    Point a, b;
    LineShape(Point a, Point b, Color c, int s) { super(c, s); this.a = a; this.b = b; }
    void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawLine(a.x, a.y, b.x, b.y);
    }
    void resize(Point p) { b = p; }
}

class RectShape extends DrawableShape {
    Point start; Rectangle r; boolean filled;
    RectShape(Point s, Point e, Color c, int stroke, boolean filled) {
        super(c, stroke); this.start = s; this.filled = filled; r = makeRect(s, e);
    }
    static Rectangle makeRect(Point s, Point e) {
        return new Rectangle(Math.min(s.x, e.x), Math.min(s.y, e.y),
                             Math.abs(e.x - s.x), Math.abs(e.y - s.y));
    }
    void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(stroke));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (filled) g.fillRect(r.x, r.y, r.width, r.height);
        else        g.drawRect(r.x, r.y, r.width, r.height);
    }
    void resize(Point p) { r = makeRect(start, p); }
}

class OvalShape extends DrawableShape {
    Point start; Rectangle r; boolean filled;
    OvalShape(Point s, Point e, Color c, int stroke, boolean filled) {
        super(c, stroke); this.start = s; this.filled = filled; r = RectShape.makeRect(s, e);
    }
    void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(stroke));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (filled) g.fillOval(r.x, r.y, r.width, r.height);
        else        g.drawOval(r.x, r.y, r.width, r.height);
    }
    void resize(Point p) { r = RectShape.makeRect(start, p); }
}

// ── Drawing panel ─────────────────────────────────────────────────────────────
class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {

    // TYPE_INT_RGB has no alpha channel — white pixels are truly opaque white,
    // so the eraser (which paints white) works without any compositing tricks.
    BufferedImage canvas;

    Stack<BufferedImage> undoStack = new Stack<>();
    Stack<BufferedImage> redoStack = new Stack<>();

    // In-progress shape for rubber-band preview (shapes) or segment tracking (freehand)
    DrawableShape liveShape;
    Point         startPoint;
    Point         lastPoint;   // tracks previous drag point for incremental freehand
    Point         mousePosition;

    Color   currentColor = Color.BLACK;
    Color   savedColor   = Color.BLACK;
    int     currentAlpha = 255;
    int     strokeWidth  = 3;
    String  currentShape = "Free Draw";
    boolean usingEraser  = false;
    boolean filledShapes = false;
    boolean showGrid     = false;

    JLabel statusBar;

    DrawingPanel() {
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    // ── Canvas helpers ────────────────────────────────────────────────────────

    // Returns (creating if needed) a TYPE_INT_RGB canvas matching the panel size.
    BufferedImage getCanvas() {
        int w = Math.max(1, getWidth());
        int h = Math.max(1, getHeight());
        if (canvas == null || canvas.getWidth() != w || canvas.getHeight() != h) {
            BufferedImage fresh = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = fresh.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, w, h);
            if (canvas != null) g.drawImage(canvas, 0, 0, null); // preserve existing drawing
            g.dispose();
            canvas = fresh;
        }
        return canvas;
    }

    // Deep copy of the current canvas for undo/redo snapshots.
    private BufferedImage snapshot() {
        BufferedImage img = new BufferedImage(
            getCanvas().getWidth(), getCanvas().getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.drawImage(canvas, 0, 0, null);
        g.dispose();
        return img;
    }

    // Creates a Graphics2D ready to draw on the canvas.
    // For the eraser we use Color.WHITE — on TYPE_INT_RGB this is guaranteed opaque.
    private Graphics2D canvasGraphics() {
        Graphics2D g = getCanvas().createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return g;
    }

    // ── Paint ─────────────────────────────────────────────────────────────────

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Committed bitmap
        g2.drawImage(getCanvas(), 0, 0, null);

        // Optional grid overlay
        if (showGrid) {
            g2.setColor(new Color(180, 180, 180));
            g2.setStroke(new BasicStroke(0.5f));
            for (int x = 0; x < getWidth();  x += 20) g2.drawLine(x, 0, x, getHeight());
            for (int y = 0; y < getHeight(); y += 20) g2.drawLine(0, y, getWidth(), y);
        }

        // Rubber-band preview for shapes (Line / Rect / Oval)
        if (liveShape != null && !(liveShape instanceof Freehand)) {
            liveShape.draw(g2);
        }

        if (usingEraser && mousePosition != null) {
            Graphics2D cursor = (Graphics2D) g2.create();
            cursor.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int size = Math.max(strokeWidth, 8);

            cursor.setColor(new Color(30, 30, 30));
            cursor.setStroke(new BasicStroke(1f));
            cursor.drawOval(
                    mousePosition.x - size / 2,
                    mousePosition.y - size / 2,
                    size,
                    size);
            cursor.dispose();
        }
    }

    // ── Mouse events ──────────────────────────────────────────────────────────

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        lastPoint  = startPoint;

        // Save undo snapshot before every stroke
        undoStack.push(snapshot());
        redoStack.clear();

        Color drawColor = usingEraser ? Color.WHITE : currentColor;

        switch (currentShape) {
            case "Free Draw" -> {
                liveShape = new Freehand(drawColor, strokeWidth);
                ((Freehand) liveShape).addPoint(startPoint);
                // Paint the initial dot so a click with no drag still marks the canvas
                Graphics2D g = canvasGraphics();
                g.setColor(drawColor);
                g.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g.drawLine(startPoint.x, startPoint.y, startPoint.x, startPoint.y);
                g.dispose();
            }
            case "Line"      -> liveShape = new LineShape(startPoint, startPoint, drawColor, strokeWidth);
            case "Rectangle" -> liveShape = new RectShape(startPoint, startPoint, drawColor, strokeWidth, filledShapes);
            case "Oval"      -> liveShape = new OvalShape(startPoint, startPoint, drawColor, strokeWidth, filledShapes);
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (liveShape == null) return;
        Point current = e.getPoint();
        mousePosition = current;

        if (liveShape instanceof Freehand fh) {
            fh.addPoint(current);

            // Paint only the newest segment directly onto the canvas.
            // Because we keep liveShape alive, lastPoint always has a valid previous point.
            Graphics2D g = canvasGraphics();
            g.setColor(usingEraser ? Color.WHITE : currentColor);
            g.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.drawLine(lastPoint.x, lastPoint.y, current.x, current.y);
            g.dispose();

            lastPoint = current;
        } else {
            // Rubber-band: just update the endpoint, paintComponent draws the preview
            liveShape.resize(current);
        }

        if (statusBar != null)
            statusBar.setText(String.format("  x:%d  y:%d   Δ%d×%d",
                current.x, current.y,
                Math.abs(current.x - startPoint.x),
                Math.abs(current.y - startPoint.y)));
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (liveShape == null) return;

        if (!(liveShape instanceof Freehand)) {
            // Commit the finished shape onto the canvas
            Graphics2D g = canvasGraphics();
            liveShape.draw(g);
            g.dispose();
        }
        // Freehand is already fully on the canvas from incremental paints in mouseDragged

        liveShape = null;
        if (statusBar != null)
            statusBar.setText("  Ready  |  undo steps: " + undoStack.size());
        repaint();
    }

    // ── Undo / redo / clear ───────────────────────────────────────────────────

    void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(snapshot());
            canvas = undoStack.pop();
            repaint();
        }
    }

    void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(snapshot());
            canvas = redoStack.pop();
            repaint();
        }
    }

    void clear() {
        undoStack.push(snapshot());
        redoStack.clear();
        Graphics2D g = canvasGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g.dispose();
        repaint();
    }

    // ── Unused mouse events ───────────────────────────────────────────────────

    @Override public void mouseMoved(MouseEvent e) {
        mousePosition = e.getPoint();
        if (statusBar != null)
            statusBar.setText(String.format("  x:%d  y:%d", e.getX(), e.getY()));
        repaint();
    }
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited (MouseEvent e) {}
}
