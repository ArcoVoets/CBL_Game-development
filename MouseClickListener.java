import java.awt.event.*;

class MouseClickListener implements MouseListener {
    interface MouseClickListenerCallback {
        void mouseClicked();
    }

    MouseClickListenerCallback callback;

    public MouseClickListener(MouseClickListenerCallback callback) {
        this.callback = callback;
    }

    public void mouseClicked(MouseEvent e) {
        callback.mouseClicked();
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }
}
