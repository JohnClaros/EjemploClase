public class Tenedor extends Grupo{
    private String codigo;

    public Tenedor(int numPersonas, String codigo) {
        super(numPersonas);
        this.codigo = codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
        
    }
    
    @Override
    public double pagar() {
        double precio=0;
        try {
            double descuento=Double.parseDouble(codigo.substring(2));
            precio=precioBebida*numBebidas+precioRacion*numRaciones-(precioRacion*numRaciones*descuento/100);
        } catch (NumberFormatException e) {
            // TODO: handle exception
            System.out.println("El codigo es incorrecto, no aplicamos descuento");
            precio=(precioBebida*numBebidas)+(precioRacion*numRaciones);
        }
        return precio;
    }


}
