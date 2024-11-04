package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
	private ProductoMenu productoMenu;
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		productoMenu = new ProductoMenu("lasagna", 20000 );
    }
	
	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	
	@Test
	void testGetNombre() {
		assertEquals("lasagna", this.productoMenu.getNombre(), "El nombre del producto del menú no es el esperado. ");
	}
	
	@Test
	void testGetPrecioBase() {
		assertEquals(20000, this.productoMenu.getPrecio(), "El precio del producto no coincide con el esperado. ");
	}
	
	@Test
	void testGenerarTextoFactura() {
		String textoFactura = "lasagna\n            20000\n";

		assertEquals(textoFactura, this.productoMenu.generarTextoFactura(), "El texto de la factura no coincide con el esperado. ");
		
	}
}
