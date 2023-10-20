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
    private JButton carregarImagem;
    private JButton realceDeBordaBotao;
    private JButton binarizar;
    private JButton inverterImagem;
    private JButton suavilizar;
    private JButton filtro1;
    private JButton filtro2;
    private JButton filtro3;
    private JButton filtro4;
    private JButton filtro5;
    private JButton filtro6;
    private JButton filtro7;
    private JButton filtro8;
    private BufferedImage imagem;

    public Main() {
        frame = new JFrame("Filtros de Imagem");
        frame.setSize(800, 600);
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
        filtro1 = new JButton("Filtro1");


        barraDeBotoes.add(carregarImagem);
        barraDeBotoes.add(realceDeBordaBotao);
        barraDeBotoes.add(suavilizar);
        barraDeBotoes.add(binarizar);
        barraDeBotoes.add(filtro1);
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
                    int limimar = 128;
                    try{
                        imagemSaida = Filtros.binarizarImagem(imagem, limimar);
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



        realceDeBordaBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;
                    double[] filter1 = {
                            0.0625, 0.125, 0.0625,
                            0.125, 0.25, 0.125,
                            0.0625, 0.125, 0.0625
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
