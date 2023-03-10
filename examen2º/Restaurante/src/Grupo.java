import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public abstract class Grupo{
    protected int numPersonas;
    protected int numRaciones, numBebidas;
    protected static final double precioRacion=15, precioBebida=2;
    private int veces;
    private LocalTime horaLlegada;
    
    public Grupo(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public void setHoraLlegada() {
        this.horaLlegada = LocalTime.now();

    }

    public long calcularTiempo(){
        LocalTime ahora=LocalTime.now();
        long tRestante=ChronoUnit.SECONDS.between(ahora, horaLlegada);
        return tRestante;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void servicio(int nraciones, int nbebidas){
        numRaciones+=nraciones;
        numBebidas+=nbebidas;
        veces++;
    }

    public int getVeces() {
        return veces;
    }

    public abstract double pagar();
}