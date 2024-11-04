package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	private Combo combo;
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		ArrayList<ProductoMenu> items= new ArrayList<>();
		ProductoMenu productoMenu = new ProductoMenu("lasagna", 20000 );
		ProductoMenu productoMenu2 = new ProductoMenu("limonada coco", 11000 );
		items.add(productoMenu);
		items.add(productoMenu2);
        combo = new Combo("lemo lasagna", 0.1,items );
    }
	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	@Test
	void testGetNombre() {
		assertEquals("lemo lasagna", this.combo.getNombre(), "El nombre del combo no es el esperado. ");
	}
	@Test
	void testGetPrecio() {
		assertEquals((int)((20000+11000)*0.9), this.combo.getPrecio(), "El precio calculado es incorrecto");
	}
	@Test
	void testGenerarTextoFactura() {
		double precio= 20000+11000*0.9;
		int precior = (int) precio;
		double descuento=0.1;
		String stringRespuesta= "Combo " + "lemo lasagna"+"\n" + " Descuento: " +  String.valueOf(descuento) + "\n"+"            " + String.valueOf(precior) + "\n";
		assertEquals(stringRespuesta, combo.generarTextoFactura(), "El texto de la factura generado es incorrecto. ");
	}
	
}
