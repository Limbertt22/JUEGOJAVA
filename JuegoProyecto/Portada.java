package JuegoProyecto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Portada extends JPanel {
    private JPanel jPanel1, jPanel2, jPanel3, jPanel4;
    private JLabel jLabel;
    private JFrame frame;
    int color = 1 , rapidez = 50, controles = 1;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        Color color1 = Color.GREEN;
        Color color2 = Color.CYAN;

        GradientPaint gp = new GradientPaint(0, 0, color1, width, height, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);

        setLayout(null);
        JLabel label = new JLabel("CAZADOR DE PUNTOS");
        label.setBounds(195, 20, 300, 50);
        label.setFont(new Font("Helvetica", Font.ITALIC | Font.BOLD, 22));
        add(label);

        JButton boton = new JButton("Start");
        boton.setBounds(235, 80, 150, 70);
        boton.setFont(new Font("Arial", Font.BOLD, 18));
        boton.setBackground(Color.GREEN);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);

        JButton boton2 = new JButton("salir");
        boton2.setBounds(235, 160, 150, 70);
        boton2.setFont(new Font("Arial", Font.BOLD, 18));
        boton2.setBackground(Color.GREEN);
        boton2.setForeground(Color.WHITE);
        boton2.setFocusPainted(false);
        add(boton2);

        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llamada(color, rapidez, controles);
            }
        });

        add(boton);
    }

    public void llamada(int color, int rapidez, int controles) {
        juego juegoPanel = new juego(color,rapidez, controles);
        frame().setContentPane(juegoPanel);// Cambiar el panel dentro del JFrame
        juegoPanel.revalidate();
    }

    public static void main(String[] args) {
        Portada portada = new Portada();
        portada.frame();
    }

    public JFrame frame() {
        frame = new JFrame("Cazador de puntos");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // Panel inicial
        jPanel1 = createGradientPanel();
        jPanel1.setBounds(0, 0, 700, 500);

        // Panel de información
        jPanel2 = createInfoPanel();
        jPanel2.setBounds(0, 0, 700, 500);
        jPanel2.setVisible(false); // Ocultamos inicialmente

        jPanel3 = Comojugar();
        jPanel3.setBounds(0,0,700,500);
        jPanel3.setVisible(false);

        jPanel4 = jPanel4();
        jPanel4.setBounds(0,0,700,500);
        jPanel4.setVisible(false);

        frame.add(jPanel4);
        frame.add(jPanel1);
        frame.add(jPanel2);
        frame.add(jPanel3);

        // Barra de menú
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menu1 = new JMenu("info");
        JMenuItem item1 = new JMenuItem("información completa");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar panel
                jPanel1.setVisible(false);
                jPanel2.setVisible(true);
                jPanel3.setVisible(false);
                jPanel4.setVisible(false);
            }
        });
        menu1.add(item1);
        menuBar.add(menu1);

        JMenu menu3 = new JMenu("como jugar");
        JMenuItem item3 = new JMenuItem("instrucciones");
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel1.setVisible(false);
                jPanel2.setVisible(false);
                jPanel3.setVisible(true);
                jPanel4.setVisible(false);
            }
        });
        menu3.add(item3);
        menuBar.add(menu3);

        JMenu menu4 = new JMenu("Config");
        JMenuItem item4 = new JMenuItem("configuracion");
        menu4.add(item4);
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel1.setVisible(false);
                jPanel2.setVisible(false);
                jPanel3.setVisible(false);
                jPanel4.setVisible(true);
            }
        });
        menuBar.add(menu4);

        color(); // Cambia colores cada cierto tiempo
        frame.setVisible(true);
        return frame;
    }

    public JPanel createGradientPanel() {
        Portada panel = new Portada();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
        return panel;
    }

    public JPanel createInfoPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.darkGray);

        String texto = "Hola comunidad de github, mi nombre es Mr.Lambert y si has llegado hasta aqui me gustaria mucho que te\n" +
                "dieras una vuelta a mi perfil de leetcode tambien.\n" +
                "\n" +
                "este es uno de mis primeros proyectos en el area de programacion java, como se puede ver en el codigo no\n" +
                "soy un experto aun en el area de programacion, pero me gustaria saber sus opiniones(aun si son malas),\n" +
                "me despido y les deseo buena tarde \uD83D\uDC7E.";
        JTextArea textArea = new JTextArea(texto);
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setForeground(Color.BLACK);
        textArea.setBounds(30, 20, 600, 140);
        panel.add(textArea);

        jLabel = new JLabel("mi github y perfil de leecode: ");
        jLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 18));
        jLabel.setForeground(Color.WHITE);
        jLabel.setBounds(30, 175, 300, 20);
        panel.add(jLabel);

        // Ajuste de imagen de Github
        ImageIcon imagenIcono = new ImageIcon(getClass().getResource("/JuegoProyecto/imagine/perfilGithub.png"));
        Image imagenEscalada = imagenIcono.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        JLabel etiquetaImagen = new JLabel(new ImageIcon(imagenEscalada));
        etiquetaImagen.setBounds(100, 210, 220, 220);
        panel.add(etiquetaImagen);

        // Ajuste de imagen de Leetcode
        ImageIcon imagenIcono2 = new ImageIcon(getClass().getResource("/JuegoProyecto/imagine/leetcodePerfil.png"));
        Image imagenEscalada2 = imagenIcono2.getImage().getScaledInstance(250, 220, Image.SCALE_SMOOTH);
        JLabel etiquetaImagen2 = new JLabel(new ImageIcon(imagenEscalada2));
        etiquetaImagen2.setBounds(340, 210, 250, 220);
        panel.add(etiquetaImagen2);

        JButton jButton = new JButton("atras");
        jButton.setBounds(1, 377, 100, 50);
        jButton.setBackground(Color.CYAN);
        jButton.setBorder(BorderFactory.createLineBorder(Color.cyan.darker(), 5));
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Volver al panel principal
                jPanel1.setVisible(true);
                jPanel2.setVisible(false);
            }
        });
        panel.add(jButton);
        return panel;
    }

    public void color() {
        Timer timer = new Timer(600, e -> CambioColor());
        timer.start();
    }

    public void CambioColor() {
        Random random = new Random();
        int rd = random.nextInt(0, 6);
        switch (rd) {
            case 1:
                jLabel.setForeground(Color.GREEN);
                break;
            case 2:
                jLabel.setForeground(Color.RED);
                break;
            case 3:
                jLabel.setForeground(Color.WHITE);
                break;
            case 4:
                jLabel.setForeground(Color.BLUE);
                break;
            case 5:
                jLabel.setForeground(Color.MAGENTA);
                break;
            default:
                jLabel.setForeground(Color.CYAN);
        }
    }

    public JPanel Comojugar(){
        JPanel jPanel = new JPanel(null);
        jPanel.setBackground(Color.red);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/JuegoProyecto/imagine/juegoCaptura.png"));
        Image image = imageIcon.getImage().getScaledInstance(400,300,Image.SCALE_SMOOTH);
        JLabel jLabel1 = new JLabel(new ImageIcon(image));
        jLabel1.setBounds(120,10,400,300);
        jPanel.add(jLabel1);

        JTextArea jTextArea = new JTextArea("tu eres el cuadrado que cambia de color, tienes que tomar los otros puntos y asi\n" +
                "subir el contador hasta ganar.");
        jTextArea.setBounds(90,340, 480,80);
        jTextArea.setForeground(Color.BLACK);
        jTextArea.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 12));
        jTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK.brighter(), 8));
        jPanel.add(jTextArea);

        JButton jButton = new JButton("regresar");
        jButton.setBounds(1,340,90,70);
        jButton.setBackground(Color.lightGray);
        jButton.setForeground(Color.BLACK);
        jButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel3.setVisible(false);
                jPanel1.setVisible(true);
            }
        });
        jPanel.add(jButton);

        return jPanel;
    }

    public JPanel jPanel4(){
        JPanel jPanel = new JPanel(null);
        jPanel.setBackground(Color.LIGHT_GRAY.darker());

        JLabel jLabel1 = new JLabel("color del cuadrado:");
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setBounds(110,10,200,20);
        jLabel1.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 18));
        jPanel.add(jLabel1);
        jPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY.darker(), 6));

        JComboBox jComboBox = new JComboBox<>();
        jComboBox.addItem("rojo");
        jComboBox.addItem("azul");
        jComboBox.addItem("rosa");
        jComboBox.addItem("verde");
        jComboBox.addItem("blanco");
        jComboBox.addItem("arcoiris");

        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String color2 = jComboBox.getSelectedItem().toString();
              switch (color2){
                  case "rojo":
                      color = 1;
                      break;
                  case "azul":
                      color = 2;
                      break;
                  case "rosa":
                      color = 3;
                      break;
                  case "verde":
                      color = 4;
                      break;
                  case "blanco":
                      color = 5;
                      break;
                  case "arcoiris":
                      color = 6;
                      break;
                  default:
              }
            }
        });
        jComboBox.setBounds(110,40,110,28);
        jPanel.add(jComboBox);

        JLabel jLabel2 = new JLabel("rapidez");
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setBounds(400,10,200,20);
        jLabel2.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 20));
        jPanel.add(jLabel2);

        JComboBox jComboBox1 = new JComboBox<>();
        jComboBox1.addItem("20");
        jComboBox1.addItem("50");
        jComboBox1.addItem("70");
        jComboBox1.addItem("100");
        jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rapidez1 = jComboBox1.getSelectedItem().toString();
                rapidez = Integer.parseInt(rapidez1);
                System.out.println(rapidez1);
            }
        });
        jComboBox1.setBounds(400,40,110,28);
        jPanel.add(jComboBox1);

        JLabel jLabel3 = new JLabel("elegir Controles");
        jLabel3.setForeground(Color.WHITE);
        jLabel3.setBounds(250,150,200,20);
        jLabel3.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 20));
        jPanel.add(jLabel3);


        JButton jButton = new JButton("w");
        jButton.setBackground(Color.white.darker());
        jButton.setBorder(BorderFactory.createLineBorder(Color.lightGray, 5));
        jButton.setBounds(100,200,50,50);
        jPanel.add(jButton);

        JButton jButton1 = new JButton("s");
        jButton1.setBackground(Color.white.darker());
        jButton1.setBorder(BorderFactory.createLineBorder(Color.lightGray, 5));
        jButton1.setBounds(100,255,50,50);
        jPanel.add(jButton1);

        JButton jButton2 = new JButton("a");
        jButton2.setBackground(Color.white.darker());
        jButton2.setBorder(BorderFactory.createLineBorder(Color.lightGray, 5));
        jButton2.setBounds(46,255,50,50);
        jPanel.add(jButton2);

        JButton jButton4 = new JButton("d");
        jButton4.setBackground(Color.white.darker());
        jButton4.setBorder(BorderFactory.createLineBorder(Color.lightGray, 5));
        jButton4.setBounds(155,255,50,50);
        jPanel.add(jButton4);

        JButton jButton5 = new JButton("↑");
        jButton5.setBackground(Color.white.darker());
        jButton5.setBorder(BorderFactory.createLineBorder(Color.lightGray, 5));
        jButton5.setBounds(500,200,50,50);
        jPanel.add(jButton5);

        JButton jButton6 = new JButton("↓");
        jButton6.setBackground(Color.white.darker());
        jButton6.setBorder(BorderFactory.createLineBorder(Color.lightGray, 5));
        jButton6.setBounds(500,255,50,50);
        jPanel.add(jButton6);

        JButton jButton7 = new JButton("←");
        jButton7.setBackground(Color.white.darker());
        jButton7.setBorder(BorderFactory.createLineBorder(Color.lightGray, 5));
        jButton7.setBounds(445,255,50,50);
        jPanel.add(jButton7);

        JButton jButton8 = new JButton("→");
        jButton8.setBackground(Color.white.darker());
        jButton8.setBorder(BorderFactory.createLineBorder(Color.lightGray, 5));
        jButton8.setBounds(555,255,50,50);
        jPanel.add(jButton8);

        JCheckBox jCheckBox = new JCheckBox("estardar");
        jCheckBox.setBounds(80,310,100,20);
        jPanel.add(jCheckBox);

        JCheckBox jCheckBox1 = new JCheckBox("clasica");
        jCheckBox1.setBounds(480,310,100,20);
        jPanel.add(jCheckBox1);

        JButton jButton3 = new JButton("seguir y jugar");
        jButton3.setBorder(BorderFactory.createLineBorder(Color.red.darker(), 5));
        jButton3.setBackground(Color.RED);
        jButton3.setForeground(Color.BLACK);
        jButton3.setBounds(257, 350,100,50);
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jCheckBox1.isSelected()){
                llamada(color,rapidez,1);
                }else{
                    llamada(color,rapidez,2);
                }
            }
        });
        jPanel.add(jButton3);

        return jPanel;
    }
}