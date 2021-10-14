package lt.terzer.checkers;

import lt.terzer.checkers.drawables.Checker;
import lt.terzer.checkers.renderer.CheckersRenderer;
import lt.terzer.checkers.renderer.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

public class Main extends JPanel {

    private final Board board = new Board();
    private final Renderer renderer;

    public Main(Dimension dimension) {
        renderer = new CheckersRenderer(board.getBoardMap(), dimension);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent event) {
                renderer.windowResize(event.getComponent().getSize());
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    Checker checker = board.getCheckerAt(renderer.getPoint2D(e.getPoint()));
                    if (checker != null) {
                        board.showPossibleMoves(checker);
                    }
                }
                else if(e.getButton() == MouseEvent.BUTTON3){
                    board.getBoardMap().clearHighlight();
                }
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.paint(g, board.getCheckers());
    }


    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            try {
                JFrame frame = new JFrame("Checkers");
                Dimension dimension = new Dimension(800, 800);
                frame.setPreferredSize(dimension);
                frame.add(new Main(dimension));
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
