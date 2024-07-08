import java.util.ArrayList;
import java.util.List;
public class Problema2_Menus {
    public static void main(String[] args) {
        Menu menu1 = new MenuALaCarta("Carne de res", 10.0, 3.0, 2.0, 10);
        Menu menu2 = new MenuDelDia("Pasta", 8.0, 1.5, 2.5);
        Menu menu3 = new MenuNinos("Helado de oreo", 6.0, 1.0, 1.5);
        Menu menu4 = new MenuEconomico("Ensalada", 5.0, 20);

        Cuenta cuenta = new Cuenta("Juan Sebastian");
        cuenta.agregarMenu(menu1);
        cuenta.agregarMenu(menu2);
        cuenta.agregarMenu(menu3);
        cuenta.agregarMenu(menu4);
        cuenta.calcularValores();

        System.out.println(cuenta);
    }
}
class Cuenta {
    public String nombreCliente;
    public List<Menu> menus;
    public double subtotal;
    public double iva;
    public double valorTotal;

    public Cuenta(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        this.menus = new ArrayList<>();
    }

    public void agregarMenu(Menu menu) {
        menus.add(menu);
        menu.calcularValorMenu();
    }

    public void calcularValores() {
        subtotal = 0;
        for (Menu menu : menus) {
            subtotal += menu.valorMenu;
        }
        iva = subtotal * 0.15; // 15% de IVA
        valorTotal = subtotal + iva;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "nombreCliente=" + nombreCliente + ", menus=" + menus + ", subtotal=" + subtotal + ", iva=" + iva + ", valorTotal=" + valorTotal + '}';
    }   
}
abstract class Menu {
    public String nombrePlato;
    public double valorInicialMenu;
    public double valorMenu;

    public Menu(String nombrePlato, double valorInicialMenu) {
        this.nombrePlato = nombrePlato;
        this.valorInicialMenu = valorInicialMenu;
    }

    public abstract void calcularValorMenu();

    @Override
    public String toString() {
        return "Nombre del plato: " + nombrePlato + ", Valor inicial del menú: " + valorInicialMenu + ", Valor del menú: " + valorMenu;
    }
}
class MenuALaCarta extends Menu {
    public double valorGuarnicion;
    public double valorBebida;
    public double porcentajeServicio;

    public MenuALaCarta(String nombrePlato, double valorInicialMenu, double valorGuarnicion, double valorBebida, double porcentajeServicio) {
        super(nombrePlato, valorInicialMenu);
        this.valorGuarnicion = valorGuarnicion;
        this.valorBebida = valorBebida;
        this.porcentajeServicio = porcentajeServicio;
    }

    @Override
    public void calcularValorMenu() {
        valorMenu = valorInicialMenu + valorGuarnicion + valorBebida + (valorInicialMenu * porcentajeServicio / 100);
    }

    @Override
    public String toString() {
        return super.toString() + ", Valor guarnición: " + valorGuarnicion + ", Valor bebida: " + valorBebida + ", Porcentaje servicio: " + porcentajeServicio;
    }
}
class MenuDelDia extends Menu {
    public double valorPostre;
    public double valorBebida;

    public MenuDelDia(String nombrePlato, double valorInicialMenu, double valorPostre, double valorBebida) {
        super(nombrePlato, valorInicialMenu);
        this.valorPostre = valorPostre;
        this.valorBebida = valorBebida;
    }

    @Override
    public void calcularValorMenu() {
        valorMenu = valorInicialMenu + valorPostre + valorBebida;
    }

    @Override
    public String toString() {
        return super.toString() + ", Valor postre: " + valorPostre + ", Valor bebida: " + valorBebida;
    }
}
class MenuNinos extends Menu {
    public double valorHelado;
    public double valorPastel;

    public MenuNinos(String nombrePlato, double valorInicialMenu, double valorHelado, double valorPastel) {
        super(nombrePlato, valorInicialMenu);
        this.valorHelado = valorHelado;
        this.valorPastel = valorPastel;
    }

    @Override
    public void calcularValorMenu() {
        valorMenu = valorInicialMenu + valorHelado + valorPastel;
    }

    @Override
    public String toString() {
        return super.toString() + ", Valor helado: " + valorHelado + ", Valor pastel: " + valorPastel;
    }
}
class MenuEconomico extends Menu {
    public double porcentajeDescuento;

    public MenuEconomico(String nombrePlato, double valorInicialMenu, double porcentajeDescuento) {
        super(nombrePlato, valorInicialMenu);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public void calcularValorMenu() {
        valorMenu = valorInicialMenu - (valorInicialMenu * porcentajeDescuento / 100);
    }

    @Override
    public String toString() {
        return super.toString() + ", Porcentaje descuento: " + porcentajeDescuento;
    }
}

