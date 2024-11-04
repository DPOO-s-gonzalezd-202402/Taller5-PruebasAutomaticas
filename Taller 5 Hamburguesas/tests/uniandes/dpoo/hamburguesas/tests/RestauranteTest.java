package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest {
	private Restaurante restaurante;
	@BeforeEach
    void setUp( ) throws Exception
    {
		restaurante = new Restaurante();
    }
	
	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	@Test
	void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
	    restaurante.iniciarPedido("Jungkook", "Ines 1445");
	    assertNotNull(restaurante.getPedidoEnCurso(), "El pedido en curso no debe ser nulo");
	    assertEquals("Jungkook", restaurante.getPedidoEnCurso().getNombreCliente(), "El nombre del cliente debe ser 'Jungkook'");

	    
	}
	@Test
	void testIniciarPedidoExc() throws YaHayUnPedidoEnCursoException {
	    restaurante.iniciarPedido("Jungkook", "Ines 1445");
		YaHayUnPedidoEnCursoException exception = assertThrows(YaHayUnPedidoEnCursoException.class, 
		        () -> restaurante.iniciarPedido("Jimin", "Juárez 900"), 
		        "Se esperaba que se lanzara una excepción YaHayUnPedidoEnCursoException");

		    assertEquals("Ya hay un pedido en curso para el cliente " + "Jungkook" + ", no se puede iniciar uno nuevo para " + "Jimin", 
		        exception.getMessage(), 
		        "El mensaje de la excepción debe ser correcto");
	}
	@Test
	void testCerrarYGuardarPedido() throws NoHayPedidoEnCursoException,YaHayUnPedidoEnCursoException  {
        
        assertNull(restaurante.getPedidoEnCurso(), "El pedido en curso debe ser null después de cerrar el pedido");
        NoHayPedidoEnCursoException exception=assertThrows(NoHayPedidoEnCursoException.class,
        		() ->restaurante.cerrarYGuardarPedido(), "No se puede cerrr el pedido en curso porque no hay ninguno");
        
    }
	
	@Test
	void testCerrarYGuardarPedidoReal() throws NoHayPedidoEnCursoException, YaHayUnPedidoEnCursoException, IOException {
	    new File("./facturas").mkdirs();

	    restaurante.iniciarPedido("Jungkook", "Ines 1445");
	    
	    restaurante.cerrarYGuardarPedido();
	    
	    assertNull(restaurante.getPedidoEnCurso(), "El pedido en curso debe ser null después de cerrar el pedido");

	    assertEquals(1, restaurante.getPedidos().size(), "La lista de pedidos cerrados debe contener un pedido");
	    
	    File archivoFactura = new File("./facturas/factura_" + restaurante.getPedidos().get(0).getIdPedido() + ".txt");
	    assertTrue(archivoFactura.exists(), "El archivo de factura debe existir");
	    
	}

	@Test
	void testGetPedidos() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
	    restaurante.iniciarPedido("Jungkook", "Ines 1445");
	    
	    restaurante.cerrarYGuardarPedido();

	    ArrayList<Pedido> pedidos = restaurante.getPedidos();

	    assertFalse(pedidos.isEmpty(), "La lista de pedidos no debe estar vacía después de cerrar un pedido.");
	    Pedido pedidoCerrado = pedidos.get(0); 
	    assertEquals("Jungkook", pedidoCerrado.getNombreCliente(), "El nombre del cliente del pedido cerrado debe ser 'Jungkook'.");
	}

	@Test
	void testGetMenuBase() throws Exception {
	    ProductoMenu hamburguesa = new ProductoMenu("Hamburguesa", 15000);
	    restaurante.getMenuBase().add(hamburguesa); 

	    ArrayList<ProductoMenu> menuBase = restaurante.getMenuBase();
	    assertEquals(1, menuBase.size(), "No se encontraron productos en el menu. "); 
	    assertEquals("Hamburguesa", menuBase.get(0).getNombre(), "No se encontró el producto ingresado previamente. "); 
	}
	
	@Test
	void testGetMenuCombos() throws Exception {
	    ArrayList<ProductoMenu> productosCombo = new ArrayList<>();
	    productosCombo.add(new ProductoMenu("Hamburguesa", 15000));
	    Combo combo = new Combo("Combo 1", 0.1, productosCombo);
	    restaurante.getMenuCombos().add(combo); 

	    ArrayList<Combo> menuCombos = restaurante.getMenuCombos();
	    assertEquals(1, menuCombos.size()); 
	    assertEquals("Combo 1", menuCombos.get(0).getNombre());
	}
	@Test
	void testGetIngredientes() throws Exception {
	    Ingrediente lechuga = new Ingrediente("Lechuga", 1000);
	    restaurante.getIngredientes().add(lechuga); 

	    ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
	    assertEquals(1, ingredientes.size()); 
	    assertEquals("Lechuga", ingredientes.get(0).getNombre());
	}

    @Test
    public void testCargarIngredientesRestaurante() throws HamburguesaException, NumberFormatException, IOException {
        File archivoIngredientes = new File("src/data/ingredientes.txt");
        File archivoMenu = new File("src/data/menu.txt");
        File archivoCombos = new File("src/data/combos.txt");

        restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);

        assertNotNull(restaurante.getIngredientes());
        assertEquals(14, restaurante.getIngredientes().size()); 
 }
    




}
