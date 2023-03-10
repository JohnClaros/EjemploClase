public class Gourmet extends Grupo{

    public Gourmet(int numPersonas) {
        super(numPersonas);
    }
    
    @Override
    public double pagar() {
        double precio=0;
        if (numBebidas>numPersonas)
            numBebidas-=numPersonas;
        precio=numBebidas*precioBebida+numRaciones*precioRacion;
        return precio;
    }
}
