import java.time.LocalDate;

public class Normal extends Grupo{

    public Normal(int numPersonas) {
        super(numPersonas);
    }
    
    @Override
    public double pagar() {
        double precio=0;
        precio=numBebidas*precioBebida+numRaciones*precioRacion;
        LocalDate hoy=LocalDate.now();

        System.out.println("Hoy es: "+hoy.getDayOfWeek().getValue());
        if (hoy.getDayOfWeek().getValue()>=0 && hoy.getDayOfWeek().getValue()<=5)
            precio=precio-precio*0.1;
            //precio=precio*0.9;
        return precio;
    }
}
