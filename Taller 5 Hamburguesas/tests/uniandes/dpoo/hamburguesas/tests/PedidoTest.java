package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {
	private Pedido pedido;
	private File archivoFactura;
	
	@BeforeEach
	void setUp() throws Exception{
		this.pedido = new Pedido("Pepito Perez",  "Cra 1#1-1");
		archivoFactura = new File("factura_test.txt");
	}
	
	 @AfterEach
	 void tearDown( ) throws Exception
	    {
		 if (archivoFactura.exists()) {
	            archivoFactura.delete();
	        }
	    }
	 @Test
	 void testIdPedido() {
		 assertEquals(1, pedido.getIdPedido(), "El id no es el esperado. ");
	 }
	 @Test
	 void testGetNombreCliente() {
		 assertEquals("Pepito Perez", pedido.getNombreCliente(), "El nombre del cliente no coincide con el ingresado. ");
		 
	 }
	
	 @Test
	 void testAgregarProducto() {
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 15000);
        int tamanioInicial= pedido.getProductos().size();
        pedido.agregarProducto(producto);
        assertEquals(tamanioInicial + 1, pedido.getProductos().size(), 
                "El tamaño de la lista debe aumentar en 1 al agregar un nuevo producto");
        assertTrue(pedido.getProductos().contains(producto), 
                   "La lista de productos debe contener el producto que se agregó");
	 }
	 
	 @Test
	 void testGetPrecioTotalPedido() {
		 int precio=15000;
		 double iva= precio*0.19;
		 int ivai= (int) iva;
		 int total=ivai+ precio;
		 assertEquals(total, pedido.getPrecioTotalPedido(), "El precio total del pedido se calcula incorrectamente. ");
	 }
	 
	 @Test
	 void testGenerarTextoFactura() {
		 int precio=15000;
		 double iva= precio*0.19;
		 int ivai= (int) iva;
		 int total=ivai+ precio;
		 ProductoMenu producto = new ProductoMenu("Hamburguesa", 15000);

        pedido.agregarProducto(producto);
		 String stringFactura= "Cliente: " + "Pepito Perez" + "\n"+  "Dirección: " + "Cra 1#1-1" + "\n"
				 				+  "----------------\n" +"Hamburguesa\n            15000\n"
				 				+ "----------------\n" + "Precio Neto:  " + "15000" + "\n"
				 				+"IVA:          " + String.valueOf(ivai)+ "\n" 
				 				+ "Precio Total: " + String.valueOf(total) + "\n";
		 assertEquals(stringFactura, pedido.generarTextoFactura(), "El texto generado para la factura es incorrecto. ");
		 
	 }
	 public void testGuardarFactura() throws FileNotFoundException {
		        // Agrega un producto de prueba al pedido
		        ProductoMenu producto = new ProductoMenu("Hamburguesa", 15000);
		        pedido.agregarProducto(producto);

		        // Genera la factura en el archivo
		        pedido.guardarFactura(archivoFactura);

		        // Verifica que el archivo se haya creado
		        assertTrue(archivoFactura.exists(), "El archivo de factura debería haberse creado");

		        // Verifica el contenido del archivo línea por línea
		        try (Scanner scanner = new Scanner(archivoFactura)) {
		            // Lee y verifica la primera línea (nombre del producto)
		            assertTrue(scanner.hasNextLine(), "El archivo debería contener al menos una línea");
		            String linea1 = scanner.nextLine().trim();
		            assertEquals("Hamburguesa", linea1, "La primera línea debería ser el nombre del producto");

		            // Lee y verifica la segunda línea (precio del producto)
		            assertTrue(scanner.hasNextLine(), "El archivo debería contener al menos dos líneas");
		            String linea2 = scanner.nextLine().trim();
		            assertEquals("            15000", linea2, "La segunda línea debería ser el precio del producto");
		        }
		    }
	        
	 
}
