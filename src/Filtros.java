import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Filtros {
    public static BufferedImage suavizar(BufferedImage entrada) throws IOException {
        int largura = entrada.getWidth();
        int altura = entrada.getHeight();

        BufferedImage saida = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        double[] filtro_gau = {
                0.0625, 0.125, 0.0625,
                0.125, 0.25, 0.125,
                0.0625, 0.125, 0.0625
        };

        Color px1, px2, px3, px4, px5, px6, px7, px8, px9;

        for (int linha = 1; linha < largura - 2; linha++) {
            for (int coluna = 1; coluna < altura - 2; coluna++) {

                px1 = new Color(entrada.getRGB(linha - 1, coluna - 1));
                px2 = new Color(entrada.getRGB(linha - 1, coluna));
                px3 = new Color(entrada.getRGB(linha - 1, coluna + 1));
                px4 = new Color(entrada.getRGB(linha, coluna - 1));
                px5 = new Color(entrada.getRGB(linha, coluna));
                px6 = new Color(entrada.getRGB(linha, coluna + 1));
                px7 = new Color(entrada.getRGB(linha + 1, coluna - 1));
                px8 = new Color(entrada.getRGB(linha + 1, coluna));
                px9 = new Color(entrada.getRGB(linha + 1, coluna + 1));

                int pixel = (int) (
                        (filtro_gau[0] * px1.getRed()) +
                                (filtro_gau[1] * px2.getRed()) +
                                (filtro_gau[2] * px3.getRed()) +
                                (filtro_gau[3] * px4.getRed()) +
                                (filtro_gau[4] * px5.getRed()) +
                                (filtro_gau[5] * px6.getRed()) +
                                (filtro_gau[6] * px7.getRed()) +
                                (filtro_gau[7] * px8.getRed()) +
                                (filtro_gau[8] * px9.getRed())
                );

                if (pixel > 255) {
                    pixel = 255;
                }
                if (pixel < 0) {
                    pixel = 0;
                }

                Color novaCor = new Color(pixel, pixel, pixel);
                saida.setRGB(linha, coluna, novaCor.getRGB());

            }
        }
        return saida;

    }

    public static BufferedImage realceBorda(BufferedImage entrada) throws IOException {
        int largura = entrada.getWidth();
        int altura = entrada.getHeight();

        BufferedImage saida = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        int[] realce = {-1, -1, -1, -1, 8, -1, -1, -1, -1};

        Color px1, px2, px3, px4, px5, px6, px7, px8, px9;

        for (int linha = 1; linha < largura - 2; linha++) {
            for (int coluna = 1; coluna < altura - 2; coluna++) {

                px1 = new Color(entrada.getRGB(linha - 1, coluna - 1));
                px2 = new Color(entrada.getRGB(linha - 1, coluna));
                px3 = new Color(entrada.getRGB(linha - 1, coluna + 1));
                px4 = new Color(entrada.getRGB(linha, coluna - 1));
                px5 = new Color(entrada.getRGB(linha, coluna));
                px6 = new Color(entrada.getRGB(linha, coluna + 1));
                px7 = new Color(entrada.getRGB(linha + 1, coluna - 1));
                px8 = new Color(entrada.getRGB(linha + 1, coluna));
                px9 = new Color(entrada.getRGB(linha + 1, coluna + 1));

                int pixel = (int) (
                        (realce[0] * px1.getRed()) +
                                (realce[1] * px2.getRed()) +
                                (realce[2] * px3.getRed()) +
                                (realce[3] * px4.getRed()) +
                                (realce[4] * px5.getRed()) +
                                (realce[5] * px6.getRed()) +
                                (realce[6] * px7.getRed()) +
                                (realce[7] * px8.getRed()) +
                                (realce[8] * px9.getRed())
                );

                if (pixel > 255) {
                    pixel = 255;
                }
                if (pixel < 0) {
                    pixel = 0;
                }

                Color novaCor = new Color(pixel, pixel, pixel);
                saida.setRGB(linha, coluna, novaCor.getRGB());

            }
        }

        return saida;
    }
    public static BufferedImage binarizarImagem(BufferedImage imagem, int limiar) throws IOException {

            int largura = imagem.getWidth();
            int altura = imagem.getHeight();


            BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);


            for (int linha = 0; linha < largura; linha++) {
                for (int coluna = 0; coluna < altura; coluna++) {

                    int pixel = imagem.getRGB(linha, coluna);
                    Color cor = new Color(pixel);

                    int valorCinza = (cor.getRed() + cor.getGreen() + cor.getBlue()) / 3;
                    if (valorCinza < limiar) {
                        novaImagem.setRGB(linha, coluna, Color.BLACK.getRGB());
                    } else {
                        novaImagem.setRGB(linha, coluna, Color.WHITE.getRGB());
                    }
            }
        }
            return novaImagem;
    }

    public static BufferedImage inverterCores(BufferedImage imagem) throws IOException {

        int largura = imagem.getWidth();
        int altura = imagem.getHeight();


        BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);


        for (int linha = 0; linha < largura; linha++) {
            for (int coluna = 0; coluna < altura; coluna++) {

                int pixel = imagem.getRGB(linha, coluna);
                Color cor = new Color(pixel);

                int imagemVermelha = 255 - cor.getRed();
                int imagemVerde = 255 - cor.getGreen();
                int imagemAzul = 255 - cor.getBlue();

                Color novaCor = new Color(imagemVermelha, imagemVerde, imagemAzul);

                novaImagem.setRGB(linha, coluna, novaCor.getRGB());
            }
        }
        return novaImagem;
    }

        public static BufferedImage filtros(BufferedImage imagem, double[] filtro) throws IOException {

            int largura = imagem.getWidth();
            int altura = imagem.getHeight();


            BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);


            for (int linha = 1; linha < largura - 1; linha++) {
                for (int coluna = 1; coluna < altura - 1; coluna++) {

                    int pixel1 = imagem.getRGB(linha - 1, coluna - 1);
                    Color cor1 = new Color(pixel1);
                    int px1 = cor1.getRed();

                    int pixel2 = imagem.getRGB(linha - 1, coluna);
                    Color cor2 = new Color(pixel2);
                    int px2 = cor2.getRed();

                    int pixel3 = imagem.getRGB(linha - 1, coluna + 1);
                    Color cor3 = new Color(pixel3);
                    int px3 = cor3.getRed();

                    int pixel4 = imagem.getRGB(linha, coluna - 1);
                    Color cor4 = new Color(pixel4);
                    int px4 = cor4.getRed();

                    int pixel5 = imagem.getRGB(linha, coluna);
                    Color cor5 = new Color(pixel5);
                    int px5 = cor5.getRed();




                    int px6 = imagem.getRGB(linha, coluna + 1);
                    int px7 = imagem.getRGB(linha + 1, coluna - 1);
                    int px8 = imagem.getRGB(linha + 1, coluna);
                    int px9 = imagem.getRGB(linha + 1, coluna + 1);

                    
                    Color cor6 = new Color(px6);
                    Color cor7 = new Color(px7);
                    Color cor8 = new Color(px8);
                    Color cor9 = new Color(px9);

                    double pixel = 0.0;

                   pixel += (px1 * filtro[0]);
                   pixel += (px2 * filtro[1]);
                   pixel += (px3 * filtro[2]);
                   pixel += (px4 * filtro[3]);
                   pixel += (px5 * filtro[4]);
                   pixel += (px6 * filtro[5]);
                   pixel += (px7 * filtro[6]);
                   pixel += (px8 * filtro[7]);
                   pixel += (px9 * filtro[8]);

                    if (pixel > 255) {
                        pixel = 255;
                    } else if (pixel < 0) {
                        pixel = 0;
                    }

                    int novoValor = (int) pixel;
                    Color novaCor = new Color(novoValor, novoValor, novoValor);

                    novaImagem.setRGB(linha, coluna, novaCor.getRGB());


            }
        }
            return novaImagem;
    }
}


