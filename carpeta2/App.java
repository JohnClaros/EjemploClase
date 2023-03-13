import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    static Scanner sc;
    public static void main(String[] args) throws Exception {
        sc=new Scanner(System.in);
        int opc=0, tipo, pos, raciones, bebidas;
        int mesasOcupadas=0, mesa;
        String tipos[]={"Normal", "Gourmet", "Tenedor"};
        double importes[][]=new double [3][4];
        Grupo mesas[]=new Grupo[4];
        Vector <Grupo> cola=new Vector<Grupo>();
		System.out.println("Nueva linea");
		System.out.println("Otra linea");
        do{
            System.out.println("Anota opcion: ");
            System.out.println("1.Llegar\n2.Servir\n3.Pagar\n4.Juntar\n5.Informacion");
            opc=leerInt();
            switch(opc){
                case 1:
                    Grupo g=null;
                    int numPersonas=(int)(Math.random()*5)+1;
                    do{
                        System.out.println("De que tipo eres?");
                        System.out.println("1.Normal/n2.Tenedor/3.Gourmet");
                        tipo=leerInt();
                        if(tipo<1 || tipo>3)
                            System.out.println("Tipo incorrecto");
                    }while(tipo<1 || tipo>3);
                    if(tipo==1)
                        g=new Normal(numPersonas);
                    if(tipo==2){
                        String codigo;
                        do{
                            System.out.println("Anota codigo");
                            codigo=sc.nextLine();
                        }while(validarCodigo(codigo)==false);
                        g=new Tenedor(numPersonas, codigo);
                    }
                    if(tipo==3)
                        g=new Gourmet(numPersonas);
                    if(mesasOcupadas==mesas.length){
                        cola.add(g);
                        g.setHoraLlegada();
                    }
                    else{
                        pos=buscarHueco(mesas);
                        if(pos==-1)
                            System.out.println("Error avise a Sistemas");
                        mesas[pos]=g;
                        mesasOcupadas++;
                    }
                    break;
                case 2:
                    do{
                        System.out.println("Anota numero de mesa");
                        mesa=leerInt();
                    }while(mesa<0 || mesa>mesas.length);
                    if(mesas[mesa]==null)
                        System.out.println("Esa mesa esta vacia");
                    else{
                        if(mesas[mesa].getVeces()==3)
                            System.out.println("Ya ha alcanzado el numero maximo de servicios");
                        else{
                            do{
                                System.out.println("Cuantas raciones");
                                raciones=sc.nextInt();
                            }while(raciones<0 || raciones>mesas[mesa].getNumPersonas());
                            do{
                                System.out.println("Anota bebidas");
                                bebidas=sc.nextInt();
                            }while(bebidas<0 || bebidas>mesas[mesa].getNumPersonas());
                            mesas[mesa].servicio(raciones, bebidas);
                        }
                    }
                    break;
                case 3:
                    do{
                        System.out.println("Anota numero de mesa");
                        mesa=leerInt();
                    }while(mesa<0 || mesa<mesas.length);
                    if(mesas[mesa]==null)
                        System.out.println("Esa mesa esta vacia");
                    else{
                        double importe=mesas[mesa].pagar();
                        int ind=buscarIndice(mesas[mesa], tipos);
                        importes[ind][mesa]+=importe;
                        if (cola.isEmpty()){
                            mesas[mesa]=null;
                            mesasOcupadas--;
                        }else{
                            mesas[mesa]=cola.remove(0);
                            System.out.println("Tiempo de espera:"+mesas[mesa].calcularTiempo());
                        }
                    }
                    break;
                case 4:
                    do{
                        System.out.println("Anota numero de mesa");
                        mesa=leerInt();
                    }while(mesa<0 || mesa>mesas.length);
                    do{
                        System.out.println("Anota numero de mesa");
                        mesa=leerInt();
                    }while(mesa<0 || mesa>mesas.length);
                    
                    break;
                case 5:
                    System.out.println(mesas.toString());
                    break;
                case 6:
                    System.out.println("Fin de sesion");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        }while(opc!=6);
    }

    public static int leerInt(){
        int num=0;
        do{
            try {
                num=sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("No se permiten letras");
            }
        }while(true);
        return num;
    }

    public static boolean validarCodigo(String codigo){
        Pattern pat=Pattern.compile("$[T][E][0-9]{2}$");
        Matcher mat=pat.matcher(codigo);
        return mat.matches();
    }

    public static int buscarHueco(Grupo mesas[]){
        for(int i=0;i<mesas.length;i++){
            if(mesas[i]==null)
                return i;
        }
        return -1;
    }

    public static int buscarIndice(Grupo g, String tipos[]){
        for(int i=0;i<tipos.length;i++)
            if (g.getClass().getSimpleName().equalsIgnoreCase(tipos[i]))
                return i;
        return -1;
    }

    public static boolean mismoTipo(Grupo g, String tipos[]){
        for(int i=0;i<tipos.length;i++)
            if (g.getClass().equals(tipos[i]))
                return true;
        return false;
    }
}
