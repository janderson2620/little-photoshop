import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class Main {

    private JFrame frame;
    private JLabel imageLabel;
    private  JButton carregarImagem;
    private JButton realceDeBordaBotao;
    private JButton binarizar;
    private JButton inverterCores;
    private JButton suavilizar;
    private JButton filtro1;
    private JButton filtro2;
    private JButton filtro3;
    private JButton filtro4;
    private JButton filtro5;

    private BufferedImage imagem;

    public Main() {
        frame = new JFrame("Filtros de Imagem");
        frame.setSize(1400, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        imageLabel = new JLabel();

        JScrollPane scrollPane = new JScrollPane(imageLabel);

        frame.add(scrollPane, BorderLayout.CENTER);
        JPanel barraDeBotoes = new JPanel();


        carregarImagem = new JButton("Carregar Imagem");
        realceDeBordaBotao = new JButton("Realce de Borda");
        binarizar = new JButton("Binarizar Imagem");
        suavilizar = new JButton("Suavilizar");
        inverterCores = new JButton("Inverter cores");
        filtro1 = new JButton("Filtro 1");
        filtro2 = new JButton("Filtro 2");
        filtro3 = new JButton("Filtro 3");
        filtro4 = new JButton("Filtro 4");
        filtro5 = new JButton("Filtro 5");

        barraDeBotoes.add(carregarImagem);
        barraDeBotoes.add(realceDeBordaBotao);
        barraDeBotoes.add(suavilizar);
        barraDeBotoes.add(binarizar);
        barraDeBotoes.add(inverterCores);
        barraDeBotoes.add(filtro1);
        barraDeBotoes.add(filtro2);
        barraDeBotoes.add(filtro3);
        barraDeBotoes.add(filtro4);
        barraDeBotoes.add(filtro5);
        frame.add(barraDeBotoes, BorderLayout.SOUTH);

        carregarImagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        imagem = ImageIO.read(selectedFile);
                        exibirImagem(imagem);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        binarizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;
                    int limiar = 128;
                    try{
                        imagemSaida = Filtros.binarizarImagem(imagem, limiar);
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });

        suavilizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null ){
                    BufferedImage imagemSaida = null;
                    try {
                        imagemSaida = Filtros.suavizar(imagem);
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });

        realceDeBordaBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;
                    try {
                        imagemSaida = Filtros.realceBorda(imagem);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });

        inverterCores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;
                    try {
                        imagemSaida = Filtros.inverterCores(imagem);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });

        filtro1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;

                    double[] filter1 = {
                            1, 1, 1, 0, 0, 0, -1, -1, -1
                    };

                    try {
                        imagemSaida = Filtros.filtros(imagem, filter1);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });

        filtro2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;

                    double[] filter2 = {
                            1, 0, -1, 1, 0, -1, 1, 0, -1
                    };

                    try {
                        imagemSaida = Filtros.filtros(imagem, filter2);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });

        filtro3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;

                    double[] filter3 = {
                            -1, -1, -1, -1, 8, -1, -1, -1, -1
                    };

                    try {
                        imagemSaida = Filtros.filtros(imagem, filter3);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });

        filtro4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;

                    double[] filter4 = {
                            0, -1, 0, -1, 5, -1, 0, -1, 0
                    };

                    try {
                        imagemSaida = Filtros.filtros(imagem, filter4);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });

        filtro5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;

                    double[] filter5 = {
                            0, 0, 0, -1, 1, 0, 0, 0, 0
                    };

                    try {
                        imagemSaida = Filtros.filtros(imagem, filter5);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });

        frame.setVisible(true);
    }

    private void exibirImagem(BufferedImage image) {
        ImageIcon imageIcon = new ImageIcon(image);
        imageLabel.setIcon(imageIcon);
        imageLabel.revalidate();
        imageLabel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
