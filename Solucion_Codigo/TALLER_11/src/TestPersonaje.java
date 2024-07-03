
public class TestPersonaje {

    public static void main(String[] args) {
        boolean turno = true;
        Personaje p1 = new Mago("Bola de fuego", 20, 100, 1, 5, 10);
        Personaje p2 = new Guerrero(30, "Hachazo", 500, 1, 10, 30);
        Personaje p3 = new Arquero(20, "Tiro Certero", 150, 1, 7, 15);
        int count=0;
        while (!p1.muerto()) {
            p2.atacar(p1);
            p1.atacar(p2);
            System.out.println("turno" + count++);
        }
        System.out.println(p1);
        System.out.println(p2);
    }
}

abstract class Personaje {

    public double puntosVida;
    public int nivelExp;
    public int nivel;
    public double danio;
    public double defensa;

    public Personaje(double puntosVida, int nivel, double danio, double defensa) {
        this.puntosVida = puntosVida;
        this.nivel = nivel;
        this.danio = danio;
        this.defensa = defensa;
    }

    abstract double calcularDanio();

    public void atacar(Personaje enemigo) {
        if (!(calcularDanio() >= enemigo.puntosVida)) {
            enemigo.puntosVida = enemigo.defender() - calcularDanio();
        } else {
            enemigo.puntosVida = 0;
            subirExp();
        }
    }

    public double defender() {
        return this.puntosVida + this.defensa;
    }

    public void subirExp() {
        this.nivelExp += 5;
        subirNivel();
    }

    public void subirNivel() {
        if (nivelExp == 10) {
            this.nivel++;
        }
    }

    public boolean muerto() {
        return this.puntosVida == 0;
    }

    @Override
    public String toString() {
        return "Personaje{" + "puntosVida=" + puntosVida + ", nivelExp=" + nivelExp + ", nivel=" + nivel + ", danio=" + danio + ", defensa=" + defensa + '}';
    }

}

class Mago extends Personaje {

    public String hechizos;
    public double poderesMagicos;

    public Mago(String hechizos, double poderesMagicos, double puntosVida, int nivel, double danio, double defensa) {
        super(puntosVida, nivel, danio, defensa);
        this.hechizos = hechizos;
        this.poderesMagicos = poderesMagicos;
    }

    public double calcularDanio() {
        return this.danio * poderesMagicos;
    }

    @Override
    public String toString() {
        return "Mago{" + super.toString() + "hechizos=" + hechizos + ", poderesMagicos=" + poderesMagicos + '}';
    }

}

class Guerrero extends Personaje {

    public Guerrero(double fuerza, String habilidadesCuerpoACuerpo, double puntosVida, int nivel, double danio, double defensa) {
        super(puntosVida, nivel, danio, defensa);
        this.fuerza = fuerza;
        this.habilidadesCuerpoACuerpo = habilidadesCuerpoACuerpo;
    }

    public double fuerza;
    public String habilidadesCuerpoACuerpo;

    public double calcularDanio() {
        return this.danio * fuerza;
    }

    @Override
    public String toString() {
        return "Guerrero{" + super.toString() + "fuerza=" + fuerza + ", habilidadesCuerpoACuerpo=" + habilidadesCuerpoACuerpo + '}';
    }

}

class Arquero extends Personaje {

    public Arquero(double precision, String habilidadesDistancia, double puntosVida, int nivel, double danio, double defensa) {
        super(puntosVida, nivel, danio, defensa);
        this.precision = precision;
        this.habilidadesDistancia = habilidadesDistancia;
    }

    public double precision;
    public String habilidadesDistancia;

    public double calcularDanio() {
        return this.danio * precision;
    }

    @Override
    public String toString() {
        return "Arquero{" + super.toString() + "precision=" + precision + ", habilidadesDistancia=" + habilidadesDistancia + '}';
    }

}
