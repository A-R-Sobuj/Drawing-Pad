import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Drawing_Pad
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(
        new Runnable()
        {
            @Override
            public void run()
            {
                new DrawingFrame();
            }
        });
    }
}

class DrawingFrame extends JFrame
{
    DrawingPanel panel;

    DrawingFrame()
    {
        setTitle("Sobuj's Drawing Pad");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new DrawingPanel();
        add(panel, BorderLayout.CENTER);

        JPanel tools = new JPanel();

        JButton colorBtn = new JButton("Color");
        JButton eraserBtn = new JButton("Eraser");
        JButton undoBtn = new JButton("Undo");
        JButton redoBtn = new JButton("Redo");
        JButton clearBtn = new JButton("Clear");

        JLabel strokeLabel = new JLabel("Stroke:");
        JSlider strokeSlider = new JSlider(1, 20, 3);

        JLabel alphaLabel = new JLabel("Alpha:");
        JSlider alphaSlider = new JSlider(0, 255, 255);

        String[] shapeOptions = { "Free Draw", "Rectangle", "Oval" };
        JComboBox<String> shapeSelector = new JComboBox<>(shapeOptions);

        JLabel previewCircle = new JLabel();
        previewCircle.setPreferredSize(new Dimension(40, 40));
        previewCircle.setOpaque(true);
        previewCircle.setHorizontalAlignment(JLabel.CENTER);
        tools.add(colorBtn);
        tools.add(eraserBtn);
        tools.add(undoBtn);
        tools.add(redoBtn);
        tools.add(clearBtn);
        tools.add(strokeLabel);
        tools.add(strokeSlider);
        tools.add(alphaLabel);
        tools.add(alphaSlider);
        tools.add(previewCircle);
        tools.add(shapeSelector);

        add(tools, BorderLayout.NORTH);

        colorBtn.addActionListener(
        new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Color c = JColorChooser.showDialog(null, "Choose Color", panel.currentColor);
                if (c != null)
                {
                    panel.currentColor = new Color(c.getRed(), c.getGreen(), c.getBlue(), panel.currentAlpha);
                    panel.usingEraser = false;
                    panel.updatePreview(previewCircle);
                }
            }
        });

        eraserBtn.addActionListener(
        new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                panel.currentColor = new Color(255, 255, 255, 255);
                panel.usingEraser = true;
                panel.currentShape = "Free Draw";
                panel.updatePreview(previewCircle);
            }
        });

        undoBtn.addActionListener(e -> panel.undo());
        redoBtn.addActionListener(e -> panel.redo());
        clearBtn.addActionListener(e -> panel.clear());

        strokeSlider.addChangeListener(e -> {
            panel.strokeWidth = strokeSlider.getValue();
            panel.updatePreview(previewCircle);
        });

        alphaSlider.addChangeListener(e -> {
            panel.currentAlpha = alphaSlider.getValue();
            Color base = panel.currentColor;
            panel.currentColor = new Color(base.getRed(), base.getGreen(), base.getBlue(), panel.currentAlpha);
            panel.updatePreview(previewCircle);
        });

        shapeSelector.addActionListener(
        new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                panel.currentShape = (String) shapeSelector.getSelectedItem();
                panel.usingEraser = false;
                panel.updatePreview(previewCircle);
            }
        });

        panel.updatePreview(previewCircle);

        setVisible(true);
    }
}

abstract class DrawableShape
{
    Color color;
    int stroke;

    DrawableShape(Color c, int s)
    {
        color = c;
        stroke = s;
    }

    abstract void draw(Graphics2D g);
    abstract boolean contains(Point p);
    abstract void resize(Point p);
}

class Freehand extends DrawableShape
{
    ArrayList<Point> points;

    Freehand(Color c, int s)
    {
        super(c, s);
        points = new ArrayList<Point>();
    }

    void addPoint(Point p)
    {
        points.add(p);
    }

    void draw(Graphics2D g)
    {
        g.setColor(color);
        g.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        for (int i = 1 ; i < points.size() ; i++)
        {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            if (p1 != null && p2 != null)
            {
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    boolean contains(Point p)
    {
        return false;
    }

    void resize(Point p) {}
}

class RectShape extends DrawableShape
{
    Rectangle rect;

    RectShape(Point start, Point end, Color c, int s)
    {
        super(c, s);
        rect = new Rectangle(
        Math.min(start.x, end.x),
        Math.min(start.y, end.y),
        Math.abs(end.x - start.x),
        Math.abs(end.y - start.y));
    }

    void draw(Graphics2D g)
    {
        g.setColor(color);
        g.setStroke(new BasicStroke(stroke));
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
    }

    boolean contains(Point p)
    {
        return rect.contains(p);
    }

    void resize(Point p)
    {
        rect.setSize(p.x - rect.x, p.y - rect.y);
    }
}

class OvalShape extends DrawableShape
{
    Rectangle bounds;

    OvalShape(Point start, Point end, Color c, int s)
    {
        super(c, s);
        bounds = new Rectangle(
        Math.min(start.x, end.x),
        Math.min(start.y, end.y),
        Math.abs(end.x - start.x),
        Math.abs(end.y - start.y));
    }

    void draw(Graphics2D g)
    {
        g.setColor(color);
        g.setStroke(new BasicStroke(stroke));
        g.drawOval(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    boolean contains(Point p)
    {
        return bounds.contains(p);
    }

    void resize(Point p)
    {
        bounds.setSize(p.x - bounds.x, p.y - bounds.y);
    }
}

class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener
{
    ArrayList<DrawableShape> shapes;
    Stack<DrawableShape> redoStack;

    Color currentColor;
    int currentAlpha;
    int strokeWidth;
    String currentShape;

    DrawableShape currentDraw;
    Point startPoint;
    boolean usingEraser;

    DrawingPanel()
    {
        shapes = new ArrayList<DrawableShape>();
        redoStack = new Stack<DrawableShape>();
        currentColor = new Color(0, 0, 0, 255);
        currentAlpha = 255;
        strokeWidth = 3;
        currentShape = "Free Draw";
        usingEraser = false;

        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (DrawableShape s : shapes)
        {
            s.draw(g2);
        }

        if (currentDraw != null)
        {
            currentDraw.draw(g2);
        }
    }

    public void mousePressed(MouseEvent e)
    {
        startPoint = e.getPoint();

        if (currentShape.equals("Free Draw"))
        {
            currentDraw = new Freehand(currentColor, strokeWidth);
            ((Freehand) currentDraw).addPoint(startPoint);
        }
        else if (currentShape.equals("Rectangle"))
        {
            currentDraw = new RectShape(startPoint, startPoint, currentColor, strokeWidth);
        }
        else if (currentShape.equals("Oval"))
        {
            currentDraw = new OvalShape(startPoint, startPoint, currentColor, strokeWidth);
        }

        repaint();
    }

    public void mouseDragged(MouseEvent e)
    {
        Point end = e.getPoint();

        if (currentShape.equals("Free Draw"))
        {
            ((Freehand) currentDraw).addPoint(end);
        }
        else
        {
            currentDraw.resize(end);
        }

        repaint();
    }

    public void mouseReleased(MouseEvent e)
    {
        if (currentDraw != null)
        {
            shapes.add(currentDraw);
            redoStack.clear();
            currentDraw = null;
        }

        repaint();
    }

    public void undo()
    {
        if (!shapes.isEmpty())
        {
            redoStack.push(shapes.remove(shapes.size() - 1));
            repaint();
        }
    }

    public void redo()
    {
        if (!redoStack.isEmpty())
        {
            shapes.add(redoStack.pop());
            repaint();
        }
    }

    public void clear()
    {
        shapes.clear();
        redoStack.clear();
        repaint();
    }

    public void updatePreview(JLabel label)
    {
        BufferedImage image = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(currentColor);
        g.setStroke(new BasicStroke(strokeWidth));
        g.fillOval(5, 5, 30, 30);  // Draw the circle with the stroke size as the diameter
        g.dispose();
        label.setIcon(new ImageIcon(image));
    }

    @Override
    public void mouseMoved(MouseEvent e)    {}
    @Override
    public void mouseClicked(MouseEvent e)  {}
    @Override
    public void mouseEntered(MouseEvent e)  {}
    @Override
    public void mouseExited(MouseEvent e)   {}
}
