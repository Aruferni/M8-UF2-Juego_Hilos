package com.example.albertfernie.m8_uf2_control;

/**
 * Created by albertfernie on 14/03/2017.
 */

public class MyThread extends Thread{

    //Atributos
    private int interval;
    private boolean alive = false;
    private GView gview;

    //constructor
    public MyThread( int interval, GView gview) {
        setInterval(interval);
        this.gview=gview;
    }

    //métodos get-set:
    public void setAlive(boolean alive) {this.alive = alive; }

    public boolean getAlive() { return this.alive; }

    public void setInterval(int interval){ this.interval=interval; }

    public int getInterval(){ return this.interval;}

    //metodo run : funcionalidad, lo que hace el hilo
    public void run() {
        while (alive) {
            try {
                this.sleep(interval);
                gview.taskTimer();
            }
            catch(Exception e){}
            finally {}
        }
    }// fin de run
}
/*nota: se puede incorporar la clase MyTimer en 2 formas más: dentro del fichero
Gview después del fin de class o de forma inner class (antes de finalizar la class de
Gview) */
