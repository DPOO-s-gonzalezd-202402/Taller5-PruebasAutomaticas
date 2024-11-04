package uniandes.dpoo.hamburguesas.tests;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
	private ProductoAjustado productoAjustado;
	private String textoFactura = "lasagna" +
            "    +tomate                1000" +
            "    -cebolla" +
            "            21000\n";
	
	@BeforeEach
    void setUp( ) throws Exception
    {
        Ingrediente ingrediente1 = new Ingrediente( "tomate", 1000 );
        Ingrediente ingrediente2 = new Ingrediente( "cebolla", 2000 );
        ProductoMenu productoBase= new ProductoMenu("lasagna",20000 );
        productoAjustado = new ProductoAjustado(productoBase);
        productoAjustado.agregarIngrediente(ingrediente1);
        productoAjustado.eliminarIngrediente(ingrediente2);
    }

	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	
	@Test
	void testGetNombre() {
		assertEquals("lasagna", this.productoAjustado.getNombre(),"El nombre del producto del menú no es el esperado.");
	}
	
	@Test
	void testGetPrecio() {
		assertEquals(21000, this.productoAjustado.getPrecio(), "El precio del producto no se está calculando de la manera esperada. ");
		
	}
	@Test
	void testGenerarTextoFactura() {
		assertEquals(textoFactura, this.productoAjustado.generarTextoFactura(), "El texto de la factura no coincide con el esperado. ");

	}
}
