package pe.edu.ulima.practica1.pc1_moviles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button juego[][];
    Button turno;
    int c[][];
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        juego = new Button[4][4];
        c = new int[4][4];
        turno = (Button) findViewById(R.id.turno);
        juego[1][1] = (Button) findViewById(R.id.uno);
        juego[1][2] = (Button) findViewById(R.id.dos);
        juego[1][3] = (Button) findViewById(R.id.tres);
        juego[2][1] = (Button) findViewById(R.id.cuatro);
        juego[2][2] = (Button) findViewById(R.id.cinco);
        juego[2][3] = (Button) findViewById(R.id.seis);
        juego[3][1] = (Button) findViewById(R.id.siete);
        juego[3][2] = (Button) findViewById(R.id.ocho);
        juego[3][3] = (Button) findViewById(R.id.nueve);

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++)
                c[i][j] = 2;
        }

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                final int finalI = i;
                final int finalJ = j;
                juego[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (pos == 0) {
                            markCircle(finalI, finalJ);
                        } else {
                            markSquare(finalI, finalJ);
                        }
                    }
                });
            }
        }
    }

    private void markSquare(int x, int y) {
        juego[x][y].setEnabled(false);
        juego[x][y].setText("X");
        turno.setText("Le toca al jugador O.");
        c[x][y] = 1;
        pos = 0;
        verificar();
    }

    private void markCircle(int x, int y) {
        juego[x][y].setEnabled(false);
        juego[x][y].setText("O");
        turno.setText("Le toca al jugador X.");
        c[x][y] = 0;
        pos = 1;
        verificar();
    }

    private boolean verificar() {
        boolean win = false;
        if ((c[1][1] == 0 && c[2][2] == 0 && c[3][3] == 0)
                || (c[1][3] == 0 && c[2][2] == 0 && c[3][1] == 0)
                || (c[1][2] == 0 && c[2][2] == 0 && c[3][2] == 0)
                || (c[1][3] == 0 && c[2][3] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[1][2] == 0 && c[1][3] == 0)
                || (c[2][1] == 0 && c[2][2] == 0 && c[2][3] == 0)
                || (c[3][1] == 0 && c[3][2] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[2][1] == 0 && c[3][1] == 0)) {
            turno.setText("Ganó el jugador O.");
            turno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recreate();
                }
            });
            win = true;
        } else if ((c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1)
                || (c[1][3] == 1 && c[2][2] == 1 && c[3][1] == 1)
                || (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1)
                || (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1)
                || (c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1)
                || (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1)) {
            turno.setText("Ganó el jugador X.");
            turno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recreate();
                }
            });
            win = true;
        } else {
            boolean empty = false;
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (c[i][j] == 2) {
                        empty = true;
                        break;
                    }
                }
            }
            if (!empty) {
                win = true;
                turno.setText("Empate");
                turno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recreate();
                    }
                });
            }
        }
        return win;
    }
}
