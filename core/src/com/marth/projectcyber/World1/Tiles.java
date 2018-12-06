package com.marth.projectcyber.World1;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.marth.projectcyber.projectCyber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by al1410644 on 21/11/2016.
 */

public class Tiles {

    private projectCyber game;
    private static HashMap<String, Texture> hash;
    private int [][] mapa;
/*
    private List<listagem> list;

    public Class listagem{
        int [][] mapageral;
        boolean desenhado;
    }
*/

    public Tiles(projectCyber game, OrthographicCamera camera) {
        hash = new HashMap<String, Texture>();
        this.game = game;
        game.batch.setProjectionMatrix(camera.combined);

        hash.put("paredeEsquerda", new Texture("Tiles/w1paredeEsquerda.png"));
        hash.put("paredeDireita", new Texture("Tiles/w1paredeDireita.png"));
        hash.put("paredeCima", new Texture("Tiles/w1paredeCima.png"));
        hash.put("paredeBaixo", new Texture("Tiles/w1paredeBaixo.png"));

        hash.put("pEsquerdaCima", new Texture("Tiles/w1pEsquerdaCima.png"));
        hash.put("pEsquerdaBaixo", new Texture("Tiles/w1pEsquerdaBaixo.png"));
        hash.put("pDireitaCima", new Texture("Tiles/w1pDireitaCima.png"));
        hash.put("pDireitaBaixo", new Texture("Tiles/w1pDireitaBaixo.png"));

        hash.put("chaoBasico", new Texture("Tiles/w1chaoBasico.png"));
        hash.put("chaoBasicoB", new Texture("Tiles/w1chaoBasicoB.png"));
    }


    private int[][] gerarAleatorio(int num){
         int [][] telas;
        switch (num){
            case 0:
                telas = new int[][]{
                        {2,6,6,0,0,6,6,5},
                        {8,0,0,0,1,0,0,9},
                        {8,0,0,0,0,0,0,9},
                        {8,0,0,0,0,0,0,9},
                        {8,0,0,0,0,0,0,9},
                        {0,0,0,0,1,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {8,0,0,0,0,0,1,9},
                        {8,0,0,0,0,0,0,9},
                        {8,0,0,0,0,0,1,9},
                        {8,0,0,0,0,0,0,9},
                        {3,10,10,0,0,10,10,4}
                };
               // list.add(telas);
                return telas;


            case 1:
                telas = new int[][] {
                        {2,6,6,0,0,6,6,5},
                        {8,0,0,0,0,0,0,9},
                        {8,0,0,0,0,0,0,9},
                        {8,0,0,0,0,0,0,9},
                        {8,0,0,0,0,0,0,9},
                        {0,0,0,0,1,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {8,0,0,0,0,0,1,9},
                        {8,0,1,0,0,0,1,9},
                        {8,0,0,0,1,0,1,9},
                        {8,0,0,0,0,0,0,9},
                        {3,10,10,0,0,10,10,4}
                };
                //list.add(telas);
                return telas;

            case 2:
                telas = new int[][]{
                        {2,6,6,0,0,6,6,5},
                        {8,0,0,0,1,0,0,9},
                        {8,0,0,0,0,0,0,9},
                        {8,0,0,0,0,0,0,9},
                        {8,0,0,0,0,0,0,9},
                        {0,0,0,0,1,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {8,0,0,0,0,0,1,9},
                        {8,0,0,0,0,0,0,9},
                        {8,0,0,0,0,0,1,9},
                        {8,0,0,0,0,0,0,9},
                        {3,10,10,0,0,10,10,4}
                };
               // list.add(telas);
                return telas;

            case 3:
                telas = new int[][]{
                        {2,6,6,0,0,6,6,5},
                        {8,1,0,1,0,1,0,9},
                        {8,0,1,0,1,0,1,9},
                        {8,1,0,1,0,1,0,9},
                        {8,0,1,0,1,0,1,9},
                        {0,1,0,1,0,1,0,0},
                        {0,0,1,0,1,0,1,0},
                        {8,1,0,1,0,1,1,9},
                        {8,0,1,0,1,0,1,9},
                        {8,1,0,1,0,1,0,9},
                        {8,0,1,0,1,0,1,9},
                        {3,10,10,0,0,10,10,4}
                };
               // list.add(telas);
                return telas;

            case 4:
                telas = new int[][]{
                        {2,6,6,0,0,6,6,5},
                        {8,0,0,0,1,0,0,9},
                        {8,0,1,0,1,0,1,9},
                        {8,0,0,0,0,0,0,9},
                        {8,1,1,0,1,0,1,9},
                        {0,0,0,0,1,0,0,0},
                        {0,1,1,0,1,0,1,0},
                        {8,0,0,0,0,0,1,9},
                        {8,1,0,1,0,1,0,9},
                        {8,0,0,0,0,0,1,9},
                        {8,1,0,1,0,1,1,9},
                        {3,10,10,0,0,10,10,4}
                };
                //list.add(telas);
                return telas;

            case 5:
                telas = new int[][]{
                        {2,6,6,0,0,6,6,5},
                        {8,0,0,0,0,0,0,9},
                        {8,0,1,0,1,0,0,9},
                        {8,0,0,0,0,0,0,9},
                        {8,0,1,0,1,0,0,9},
                        {0,0,0,0,0,0,0,0},
                        {0,0,1,0,1,0,1,0},
                        {8,0,0,0,0,0,0,9},
                        {8,0,1,0,1,0,0,9},
                        {8,0,0,0,0,0,1,9},
                        {8,0,0,0,0,0,0,9},
                        {3,10,10,0,0,10,10,4}
                };
                //list.add(telas);
                return telas;
            default:
                telas = new int[][] {
                        {2,6,6,0,0,6,6,5},
                        {8,0,0,0,0,0,0,9},
                        {8,0,0,0,0,0,0,9},
                        {8,0,0,0,0,0,0,9},
                        {8,0,0,1,0,0,0,9},
                        {0,1,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {8,0,0,0,0,0,0,9},
                        {8,0,0,1,0,0,0,9},
                        {8,0,0,0,0,0,0,9},
                        {8,0,0,0,0,0,0,9},
                        {3,10,10,0,0,10,10,4}
                };
                //list.add(telas);
                return telas;
        }
    }





    public int randomizar(){
        Random n = new Random();
        int numero;

        numero  = n.nextInt(6);
        return numero;

    }

    public int[][] gerarFase(){
        mapa = gerarAleatorio(randomizar());
       return mapa;
    }


    public void desenhar(Vector2 pos,int [][] mapa){
        float posicaoX = pos.x;
        float posicaoY = pos.y;

        game.batch.begin();
        for (int x = 0; x < mapa.length; x++) {

            for (int y = 0; y < mapa[y].length; y++) {

                if (mapa[x][y] == 0) {

                    game.batch.draw(hash.get("chaoBasico"), posicaoX+x*(32/projectCyber.PPM), posicaoY+y*(32/projectCyber.PPM), 32/projectCyber.PPM , 32/projectCyber.PPM);

                }
                if (mapa[x][y] == 1) {

                    game.batch.draw(hash.get("chaoBasicoB"), posicaoX+x*(32/projectCyber.PPM), posicaoY+y*(32/projectCyber.PPM), 32/projectCyber.PPM , 32/projectCyber.PPM);

                }
                if (mapa[x][y] == 2) {

                    game.batch.draw(hash.get("pEsquerdaBaixo"), posicaoX+x*(32/projectCyber.PPM), posicaoY+y*(32/projectCyber.PPM), 32/projectCyber.PPM , 32/projectCyber.PPM);

                }
                if (mapa[x][y] == 3) {

                    game.batch.draw(hash.get("pDireitaBaixo"), posicaoX+x*(32/projectCyber.PPM), posicaoY+y*(32/projectCyber.PPM), 32/projectCyber.PPM , 32/projectCyber.PPM);

                }
                if (mapa[x][y] == 4) {

                    game.batch.draw(hash.get("pDireitaCima"), posicaoX+x*(32/projectCyber.PPM), posicaoY+y*(32/projectCyber.PPM), 32/projectCyber.PPM , 32/projectCyber.PPM);

                }
                if (mapa[x][y] == 5) {

                    game.batch.draw(hash.get("pEsquerdaCima"), posicaoX+x*(32/projectCyber.PPM), posicaoY+y*(32/projectCyber.PPM), 32/projectCyber.PPM , 32/projectCyber.PPM);

                }
                if (mapa[x][y] == 6) {

                    game.batch.draw(hash.get("paredeEsquerda"), posicaoX+x*(32/projectCyber.PPM), posicaoY+y*(32/projectCyber.PPM), 32/projectCyber.PPM , 32/projectCyber.PPM);

                }
                if (mapa[x][y] == 7) {

                    game.batch.draw(hash.get("pEsquerdaCima"), posicaoX+x*(32/projectCyber.PPM), posicaoY+y*(32/projectCyber.PPM), 32/projectCyber.PPM , 32/projectCyber.PPM);

                }
                if (mapa[x][y] == 8) {

                    game.batch.draw(hash.get("paredeBaixo"), posicaoX+x*(32/projectCyber.PPM), posicaoY+y*(32/projectCyber.PPM), 32/projectCyber.PPM , 32/projectCyber.PPM);

                }
                if (mapa[x][y] == 9) {

                    game.batch.draw(hash.get("paredeCima"), posicaoX+x*(32/projectCyber.PPM), posicaoY+y*(32/projectCyber.PPM), 32/projectCyber.PPM , 32/projectCyber.PPM);

                }
                if (mapa[x][y] == 10) {

                    game.batch.draw(hash.get("paredeDireita"), posicaoX+x*(32/projectCyber.PPM), posicaoY+y*(32/projectCyber.PPM), 32/projectCyber.PPM , 32/projectCyber.PPM);
                }
            }
        }
        game.batch.end();
    }
}
