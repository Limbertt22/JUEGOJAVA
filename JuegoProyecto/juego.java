package JuegoProyecto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class juego extends JPanel {
    JButton jButton, punto;
    int x , y, contador;
    JLabel contadorMain;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int filas = 8;  // Número de filas
        int columnas = 8; // Número de columnas

        // Tamaño de cada celda
        int ancho = getWidth() / columnas;
        int alto = getHeight() / filas;

        // Dibujar el patrón
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                // Dibujar cuadrado gris
                g.setColor(Color.LIGHT_GRAY); // Color gris claro
                g.fillRect(columna * ancho, fila * alto, ancho, alto);

                // Dibujar bordes oscuros para las separaciones
                g.setColor(Color.DARK_GRAY);
                g.drawRect(columna * ancho, fila * alto, ancho, alto);
            }
        }
    }

    public juego(int color, int rapize, int controles) {
        setLayout(null);
        setFocusable(true);
        requestFocusInWindow();

        contadorMain = new JLabel("contador: ");
        contadorMain.setBounds(480,3,200,30);
        contadorMain.setForeground(Color.BLACK);
        contadorMain.setFont(new Font("Arail", Font.ITALIC | Font.BOLD, 15));
        add(contadorMain);
        x = 100;
        y = 250;

        jButton = new JButton();
        jButton.setBounds(x, y, 50, 50);
        add(jButton);

        switch (color) {
            case 1:
                jButton.setBackground(Color.RED);
                break;
            case 2:
                jButton.setBackground(Color.BLUE);
                break;
            case 3:
                jButton.setBackground(Color.PINK);
                break;
            case 4:
                jButton.setBackground(Color.GREEN);
                break;
            case 5:
                jButton.setBackground(Color.white);
                break;
            default:
        }

        punto = new JButton();
        punto.setBounds(200, 200, 30, 30);
        punto.setBackground(Color.ORANGE);
        punto.setBorder(BorderFactory.createLineBorder(Color.red, 10));
        add(punto);

        if (controles == 1) {
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int keycode = e.getKeyCode();
                    switch (keycode) {
                        case KeyEvent.VK_DOWN:
                            y += rapize;
                            break;
                        case KeyEvent.VK_RIGHT:
                            x += rapize;
                            break;
                        case KeyEvent.VK_LEFT:
                            x -= rapize;
                            break;
                        case KeyEvent.VK_UP:
                            y -= rapize;
                            break;
                        default:
                            break;
                    }

                    jButton.setBounds(x, y, 50, 50);
                    if(colicion(jButton, punto)){
                        contador += 1;
                        contadorMain.setText("contador: "+String.valueOf(contador));
                        puntoX();
                    }

                    if (color == 6) {
                        color();
                    }
                    repaint();
                }
            });
        } else {
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int keycode = e.getKeyCode();
                    switch (keycode) {
                        case KeyEvent.VK_S:
                            y += rapize;
                            break;
                        case KeyEvent.VK_D:
                            x += rapize;
                            break;
                        case KeyEvent.VK_A:
                            x -= rapize;
                            break;
                        case KeyEvent.VK_W:
                            y -= rapize;
                            break;
                        default:
                            break;
                    }

                    jButton.setBounds(x, y, 50, 50);

                    if(colicion(jButton, punto)){
                        System.out.println("colicion");
                        puntoX();
                    }

                    if (color == 6) {
                        color();
                    }
                    repaint();
                }
            });
        }
    }

    public void puntoX(){
        Random rd = new Random();
        int random = rd.nextInt(0, 400);
        int random2 = rd.nextInt(0,400);
        punto.setBounds(random,random2, 30,30);
        repaint();
    }

    public boolean colicion(JButton a , JButton b){
        Rectangle s = a.getBounds();
        Rectangle x = b.getBounds();
        System.out.println(s +","+x);
        return s.intersects(x);
    }

    public void color(){
        Random rd = new Random();
        int random = rd.nextInt(0,10);
        switch (random){
            case 1:
                jButton.setBackground(Color.BLUE);
                break;
            case 2:
                jButton.setBackground(Color.ORANGE);
                break;
            case 3:
                jButton.setBackground(Color.CYAN);
                break;
            case 4:
                jButton.setBackground(Color.white);
                break;
            case 5:
                jButton.setBackground(Color.GREEN);
                break;
            case 6:
                jButton.setBackground(Color.pink);
                break;
            case 7:
                jButton.setBackground(Color.MAGENTA);
                break;
            case 8:
                jButton.setBackground(Color.red);
                break;
            case 9:
                jButton.setBackground(Color.YELLOW);
                break;
            default:
        }
    }
}
